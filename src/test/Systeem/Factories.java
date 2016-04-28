/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem;

import java.util.Date;
import java.util.List;

import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;

// TODO: Auto-generated Javadoc
/**
 * The Class Factories.
 */
public class Factories {

	/**
	 * Verzekering factory.
	 *
	 * @param id            the primarykey
	 * @param v the v
	 * @param b the b
	 * @return the verzekering
	 */
	public static Verzekering verzekeringFactory(String id, Verzekeringsmaatschappij v, List<Behandelingtraject> b) {
		return new Verzekering("test", "basis", "geen", id, v, b);
	}

	/**
	 * Verzekeringmaatschappij factory.
	 *
	 * @param kvKNr
	 *            the primarykey
	 * @return the verzekeringsmaatschappij
	 */
	public static Verzekeringsmaatschappij verzekeringmaatschappijFactory(String kvKNr) {
		return new Verzekeringsmaatschappij("naam", "233222", "adres", "plaats", kvKNr, "ibn", "postcode", "tt");
	}

	/**
	 * Klant factory.
	 *
	 * @param bsn            the primarykey
	 * @param v the v
	 * @param d the d
	 * @param bt the bt
	 * @return the klant
	 */
	public static Klant klantFactory(String bsn, List<Verzekering> v, List<Diagnose> d, List<Behandelingtraject> bt) {
		return new Klant(bsn, "Jermey", "Jungbeker", "huisland 23", "433PT", "Breda", new Date(), "test@mail", 440.20,
				440.20, false, d, bt, v, bsn, false, new Date(), "man");
	}

	/**
	 * Factuur factory.
	 *
	 * @param nr            the primarykey
	 * @param k the k
	 * @return the factuur
	 */
	public static Factuur factuurFactory(int nr, String k) {
		return new Factuur(nr, "naam", new Date(), new Date(), 100.00, 200.00, 300.00, 21.0, "geen", true, k, "verzekeringmaatschappij", 2);
	}

	/**
	 * Diagnose factory.
	 *
	 * @param diagnoseCode            the primarykey
	 * @param btL the bt l
	 * @return the diagnose
	 */
	public static Diagnose diagnoseFactory(String diagnoseCode, List<Behandelingtraject> btL) {
		return new Diagnose(diagnoseCode, btL);
	}
	/**
	 * Behandelin traject.
	 *
	 * @param behandelCode
	 *            the primarykey
	 * @return the behandelingtraject
	 */
	public static Behandelingtraject behandelingTraject(String behandelCode) {
		return new Behandelingtraject(behandelCode, "naam", 22.1, 20.0, 0);
	}
}
