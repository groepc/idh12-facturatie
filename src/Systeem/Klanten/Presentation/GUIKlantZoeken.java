/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Klanten.Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Klant;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Klanten.Businesslogic.ControllerKlant;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIKlantZoeken.
 * 
 * @author Jermey
 */
public class GUIKlantZoeken extends JPanel {

	/** The parent. */
	private ParentFrame parent;

	/** The controller klant. */
	private ControllerKlant controllerKlant;

	/** The current pattern. */
	String currentPattern;

	/** The zoek combo box. */
	JComboBox<String> zoekComboBox;

	/** The zoek resultaat. */
	List<Klant> zoekResultaat;

	/** The btn reset. */
	JButton btnReset;

	/** The zoek timer. */
	Timer zoekTimer;

	/**
	 * Create the panel.
	 *
	 * @param parent
	 *            the parent
	 */
	public GUIKlantZoeken(ParentFrame parent) {
		try {
			zoekResultaat = new ArrayList<Klant>();
			this.parent = parent;
			controllerKlant = new ControllerKlant();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(GUIKlantZoeken.this,
					"Fout bij het ophalen van klanten", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		initializeComponents();
	}

	/**
	 * Initialize Components of te panel.
	 */
	private void initializeComponents() {
		setSize(750, 550);
		setLayout(new BorderLayout(0, 0));

		setVisible(true);
		setSize(750, 550);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnTerug = new JButton("Terug");
		btnTerug.setVisible(false);
		panel_menu.add(btnTerug);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		panel_menu.add(verticalStrut_1);

		JPanel panel_content = new JPanel();
		add(panel_content);
		panel_content.setLayout(new MigLayout("", "[150][40%,grow][150]",
				"[40%][][grow]"));

		JPanel panel_Zoekvenster = new JPanel();
		panel_content.add(panel_Zoekvenster, "cell 1 1,grow");
		panel_Zoekvenster.setLayout(new BoxLayout(panel_Zoekvenster,
				BoxLayout.Y_AXIS));

		JLabel lblVerzekeringZoeken = new JLabel("klanten zoeken");
		lblVerzekeringZoeken.setMaximumSize(new Dimension(3000, 16));
		panel_Zoekvenster.add(lblVerzekeringZoeken);
		lblVerzekeringZoeken.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerzekeringZoeken.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblVerzekeringZoeken.setBorder(new MatteBorder(0, 0, 1, 0,
				(Color) new Color(0, 0, 0)));

		Component verticalStrut = Box.createVerticalStrut(5);
		panel_Zoekvenster.add(verticalStrut);

		JPanel panel_zoekInput = new JPanel();
		panel_Zoekvenster.add(panel_zoekInput);
		panel_zoekInput.setLayout(new MigLayout("", "0[grow][]0", "0[]4[]0"));

		zoekComboBox = new JComboBox<>();
		panel_zoekInput.add(zoekComboBox, "flowx,cell 0 0,grow");
		zoekComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		zoekComboBox.getEditor().getEditorComponent()
				.addKeyListener(new TxtVerzekeringZoekenKeyListener());
		zoekComboBox.setEditable(true);

		JButton zoekenButton = new JButton("zoeken");
		panel_zoekInput.add(zoekenButton, "cell 1 0");
		zoekenButton.setFont(new Font("Tahoma", Font.PLAIN, 21));

		btnReset = new JButton("reset");
		panel_zoekInput.add(btnReset, "cell 1 1,growx");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnReset.setVisible(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// zoekComboBox.setModel(new DefaultComboBoxModel<>());
				zoekComboBox.setEditable(true);
				btnReset.setVisible(false);
			}
		});
		zoekenButton
				.addActionListener(new BtnVerzekeringZoekenActionListener());

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnKlantToevoegen = new JButton("Klant toevoegen");
		panel.add(btnKlantToevoegen);
		btnKlantToevoegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.changePanel(new GUIKlantInvoerenWijzigen(parent), 0);
			}
		});

		// Knop om naar het klanten overzicht te gaan
		JButton Klantenoverzicht = new JButton("Klanten Overzicht");
		panel_menu.add(Klantenoverzicht);
		Klantenoverzicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.changePanel(new GUIKlantenOverzicht(parent), 0);
			}
		});

		zoekTimer = new Timer(350, new ZoekActie());
		zoekTimer.setRepeats(false);
	}

	/**
	 * Zoeken.
	 *
	 * @param zoekTerm
	 *            the zoek term
	 * @return List with Klant objecten
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public List<Klant> zoeken(String zoekTerm) throws IOException {
		// ArrayList<Klant> d = new ArrayList<Klant>();
		// d.add(Factories.klantFactory("2"));
		// d.add(Factories.klantFactory("3"));
		// return d;
		return controllerKlant.zoekKlanten(zoekTerm);
	}

	/**
	 * Zoeken naar klanten
	 */
	private class BtnVerzekeringZoekenActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			System.out.println(zoekComboBox.getSelectedIndex());
			if (zoekComboBox.getSelectedIndex() != -1) {
				parent.changePanel(
						new GUIKlantWeergeven(parent, zoekResultaat
								.get(zoekComboBox.getSelectedIndex())), 0);
			} else {
				try {
					String zoekTekst = (String) zoekComboBox.getEditor()
							.getItem();
					if (zoekTekst.length() == 0)
						zoekResultaat = controllerKlant.geefAlleKlanten();
					else if (zoekResultaat.isEmpty())
						zoekResultaat = new ArrayList<>();
					else
						zoekResultaat = zoekResultaat;
					if (zoekResultaat.size() == 0) {
						JOptionPane.showMessageDialog((JFrame) parent,
								"Geen klanten gevonden.");
					} else {
						parent.changePanel(new GUIKlantenOverzicht(parent,
								zoekResultaat), 0);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * zoekt verzekering die bij klant horen erbij.
	 */
	private class TxtVerzekeringZoekenKeyListener extends KeyAdapter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent arg0) {
			if (zoekTimer.isRunning()) {
				zoekTimer.restart();
			} else {
				zoekTimer.start();
			}
		}
	}

	/**
	 * Klant zoeken The Class ZoekActie.
	 */
	private class ZoekActie implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String zoekTekst = (String) zoekComboBox.getEditor().getItem();
				zoekResultaat = controllerKlant.zoekKlanten(zoekTekst);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

				zoekComboBox.getEditor().setItem(zoekTekst);
				if (zoekResultaat.size() > 0 && zoekTekst.length() > 2) {
					for (Klant klant : zoekResultaat) {
						model.addElement(klant.getVoornaam() + " "
								+ klant.getAchternaam());
					}
					zoekComboBox.setModel(model);
					zoekComboBox.showPopup();
				}
				zoekComboBox.setModel(model);
				zoekComboBox.getEditor().setItem(zoekTekst);
			} catch (IOException ex) {

			}
		}
	}
}