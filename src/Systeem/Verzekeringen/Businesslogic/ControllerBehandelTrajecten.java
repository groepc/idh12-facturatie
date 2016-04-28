/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Businesslogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.Datastorage.DAO.DAOBehandelingtraject;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Settings.VerzekeringSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerBehandelTrajecten.
 */
public class ControllerBehandelTrajecten {

	/** The dao behandelingtraject. */
	private DAOBehandelingtraject daoBehandelingtraject;

	/**
	 * Instantiates a new controller behandel trajecten.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ControllerBehandelTrajecten() throws IOException {
		daoBehandelingtraject = new DAOBehandelingtraject();
	}

	/**
	 * Haal behandeling trajecten op.
	 *
	 * @param filterBasisBehandelingen
	 *            the filter basis behandelingen
	 * @return the list
	 */
	public List<Behandelingtraject> haalBehandelingTrajectenOp(boolean filterBasisBehandelingen) {
		List<Behandelingtraject> behandelingen = new ArrayList<>();
		try {
			behandelingen = daoBehandelingtraject.geefAlles();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filterBasisBehandelingen ? filterBasisBehandelingen(behandelingen) : behandelingen;
	}

	/**
	 * Zoek behandel trajecten.
	 *
	 * @param text
	 *            the text
	 * @param filterBasisBehandelingen
	 *            the filter basis behandelingen
	 * @return the list
	 */
	public List<Behandelingtraject> zoekBehandelTrajecten(String text, boolean filterBasisBehandelingen) {

		List<Behandelingtraject> behandelingen = new ArrayList<>();
		try {
			behandelingen = daoBehandelingtraject.geefMeerdere(text, SearchField.SECOND, SearchLevel.CONTAINS);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filterBasisBehandelingen ? filterBasisBehandelingen(behandelingen) : behandelingen;
	}

	/**
	 * s Lijst van behandelingtrajecten
	 * 
	 * @param behandelingen
	 * @return new ArrayList<Behandelingtraject>
	 */
	private List<Behandelingtraject> filterBasisBehandelingen(List<Behandelingtraject> behandelingen) {
		String[] basisBehandelCodes = VerzekeringSettings.basisDekking;
		Map<String, Behandelingtraject> behandelingLijst = new LinkedHashMap<>();

		for (Behandelingtraject behandeling : behandelingen) {
			behandelingLijst.put(behandeling.getBehandelcode(), behandeling);
		}

		for (int x = 0; x < basisBehandelCodes.length; x++) {
			behandelingLijst.remove(basisBehandelCodes[x]);
		}
		return new ArrayList<Behandelingtraject>(behandelingLijst.values());
	}

	/**
	 * Lijst van basis behandelingen
	 * 
	 * @return basisBehandelingLijst ======= Haal basis behandelingen op.
	 */
	public List<Behandelingtraject> haalBasisBehandelingenOp() {
		String[] basisBehandelCodes = VerzekeringSettings.basisDekking;
		Map<String, Behandelingtraject> behandelingLijst = new LinkedHashMap<>();
		List<Behandelingtraject> basisBehandelingLijst = new ArrayList<>();

		for (Behandelingtraject behandeling : haalBehandelingTrajectenOp(false)) {
			behandelingLijst.put(behandeling.getBehandelcode(), behandeling);
		}

		for (int x = 0; x < basisBehandelCodes.length; x++) {
			basisBehandelingLijst.add(behandelingLijst.get(basisBehandelCodes[x]));
		}
		return basisBehandelingLijst;
	}
}
