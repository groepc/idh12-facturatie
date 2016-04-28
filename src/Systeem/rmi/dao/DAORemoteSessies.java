/**
 * 
 */
package Systeem.rmi.dao;

import interfaces.FysiotherapieInf;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.rmi.ConnectIOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import Systeem.Settings.RmiSettings;
import businessEntityDomain.ImmutableSessie;

/**
 * @author Gregor
 *
 */
public class DAORemoteSessies {

	FysiotherapieInf fysioTherapieInterface;

	public DAORemoteSessies() {
		try {
			fysioTherapieInterface = (FysiotherapieInf) Naming.lookup("//" + RmiSettings.RmiSessieHost + "/" + RmiSettings.RmiSessieServiceName);
		} catch (ConnectIOException ex) {
			System.out.println("RMI connectie timeout.");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Url klopt niet");
			e.printStackTrace();
		}
	}

	public Map<ImmutableSessie, String> fetchSessies(String bsn, Timestamp datum) throws RemoteException {
		Map<ImmutableSessie, String> sessieLijst = new LinkedHashMap<>();

		sessieLijst = fysioTherapieInterface.getSessies(bsn, datum);
		System.out.println(sessieLijst.size());
		return sessieLijst;
	}

	public int fetchFysiopraktijNummer() throws RemoteException {
		return fysioTherapieInterface.getFysiotherapiePraktijkId();
	}
}
