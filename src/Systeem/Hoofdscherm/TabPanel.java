/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Hoofdscherm;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Container klasse voor de tabbladen die gebruikt worden door het hoodscherm.
 * Deze klasse bevat het JPanel dat weergegeven moet worden in een tabblad en de
 * titel voor een tabblad.
 * 
 * @author Gregor
 *
 */
public class TabPanel extends JPanel {

	/** The title. */
	private String	title;

	/**
	 * Instantiates a new tab panel.
	 *
	 * @param beginPaneel
	 *            the begin paneel
	 * @param title
	 *            the title
	 */
	public TabPanel(JPanel beginPaneel, String title)
	{
		this.title = title;
		this.setLayout(new GridLayout());
		this.add(beginPaneel);
		this.setPreferredSize(new Dimension(750, 550));
		this.setMinimumSize(this.getPreferredSize());
	}

	/**
	 * Haalt de titel op.
	 *
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Vervangt het paneel dat weergegeven wordt. Vergeet niet repaint() aan te
	 * roepen na deze methode.
	 * 
	 * @param panel
	 *            Het nieuwe paneel.
	 */
	public void replaceContentPanel(JPanel panel)
	{
		this.removeAll();
		this.add(panel);
	}
}
