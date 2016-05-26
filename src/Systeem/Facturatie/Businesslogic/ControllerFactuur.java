/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Facturatie.Businesslogic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import businessEntityDomain.ImmutableSessie;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Sessie;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.BaseClasses.XMLBase;
import Systeem.Datastorage.DAO.DAOBehandelingtraject;
import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Settings.Settings;
import Systeem.rmi.business.RemoteSessies;

/**
 * Deze klasse verzorgt alle communicatie omtremt Factuur objecten
 *
 * @author Mark
 */
public class ControllerFactuur {

	private DAOFactuur dao;
	/**
	 * Maakt decimaal en Datum format aan
	 */
	private DecimalFormat df = new DecimalFormat("€ ###,##0.00");

	/**
	 * Geeft dag maand jaar door in getallen voorbeeld: 04-11-2014
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public ControllerFactuur() {
		try {
			dao = new DAOFactuur();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Geeft de facturen die bij een klant horen.
	 * 
	 * @param klant
	 *            De klant waarvan de facturen worden opgevraagd.
	 * @return De gevonden facturen.
	 */
	public List<Factuur> geefFacturen(Klant klant) {
		// System.out.println(klant.getBsn());
		List<Factuur> factuurlist = new ArrayList<Factuur>();
		try {
			// Haalt de facturen voor de juiste klant uit de DAO
			factuurlist = dao.geefMeerdere(klant.getBsn(), SearchField.THIRD,
					SearchLevel.COMPLETE);

		} catch (IOException e) {
			// System.out.println("ERROR IN DAO");
			e.printStackTrace();
		}

		// Implementeert Comparator om deze te sorteren op ID
		factuurlist.sort(new IdComparator());

		return factuurlist;
	}

	/**
	 * 
	 * Klasse IdComparator
	 *
	 */
	private class IdComparator implements Comparator<Factuur> {
		// Sorteert de factuurobjecten in factuurlist
		public int compare(Factuur a, Factuur b) {
			return a.getNr() < b.getNr() ? -1 : a.getNr() == b.getNr() ? 0 : 1;
		}
	}

