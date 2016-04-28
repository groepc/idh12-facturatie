/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.BusinessDomain;

import java.util.Date;

/**
 * @author Mark
 *
 */
public class Sessie {
	/** Sessie id van de sessie */
	private int sessieId;
	/** Datum van de sessie */
	private Date date;
	/** Behandel id bij de sessie */
	private String behandelId;

	/**
	 * Constructor sessie
	 * 
	 * @param sessieId
	 * @param date
	 * @param behandelId
	 */
	public Sessie(int sessieId, Date date, String behandelId) {
		this.sessieId = sessieId;
		this.date = date;
		this.behandelId = behandelId;
	}

	/**
	 * Geeft sessie id
	 * 
	 * @return sessieId
	 */
	public int getSessieID() {
		return sessieId;

	}

	/**
	 * Geeft Datum
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;

	}

	/**
	 * Geeft behandel id
	 * 
	 * @return behandelId
	 */
	public String getBehandelId() {
		return behandelId;

	}

}
