/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Main;

import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import Systeem.Main.TextValidatieManager.ValidatieType;

// TODO: Auto-generated Javadoc
/**
 * The Class TextValidatieItem.
 *
 * @author Gregor
 */
public class TextValidatieItem {

	/** The text component. */
	private JTextComponent	textComponent;

	/** The orginele border. */
	private Border			orgineleBorder;

	/** The orginele tooltip. */
	private String			orgineleTooltip;

	/** The naam. */
	private String			naam;

	/** The mag niet leeg zijn. */
	private boolean			magNietLeegZijn;

	/** The max length. */
	private int				maxLength;

	/** The validatie type. */
	private ValidatieType	validatieType;

	/**
	 * Instantiates a new text validatie item.
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
	 */
	public TextValidatieItem(JTextComponent textComponent,
			boolean magNietLeegZijn, int maxLength,
			ValidatieType validatieType, String naam) {

		this.naam = naam;
		this.textComponent = textComponent;
		this.magNietLeegZijn = magNietLeegZijn;
		this.maxLength = maxLength;
		this.validatieType = validatieType;
		this.orgineleBorder = textComponent.getBorder();
		this.orgineleTooltip = textComponent.getToolTipText();
	}

	/**
	 * 
	 * Reset component.
	 */
	public void resetComponent() {
		textComponent.setBorder(orgineleBorder);
		textComponent.setToolTipText(orgineleTooltip);
	}

	/**
	 * controleerd of een veld gevuld is
	 * 
	 * @return magNietLeegZijn Mag niet leeg zijn.
	 */
	public boolean magNietLeegZijn() {
		return magNietLeegZijn;
	}

	/**
	 * Maximale lengte van een veld
	 * 
	 * @return maxLength Gets the max length.
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * Geeft juiste validatietype
	 * 
	 * @return the validatie type
	 */
	public ValidatieType getValidatieType() {
		return validatieType;
	}

	/**
	 * Geeft text
	 * 
	 * @return textComponent.getText() Gets the text.
	 */
	public String getText() {
		return textComponent.getText();
	}

	/**
	 * Geeft naam
	 * 
	 * @return naam Gets the naam.
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * 
	 * @param border
	 *            Sets the border.
	 *
	 * @param border
	 *            the new border
	 */
	public void setBorder(Border border) {
		textComponent.setBorder(border);
	}

	/**
	 * 
	 * @param text
	 *            Sets the tool tip text.
	 *
	 * @param text
	 *            the new tool tip text
	 */
	public void setToolTipText(String text) {
		textComponent.setToolTipText(text);
	}

	/**
	 * 
	 * @param border
	 * @param toolTip
	 *            Sets the fout.
	 *
	 * @param border
	 *            the border
	 * @param toolTip
	 *            the tool tip
	 */
	public void setFout(Border border, String toolTip) {
		setBorder(border);
		setToolTipText(toolTip);
	}
}
