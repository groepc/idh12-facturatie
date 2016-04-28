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
 * The Class Verzekering.
 *
 * @author Warlord Verzekering van een verzekeringmaatschappij
 */
public class Verzekering {

	/** De verzekeraar. */
	Verzekeringsmaatschappij verzekeraar;

	/** gedekteBehandelingstrajecten. */
	List<Behandelingtraject> gedekteBehandelingstrajecten;

	/** naam. */
	private String naam;

	/** type. */
	private String type;

	/** omschrijving. */
	private String omschrijving;

	/** id. */
	private String id;

	/**
	 * Constructor.
	 *
	 * @param naam
	 *            naam van de verzekering
	 * @param type
	 *            type verzekering
	 * @param omschrijving
	 *            omschrijving van de verzekering
	 * @param id
	 *            id van de verzekering
	 * @param verzekeringMaatschappij
	 *            the verzekering maatschappij
	 * @param gedekteBehandelingstrajecten
	 *            the gedekte behandelingstrajecten
	 */
	public Verzekering(String naam, String type, String omschrijving, String id, Verzekeringsmaatschappij verzekeringMaatschappij, List<Behandelingtraject> gedekteBehandelingstrajecten) {
		this.naam = naam;
		this.type = type;
		this.omschrijving = omschrijving;
		this.id = id;
		this.verzekeraar = verzekeringMaatschappij;
		this.gedekteBehandelingstrajecten = gedekteBehandelingstrajecten != null ? gedekteBehandelingstrajecten : new ArrayList<Behandelingtraject>();
	}

	/**
	 * Getters voor naam.
	 *
	 * @return Naam
	 */
	public String getNaam() {
		return this.naam;
	}

	/**
	 * Setters voor naam.
	 *
	 * @param naam
	 *            the new naam
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * Getter voor type.
	 *
	 * @return Type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Setter voor type.
	 *
	 * @param type
	 *            type verzekering
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter voor omschrijving.
	 *
	 * @return omschrijving
	 */
	public String getOmschrijving() {
		return this.omschrijving;
	}

	/**
	 * Setter voor omschrijving.
	 *
	 * @param omschrijving
	 *            omschrijving van een verzekering
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	/**
	 * Getter voor verzekeringmaatschappij.
	 *
	 * @return Verzekeringmaatschappij
	 */
	public Verzekeringsmaatschappij getVerzekeringmaatschappij() {
		return this.verzekeraar;
	}

	/**
	 * Setter voor verzekeringmaatschappij.
	 *
	 * @param verzekeringmaatschappij
	 *            Verzekeringmaatschappij waar de verzekering van is.
	 */
	public void setVerzekeringmaatschappij(Verzekeringsmaatschappij verzekeringmaatschappij) {
		this.verzekeraar = verzekeringmaatschappij;
	}

	/**
	 * Getter voor het id.
	 *
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getNaam();
	}

	/**
	 * Gets the gedekte behandeltrajecten.
	 *
	 * @return the gedekte behandeltrajecten
	 */
	public List<Behandelingtraject> getGedekteBehandeltrajecten() {
		return gedekteBehandelingstrajecten;
	}

	public void setGedekteBehandeltrajecten(List<Behandelingtraject> behandelingen) {
		this.gedekteBehandelingstrajecten = behandelingen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof Verzekering) {
			Verzekering other = (Verzekering) obj;

			return Objects.equals(naam, other.naam) && Objects.equals(id, other.id) && Objects.equals(omschrijving, other.omschrijving) && Objects.equals(type, other.type) && Objects.equals(verzekeraar, other.verzekeraar)
					&& Objects.equals(gedekteBehandelingstrajecten, other.gedekteBehandelingstrajecten);
		}
		return false;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
}