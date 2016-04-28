/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Map;

import businessEntityDomain.ImmutableSessie;

/**
 * @author robbie
 *
 */
public interface FysiotherapieInf extends Remote {

	public static final String servicename = "fysiotherapieInf";

	public Map<ImmutableSessie, String> getSessies(String bsn, Timestamp datum) throws RemoteException;

	public int getFysiotherapiePraktijkId() throws RemoteException;
}
