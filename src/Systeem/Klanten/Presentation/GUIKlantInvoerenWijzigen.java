/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Klanten.Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Klanten.Businesslogic.ControllerKlant;
import Systeem.Main.TextValidatieManager;
import Systeem.Main.TextValidatieManager.ValidatieType;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;

// TODO: Auto-generated Javadoc
/**
 * @author Gregor
 * 
 *         De klasse GUIKlantInvoerenWijzigen.
 */
public class GUIKlantInvoerenWijzigen extends JPanel {

	/** The naam text veld. */
	private JTextField naamTextVeld;

	/** The achternaam text veld. */
	private JTextField achternaamTextVeld;

	/** The bsn text veld. */
	private JTextField bsnTextVeld;

	/** The adres text veld. */
	private JTextField adresTextVeld;

	/** The postcode text veld. */
	private JTextField postcodeTextVeld;

	/** The plaats text veld. */
	private JTextField plaatsTextVeld;

	/** The geboortedatum text veld. */
	private JFormattedTextField geboortedatumTextVeld;

	/** The telefoonnummer text veld. */
	private JTextField telefoonnummerTextVeld;

	/** The email text veld. */
	private JTextField emailTextVeld;

	/** The factuur ontvangst combo box. */
	private JComboBox<String> factuurOntvangstComboBox;

	/** The automatisch incasso combo box. */
	private JComboBox<String> automatischIncassoComboBox;

	/** The verzekerings maatschapij combo box. */
	private JComboBox<Verzekeringsmaatschappij> verzekeringsMaatschapijComboBox;

	/** The verzekering lijst_1. */
	private JList<Verzekering> verzekeringLijst_1;

	/** The parent. */
	private ParentFrame parent;

	/** The maatschappijen. */
	private List<Verzekeringsmaatschappij> maatschappijen;

	/** The klant. */
	private Klant klant;

	/** The controller klant. */
	private ControllerKlant controllerKlant;

	/** The controller verzekering. */
	private ControllerVerzekeringen controllerVerzekering;

	/** The controller verzekering maatschappijen. */
	private ControllerVerzekeringMaatschappijen controllerVerzekeringMaatschappijen;

	/** The validatie manager. */
	private TextValidatieManager validatieManager;

	/** The Constant EIGENRISICO. */
	private static final double EIGENRISICO = 350;

	/** The geslacht combobox. */
	private JComboBox<String> geslachtCombobox;

