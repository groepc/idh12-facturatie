/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.BusinessDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Diagnose.
 *
 * @author Warlord Diagnose van een klant
 */
public class Diagnose {

	/** behandeltrajecten die moeten doorgelopen worden om 100% gezond te zijn. */
	private List<Behandelingtraject>	behandelingen;

	/** DiagnoseCode van de diagnose zelf. */
	private String				diagnoseCode;

	/**
	 * XML Constructor.
	 *
	 * @param diagnoseCode
	 *            DiagnoseCode van de diagnose
	 * @param behandelingstrajecten
	 *            BehandelTrajecten die moeten doorgelopen worden om 100% gezond
	 *            te worden/zijn
	 */
	public Diagnose(String diagnoseCode, List<Behandelingtraject> behandelingstrajecten) {
		this.diagnoseCode = diagnoseCode;
		this.setBehandelingen(behandelingstrajecten != null
				? behandelingstrajecten
				: new ArrayList<Behandelingtraject>());
	}

	/**
	 * Getter voor behandelingTrajecten.
	 *
	 * @return Behandeltrajecten
	 */
	public List<Behandelingtraject> getBehandelingstrajecten() {
		return this.getBehandelingen();
	}

	/**
	 * Setter voor BehandelTrajecten.
	 *
	 * @param behandelingstrajecten
	 *            Behandeltrajecten in de diagnose lijst
	 */
	public void setBehandelingstrajecten(List<Behandelingtraject> behandelingstrajecten) {
		this.setBehandelingen(behandelingstrajecten);
	}

	/**
	 * Getter voor DiagnoseCode.
	 *
	 * @return DiagnoseCode
	 */
	public String getDiagnoseCode() {
		return this.diagnoseCode;
	}

	/**
	 * Gets the behandelingen.
	 *
	 * @return the behandelingen
	 */
	List<Behandelingtraject> getBehandelingen() {
		return behandelingen;
	}

	/**
	 * Sets the behandelingen.
	 *
	 * @param behandelingen the new behandelingen
	 */
	void setBehandelingen(List<Behandelingtraject> behandelingen) {
		this.behandelingen = behandelingen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.diagnoseCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof Diagnose) {
			Diagnose other = (Diagnose) obj;

			return Objects.equals(diagnoseCode, other.diagnoseCode)
					&& Objects.equals(getBehandelingen(), other.getBehandelingen());
		}
		return false;
	}

}