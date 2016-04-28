/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package businessEntityDomain;

import java.sql.Timestamp;

public interface ImmutableSessie {

	/** Geeft begindatum en tijd */
	public Timestamp getBeginDatumTijd();

	/** Geeft einddatum en tijd */
	public Timestamp getEindDatumTijd();

	/** Geeft status */
	public String getStatus();

	/** Geeft sessienummer */
	public int getSessieNummer();

	/** Geeft opmerking */
	public String getOpmerking();

	/** Geeft behandeltraject id */
	public int getBehandelTrajectId();

}
