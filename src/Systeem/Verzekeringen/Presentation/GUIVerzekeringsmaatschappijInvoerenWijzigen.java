/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Main.TextValidatieManager;
import Systeem.Main.TextValidatieManager.ValidatieType;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;

// TODO: Auto-generated Javadoc
/**
 * Verzorgt het aanmaak- of wijzigingsscherm van een verzekeringmaatschappij
 * aan.
 * 
 * @author Gregor
 */
public class GUIVerzekeringsmaatschappijInvoerenWijzigen extends JPanel {

	/** The parent. */
	private ParentFrame parent;

	/** The txt naam. */
	private JTextField txtNaam;

	/** The txt adres. */
	private JTextField txtAdres;

	/** The txt postcode. */
	private JTextField txtPostcode;

	/** The txt plaats. */
	private JTextField txtPlaats;

	/** The txt telefoonnummer. */
	private JTextField txtTelefoonnummer;

	/** The txt kvk nr. */
	private JTextField txtKvkNr;

	/** The txt iban. */
	private JTextField txtIban;

	/** The maatschappij. */
	private Verzekeringsmaatschappij maatschappij;

	/** The controller maatschappijen. */
	private ControllerVerzekeringMaatschappijen controllerMaatschappijen;

	/** The txt btw. */
	private JTextField txtBtw;

	/** The lbl verzekering modus. */
	private JLabel lblVerzekeringModus;

	/** The validatie manager. */
	private TextValidatieManager validatieManager;

