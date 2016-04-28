/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Main;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class TextValidatieManager.
 *
 * @author Gregor
 */
public class TextValidatieManager {

	/** The Constant standaardFoutBorder. */
	private static final Border standaardFoutBorder = new LineBorder(Color.RED);

	/**
	 * The Enum ValidatieType.
	 */
	public static enum ValidatieType {

		/** The tekst. */
		TEKST("^[a-zA-Z0-9_:!.,\\(\\)\\s]+$", "De volgende tkens zijn geaccepteerd [a-zA-Z0-9_:!.,\\(\\)]"),
		/** The getal. */
		GETAL("^\\d+$", "Geen valide getal ingevoerd."),
		/** The kommagetal. */
		KOMMAGETAL("^\\d+,?\\d*$", "Geen valide kommagetal ingevoerd."),
		/** The datum. */
		DATUM("^(\\d{2})-(\\d{2})-(\\d{4})$", "Geen valide datum ingevoerd, het juiste formaat is dd-mm-yyyy"),
		/** The geboortedatum. */
		GEBOORTEDATUM(DATUM.getRegExp(), DATUM.getFoutMelding()),
		/** The telefoonnummer. */
		TELEFOONNUMMER("^[\\d]{4}-[\\d]{6}$|^06-[\\d]{8}$|^[\\d]{3}-[\\d]{7}$", "Het juiste formaat is 06-12345678 of 123-1234567 of 1234-123456."),
		/** The bsn. */
		BSN("^\\d{8,9}$", "Het BSN moet 8 of 9 cijfers bevatten."),
		/** The iban. */
		IBAN("^[a-zA-Z]{2}[0-9]{2}[0-9a-zA-Z]{1,30}$", "Het juiste formaat is <Landcode><Controlegetal><Rekeningnummer>"),
		/** The kvknr. */
		KVKNR("\\d{8}", "Het kvkNr moet 8 cijfers lang zijn."),
		/** The email. */
		EMAIL("^[\\w.%+-]+@[\\w]+\\.[A-Za-z]{2,4}$", "Het juiste formaat <tekst>@<tekst>.<tekst>"),
		/** The postcode. */
		POSTCODE("^\\d{4}[A-Za-z]{2}$", "Het juiste formaat is 1234AZ"),
		/** The btwnummer. */
		BTWNUMMER("^[a-zA-Z0-9]{14}$", "Het BTW nummer moet 14 tekens lang zijn en alleen uit letters en cijfers bestaan.");

		/** The reg exp. */
		String regExp;

		/** The foutmelding. */
		String foutmelding;

		/**
		 * Instantiates a new validatie type.
		 *
		 * @param regExp
		 *            the reg exp
		 * @param foutmelding
		 *            the foutmelding
		 */
		ValidatieType(String regExp, String foutmelding) {
			this.regExp = regExp;
			this.foutmelding = foutmelding;
		}

		/**
		 * Gets the reg exp.
		 *
		 * @return the reg exp
		 */
		public String getRegExp() {
			return regExp;
		}

		/**
		 * Gets the fout melding.
		 *
		 * @return the fout melding
		 */
		public String getFoutMelding() {
			return foutmelding;
		}
	}

	/**
	 * Lijst van validatie items
	 */
	private List<TextValidatieItem> validatieItems;

	/**
	 * Arraylist aanmaken voor validatie Items /** The validatie items.
	 */

	/**
	 * Instantiates a new text validatie manager. ]
	 */
	public TextValidatieManager() {
		validatieItems = new ArrayList<>();
	}

	/**
	 * @param textComponent
	 * @param magNietLeegZijn
	 * @param validatieType
	 * @param naam
	 * @throws IllegalArgumentException
	 */
	public void addValidatie(JTextComponent textComponent, boolean magNietLeegZijn, String naam) throws IllegalArgumentException {
		addValidatie(textComponent, magNietLeegZijn, 0, ValidatieType.TEKST, naam);
	}

