/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Hoofdscherm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Systeem.Klanten.Presentation.GUIKlantZoeken;
import Systeem.Verzekeringen.Presentation.GUIVerzekeringZoeken;
import Systeem.Verzekeringen.Presentation.GUIVerzekeringsmaatschappijOverzicht;

// TODO: Auto-generated Javadoc
/**
 * The Class Hoofdscherm.
 */
public class Hoofdscherm extends JFrame implements ParentFrame {

	/** The tabbed pane. */
	private JTabbedPane tabbedPane;

	/** The tabs. */
	private List<TabPanel> tabs;

	/**
	 * Constructor voor Hoofdscherm.
	 */
	public Hoofdscherm() {
		super();
		tabs = new ArrayList<>();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// ------Hier tabs toegvoegen.
		tabs.add(new TabPanel(new GUIKlantZoeken(this), "Klant subsysteem"));
		tabs.add(new TabPanel(new GUIVerzekeringZoeken(this),
				"Verzekering subsysteem"));
		tabs.add(new TabPanel(new GUIVerzekeringsmaatschappijOverzicht(this),
				"Verzekeringmaatschappij"));
		// tabs.add(new TabPanel((JPanel) new TestFrame().getContentPane(),
		// "Facturatie"));
		// ------

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		for (TabPanel panel : tabs) {
			tabbedPane.add(panel, panel.getTitle());
		}

		this.setContentPane(tabbedPane);
		this.setTitle("Facturatie & Incasso");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Vervangt het paneel dat weergegeven wordt in een tab en zorgt dat het
	 * geheet gerepaint wordt.
	 *
	 * @param panel
	 *            the panel
	 * @param tabId
	 *            the tab id
	 */
	public void changePanel(JPanel panel, int tabId) {
		tabs.get(tabId).replaceContentPanel(panel);
		tabbedPane.repaint();
	}
}
