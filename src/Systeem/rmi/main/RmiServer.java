/*
 * creator Jermey
 * date    2-nov-2014
 * version 7.1
 */
package Systeem.rmi.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Systeem.Settings.RmiSettings;
import Systeem.rmi.business.RemoteKlant;
import Systeem.rmi.business.RemoteKlantenInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class RmiServer.
 *
 * @author Gregor
 */
public class RmiServer extends Thread {

	/** The debug. */
	boolean debug = RmiSettings.RmiDebug;

	/**
	 * Start RMI server
	 * **/
	public void startRmiServer() {
		try {
			if (debug) {
				System.out.println("RMI_SERVER>>> Service does not excists, creating service.");
			}

			RemoteKlantenInterface klantInterface = new RemoteKlant();
			if (debug) {
				System.out.println("RMI_SERVER>>> Creating stub");
			}
			RemoteKlantenInterface klantStub = (RemoteKlantenInterface) UnicastRemoteObject.exportObject(klantInterface, 0);

			if (debug) {
				System.out.println("RMI_SERVER>>> Loading registry");
			}
			Registry registry = LocateRegistry.getRegistry();

			if (debug) {
				System.out.println("RMI_SERVER>>> Binding registry");
			}
			registry.rebind(RmiSettings.RmiKlantServiceName, klantStub);

		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if (debug) {
			System.out.println("RMI_SERVER>>> Starting thread");
		}
		startRmiServer();
	}
}