	/**
	 * 
	 * @param factuur
	 */
	public void setBetaald(Factuur factuur) {
		// Set het betaald op true
		factuur.setBetaald(true);
		try {
			// Schrijft het aangepaste factuur object weg mbv de DAO
			dao.opslaan(factuur);
			dao.saveDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Maakt een factuur aan voor een klant.
	 * 
	 * @param klant
	 * 
	 * @return De aangemaakte factuur.
	 */

	public String maakFactuur(Klant klant) {
		// Check of alle informatie voorradig is
		String terug = "Niks gedaan :(";
		try {
			klant.getVerzekeringen().get(0);
		} catch (Exception e1) {
			terug = "Error!\nDeze klant kan niet gefactureerd worden, er is geen verzekeringen gekoppelt of deze is niet volledig ingevuld";
			return terug;
		}
		try {
			klant.getVerzekeringen().get(0).getVerzekeringmaatschappij()
					.getNaam();
		} catch (Exception e1) {
			terug = "Error!\nDeze klant kan niet gefactureerd worden, er is geen maatschapij aan de verzekering gekoppeld of deze is niet volledig ingevult";
			return terug;
		}
		// Haal Sessies op van mbv RMI
		RemoteSessies rmiSessies = new RemoteSessies();

		List<Sessie> sessies = rmiSessies.getSessies(
				klant.getLaatsteFactuurDatum(), klant.getBsn());

		// Check of er uberhaupt sessie zijn om te factureren
		try {
			sessies.get(0).getBehandelId();
		} catch (Exception e2) {
			terug = "Deze klant heeft geen sessies om te factureren";
			return terug;
		}

		// Bepaal ID voor factuur
		int newID = 0;
		try {
			// System.out.println(newID);

			List<Integer> ids = dao.geefIds();
			// System.out.println(ids);
			for (int j = 0; j < ids.size(); j++) {
				if (ids.get(j) > newID) {
					newID = ids.get(j);
				}

			}
		}

		catch (Exception e) {

		}
		newID = newID + 1;
		// System.out.println(newID);

		// Maakfactuur
		Verzekeringsmaatschappij stam = klant.getVerzekeringen().get(0)
				.getVerzekeringmaatschappij();
		Date beginDatum = new Date();
		// System.out.println(beginDatum);

		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDatum);
		cal.add(Calendar.MONTH, 1);
		Date eindDatum = cal.getTime();

		SimpleDateFormat dateFormatYearOnly = new SimpleDateFormat("yyyy");
		String year = dateFormatYearOnly.format(beginDatum);
		String factuurNaam = year + " - " + newID;

		ArrayList<String> factuurregels = new ArrayList<>();
		factuurregels = new ArrayList<String>();
		factuurregels.add("<P align=\"right\">" + stam.getNaam());
		factuurregels.add("<br>" + stam.getAdres());
		factuurregels.add("<br>" + stam.getPostcode() + " " + stam.getPlaats());
		factuurregels.add("<br>");
		factuurregels.add("<br>KVK: " + stam.getKvkNr());
		factuurregels.add("<br>BTW: " + stam.getBtwNr());
		factuurregels.add("<br>Bank: " + stam.getIban() + "</P>");
		factuurregels.add("<br>");
		factuurregels.add("<P align=\"left\">" + klant.getVoornaam() + " "
				+ klant.getAchternaam());
		factuurregels.add("<br>" + klant.getAdres());
		factuurregels.add("<br>" + klant.getPostcode() + " "
				+ klant.getPlaats() + "</p>");
		factuurregels.add("<p align=\"right\"> Factuuurdatum:  "
				+ dateFormat.format(beginDatum));
		factuurregels.add("<br>Vervaldatum: " + dateFormat.format(eindDatum)
				+ "</p>");
		factuurregels.add("<h1 align=\"center\">Factuurnummer: " + factuurNaam
				+ "</h1>");
		factuurregels.add("" + "<table align=\"center\">"
				+ "<tr class=\"border\">"
				+ "<th class=\"border\">SessieNummer</th>"
				+ "<th class=\"border\">Omschrijving</th>"
				+ "<th class=\"border\">Datum</th>"
				+ "<th class=\"border\">Dekking?</th>"
				+ "<th class=\"border\">Bedrag</th>"
				+ "<th class=\"border\">Eigen bijdrage</th>"
				+ "<th class=\"border\">BTWPercentage</th>" + "</tr>");
		double btwPercantage = 21.0;
		String betalingsCondities = "TODO: Betalingscondities navragen";
		double beginRisico = klant.getRestantEigenRisico();
		double eindRisico = klant.getRestantEigenRisico();
		double vergoedeKosten = 0;
		double nietVergoedeKosten = 0;
		double eigenBijdrage = 0;
		double totaleKosten = 0;
		String dekking = "error";
		String eigenbijdrage = "error";
		int gevonden = 0;
		for (int i = 0; i < sessies.size(); i++) {
			Sessie ses = sessies.get(i);
			Behandelingtraject sesTraject = null;
			try {
				DAOBehandelingtraject daoB = new DAOBehandelingtraject();
				sesTraject = daoB.geef(String.valueOf(ses.getBehandelId()));
			} catch (Exception e) {
			}

			List<Verzekering> verzekering = klant.getVerzekeringen();
			for (int j = 0; j < verzekering.size(); j++) {
				Verzekering gevondenVerzerking = verzekering.get(j);

				List<Behandelingtraject> dekTraject = gevondenVerzerking
						.getGedekteBehandeltrajecten();
				for (int k = 0; k < dekTraject.size(); k++) {
					Behandelingtraject behandel = dekTraject.get(k);
					if (behandel.getBehandelcode().equals(
							sesTraject.getBehandelcode())) {
						eigenBijdrage = eigenBijdrage
								+ sesTraject.getEigenBijdrage();
						vergoedeKosten = vergoedeKosten
								+ sesTraject.getTarief();
						gevonden = 1;
						dekking = "Ja";
						eigenbijdrage = df
								.format(sesTraject.getEigenBijdrage())
								.toString();
						// System.out.println(vergoedeKosten);

					}
				}
			}

			if (gevonden == 0) {
				nietVergoedeKosten = nietVergoedeKosten
						+ sesTraject.getTarief();
				dekking = "Nee";
				eigenbijdrage = "NVT";
			}
			gevonden = 0;
			// Voegt elke gevonde sessie op de juiste manier toe aan de HTML

			factuurregels.add("" + "<tr class=\"border\">"
					+ "<td class=\"border\">" + ses.getSessieID() + "</td>"
					+ "<td class=\"border\">" + sesTraject.getNaam() + "</td>"
					+ "<td class=\"border\">"
					+ dateFormat.format(ses.getDate()) + "</td>"
					+ "<td class=\"border\">" + dekking + "</td>"
					+ "<td class=\"border\">\n"
					+ df.format(sesTraject.getTarief()) + "</td>"
					+ "<td class=\"border\">" + eigenbijdrage + "</td>"
					+ "<td class=\"border\">" + btwPercantage + "%</td>"
					+ "</tr>");

		}

		// Voegt de optelling toe aan de HTML
		totaleKosten = vergoedeKosten + nietVergoedeKosten;
		factuurregels
				.add(""
						+ "<tr >"
						+ "<th></th>"
						+ "<th></th>"
						+ "<th></th>"
						+ "<th align=\"right\" class=\"border\" font: bolt;>Subtotaal</th>"
						+ "<th class=\"border\">" + df.format(totaleKosten)
						+ "</th>" + "<th></th>" + "</tr>");
		totaleKosten = totaleKosten * (btwPercantage / 100);
		factuurregels.add("" + "<tr>" + "<th></th>" + "<th></th>" + "<th></th>"
				+ "<td align=\"right\">" + btwPercantage + "% BTW</td>"
				+ "<td>" + df.format(totaleKosten) + "</td>" + "<th></th>"
				+ "</tr>" + "<tr>");

		nietVergoedeKosten = nietVergoedeKosten * (btwPercantage / 100 + 1);
		vergoedeKosten = vergoedeKosten * (btwPercantage / 100 + 1);
		totaleKosten = nietVergoedeKosten + vergoedeKosten;
		factuurregels.add("" + "<tr>" + "<th></th>" + "<th></th>" + "<th></th>"
				+ "<th align=\"right\">Prijs van de sessies</th>" + "<th>"
				+ df.format(totaleKosten) + "</th>" + "<th></th>" + "</tr>"
				+ "<tr class=\"border\">" + "</tr>" + "</table>" + "<br>");

		eigenBijdrage = eigenBijdrage * (btwPercantage / 100 + 1);
		factuurregels
				.add(""
						+ "<table align=\"center\">"
						+ "<tr class=\"border\">"
						+ "<td class=\"border\" align='right'>Som van de sessies die gedekt worden: </td>"
						+ "<td>"
						+ df.format(vergoedeKosten)
						+ "</td>"
						+ "</tr>"
						+ "<tr class=\"border\">"

						+ "<td class=\"border\" align='right'>Eigenbijdrage voor deze sessies: </td>"
						+ "<td>" + df.format(eigenBijdrage) + "</td>");

		vergoedeKosten = vergoedeKosten - eigenBijdrage;
		factuurregels
				.add(""
						+ "</tr>"
						+ "<tr class=\"border\">"
						+ "<td class=\"border\" align=\"right\">Bruto Vergoeding van de verzekeraar: </td>"
						+ "<td>" + df.format(vergoedeKosten) + "</td>");

		if (vergoedeKosten >= beginRisico) {
			vergoedeKosten = vergoedeKosten - beginRisico;
			eindRisico = 0;
		} else if (vergoedeKosten < beginRisico) {
			eindRisico = beginRisico - vergoedeKosten;
			vergoedeKosten = 0;
		}
		nietVergoedeKosten = nietVergoedeKosten + (beginRisico - eindRisico)
				+ eigenBijdrage;
		factuurregels
				.add(""
						+ "<tr class=\"border\">"
						+ "<td class=\"border\" align='right'>Uw eigen risico is nog: </td>"
						+ "<td>"
						+ df.format(beginRisico)
						+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<th align=\"right\">De verzekeraar vergoed: </th>"
						+ "<th align=\"left\">"
						+ df.format(vergoedeKosten)
						+ "</th>"
						+ "</tr>"
						+ "<tr>"
						+ "<th align=\"right\">Te voldoen aan uw verzekeraar*: </th>"
						+ "<th align=\"left\">"
						+ df.format(nietVergoedeKosten)
						+ "</th>"
						+ "</tr>"
						+ "<tr>"
						+ "<th align=\"right\">Uw eigen risico na deze facturatie bedraagd: </th>"
						+ "<th align=\"left\">"
						+ df.format(eindRisico)
						+ "</th>"
						+ "</tr>"
						+ "<tr class=\"border\">"
						+ "</tr>"
						+ "</table>"
						+ "<br>"
						+ "<p class=\"big\">Wij verzoeken u vriendelijk het bovenstaande bedrag van "
						+ df.format(nietVergoedeKosten)
						+ " voor "
						+ dateFormat.format(eindDatum)
						+ " te voldoen op onze bankrekening onder vermelding van het factuurnummer("
						+ factuurNaam
						+ "). Voor vragen kunt u contact opnemen per e-mail. </p>"
						+ "<p class=\"big\"> * Dit bedrag is de som van niet vergoede behandelingen, eigenbijdrage en eigenrisico</P>");

		// Maak het factuur object aan
		Factuur gemaaktefactuur = new Factuur(newID, year + " - " + newID,
				beginDatum, eindDatum, vergoedeKosten, nietVergoedeKosten,
				beginRisico, btwPercantage, betalingsCondities, false,
				klant.getBsn(), stam.getNaam(), 1);

		// Maak Factuur HTML
		schrijfHtml(factuurregels, factuurNaam);

		// Schrijf factuur naar de DAO
		try {
			dao.opslaan(gemaaktefactuur);

			dao.saveDocument();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (klant.getAutomatischIncasso() == true) {
			// Wanneer de klant een Automatischeincasso wil, maak een Sepa

			maakSepa(gemaaktefactuur);

		}

		// Update het Klant object
		try {
			klant.setRestantEigenRisico(eindRisico);
			klant.setLaatsteFactuurDatum(beginDatum);
			DAOKlant dao = new DAOKlant();
			dao.opslaan(klant);
			dao.saveDocument();
			terug = "Geslaagd!\nFactuur " + gemaaktefactuur.getNaam()
					+ " succesvol aangemaakt";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return terug;

	}

	/**
	 * Maakt sepa aan
	 * 
	 * @param factuur
	 */
	private void maakSepa(Factuur factuur) {
		// Maak de variablen van nietVergoedekosten, factuur en vervaldatum
		// gereed
		String rekening = df.format(factuur.getNietVergoedeKosten());
		String beginDatum = dateFormat.format(factuur.getFactuurDatum());
		String eindDatum = dateFormat.format(factuur.getVervalDatum());

		try {
			@SuppressWarnings("rawtypes")
			XMLBase base = new XMLBase(Settings.DEFAULTSEPA, "Sepa-"
					+ factuur.getNaam()); // Maakt
											// XML,
											// Pad,
											// bestandsnaam
			base.create("SEPA"); // Geeft hooddtag
			Document document = base.getDocument(); // Genereer XML document

			// Voeg de variabelen toe tussen de aangegeven tags
			Element root = document.getRootElement();
			root.addElement("incassoDatum").addText(beginDatum);
			root.addElement("rekening").addText(rekening);
			root.addElement("Te voldoen voor").addText(eindDatum);

			// Schrijft het XML document weg
			base.saveDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block+
			e.printStackTrace();
		}

	}

	/**
	 * Maakt html voor factuur
	 * 
	 * @param factuurllist
	 * @param naam
	 */
	private void schrijfHtml(ArrayList<String> factuurllist, String naam) {
		String htmlOUT = "<style>td, tr.border, th.border, table{border-bottom: 1px solid black} p.big {font-size: 10pt;}</style>";
		for (int i = 0; i < factuurllist.size(); i++) {
			htmlOUT = htmlOUT + factuurllist.get(i);
		}
		String exportPath = Settings.DEFAULTHTML + naam + ".html";
		try {
			PrintWriter outPut = new PrintWriter(new FileOutputStream(
					exportPath));
			outPut.print(htmlOUT);
			outPut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}
}
