/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.rmi.domain;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * De klasse klant.
 */
public class RmiKlant implements RmiKlantIRead, Serializable {

	private static final long serialVersionUID = 4387L;

	/** Burger service nummer van de klant. */
	private String bsn;

	/** Voornaam van de klant. */
	private String voornaam;

	/** Achternaam van de klant. */
	private String achternaam;

	/** Adres van de klant. */
	private String adres;

	/** Postcode van de klant. */
	private String postcode;

	/** plaats van de klant. */
	private String plaats;

	/** Geboorte datum van de klant. */
	private Date geboortedatum;

	/** Email van de klant. */
	private String email;

	/** The telefoonnummer. */
	private String telefoonnummer;

	/** The geslacht. */
	private String geslacht;

	/**
	 * Constructor normal.
	 *
	 * @param bsn            Burger service nummer
	 * @param voornaam            Voornaam van de klant
	 * @param achternaam            Achternaam van de klant
	 * @param adres            Adres van de klant
	 * @param postcode            Postcode van de klant
	 * @param plaats            the plaats
	 * @param geboortedatum            Geboorteddatum van de klant
	 * @param telefoonnummer            the telefoonnummer
	 * @param email            Email van de klant
	 * @param geslacht the geslacht
	 */
	public RmiKlant(String bsn, String voornaam, String achternaam, String adres, String postcode, String plaats, Date geboortedatum, String telefoonnummer, String email, String geslacht) {
		this.bsn = bsn;// String
		this.voornaam = voornaam;// String
		this.achternaam = achternaam;// String
		this.adres = adres;// String
		this.plaats = plaats;// String
		this.postcode = postcode;// String
		this.geboortedatum = geboortedatum;// Date
		this.telefoonnummer = telefoonnummer;// String
		this.email = email;// String
		this.geslacht = geslacht;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getBsn()
	 */
	@Override
	public String getBsn() {
		return this.bsn;
	}

	/**
	 * Settter voor bsn.
	 *
	 * @param bsn
	 *            the new bsn
	 */
	public void setBsn(String bsn) {
		this.bsn = bsn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getVoornaam()
	 */
	@Override
	public String getVoornaam() {
		return this.voornaam;
	}

	/**
	 * Setter voor voornaam.
	 *
	 * @param voornaam
	 *            the new voornaam
	 */
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getAchternaam()
	 */
	@Override
	public String getAchternaam() {
		return this.achternaam;
	}

	/**
	 * Setter voor achternaam.
	 *
	 * @param achternaam
	 *            the new achternaam
	 */
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getAdres()
	 */
	@Override
	public String getAdres() {
		return this.adres;
	}

	/**
	 * Setter voor adres.
	 *
	 * @param adres
	 *            the new adres
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getPostcode()
	 */
	@Override
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
	 * Sets the plaats.
	 *
	 * @param plaats
	 *            the new plaats
	 */
	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getPlaats()
	 */
	@Override
	public String getPlaats() {
		return this.plaats;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getGeboortedatum()
	 */
	@Override
	public Date getGeboortedatum() {
		return this.geboortedatum;
	}

	/**
	 * Setter voor geboortedatum.
	 *
	 * @param geboortedatum
	 *            the new geboortedatum
	 */
	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getEmail()
	 */
	@Override
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter voor Email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.bsn + ": " + this.voornaam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getTelefoonnummer()
	 */
	@Override
	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	/**
	 * Sets the telefoonnummer.
	 *
	 * @param telefoonnummer
	 *            the new telefoonnummer
	 */
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.rmi.domain.RmiKlantIRead#getGeslacht()
	 */
	@Override
	public String getGeslacht() {
		return geslacht;
	}

	/**
	 * Sets the geslacht.
	 *
	 * @param geslacht the new geslacht
	 */
	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}
}