	/**
	 * Maakt het verzekeringsmaatschappij invoerscherm aan.
	 *
	 * @param parent
	 *            the parent
	 * @wbp.parser.constructor
	 */
	public GUIVerzekeringsmaatschappijInvoerenWijzigen(ParentFrame parent) {
		this.maatschappij = null;
		this.parent = parent;

		try {
			this.controllerMaatschappijen = new ControllerVerzekeringMaatschappijen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}

	/**
	 * Maakt het verzekeringsmaatschappij wijzigingsscherm aan.
	 *
	 * @param parent
	 *            the parent
	 * @param verzekeringsmaatschappij
	 *            De verzekeringsmaatschappij die gewijzigd wordt.
	 */
	public GUIVerzekeringsmaatschappijInvoerenWijzigen(ParentFrame parent, Verzekeringsmaatschappij verzekeringsmaatschappij) {
		this(parent);
		this.maatschappij = verzekeringsmaatschappij;
		txtNaam.setText(maatschappij.getNaam());
		txtAdres.setText(maatschappij.getAdres());
		txtPostcode.setText(maatschappij.getPostcode());
		txtPlaats.setText(maatschappij.getPlaats());
		txtTelefoonnummer.setText(maatschappij.getTelefoonnummer());
		txtKvkNr.setText(maatschappij.getKvkNr());
		txtKvkNr.setEditable(false);
		txtKvkNr.setEnabled(false);
		txtIban.setText(maatschappij.getIban());
		txtBtw.setText(maatschappij.getBtwNr());
		lblVerzekeringModus.setText("Verzekeringmaatschappij " + verzekeringsmaatschappij.getNaam() + " wijzigen.");
	}

	/**
	 * Inits the.
	 */
	private void init() {
		setMinimumSize(VerzekeringSettings.panelSize);
		setPreferredSize(VerzekeringSettings.panelSize);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTerug = new JButton("Terug");
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		JPanel panel_content = new JPanel();
		add(panel_content, BorderLayout.CENTER);
		panel_content.setLayout(new MigLayout("", "[:150:150,right]1%[grow,center]1%[150:150,center]", "[25,top][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][grow][grow,bottom]"));

		lblVerzekeringModus = new JLabel();
		panel_content.add(lblVerzekeringModus, "cell 0 0 3 1,alignx center");

		JLabel lblNaam = new JLabel("Naam");
		panel_content.add(lblNaam, "cell 0 1,alignx trailing");

		txtNaam = new JTextField();
		panel_content.add(txtNaam, "cell 1 1,growx");
		txtNaam.setColumns(10);

		JLabel lblAdres = new JLabel("Adres");
		panel_content.add(lblAdres, "cell 0 2,alignx trailing");

		txtAdres = new JTextField();
		panel_content.add(txtAdres, "cell 1 2,growx");
		txtAdres.setColumns(10);

		JLabel lblPostcode = new JLabel("Postcode");
		panel_content.add(lblPostcode, "cell 0 3,alignx trailing");

		txtPostcode = new JTextField();
		panel_content.add(txtPostcode, "cell 1 3,growx");
		txtPostcode.setColumns(10);

		JLabel lblPlaats = new JLabel("Plaats");
		panel_content.add(lblPlaats, "cell 0 4,alignx trailing");

		txtPlaats = new JTextField();
		txtPlaats.setText("");
		panel_content.add(txtPlaats, "cell 1 4,growx");
		txtPlaats.setColumns(10);

		JLabel lblTelefoonnummer = new JLabel("Telefoonnummer");
		panel_content.add(lblTelefoonnummer, "cell 0 5,alignx trailing");

		txtTelefoonnummer = new JTextField();
		panel_content.add(txtTelefoonnummer, "cell 1 5,growx");
		txtTelefoonnummer.setColumns(10);

		JLabel lblKvkNr = new JLabel("Kvk nr");
		panel_content.add(lblKvkNr, "cell 0 6,alignx trailing");

		txtKvkNr = new JTextField();
		panel_content.add(txtKvkNr, "cell 1 6,growx");
		txtKvkNr.setColumns(10);

		JLabel lblIbanNr = new JLabel("Iban nr");
		panel_content.add(lblIbanNr, "cell 0 7,alignx trailing");

		txtIban = new JTextField();
		panel_content.add(txtIban, "cell 1 7,growx");
		txtIban.setColumns(10);

		JButton btnOpslaan = new JButton("Opslaan");
		btnOpslaan.addActionListener(new BtnOpslaanActionListener(this));

		JLabel lblBtwNr = new JLabel("Btw nr");
		panel_content.add(lblBtwNr, "cell 0 8,alignx trailing");

		txtBtw = new JTextField();
		panel_content.add(txtBtw, "cell 1 8,growx");
		txtBtw.setColumns(10);
		panel_content.add(btnOpslaan, "cell 1 10");

		validatieManager = new TextValidatieManager();
		validatieManager.addValidatie(txtNaam, true, 100, ValidatieType.TEKST, "naam");
		validatieManager.addValidatie(txtAdres, true, 100, ValidatieType.TEKST, "adres");
		validatieManager.addValidatie(txtIban, true, 100, ValidatieType.IBAN, "iban");
		validatieManager.addValidatie(txtKvkNr, true, 100, ValidatieType.KVKNR, "kvknr");
		validatieManager.addValidatie(txtPlaats, true, 100, ValidatieType.TEKST, "plaats");
		validatieManager.addValidatie(txtPostcode, true, 100, ValidatieType.POSTCODE, "postcode");
		validatieManager.addValidatie(txtTelefoonnummer, true, 100, ValidatieType.TELEFOONNUMMER, "telefoonnummer");
		validatieManager.addValidatie(txtBtw, true, 100, ValidatieType.BTWNUMMER, "btw");
	}

	/**
	 * Maakt een verzekeringsmaatschappij met de ingevoerde gegevens.
	 */
	private void maakVerzekeringsmaatschappij() {

		if (controllerMaatschappijen.geefVerzekeringsmaatschappij(txtKvkNr.getText()) == null) {
			controllerMaatschappijen.verzekeringsmaatschappijToevoegen(new Verzekeringsmaatschappij(txtNaam.getText(), txtTelefoonnummer.getText(), txtAdres.getText(), txtPlaats.getText(), txtKvkNr.getText(), txtIban.getText(), txtPostcode.getText(), txtBtw.getText()));
			parent.changePanel(new GUIVerzekeringsmaatschappijOverzicht(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
		} else {
			JOptionPane.showMessageDialog(GUIVerzekeringsmaatschappijInvoerenWijzigen.this, "Kan de verzekeringmaatschapp niet toevoegen\nHet KvK nummer(" + txtKvkNr.getText() + ") is al bekend in het systeem", "KvK bestaat al", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Wijzigt een verzekeringsmaatschappij met de ingevooerde gegevens.
	 */
	private void wijzigVerzekeringsmaatschappij() {

		maatschappij.setNaam(txtNaam.getText());
		maatschappij.setAdres(txtAdres.getText());
		maatschappij.setPlaats(txtPlaats.getText());
		maatschappij.setPostcode(txtPostcode.getText());
		maatschappij.setPlaats(txtPlaats.getText());
		maatschappij.setIban(txtIban.getText());
		maatschappij.setTelefoonnummer(txtTelefoonnummer.getText());
		maatschappij.setBtwNr(txtBtw.getText());

		controllerMaatschappijen.verzekeringsmaatschappijWijzigen(maatschappij);
	}

	/**
	 * The listener interface for receiving btnTerugAction events. The class
	 * that is interested in processing a btnTerugAction event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addBtnTerugActionListener<code> method. When
	 * the btnTerugAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnTerugActionEvent
	 */
	private class BtnTerugActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			parent.changePanel(new GUIVerzekeringsmaatschappijOverzicht(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
		}

	}

	/**
	 * The listener interface for receiving btnOpslaanAction events. The class
	 * that is interested in processing a btnOpslaanAction event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addBtnOpslaanActionListener<code> method. When
	 * the btnOpslaanAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnOpslaanActionEvent
	 */
	private class BtnOpslaanActionListener implements ActionListener {

		/** The parent panel. */
		JPanel parentPanel;

		/**
		 * Instantiates a new btn opslaan action listener.
		 *
		 * @param parentPanel
		 *            the parent panel
		 */
		BtnOpslaanActionListener(JPanel parentPanel) {
			this.parentPanel = parentPanel;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			if (validatieManager.valideer()) {
				if (maatschappij == null) {
					maakVerzekeringsmaatschappij();
					return;
				} else {
					wijzigVerzekeringsmaatschappij();
				}
				parent.changePanel(new GUIVerzekeringsmaatschappijOverzicht(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
			} else {
				JOptionPane.showMessageDialog(parentPanel, "Er zijn enkele velden niet goed ingevuld, voor de exacte foutmeldingen zie de tooltip van het veld.");
			}
		}
	}
}

// TODO Valideer code schrijven.
