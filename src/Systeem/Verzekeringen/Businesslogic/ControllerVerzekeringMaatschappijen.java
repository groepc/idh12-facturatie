/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Businesslogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt alle communicatie omtremt verzekeringsmaatschappij
 * objecten.
 * 
 * @author Gregor
 */
public class ControllerVerzekeringMaatschappijen {

	/** The dao verzekeringmaatschappij. */
	DAOVerzekeringmaatschappij daoVerzekeringmaatschappij;

	// Map<String, Verzekeringsmaatschappij> dummyVerzekeringen;

	/**
	 * Instantiates a new controller verzekering maatschappijen.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ControllerVerzekeringMaatschappijen() throws IOException {
		daoVerzekeringmaatschappij = new DAOVerzekeringmaatschappij();

		// -------------------DUMMY DATA-------------------------//
		// dummyVerzekeringen = new LinkedHashMap<>();

	}

	/**
	 * Geeft alle verzekeringsmaatschappijen terug die opgeslagen zijn.
	 * 
	 * @return De lijst van alle verzekeringsmaatschappijen.
	 */
	public List<Verzekeringsmaatschappij> geefVerzekeringsmaatschappijen() {
		try {
			return daoVerzekeringmaatschappij.geefAlles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/**
	 * Geeft een verzekeringsmaatschappij terug die hoort bij het kvknr.
	 * 
	 * @param kvkNr
	 *            Het kamer van koophandel nummer van een
	 *            verzekeringsmaatschappij.
	 * @return De gevonde verzekeringsmaatschappij.
	 */
	public Verzekeringsmaatschappij geefVerzekeringsmaatschappij(String kvkNr) {
		try {
			return daoVerzekeringmaatschappij.geef(kvkNr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Voegt een verzekeringsmaatschappij toe.
	 * 
	 * @param verzekeringsmaatschappij
	 *            De verzekeringsmaatschappij die toegevoegd moet worden.
	 */
	public void verzekeringsmaatschappijToevoegen(Verzekeringsmaatschappij verzekeringsmaatschappij) {
		try {
			daoVerzekeringmaatschappij.opslaan(verzekeringsmaatschappij);
			daoVerzekeringmaatschappij.saveDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wijzigt de gegevens van een verzekeringsmaatschappij.
	 * 
	 * @param verzekeringsmaatschappij
	 *            De verzekeringsmaatschappij die gewijzigd is.
	 */
	public void verzekeringsmaatschappijWijzigen(Verzekeringsmaatschappij verzekeringsmaatschappij) {
		try {
			daoVerzekeringmaatschappij.opslaan(verzekeringsmaatschappij);
			daoVerzekeringmaatschappij.saveDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verzekeringmaatschappij verwijderen.
	 *
	 * @param verzekeringmaatschappij
	 *            the verzekeringmaatschappij
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public boolean verzekeringmaatschappijVerwijderen(Verzekeringsmaatschappij verzekeringmaatschappij) throws IOException {
		if (checkIngebruik(verzekeringmaatschappij)) {
			return false;
		} else {
			daoVerzekeringmaatschappij.verwijderen(verzekeringmaatschappij);
			daoVerzekeringmaatschappij.saveDocument();
			return true;
		}
	}

	/**
	 * Controleerd of verzekering in gebruik is Check ingebruik.
	 *
	 * @param verzekeringmaatschappij
	 *            the verzekeringmaatschappij
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public boolean checkIngebruik(Verzekeringsmaatschappij verzekeringmaatschappij) throws IOException {
		ControllerVerzekeringen controller = new ControllerVerzekeringen();
		return !controller.zoekVerzekeringenVanMaatschappij(verzekeringmaatschappij).isEmpty();
	}
}
