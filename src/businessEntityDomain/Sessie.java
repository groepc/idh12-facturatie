/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package businessEntityDomain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Sessie implements ImmutableSessie, Serializable {

	private static final long serialVersionUID = -5195080112007670753L;
	private Timestamp beginDatumTijd;
	private Timestamp eindDatumTijd;
	private String status;
	private int sessieNummer;
	private String opmerking;
	private int behandelTrajectId;

	/**
	 * 
	 * @param beginDatumTijd
	 * @param eindDatumTijd
	 * @param status
	 * @param sessieNummer
	 * @param behandelTrajectId
	 * @param opmerking
	 */
	public Sessie(Timestamp beginDatumTijd, Timestamp eindDatumTijd,
			String status, int sessieNummer, int behandelTrajectId,
			String opmerking) {
		this.beginDatumTijd = beginDatumTijd;
		this.eindDatumTijd = eindDatumTijd;
		this.status = status;
		this.opmerking = opmerking;
		this.behandelTrajectId = behandelTrajectId;
		this.sessieNummer = sessieNummer;

	}

	/**
	 * Geeft begindatum en tijd
	 * 
	 * @return beginDatumTijd
	 */
	public Timestamp getBeginDatumTijd() {
		return beginDatumTijd;
	}

	/**
	 * Geeft einddatum en tijd
	 * 
	 * @return eindDatumTijd
	 */
	public Timestamp getEindDatumTijd() {
		return eindDatumTijd;
	}

	/**
	 * Geeft status
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Geeft sessienummer
	 * 
	 * @return sessieNummer
	 */
	public int getSessieNummer() {
		return sessieNummer;
	}

	/**
	 * Geeft opmerking
	 * 
	 * @return opmerking
	 */
	public String getOpmerking() {
		return opmerking;
	}

	/**
	 * Geeft behandeltraject id
	 * 
	 * @return behandelTrajectId
	 */
	public int getBehandelTrajectId() {
		return behandelTrajectId;
	}

	/**
	 * Zet SessieNummer
	 * 
	 * @param nummer
	 */
	public void setSessieNummer(int nummer) {
		sessieNummer = nummer;
	}

	/**
	 * Update Sessie
	 * 
	 * @param beginDatum
	 * @param eindDatum
	 * @param status
	 * @param opmerking
	 */
	public void update(Timestamp beginDatum, Timestamp eindDatum,
			String status, String opmerking) {
		this.beginDatumTijd = beginDatum;
		this.eindDatumTijd = eindDatum;
		this.status = status;
		this.opmerking = opmerking;
	}
}