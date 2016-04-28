/**
 * 
 */
package Systeem.rmi.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import businessEntityDomain.ImmutableSessie;
import Systeem.BusinessDomain.Sessie;
import Systeem.Settings.RmiSettings;
import Systeem.rmi.dao.DAORemoteSessies;

/**
 * @author Gregor/Mark
 *
 */
public class RemoteSessies {
	private Boolean rmidone = false;
	private ArrayList<Sessie> sessies;

	public List<Sessie> getSessies(Date ophaalDatum, String bsn) {
		if (rmidone == true) {
			try {

				if (RmiSettings.RmiDebug) {
					System.out.println("RMI Sessies ophalen...");
					System.out
							.println("BSN: " + bsn + " Datum: " + ophaalDatum);
				}

				DAORemoteSessies sessieDao = new DAORemoteSessies();
				Map<ImmutableSessie, String> sessieMap = sessieDao
						.fetchSessies(bsn, new Timestamp(ophaalDatum.getTime()));

				if (RmiSettings.RmiDebug) {
					System.out.println(sessieMap.size()
							+ "sessies teruggekregen.");
				}

				for (Map.Entry<ImmutableSessie, String> sessieMapEntry : sessieMap
						.entrySet()) {
					ImmutableSessie rmiSessie = sessieMapEntry.getKey();
					String behandelCode = sessieMapEntry.getValue();

					if (behandelCode == null
							|| rmiSessie.getEindDatumTijd() == null)
						throw new NullPointerException();

					sessies.add(new Sessie(rmiSessie.getSessieNummer(),
							new Date(rmiSessie.getEindDatumTijd().getTime()),
							behandelCode));
				}

			} catch (Exception ex) {
				System.out.print("error 1");
			}
		} else {
			fallBack();
		}
		return sessies;
	}

	private void fallBack() {
		sessies = new ArrayList<>();
		Random rn = new Random();

		if (RmiSettings.RmiDebug) {
			System.out.println("RMI mislukt!\nGaat door met fallback");
		}
		Date now = new Date();
		Integer amount = rn.nextInt(40) + 1;
		for (int i = 0; i < amount; i++) {
			Integer sesID = rn.nextInt(9) + 1;
			sessies.add(new Sessie(sesID * 8 - 7 * 6 - 5, now, "00"
					+ sesID.toString()));

		}

	}
}
