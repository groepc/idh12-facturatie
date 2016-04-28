/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

import java.util.List;

import Systeem.BusinessDomain.Behandelingtraject;

// TODO: Auto-generated Javadoc
//Interface die gebruikt moet worden door panels die het JDialog GUIBEhandelingZoeken willen gebruiken.
/**
 * The Interface BehandelingToevoegPanel.
 */
public interface BehandelingToevoegPanel {

	/**
	 * Voeg behandelingen toe.
	 *
	 * @param behandelingen
	 *            the behandelingen
	 */
	public void voegBehandelingenToe(List<Behandelingtraject> behandelingen);
}
