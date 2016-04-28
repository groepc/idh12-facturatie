/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

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
import Systeem.BusinessDomain.Verzekering;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt het zoekscherm waarmee een verzekering gezocht kan
 * worden.
 * 
 * @author Gregor
 */
@SuppressWarnings("serial")
public class GUIVerzekeringZoeken extends JPanel {

	/** The txt verzekering zoeken. */
	private JComboBox<String> txtVerzekeringZoeken;

	/** The lbl verzekering zoeken. */
	private JLabel lblVerzekeringZoeken;

	/** The btn verzekering zoeken. */
	private JButton btnVerzekeringZoeken;

	/** The controller. */
	private ControllerVerzekeringen controller;

	/** The parent. */
	private ParentFrame parent;

	/** The btn verzekeringmaatschappij overzicht. */
	private JButton btnVerzekeringmaatschappijOverzicht;
	private JPanel panel_toeveogKnop;
	private JButton btnVerzekeringToevoegen;
	private JPanel panel_zoekVenster;
	private JPanel panelZoekInput;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component horizontalStrut;

	private List<Verzekering> zoekResultaat;

	private Timer zoekTimer;

	/**
	 * Maakt een nieuw zoekscherm aan.
	 *
	 * @param parent
	 *            the parent
	 */
	public GUIVerzekeringZoeken(ParentFrame parent) {

		zoekResultaat = new ArrayList<>();

		try {
			controller = new ControllerVerzekeringen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.parent = parent;

		setMinimumSize(VerzekeringSettings.panelSize);
		setPreferredSize(VerzekeringSettings.panelSize);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_content = new JPanel();
		add(panel_content, BorderLayout.CENTER);

		panel_content.setLayout(new MigLayout("", "[150][grow][150]",
				"[40%][][33%,grow]"));

		panel_zoekVenster = new JPanel();
		panel_content.add(panel_zoekVenster, "cell 1 1,growx");
		panel_zoekVenster.setLayout(new BoxLayout(panel_zoekVenster,
				BoxLayout.Y_AXIS));

		lblVerzekeringZoeken = new JLabel("verzekering zoeken");
		lblVerzekeringZoeken.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblVerzekeringZoeken.setMaximumSize(new Dimension(20000, 16));
		panel_zoekVenster.add(lblVerzekeringZoeken);
		lblVerzekeringZoeken.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerzekeringZoeken.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblVerzekeringZoeken.setBorder(new MatteBorder(0, 0, 1, 0,
				(Color) new Color(0, 0, 0)));

		verticalStrut = Box.createVerticalStrut(5);
		panel_zoekVenster.add(verticalStrut);

		panelZoekInput = new JPanel();
		panel_zoekVenster.add(panelZoekInput);
		panelZoekInput
				.setLayout(new BoxLayout(panelZoekInput, BoxLayout.X_AXIS));

		txtVerzekeringZoeken = new JComboBox<>();
		txtVerzekeringZoeken.getEditor().getEditorComponent()
				.addKeyListener(new TxtVerzekeringZoekenKeyListener());
		txtVerzekeringZoeken.setEditable(true);
		panelZoekInput.add(txtVerzekeringZoeken);
		txtVerzekeringZoeken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		horizontalStrut = Box.createHorizontalStrut(4);
		panelZoekInput.add(horizontalStrut);

		btnVerzekeringZoeken = new JButton("zoeken");
		panelZoekInput.add(btnVerzekeringZoeken);
		btnVerzekeringZoeken
				.addActionListener(new BtnVerzekeringZoekenActionListener());
		btnVerzekeringZoeken.setFont(new Font("Tahoma", Font.PLAIN, 21));

		btnVerzekeringmaatschappijOverzicht = new JButton(
				"Verzekering overzicht");
		btnVerzekeringmaatschappijOverzicht
				.addActionListener(new BtnVerzekeringmaatschappijOverzichtActionListener());
		panel_menu.add(btnVerzekeringmaatschappijOverzicht);

		verticalStrut_1 = Box.createVerticalStrut(30);
		panel_menu.add(verticalStrut_1);

		panel_toeveogKnop = new JPanel();
		add(panel_toeveogKnop, BorderLayout.SOUTH);

		btnVerzekeringToevoegen = new JButton("Verzekering toevoegen");
		btnVerzekeringToevoegen
				.addActionListener(new BtnVerzekeringToevoegenActionListener());
		panel_toeveogKnop.add(btnVerzekeringToevoegen);
		zoekTimer = new Timer(500, new zoekActie());
		zoekTimer.setRepeats(false);
	}

	/**
	 * Zoekt een verzekering op die voldoet aan de ingevoerde gegevens.
	 */
	public void zoekVerzekering() {
		/*
		 * if (!txtVerzekeringZoeken.getText().isEmpty()) { List<Verzekering>
		 * verzekeringen =
		 * controller.zoekVerzekeringen(txtVerzekeringZoeken.getText()); if
		 * (!verzekeringen.isEmpty()) { parent.changePanel(new
		 * GUIVerzekeringOverzicht(parent, verzekeringen),
		 * VerzekeringSettings.verzekeringTabNummer); } else {
		 * JOptionPane.showMessageDialog(this, "Geen verzekeringen gevonden"); }
		 * }
		 */
	}

	/**
	 * The listener interface for receiving btnVerzekeringZoekenAction events.
	 * The class that is interested in processing a btnVerzekeringZoekenAction
	 * event implements this interface, and the object created with that class
	 * is registered with a component using the component's
	 * <code>addBtnVerzekeringZoekenActionListener<code> method. When
	 * the btnVerzekeringZoekenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnVerzekeringZoekenActionEvent
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
			System.out.println(txtVerzekeringZoeken.getSelectedIndex());
			if (txtVerzekeringZoeken.getSelectedIndex() != -1) {
				parent.changePanel(
						new GUIVerzekeringWeergeven(parent, zoekResultaat
								.get(txtVerzekeringZoeken.getSelectedIndex())),
						VerzekeringSettings.verzekeringTabNummer);
			} else {
				try {
					String zoekTekst = (String) txtVerzekeringZoeken
							.getEditor().getItem();
					if (zoekTekst.length() == 0)
						zoekResultaat = controller.haalAlleVerzekeringenOp();
					else if (zoekResultaat.isEmpty())
						zoekResultaat = new ArrayList<>();
					else
						zoekResultaat = zoekResultaat;
					if (zoekResultaat.size() == 0) {
						JOptionPane.showMessageDialog((JFrame) parent,
								"Geen verzekeringen gevonden.");
					} else {
						parent.changePanel(new GUIVerzekeringOverzicht(parent,
								zoekResultaat),
								VerzekeringSettings.verzekeringTabNummer);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * The listener interface for receiving
	 * btnVerzekeringmaatschappijOverzichtAction events. The class that is
	 * interested in processing a btnVerzekeringmaatschappijOverzichtAction
	 * event implements this interface, and the object created with that class
	 * is registered with a component using the component's
	 * <code>addBtnVerzekeringmaatschappijOverzichtActionListener<code> method. When
	 * the btnVerzekeringmaatschappijOverzichtAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnVerzekeringmaatschappijOverzichtActionEvent
	 */
	private class BtnVerzekeringmaatschappijOverzichtActionListener implements
			ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent arg0) {
			parent.changePanel(new GUIVerzekeringOverzicht(parent),
					VerzekeringSettings.verzekeringTabNummer);
		}
	}

	/**
	 * Button verzerkering toevoegen
	 */
	private class BtnVerzekeringToevoegenActionListener implements
			ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new GUIVerzekeringInvoerenWijzigen(parent),
					VerzekeringSettings.verzekeringTabNummer);
		}
	}

	/**
	 * Tekstveld verzekering zoeken
	 */
	private class TxtVerzekeringZoekenKeyListener extends KeyAdapter {
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
	 * Zoeken naar verzekering
	 */
	private class zoekActie implements ActionListener {

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
				String zoekTekst = (String) txtVerzekeringZoeken.getEditor()
						.getItem();
				zoekResultaat = controller.zoekVerzekeringen(zoekTekst);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

				if (zoekResultaat.size() > 0 && zoekTekst.length() > 2) {
					for (Verzekering verzekering : zoekResultaat) {
						model.addElement(verzekering.getNaam());
					}
					txtVerzekeringZoeken.setModel(model);
					txtVerzekeringZoeken.showPopup();
				}
				txtVerzekeringZoeken.setModel(model);
				txtVerzekeringZoeken.getEditor().setItem(zoekTekst);
			} catch (IOException ex) {

			}
		}
	}
}