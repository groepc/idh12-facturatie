/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Businesslogic;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.DAO.DAOVerzekering;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt alle communicatie omtremt verzekering objecten.
 * 
 * @author Gregor
 */
public class ControllerVerzekeringen {

	/** The dao verzekering. */
	DAOVerzekering daoVerzekering;

	/** The controller controllerMaatschappijen. */
	ControllerVerzekeringMaatschappijen controllerMaatschappijen;

	/**
	 * Instantiates a new controller verzekeringen.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ControllerVerzekeringen() throws IOException {
		daoVerzekering = new DAOVerzekering();
		controllerMaatschappijen = new ControllerVerzekeringMaatschappijen();
	}

	/**
	 * Geeft de verzekering terug die hoort bij het id.
	 * 
	 * @param id
	 *            Het id van de verzekering.
	 * @return De gevonde verzekering.
	 */
	public Verzekering geefVerzekering(String id) {
		try {
			return daoVerzekering.geef(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Zoek verzekeringen van maatschappij.
	 * 
	 * @param zoekTerm
	 *
	 * @param maatschappij
	 *            the maatschappij
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Verzekering> zoekVerzekeringen(String zoekTerm) throws IOException {
		return daoVerzekering.geefMeerdere(zoekTerm, SearchField.SECOND, SearchLevel.CONTAINS);
	}

	/**
	 * @param maatschappij
	 * @return h
	 * @throws IOException
	 */
	public List<Verzekering> zoekVerzekeringenVanMaatschappij(Verzekeringsmaatschappij maatschappij) throws IOException {
		return daoVerzekering.geefMeerdere(maatschappij.getKvkNr(), SearchField.THIRD, SearchLevel.CONTAINS);

	}

	/**
	 * Haal alle verzekeringen op.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Verzekering> haalAlleVerzekeringenOp() throws IOException {
		return daoVerzekering.geefAlles();
	}

	/**
	 * Voegt een verzekering toe.
	 *
	 * @param verzekering
	 *            De aangemaakte verzekering.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void verzekeringToeveoegen(Verzekering verzekering) throws IOException {
		daoVerzekering.opslaan(verzekering);
		daoVerzekering.saveDocument();

		Verzekeringsmaatschappij maatschappij = verzekering.getVerzekeringmaatschappij();
		maatschappij.addVerzekering(verzekering);
		controllerMaatschappijen.verzekeringsmaatschappijWijzigen(maatschappij);
	}

	/**
	 * Verwijder verzekering.
	 *
	 * @param verzekering
	 *            the verzekering
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean verwijderVerzekering(Verzekering verzekering) throws IOException {
		if (checkInGebruik(verzekering.getId())) {
			return false;
		} else {
			daoVerzekering.verwijderen(verzekering);
			daoVerzekering.saveDocument();
			return true;
		}
	}

	/**
	 * Wijzigt de gegevens van een verzekering.
	 *
	 * @param verzekering
	 *            De gewijzigde verzekering.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void verzekeringWijzigen(Verzekering verzekering) throws IOException {
		daoVerzekering.opslaan(verzekering);
		daoVerzekering.saveDocument();
	}

	/**
	 * Sorteerd de verzekeringen lijst op type.
	 * 
	 * @param teSorterenVerzekeringen
	 *            De te sorteren lijst.
	 * @return De gesorteerde lijst.
	 */
	public List<Verzekering> soteerOpType(List<Verzekering> teSorterenVerzekeringen) {
		teSorterenVerzekeringen.sort(new typeCompare());
		return teSorterenVerzekeringen;
	}

	public String getLastId() {
		List<Verzekering> verzekeringen;
		try {
			verzekeringen = daoVerzekering.geefAlles();
			verzekeringen.sort(new idCompare());
			return verzekeringen.isEmpty() ? "1" : String.valueOf(Integer.parseInt(verzekeringen.get(0).getId()) + 1);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Controleert of een verzekering gekoppeld is aan klanten.
	 *
	 * @param verzekeringId
	 *            Het ID van de te controleren verzekering.
	 * @return true als de verzekering gekoppeld is of false wanneer het nog
	 *         niet in gebeuik is.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean checkInGebruik(String verzekeringId) throws IOException {
		List<Klant> klanten = new DAOKlant().geefAlles();
		for (Klant klant : klanten) {
			List<Verzekering> verzekeringenVanKlant = klant.getVerzekeringen();
			if (!verzekeringenVanKlant.isEmpty()) {
				for (Verzekering verzekering : verzekeringenVanKlant) {
					if (verzekering.getId().equals(verzekeringId)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * The Class typeCompare.
	 */
	private class typeCompare implements Comparator<Verzekering> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Verzekering o1, Verzekering o2) {
			return o1.getType().equals("basis") ? -1 : 1;
		}

	}

	/**
	 * The Class idCompare.
	 */
	private class idCompare implements Comparator<Verzekering> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Verzekering o1, Verzekering o2) {
			return Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId()) ? -1 : 1;
		}

	}
}
