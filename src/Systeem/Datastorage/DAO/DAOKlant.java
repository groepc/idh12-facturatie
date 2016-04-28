/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.DAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLDeletableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOKlant.
 *
 * @author Jermey
 */
public class DAOKlant extends XMLDeletableDAOBase<Klant, String> {

	/**
	 * Instantiates a new DAO klant.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOKlant() throws IOException {
		this(Settings.DAOKLANTPAD, Settings.DAOKLANTNAAM);
	}

	/**
	 * Instantiates a new DAO klant.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOKlant(final String bestandpad, final String naam)
			throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		} catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out.println("DAO Klant class is aangeroepen");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Datastorage.Interfaces.IReadDAO#geef(java.lang.Object)
	 */
	/**
	 * Geef.
	 *
	 * @param key
	 *            the key
	 * @return the klant
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Klant geef(final String key) throws IOException {

		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Klant[@BSN=\"" + key + "\"]");
		final List<Klant> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out.println("geef functie van DAOKlant is aangeroepen");
			System.out.println(result.size() + " zijn er gevonden");
		}
		return result.isEmpty() ? null : result.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IReadDAO#geefMeerdere(java.lang.String)
	 */
	/**
	 * Geef meerdere.
	 *
	 * @param zoekString
	 *            the zoek string
	 * @param field
	 *            is the field that gets searched on FIRST : BSN, SECOND : naam,
	 *            THIRD : achternaam
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Klant objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Klant> geefMeerdere(final String zoekString,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Klant");
		final List<Klant> klanten = parse(nodes, zoekString, field, level);
		if (Settings.BUGGING) {
			System.out
					.println("geefMeerdere functie van DAOKlant is aangeroepen");
			for (final Klant k : klanten) {
				System.out.println(k + " is gevonden en mee gegeven");
			}
		}
		return klanten;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Datastorage.Interfaces.IReadDAO#geefAlles()
	 */
	/**
	 * Geef alles.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Klant> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Klant");
		final List<Klant> klanten = parse(nodes);
		if (Settings.BUGGING) {
			System.out.println("geefAlles functie van DAOKlant is aangeroepen");
			for (final Klant k : klanten) {
				System.out.println(k + " is gevonden en mee gegeven");
			}
		}
		return klanten;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IEditableDAO#opslaan(java.lang.Object)
	 */
	/**
	 * Opslaan.
	 *
	 * @param object
	 *            the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public void opslaan(final Klant object) throws IOException {
		final Element root = super.getDocument().getRootElement();
		if (geef(object.getBsn()) != null) {
			final Element rootK = (Element) document.selectNodes(
					"//" + super.getName() + "/Klant[@BSN=\"" + object.getBsn()
							+ "\"]/descendant-or-self::*").get(0);
			rootK.detach();
		}

		final Element klantRoot = root.addElement("Klant").addAttribute("BSN",
				object.getBsn());
		klantRoot.addElement("voornaam").addText(object.getVoornaam());
		klantRoot.addElement("achternaam").addText(object.getAchternaam());
		klantRoot.addElement("adres").addText(object.getAdres());
		klantRoot.addElement("postcode").addText(object.getPostcode());
		klantRoot.addElement("plaats").addText(object.getPlaats());
		klantRoot.addElement("geboortedatum").addText(
				object.getGeboortedatum().toString());
		klantRoot.addElement("email").addText(object.getEmail());
		klantRoot.addElement("eigenRisico").addText(
				String.valueOf(object.getEigenRisico()));
		klantRoot.addElement("restantEigenRisico").addText(
				String.valueOf(object.getRestantEigenRisico()));
		klantRoot.addElement("automatischIncasso").addText(
				String.valueOf(object.getAutomatischIncasso()));

		final Element diagnosesRoot = klantRoot.addElement("Diagnoses");
		// diagnoses
		if (object.getDiagnoses() != null) {
			for (final Diagnose b : object.getDiagnoses()) {
				diagnosesRoot.addElement("diagnose").addText(
						b.getDiagnoseCode());
			}
		}
		final Element behandeltrajectenRoot = klantRoot
				.addElement("Behandelingtrajecten");
		// behandeltrajecten
		if (object.getBehandelingtrajecten() != null) {
			for (final Behandelingtraject b : object.getBehandelingtrajecten()) {
				behandeltrajectenRoot.addElement("behandeltraject").addText(
						b.getBehandelcode());
			}
		}

		final Element verzekeringenRoot = klantRoot.addElement("Verzekeringen");
		// verzekeringen
		if (object.getVerzekeringen() != null) {
			for (final Verzekering v : object.getVerzekeringen()) {
				verzekeringenRoot.addElement("verzekering").addText(v.getId());
			}
		}

		klantRoot.addElement("telefoonnummer").addText(
				object.getTelefoonnummer());
		klantRoot.addElement("factuurOntvangst").addText(
				String.valueOf(object.getFactuurOntvangst()));
		klantRoot.addElement("laatsteFactuurDatum").addText(
				object.getLaatsteFactuurDatum().toString());
		klantRoot.addElement("Geslacht").addText(object.getGeslacht());
		if (Settings.BUGGING) {
			System.out.println("opslaan functie van DAOKlant is aangeroepen");
			System.out.println(object + " is opgeslagen");
		}
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            the nodes
	 * @return the array list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Klant> parse(final List<Node> nodes) throws IOException {
		return parse(nodes, null, null, null);
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            Nodes that needs to be searched and then parsed
	 * @param zoeken
	 *            Zoekstring
	 * @param field
	 *            is the field that gets searched on FIRST : BSN, SECOND : naam,
	 *            THIRD : achternaam
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Klant objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Klant> parse(final List<Node> nodes, final String zoeken,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		final List<Klant> klanten = new ArrayList<Klant>();
		final SimpleDateFormat parserSDF = new SimpleDateFormat(
				"EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
		DAOBehandelingtraject daoBehandelingtraject = null;
		DAOVerzekering daoVerzekering = null;
		DAODiagnose daoDiagnose = null;
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			@SuppressWarnings("unchecked")
			final List<Element> currentNodes = current.elements();
			boolean add = false;

			if (zoeken == null)
				add = true;
			else if (level == IReadDAO.SearchLevel.CONTAINS) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("BSN")
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(1).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.STARTSWITH) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("BSN")
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(1).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.COMPLETE) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("BSN")
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(1).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			}

			if (add) {
				final String bsn = current.attributeValue("BSN");
				final String naam = (String) currentNodes.get(0).getText();
				final String achternaam = (String) currentNodes.get(1)
						.getText();
				final String adres = (String) currentNodes.get(2).getText();
				final String postcode = (String) currentNodes.get(3).getText();
				final String plaats = (String) currentNodes.get(4).getText();
				final String date2 = currentNodes.get(5).getText();

				Date geboortedatum = null;
				try {
					geboortedatum = parserSDF.parse(date2);
				} catch (ParseException e) {
					if (Settings.BUGGING) {
						System.out.println(date2
								+ " is kon niet worden geparsed");
					}
				}

				final String mail = (String) currentNodes.get(6).getText();
				final Double eigenRisico = Double.valueOf(currentNodes.get(7)
						.getText());
				final Double restantEigenRisico = Double.valueOf(currentNodes
						.get(8).getText());
				final boolean automatischeIncasso = Boolean
						.getBoolean(currentNodes.get(9).getText());

				final List<Diagnose> diagnoses = new ArrayList<Diagnose>();
				final Element diagnosesRoot = (Element) currentNodes.get(10);
				@SuppressWarnings("unchecked")
				final List<Element> elementDiagnosesLijst = diagnosesRoot
						.elements();
				if (daoDiagnose == null && !elementDiagnosesLijst.isEmpty())
					daoDiagnose = new DAODiagnose(super.getBestandpad(),
							"Diagnoses");
				for (final Element v : elementDiagnosesLijst) {
					diagnoses.add(daoDiagnose.geef(v.getText()));
				}

				final List<Behandelingtraject> behandelingtrajecten = new ArrayList<Behandelingtraject>();
				final Element behandelingtrajectenRoot = (Element) currentNodes
						.get(11);
				@SuppressWarnings("unchecked")
				final List<Element> elementBehandeltrajectLijst = behandelingtrajectenRoot
						.elements();
				if (daoBehandelingtraject == null
						&& !elementBehandeltrajectLijst.isEmpty())
					daoBehandelingtraject = new DAOBehandelingtraject(
							super.getBestandpad(), "Behandelingtrajecten");
				for (final Element v : elementBehandeltrajectLijst) {
					behandelingtrajecten.add(daoBehandelingtraject.geef(v
							.getText()));
				}

				final List<Verzekering> verzekeringenLijst = new ArrayList<Verzekering>();
				final Element verzekeringenRoot = (Element) currentNodes
						.get(12);
				@SuppressWarnings("unchecked")
				final List<Element> elementverzekeringenLijst = verzekeringenRoot
						.elements();
				if (daoVerzekering == null
						&& !elementverzekeringenLijst.isEmpty())
					daoVerzekering = new DAOVerzekering(super.getBestandpad(),
							"Verzekeringen");
				for (final Element v : elementverzekeringenLijst) {
					verzekeringenLijst.add(daoVerzekering.geef(v.getText()));
				}

				final String telefoonnummer = currentNodes.get(13).getText();
				final boolean factuurOntvangst = Boolean
						.getBoolean(currentNodes.get(14).getText());

				Date laatsteFactuurDatum = null;
				try {
					laatsteFactuurDatum = parserSDF.parse(currentNodes.get(15)
							.getText());
				} catch (ParseException e) {
					System.out.println(laatsteFactuurDatum.toString()
							+ " is kon niet worden geparsed");
				}

				final String geslacht = currentNodes.get(16).getText();

				final Klant klant = new Klant(bsn, naam, achternaam, adres,
						postcode, plaats, geboortedatum, mail, eigenRisico,
						restantEigenRisico, automatischeIncasso, diagnoses,
						behandelingtrajecten, verzekeringenLijst,
						telefoonnummer, factuurOntvangst, laatsteFactuurDatum,
						geslacht);
				klanten.add(klant);
			}
		}
		if (Settings.BUGGING) {
			System.out.println("parse functie van DAOKlant is aangeroepen");
			for (final Klant k : klanten) {
				System.out.println(k + " is gevonden en mee gegeven");
			}
		}
		return klanten;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IDeletableDAO#verwijderen(java.lang.Object
	 * )
	 */
	@Override
	public void verwijderen(Klant object) {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Klant[@BSN=\"" + object.getBsn()
				+ "\"]/descendant-or-self::*");
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			current.detach();
		}

		if (Settings.BUGGING) {
			System.out
					.println("verwijderen functie van DAOKlanten is aangeroepen");
			System.out.println(object + " is verwijderd");
		}
	}
}