	/**
	 * Create the panel.
	 *
	 * @param parent
	 *            the parent
	 * @wbp.parser.constructor
	 */
	public GUIKlantInvoerenWijzigen(ParentFrame parent) {
		setSize(750, 550);
		this.parent = parent;
		try {
			this.controllerKlant = new ControllerKlant();
			this.controllerVerzekering = new ControllerVerzekeringen();
			this.controllerVerzekeringMaatschappijen = new ControllerVerzekeringMaatschappijen();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		maatschappijen = controllerVerzekeringMaatschappijen
				.geefVerzekeringsmaatschappijen();
		init();
	}

	/**
	 * Instantiates a new GUI klant invoeren wijzigen.
	 *
	 * @param parent
	 *            the parent
	 * @param klantInfo
	 *            the klant info
	 */
	public GUIKlantInvoerenWijzigen(ParentFrame parent, Klant klantInfo) {
		setSize(750, 550);
		this.parent = parent;
		this.klant = klantInfo;
		try {
			this.controllerKlant = new ControllerKlant();
			this.controllerVerzekering = new ControllerVerzekeringen();
			this.controllerVerzekeringMaatschappijen = new ControllerVerzekeringMaatschappijen();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		maatschappijen = controllerVerzekeringMaatschappijen
				.geefVerzekeringsmaatschappijen();
		init();

		naamTextVeld.setText(klantInfo.getVoornaam());
		achternaamTextVeld.setText(klantInfo.getAchternaam());
		adresTextVeld.setText(klantInfo.getAdres());
		postcodeTextVeld.setText(klantInfo.getPostcode());
		plaatsTextVeld.setText(klantInfo.getPlaats());
		bsnTextVeld.setText(klantInfo.getBsn());
		bsnTextVeld.setEditable(false);
		bsnTextVeld.setEnabled(false);
		geboortedatumTextVeld.setText(new SimpleDateFormat("dd-MM-yyyy")
				.format(klantInfo.getGeboortedatum()));
		emailTextVeld.setText(klantInfo.getEmail());
		factuurOntvangstComboBox.setSelectedItem(klantInfo
				.getFactuurOntvangst() ? "Ja" : "Nee");
		automatischIncassoComboBox.setSelectedItem(klantInfo
				.getAutomatischIncasso() ? "Ja" : "Nee");
		telefoonnummerTextVeld.setText(klantInfo.getTelefoonnummer());
		geslachtCombobox.setSelectedItem(klantInfo.getGeslacht());
	}

	/**
	 * Inits the.
	 */
	public void init() {
		List<Verzekering> verzekeringen;
		try {
			if (maatschappijen.isEmpty())
				verzekeringen = new ArrayList<>();
			else
				verzekeringen = controllerVerzekering
						.zoekVerzekeringenVanMaatschappij(maatschappijen.get(0));
		} catch (IOException e) {
			e.printStackTrace();
			verzekeringen = new ArrayList<>();
		}
		setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		this.add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTerug = new JButton("Annuleren");
		btnTerug.setVisible(false);
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		JPanel panel_content = new JPanel();
		add(panel_content);
		panel_content.setLayout(new MigLayout("", "[200][grow][grow][250]",
				"[][][][][][][][][][][][][grow][]"));

		JPanel panel = new JPanel();
		panel_content.add(panel, "cell 3 0 1 13,alignx left,growy");
		panel.setLayout(new MigLayout("", "[222px]",
				"[14px][27px][14px][246px]"));

		JLabel lblVerzekeringMaatschappij = new JLabel(
				"Verzekering Maatschappij:");
		panel.add(lblVerzekeringMaatschappij, "cell 0 0,grow");

		// verzekeringsMaatschapijComboBox = new JComboBox<>();
		verzekeringsMaatschapijComboBox = new JComboBox<>(
				maatschappijen
						.toArray(new Verzekeringsmaatschappij[maatschappijen
								.size()]));
		verzekeringsMaatschapijComboBox
				.addActionListener(new VerzekeringsMaatschapijComboBoxActionListener());
		panel.add(verzekeringsMaatschapijComboBox, "cell 0 1,grow");

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 3,grow");

		JLabel lblVerzekering = new JLabel("Verzekering:");
		panel.add(lblVerzekering, "cell 0 2,alignx left,growy");
		// verzekeringLijst_1 = new JList<>();
		verzekeringLijst_1 = new JList<>(
				verzekeringen.toArray(new Verzekering[verzekeringen.size()]));
		verzekeringLijst_1
				.addMouseListener(new VerzekeringLijst_1MouseListener());
		scrollPane.setViewportView(verzekeringLijst_1);

		JLabel lblNaam = new JLabel("Naam:");
		panel_content.add(lblNaam, "cell 0 0,alignx right,aligny center");
		lblNaam.setHorizontalAlignment(SwingConstants.TRAILING);

		naamTextVeld = new JTextField();
		panel_content.add(naamTextVeld, "cell 1 0 2 1,growx,aligny top");
		naamTextVeld.setColumns(10);

		JLabel lblAchternaam = new JLabel("Achternaam:");
		panel_content.add(lblAchternaam, "cell 0 1,alignx right,aligny center");
		lblAchternaam.setHorizontalAlignment(SwingConstants.TRAILING);

		achternaamTextVeld = new JTextField();
		panel_content.add(achternaamTextVeld, "cell 1 1 2 1,growx,aligny top");
		achternaamTextVeld.setColumns(10);

		JLabel lblGeslacht = new JLabel("Geslacht:");
		panel_content.add(lblGeslacht, "cell 0 2,alignx trailing");

		geslachtCombobox = new JComboBox<>();
		geslachtCombobox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Man", "Vrouw" }));
		panel_content.add(geslachtCombobox, "cell 1 2 2 1,growx");

		JLabel lblAdres = new JLabel("Adres:");
		panel_content.add(lblAdres, "cell 0 3,alignx right,aligny center");
		lblAdres.setHorizontalAlignment(SwingConstants.TRAILING);

		adresTextVeld = new JTextField();
		panel_content.add(adresTextVeld, "cell 1 3 2 1,growx,aligny top");
		adresTextVeld.setColumns(10);

		JLabel lblBsn = new JLabel("BSN:");
		panel_content.add(lblBsn, "cell 0 4,alignx right,aligny center");
		lblBsn.setHorizontalAlignment(SwingConstants.TRAILING);

		bsnTextVeld = new JTextField();
		panel_content.add(bsnTextVeld, "cell 1 4 2 1,growx,aligny top");
		bsnTextVeld.setColumns(10);

		JLabel lblPostcode = new JLabel("Postcode:");
		panel_content.add(lblPostcode, "cell 0 5,alignx right,aligny center");
		lblPostcode.setHorizontalAlignment(SwingConstants.TRAILING);

		postcodeTextVeld = new JTextField();
		panel_content.add(postcodeTextVeld, "cell 1 5 2 1,growx,aligny top");
		postcodeTextVeld.setColumns(10);

		JLabel lblPlaats = new JLabel("Plaats:");
		panel_content.add(lblPlaats, "cell 0 6,alignx right,aligny center");
		lblPlaats.setHorizontalAlignment(SwingConstants.TRAILING);

		plaatsTextVeld = new JTextField();
		panel_content.add(plaatsTextVeld, "cell 1 6 2 1,growx,aligny top");
		plaatsTextVeld.setColumns(10);

		JLabel lblTelefoonnummer = new JLabel("Telefoonnummer:");
		panel_content.add(lblTelefoonnummer,
				"cell 0 7,alignx right,aligny center");
		lblTelefoonnummer.setHorizontalAlignment(SwingConstants.TRAILING);

		telefoonnummerTextVeld = new JTextField();
		panel_content.add(telefoonnummerTextVeld, "cell 1 7 2 1,grow");
		telefoonnummerTextVeld.setColumns(10);

		JLabel lblGeboortedatum = new JLabel("Geboortedatum:");
		panel_content.add(lblGeboortedatum,
				"cell 0 8,alignx right,aligny center");
		lblGeboortedatum.setHorizontalAlignment(SwingConstants.TRAILING);

		geboortedatumTextVeld = new JFormattedTextField();
		panel_content.add(geboortedatumTextVeld,
				"cell 1 8 2 1,growx,aligny top");
		geboortedatumTextVeld.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		panel_content.add(lblEmail, "cell 0 9,alignx right,aligny center");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);

		emailTextVeld = new JTextField();
		panel_content.add(emailTextVeld, "cell 1 9 2 1,growx,aligny top");
		emailTextVeld.setColumns(10);

		JLabel lblEmail_1 = new JLabel("Factuurontvangst:");
		panel_content.add(lblEmail_1, "cell 0 10,alignx right,aligny center");
		lblEmail_1.setHorizontalAlignment(SwingConstants.TRAILING);

		factuurOntvangstComboBox = new JComboBox<String>();
		panel_content.add(factuurOntvangstComboBox,
				"cell 1 10 2 1,growx,aligny center");
		factuurOntvangstComboBox.addItem("Per post");
		factuurOntvangstComboBox.addItem("Per e-mail");
		factuurOntvangstComboBox.setToolTipText("");

		JLabel lblAutomtischIncasso = new JLabel("Automtisch Incasso:");
		panel_content.add(lblAutomtischIncasso,
				"cell 0 11,alignx right,aligny center");
		lblAutomtischIncasso.setHorizontalAlignment(SwingConstants.TRAILING);

		automatischIncassoComboBox = new JComboBox<String>();
		panel_content.add(automatischIncassoComboBox,
				"cell 1 11 2 1,growx,aligny center");

		JButton btnAnnulerenButton = new JButton("Annuleren");
		panel_menu.add(btnAnnulerenButton);
		btnAnnulerenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int userInput = JOptionPane
						.showConfirmDialog(
								GUIKlantInvoerenWijzigen.this,
								"Weet u zeker dat u terug wilt?\nDe informatie in de ingevulde velden zal verloren gaan",
								"Annuleren", JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {
					if (klant == null) {
						System.out.println("ga terug naar zoeken");
						parent.changePanel(new GUIKlantZoeken(parent), 0);
					} else {
						System.out.println("ga terug naar weergeven");
						parent.changePanel(
								new GUIKlantWeergeven(parent, klant), 0);
					}
				}
			}
		});

