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
 * The Class Verzekeringsmaatschappij.
 *
 * @author Warlord Verzekering maatschappij waar al de verzekeringen vandaan
 *         komen
 */
public class Verzekeringsmaatschappij {

	/** Verzekeringen vsn de verzekeringmaatschappij. */
	private List<Verzekering> verzekeringen;

	/** Naam vsn de verzekeringmaatschappij. */
	private String naam;

	/** telefoonnummer vsn de verzekeringmaatschappij. */
	private String telefoonnummer;

	/** adres vsn de verzekeringmaatschappij. */
	private String adres;

	/** plaats vsn de verzekeringmaatschappij. */
	private String plaats;

	/** kvkNr vsn de verzekeringmaatschappij. */
	private String kvkNr;

	/** iban vsn de verzekeringmaatschappij. */
	private String iban;

	/** postcode vsn de verzekeringmaatschappij. */
	private String postcode;

	/** btwNr vsn de verzekeringmaatschappij. */
	private String btwNr;

	/**
	 * Instantiates a new verzekeringsmaatschappij.
	 *
	 * @param naam
	 *            Naam vsn de verzekeringmaatschappij
	 * @param telefoonnummer
	 *            Telefoon nummer vsn de verzekeringmaatschappij
	 * @param adres
	 *            adres vsn de verzekeringmaatschappij
	 * @param plaats
	 *            plaats vsn de verzekeringmaatschappij
	 * @param kvkNr
	 *            kvkNr vsn de verzekeringmaatschappij
	 * @param ibn
	 *            ibn vsn de verzekeringmaatschappij
	 * @param postcode
	 *            postcode vsn de verzekeringmaatschappij
	 * @param btwNr
	 *            btwNr vsn de verzekeringmaatschappij
	 */
	public Verzekeringsmaatschappij(String naam, String telefoonnummer, String adres, String plaats, String kvkNr,
			String ibn, String postcode, String btwNr) {
		this.naam = naam;
		this.telefoonnummer = telefoonnummer;
		this.adres = adres;
		this.plaats = plaats;
		this.kvkNr = kvkNr;
		this.iban = ibn;
		this.postcode = postcode;
		this.btwNr = btwNr;
		this.verzekeringen = new ArrayList<Verzekering>();
	}

	/**
	 * Instantiates a new verzekeringsmaatschappij.
	 *
	 * @param naam
	 *            Naam vsn de verzekeringmaatschappij
	 * @param telefoonnummer
	 *            Telefoon nummer vsn de verzekeringmaatschappij
	 * @param adres
	 *            adres vsn de verzekeringmaatschappij
	 * @param plaats
	 *            plaats vsn de verzekeringmaatschappij
	 * @param kvkNr
	 *            kvkNr vsn de verzekeringmaatschappij
	 * @param ibn
	 *            ibn vsn de verzekeringmaatschappij
	 * @param postcode
	 *            postcode vsn de verzekeringmaatschappij
	 * @param btwNr
	 *            btwNr vsn de verzekeringmaatschappij
	 * @param verzekeringen
	 *            De verzekeringen van de verzekeringmaatschappij
	 */
	public Verzekeringsmaatschappij(String naam, String telefoonnummer, String adres, String plaats, String kvkNr,
			String ibn, String postcode, String btwNr, List<Verzekering> verzekeringen) {
		this(naam, telefoonnummer, adres, plaats, kvkNr, ibn, postcode, btwNr);
		this.verzekeringen = verzekeringen != null ? verzekeringen : new ArrayList<Verzekering>();
	}

	/**
	 * Getter voor de naam.
	 *
	 * @return naam
	 */
	public String getNaam() {
		return this.naam;
	}

	/**
	 * Setter voor de naam.
	 *
	 * @param naam
	 *            Naam van de verzekering
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * Getter voor de Telefoonnummer.
	 *
	 * @return Telefoonnummer
	 */
	public String getTelefoonnummer() {
		return this.telefoonnummer;
	}

	/**
	 * Setter voor de Telefoonnummer.
	 *
	 * @param telefoonnummer
	 *            Telefoonnummer van de verzekeringmaatschappij
	 */
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	/**
	 * Getter van het adres.
	 *
	 * @return Adres Adres van de verzekeringmaatschappij
	 */
	public String getAdres() {
		return this.adres;
	}

	/**
	 * Setter voor het adres.
	 *
	 * @param adres
	 *            the new adres
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * Getter voor plaats.
	 *
	 * @return plaats
	 */
	public String getPlaats() {
		return this.plaats;
	}

	/**
	 * Setter van de plaats.
	 *
	 * @param plaats
	 *            the new plaats
	 */
	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	/**
	 * Getter voor het kvkNr.
	 *
	 * @return kvkNr
	 */
	public String getKvkNr() {
		return this.kvkNr;
	}

	/**
	 * Setter voor het kvkNr.
	 *
	 * @param kvkNr
	 *            Kamer van koophandel nummer
	 */
	public void setKvkNr(String kvkNr) {
		this.kvkNr = kvkNr;
	}

	/**
	 * Getter voor het Iban.
	 *
	 * @return Iban
	 */
	public String getIban() {
		return this.iban;
	}

	/**
	 * Setter voor het Iban.
	 *
	 * @param iban
	 *            Rekening nummer
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * Getter voor het postcode.
	 *
	 * @return Postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Setter voor postcode.
	 *
	 * @param postcode
	 *            the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Getter voor btw nummer.
	 *
	 * @return btw nummer
	 */
	public String getBtwNr() {
		return this.btwNr;
	}

	/**
	 * Setter voor btw nummer.
	 *
	 * @param btwNr
	 *            Btw nummer
	 */
	public void setBtwNr(String btwNr) {
		this.btwNr = btwNr;
	}

	/**
	 * Getter voor verzekeringen.
	 *
	 * @return verzekeringen
	 */
	public List<Verzekering> getVerzekeringen() {
		return this.verzekeringen;
	}

	/**
	 * Setter voor verzekeringen.
	 *
	 * @param verzekering
	 *            Verzekering toevoegen
	 */
	public void addVerzekering(Verzekering verzekering) {
		this.verzekeringen.add(verzekering);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.naam;
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

		if (obj instanceof Verzekeringsmaatschappij) {
			Verzekeringsmaatschappij other = (Verzekeringsmaatschappij) obj;

			return Objects.equals(naam, other.naam) && Objects.equals(telefoonnummer, other.telefoonnummer)
					&& Objects.equals(adres, other.adres) && Objects.equals(plaats, other.plaats)
					&& Objects.equals(kvkNr, other.kvkNr) && Objects.equals(iban, other.iban)
					&& Objects.equals(postcode, other.postcode) && Objects.equals(btwNr, other.btwNr)
					&& Objects.equals(verzekeringen, other.verzekeringen);
		}
		return false;
	}
}