	/**
	 * Adds the validatie.
	 *
	 * @param textComponent
	 *            the text component
	 * @param magNietLeegZijn
	 *            the mag niet leeg zijn
	 * @param validatieType
	 *            the validatie type
	 * @param naam
	 *            the naam
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public void addValidatie(JTextComponent textComponent, boolean magNietLeegZijn, ValidatieType validatieType, String naam) throws IllegalArgumentException {

		addValidatie(textComponent, magNietLeegZijn, 0, validatieType, naam);
	}

	/**
	 * 
	 * @param textComponent
	 * @param magNietLeegZijn
	 * @param maxLength
	 * @param validatieType
	 * @param naam
	 * @throws IllegalArgumentException
	 */

	public void addValidatie(JTextComponent textComponent, boolean magNietLeegZijn, int maxLength, String naam) throws IllegalArgumentException {
		addValidatie(textComponent, magNietLeegZijn, maxLength, ValidatieType.TEKST, naam);
	}

	/**
	 * Adds the validatie.
	 *
	 * @param textComponent
	 *            the text component
	 * @param magNietLeegZijn
	 *            the mag niet leeg zijn
	 * @param maxLength
	 *            the max length
	 * @param validatieType
	 *            the validatie type
	 * @param naam
	 *            the naam
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public void addValidatie(JTextComponent textComponent, boolean magNietLeegZijn, int maxLength, ValidatieType validatieType, String naam) throws IllegalArgumentException {

		if (textComponent == null || validatieType == null || naam.isEmpty()) {
			throw new IllegalArgumentException("textCompoenent en validatieType mogen niet null zijn, naam mag geen lege string zijn.");
		}

		validatieItems.add(new TextValidatieItem(textComponent, magNietLeegZijn, maxLength, validatieType, naam));
	}

	/**
	 * 
	 * Valideer gegevens
	 * 
	 * @return Valideer.
	 */
	public boolean valideer() {
		boolean alleItemsGoed = true;

		for (TextValidatieItem item : validatieItems) {
			item.resetComponent();

			String text = item.getText();

			if (item.magNietLeegZijn()) {
				if (text.isEmpty()) {
					alleItemsGoed = false;
					item.setFout(standaardFoutBorder, "Het veld mag niet leeg zijn.");
					continue;
				}
			}

			if (item.getMaxLength() > 0) {
				if (text.length() > item.getMaxLength()) {
					alleItemsGoed = false;
					item.setFout(standaardFoutBorder, "De inhoud is groter dan de maximum lengte van het veld.");
					continue;
				}
			}

			if (!valideer(item)) {
				item.setFout(standaardFoutBorder, item.getValidatieType().getFoutMelding());
				alleItemsGoed = false;
				continue;
			}
		}
		return alleItemsGoed;
	}

	/**
	 * 
	 * Valideer gegevens item
	 * 
	 * @param item
	 * @return Valideer.
	 */
	private boolean valideer(TextValidatieItem item) {
		boolean goed = true;
		Pattern pattern = Pattern.compile(item.getValidatieType().getRegExp());
		pattern.matcher(item.getText()).matches();

		// Controleren of de inhoud valideerd tegen de megegeven regExp
		if (!pattern.matcher(item.getText()).matches()) {
			goed = false;
		}

		if (goed) {
			// Controleren op specifiek geldende regels welke niet met een
			// regExp te controleren zijn.
			switch (item.getValidatieType()) {
			case DATUM:
				try {
					new SimpleDateFormat("dd-MM-yyyy").parse(item.getText());
				} catch (ParseException e) {
					goed = false;
				}
				break;
			default:
				break;
			}
		}

		return goed;
	}

	/**
	 * Geeft een popup wanneer iets niet goed is ingevuld
	 * 
	 * @param parentPanel
	 *            Show popup.
	 *
	 * @param parentPanel
	 *            the parent panel
	 */
	public void showPopup(Component parentPanel) {
		JOptionPane.showMessageDialog(parentPanel, "Sommige velden zijn verkeerd ingevuld, zie de tooltip van een rood gemarkeerd veld voor meer informatie.");
	}
}