		JButton btnOpslaanButton = new JButton("Opslaan");
		panel_content.add(btnOpslaanButton, "cell 1 13,alignx right");
		btnOpslaanButton.addActionListener(new OpslaanActie(this) {

		});
		automatischIncassoComboBox.addItem("Ja");
		automatischIncassoComboBox.addItem("Nee");

		validatieManager = new TextValidatieManager();
		validatieManager.addValidatie(naamTextVeld, true, 100,
				ValidatieType.TEKST, "naam");
		validatieManager.addValidatie(achternaamTextVeld, true, 100,
				ValidatieType.TEKST, "omschrijving");
		validatieManager.addValidatie(adresTextVeld, true, 100,
				ValidatieType.TEKST, "adres");
		validatieManager.addValidatie(postcodeTextVeld, true, 100,
				ValidatieType.POSTCODE, "postcode");
		validatieManager.addValidatie(plaatsTextVeld, true, 100,
				ValidatieType.TEKST, "plaats");
		validatieManager.addValidatie(geboortedatumTextVeld, true, 100,
				ValidatieType.DATUM, "geboortedatum");
		validatieManager.addValidatie(telefoonnummerTextVeld, true, 100,
				ValidatieType.TELEFOONNUMMER, "telefoonnummer");
		validatieManager.addValidatie(emailTextVeld, true, 100,
				ValidatieType.EMAIL, "email");
		validatieManager.addValidatie(bsnTextVeld, true, 100,
				ValidatieType.BSN, "email");
	}

	/**
	 * The listener interface for receiving
	 * verzekeringsMaatschapijComboBoxAction events. The class that is
	 * interested in processing a verzekeringsMaatschapijComboBoxAction event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addVerzekeringsMaatschapijComboBoxActionListener<code> method. When
	 * the verzekeringsMaatschapijComboBoxAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see VerzekeringsMaatschapijComboBoxActionEvent
	 */
	private class VerzekeringsMaatschapijComboBoxActionListener implements
			ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent arg0) {
			Verzekeringsmaatschappij maatschappij = (Verzekeringsmaatschappij) verzekeringsMaatschapijComboBox
					.getSelectedItem();
			DefaultListModel<Verzekering> verzekeringModel = new DefaultListModel<>();

			try {
				for (Verzekering verzekering : controllerVerzekering
						.zoekVerzekeringenVanMaatschappij(maatschappij)) {
					verzekeringModel.addElement(verzekering);
				}
			} catch (IOException ex) {

			}

			verzekeringLijst_1.setModel(verzekeringModel);
		}
	}

	/**
	 * The listener interface for receiving verzekeringLijst_1Mouse events. The
	 * class that is interested in processing a verzekeringLijst_1Mouse event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addVerzekeringLijst_1MouseListener<code> method. When
	 * the verzekeringLijst_1Mouse event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see VerzekeringLijst_1MouseEvent
	 */
	private class VerzekeringLijst_1MouseListener extends MouseAdapter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			int[] selectedLines = verzekeringLijst_1.getSelectedIndices();

			if (selectedLines.length > 0) {
				if (selectedLines[0] != 0) {
					int[] basisSelectie = new int[] { 0 };
					int[] tempArray = new int[selectedLines.length
							+ basisSelectie.length];
					System.arraycopy(basisSelectie, 0, tempArray, 0,
							basisSelectie.length);
					System.arraycopy(selectedLines, 0, tempArray,
							basisSelectie.length, selectedLines.length);
					selectedLines = tempArray;
				}
			}
			verzekeringLijst_1.setSelectedIndices(selectedLines);

		}
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
		public void actionPerformed(ActionEvent e) {
			if (klant == null) {
				parent.changePanel(new GUIKlantZoeken(parent), 0);
			} else {
				parent.changePanel(new GUIKlantWeergeven(parent, klant), 0);
			}
		}
	}

	/**
	 * Haal geselecteerde verzekeringen op.
	 *
	 * @return vv
	 */
	private List<Verzekering> haalGeselecteerdeVerzekeringenOp() {
		List<Verzekering> verzekeringen = verzekeringLijst_1
				.getSelectedValuesList();
		return verzekeringen;
	}

	/**
	 * The Class OpslaanActie.
	 */
	private class OpslaanActie implements ActionListener {

		/** The parent panel. */
		JPanel parentPanel;

		/**
		 * Instantiates a new opslaan actie.
		 *
		 * @param parentPanel
		 *            the parent panel
		 */
		public OpslaanActie(JPanel parentPanel) {
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

			try {

				if (validatieManager.valideer()) {
					List<Verzekering> verzekeringlijst = verzekeringLijst_1
							.getSelectedIndices().length > 0 ? haalGeselecteerdeVerzekeringenOp()
							: new ArrayList<Verzekering>();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date geboorteDatum = dateFormat.parse(geboortedatumTextVeld
							.getText());

					boolean automatischIncasso = automatischIncassoComboBox
							.getSelectedItem().toString().equals("Ja") ? true
							: false;
					boolean factuurOntvangst = factuurOntvangstComboBox
							.getSelectedItem().toString().equals("Ja") ? true
							: false;

					if (klant == null) {
						if (controllerKlant.geefKlant(bsnTextVeld.getText()) == null) {
							klant = new Klant(bsnTextVeld.getText(),
									naamTextVeld.getText(),
									achternaamTextVeld.getText(),
									adresTextVeld.getText(),
									postcodeTextVeld.getText(),
									plaatsTextVeld.getText(), geboorteDatum,
									telefoonnummerTextVeld.getText(),
									emailTextVeld.getText(), EIGENRISICO,
									EIGENRISICO, automatischIncasso,
									verzekeringlijst, factuurOntvangst,
									(String) geslachtCombobox.getSelectedItem());
							controllerKlant.klantOpslaan(klant);
						} else {
							JOptionPane
									.showMessageDialog(
											GUIKlantInvoerenWijzigen.this,
											"Kan klant niet toevoegen\nHet BSN nummer("
													+ bsnTextVeld.getText()
													+ ") is al bekend in het systeem",
											"BSN bestaat al",
											JOptionPane.ERROR_MESSAGE);
							return;
						}
					} else {
						klant.setBsn(bsnTextVeld.getText());
						klant.setVoornaam(naamTextVeld.getText());
						klant.setAchternaam(achternaamTextVeld.getText());
						klant.setAdres(adresTextVeld.getText());
						klant.setPostcode(postcodeTextVeld.getText());
						klant.setPlaats(plaatsTextVeld.getText());
						klant.setGeboortedatum(geboorteDatum);
						klant.setTelefoonnummer(telefoonnummerTextVeld
								.getText());
						klant.setEmail(emailTextVeld.getText());
						klant.setRestantEigenRisico(klant
								.getRestantEigenRisico());
						klant.setAutomatischIncasso(automatischIncasso);
						klant.setVerzekeringen(verzekeringlijst.size() < 1 ? klant
								.getVerzekeringen() : verzekeringlijst);
						klant.setFactuurOntvangst(factuurOntvangst);
						klant.setGeslacht((String) geslachtCombobox
								.getSelectedItem());
						controllerKlant.klantOpslaan(klant);
					}

					parent.changePanel(new GUIKlantWeergeven(parent, klant), 0);
				} else {
					validatieManager.showPopup(parentPanel);
				}

				return;
			} catch (ParseException ex) {
				return;
			} catch (IOException ex) {

			}
		}
	}
}