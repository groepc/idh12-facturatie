/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.rmi.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Systeem.rmi.domain.RmiKlantIRead;

/**
 * 
 * @author Gregor
 *
 */
public interface RemoteKlantenInterface extends Remote {

	public static final String servicename = "facturatieServer";

	public RmiKlantIRead geefKlant(String bsn) throws RemoteException;

	public List<RmiKlantIRead> geefAlleKlanten() throws RemoteException;
}
