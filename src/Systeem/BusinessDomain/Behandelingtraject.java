/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.BusinessDomain;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Behandelingtraject.
 *
 * @author Jermey
 * @Een behandelTraject is een traject dat de klant doorloopt om een medisch
 *      probleem op te lossen
 */
public class Behandelingtraject {

	/** behandelcode van een behandel traject. */
	private String	behandelcode;

	/** Tarief van de behandel traject. */
	private Double	tarief;

	/** eigen bijdrage die de klant moet leveren voor behandel traject. */
	private Double	eigenBijdrage;

	/** maximum aantal sessies in een behandel traject. */
	private int		aantalSessies;

	/** The naam. */
	private String	naam;

	/**
	 * Instantiates a new behandelingtraject.
	 *
	 * @param behandelcode            Behandelcode van de behandelTraject
	 * @param naam            the naam
	 * @param tarief            Tarief van de behandel code
	 * @param eigenBijdrage            Eigen bijdrage die de klant moet leveren
	 * @param aantalSessies            maximale aantal sessies
	 */
	public Behandelingtraject(String behandelcode, String naam, Double tarief, Double eigenBijdrage, int aantalSessies) {
		this.behandelcode = behandelcode;
		this.naam = naam;
		this.tarief = tarief;
		this.eigenBijdrage = eigenBijdrage;
		this.aantalSessies = aantalSessies;
	}

	/**
	 * Gets the behandelcode.
	 *
	 * @return the behandelcode
	 */
	public String getBehandelcode() {
		return behandelcode;
	}

	/**
	 * Sets the behandelcode.
	 *
	 * @param behandelcode            the behandelcode to set
	 */
	public void setBehandelcode(String behandelcode) {
		this.behandelcode = behandelcode;
	}

	/**
	 * Gets the tarief.
	 *
	 * @return the tarief
	 */
	public Double getTarief() {
		return tarief;
	}

	/**
	 * Sets the tarief.
	 *
	 * @param tarief            the tarief to set
	 */
	public void setTarief(Double tarief) {
		this.tarief = tarief;
	}

	/**
	 * Gets the eigen bijdrage.
	 *
	 * @return the eigenBijdrage
	 */
	public Double getEigenBijdrage() {
		return eigenBijdrage;
	}

	/**
	 * Sets the eigen bijdrage.
	 *
	 * @param eigenBijdrage            the eigenBijdrage to set
	 */
	public void setEigenBijdrage(Double eigenBijdrage) {
		this.eigenBijdrage = eigenBijdrage;
	}

	/**
	 * Gets the aantal sessies.
	 *
	 * @return the aantalSessies
	 */
	public int getAantalSessies() {
		return aantalSessies;
	}

	/**
	 * Sets the aantal sessies.
	 *
	 * @param aantalSessies            the aantalSessies to set
	 */
	public void setAantalSessies(int aantalSessies) {
		this.aantalSessies = aantalSessies;
	}

	/**
	 * Gets the naam.
	 *
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Sets the naam.
	 *
	 * @param naam            the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getBehandelcode() + ": " + this.tarief.toString();
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

		if (obj instanceof Behandelingtraject) {
			Behandelingtraject other = (Behandelingtraject) obj;

			return Objects.equals(behandelcode, other.behandelcode) && Objects.equals(naam, other.naam) && Objects.equals(tarief, other.tarief) && Objects.equals(eigenBijdrage, other.eigenBijdrage)
					&& Objects.equals(aantalSessies, other.aantalSessies);
		}
		return false;
	}

}