/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Klanten.Businesslogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Systeem.BusinessDomain.Klant;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt alle communicatie omtremt Klant objecten.
 *
 * @author Gregor
 * @author Jermey
 */
public class ControllerKlant {

	/** The dao klant. */
	DAOKlant	daoKlant;

	/**
	 * Instantiates a new controller klant.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ControllerKlant() throws IOException {
		daoKlant = new DAOKlant();
	}

	/**
	 * Geeft een klant terug die hoort bij het meegegeven burgerservicenummer.
	 *
	 * @param bsn
	 *            Het burgerservicenummer van de klant.
	 * @return De gevonden klant.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public Klant geefKlant(String bsn) throws IOException {
		return daoKlant.geef(bsn);
	}

	/**
	 * Verwijder klant.
	 *
	 * @param klant
	 *            the klant
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void verwijderKlant(Klant klant) throws IOException {
		daoKlant.verwijderen(klant);
		daoKlant.saveDocument();
	}

	/**
	 * Geef klanten.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Klant> geefKlanten() throws IOException {
		return daoKlant.geefAlles();
	}

	/**
	 * Zoekt alle klanten op die voldoen aan de zoekterm.
	 *
	 * @param invoer
	 *            the invoer
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Klant> zoekKlanten(String invoer) throws IOException {
		List<Klant> gevondenKlanten = new ArrayList<Klant>();

		String[] explodedZoekterm = invoer.split(" ");
		String[] tempArray = new String[explodedZoekterm.length];
		int z = 0;
		for (int x = 0; x < explodedZoekterm.length; x++) {
			if (!isTussenVoegsel(explodedZoekterm[x])) {
				tempArray[z] = explodedZoekterm[x];
				z++;
			}
		}
		explodedZoekterm = new String[z];
		System.arraycopy(tempArray, 0, explodedZoekterm, 0, z);

		switch (explodedZoekterm.length) {
			case 2 :
				gevondenKlanten = zoekOpTweeWoorden(explodedZoekterm[0],
						explodedZoekterm[1]);
				break;
			default :
				gevondenKlanten = zoekOpEenWoord(explodedZoekterm[0]);
				break;
		}
		return gevondenKlanten;
	}

	/**
	 * Zoek op een woord.
	 *
	 * @param woord
	 *            the woord
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred. >>>>>>> Dao-goed
	 */
	private List<Klant> zoekOpEenWoord(String woord) throws IOException {
		List<Klant> gevondenKlanten = new ArrayList<Klant>();
		gevondenKlanten.addAll(daoKlant.geefMeerdere(woord,
				IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.STARTSWITH));

		for (Klant k : daoKlant.geefMeerdere(woord,
				IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS)) {
			if (!gevondenKlanten.contains(k))
				gevondenKlanten.add(k);
		}

		for (Klant k : daoKlant.geefMeerdere(woord, IReadDAO.SearchField.THIRD,
				IReadDAO.SearchLevel.CONTAINS)) {
			if (!gevondenKlanten.contains(k))
				gevondenKlanten.add(k);
		}
		return gevondenKlanten;
	}

	/**
	 * Zoeken naar klanten op twee woorden
	 * 
	 * @param voornaam
	 * @param achternaam
	 * @return v
	 * @throws IOException
	 */

	private List<Klant> zoekOpTweeWoorden(String voornaam, String achternaam)
			throws IOException {

		List<Klant> gevondenKlanten = new ArrayList<Klant>();
		List<Klant> gevondenVoornamen = new ArrayList<Klant>();

		for (Klant k : daoKlant.geefMeerdere(voornaam,
				IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS)) {
			gevondenVoornamen.add(k);
		}

		for (Klant k : daoKlant.geefMeerdere(achternaam,
				IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.CONTAINS)) {
			if (gevondenVoornamen.contains(k))
				gevondenKlanten.add(k);
		}
		return gevondenKlanten;
	}

	/**
	 * Sla.
	 *
	 * @param klant
	 *            De klant die toegevoegd moet worden.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void klantOpslaan(Klant klant) throws IOException {
		daoKlant.opslaan(klant);
		daoKlant.saveDocument();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Klanten.Businesslogic.ControllerKlantenI#geefAlleKlanten()
	 */
	/**
	 * Geef alle klanten.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Klant> geefAlleKlanten() throws IOException {
		return daoKlant.geefAlles();
	}

	/**
	 * Controleert of tussenvoegsel correct is.
	 * 
	 * @param tekst
	 * @return Checks if is tussen voegsel.
	 *
	 * @param tekst
	 *            the tekst
	 */
	private boolean isTussenVoegsel(String tekst) {
		String[] tussenVoegsel = new String[]{"af", "aan", "bij", "de",
				"den", "der", "d'", "het", "'t", "in", "onder", "op", "over",
				"'s", "'t", "te", "ten", "ter", "tot", "uit", "uijt", "van",
				"vanden", "ver", "voor"};
		for (int x = 0; x < tussenVoegsel.length; x++) {
			if (tekst.equals(tussenVoegsel[x])) {
				return true;
			}
		}
		return false;
	}
}
