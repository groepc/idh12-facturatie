/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Main;

import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Systeem.Settings.RmiSettings;
import Systeem.rmi.main.RmiServer;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("OptionPane.yesButtonText", "Ja");
					UIManager.put("OptionPane.noButtonText", "Nee" + "");
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// Zet de timeout op 5 sec voordat de rmi transactie wordt geannuleerd.
		Properties props = System.getProperties();
		props.setProperty("sun.rmi.transport.tcp.responseTimeout", RmiSettings.RmiTimeout);
		props.setProperty("sun.rmi.transport.connectionTimeout", RmiSettings.RmiTimeout);
		props.setProperty("sun.rmi.transport.tcp.handshakeTimeout", RmiSettings.RmiTimeout);
		props.setProperty("sun.rmi.transport.tcp.readTimeout", RmiSettings.RmiTimeout);

		// Start RMI server...
		RmiServer rmiServer = new RmiServer();
		rmiServer.start();

		// Start Login scherm.
		Login log = new Login();
		log.setVisible(true);

	}

}
