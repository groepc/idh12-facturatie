/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.rmi.domain;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Interface RmiKlantIRead.
 *
 * @author Gregor
 */
public interface RmiKlantIRead {

	/**
	 * Getter voor Burgerservicenummer.
	 *
	 * @return bsn
	 */
	public abstract String getBsn();

	/**
	 * Getter voor voornaam.
	 *
	 * @return voornaam
	 */
	public abstract String getVoornaam();

	/**
	 * Getter voor voornaam.
	 *
	 * @return voornaam
	 */
	public abstract String getAchternaam();

	/**
	 * Getter voor adres.
	 *
	 * @return adres
	 */
	public abstract String getAdres();

	/**
	 * Getter voor postcode.
	 *
	 * @return postcode
	 */
	public abstract String getPostcode();

	/**
	 * Gets the plaats.
	 *
	 * @return the plaats
	 */
	public abstract String getPlaats();

	/**
	 * Getter voor geboortedatum.
	 *
	 * @return geboortedatum
	 */
	public abstract Date getGeboortedatum();

	/**
	 * Getter voor Email.
	 *
	 * @return Email
	 */
	public abstract String getEmail();

	/**
	 * Gets the telefoonnummer.
	 *
	 * @return the telefoonnummer
	 */
	public abstract String getTelefoonnummer();

	/**
	 * Geeft geslacht
	 * 
	 * @return the geslacht
	 */
	public abstract String getGeslacht();

}