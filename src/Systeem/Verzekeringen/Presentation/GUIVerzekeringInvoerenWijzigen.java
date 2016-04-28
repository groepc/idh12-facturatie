/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Main.TextValidatieManager;
import Systeem.Main.TextValidatieManager.ValidatieType;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;
import Systeem.Verzekeringen.Presentation.customRenderers.BehandelingListRenderer;
import Systeem.Verzekeringen.Presentation.customRenderers.VerzekeringmaatschappijListRenderer;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt het aanmaak- of wijzigingsscherm voor verzekeringen.
 *
 * @author Gregor
 */
public class GUIVerzekeringInvoerenWijzigen extends javax.swing.JPanel
		implements BehandelingToevoegPanel {

	/** The txt_ naam. */
	private JTextField txt_Naam;

	/** The cmb_ verzekeraar. */
	private JComboBox<Verzekeringsmaatschappij> cmb_Verzekeraar;

	/** The txta_ omschrijving. */
	private JTextArea txta_Omschrijving;

	/** The lbl_ mode label. */
	private JLabel lbl_ModeLabel;

	/** The parent. */
	private ParentFrame parent;

	/** The controller verzekeringen. */
	private ControllerVerzekeringen controllerVerzekeringen;

	/** The controller maatschappijen. */
	private ControllerVerzekeringMaatschappijen controllerMaatschappijen;

	/** The controller behandelingen. */
	private ControllerBehandelTrajecten controllerBehandelingen;

	/** The verzekering. */
	private Verzekering verzekering;

	/** The list. */
	private JList<Behandelingtraject> list;

	/** The cmb_ type. */
	private JComboBox<String> cmb_Type;

	/** The btn toevoegen. */
	private JButton btnBehandelingToevoegen;

	/** The panel_behandeling card layout. */
	private JPanel panel_behandelingCardLayout;

	/** The Constant behandelingButtonPanelName. */
	private final static String behandelingButtonPanelName = "buttonPanel";

	/** The Constant behandelingTextPanelName. */
	private final static String behandelingTextPanelName = "textPanel";

	/** The lbl type aanvulled. */
	private JTextArea lblTypeAanvulled;

	/** The validatie manager. */
	private TextValidatieManager validatieManager;

	/**
	 * Instantiates a new GUI verzekering invoeren wijzigen.
	 *
	 * @param parent
	 *            the parent
	 * @wbp.parser.constructor
	 */
	public GUIVerzekeringInvoerenWijzigen(ParentFrame parent) {
		this.parent = parent;
		init();
		try {
			setVerzekeringType(bepaalTypeVerzekering(cmb_Verzekeraar.getModel()
					.getElementAt(cmb_Verzekeraar.getSelectedIndex())));
			lbl_ModeLabel.setText("Aanmaken van een nieuwe verzekering.");
		} catch (IOException ex) {
			parent.changePanel(new GUIVerzekeringOverzicht(parent),
					VerzekeringSettings.verzekeringTabNummer);
		}
	}

	/**
	 * Maakt een wijzigingsscherm aan en vult deze met de gegevens van een
	 * verzekering..
	 *
	 * @param parent
	 *            the parent
	 * @param verzekering
	 *            De verzekering die wordt gewijzigd.
	 */
	public GUIVerzekeringInvoerenWijzigen(ParentFrame parent,
			Verzekering verzekering) {

		this.verzekering = verzekering;
		this.parent = parent;
		this.init();

		cmb_Verzekeraar.setSelectedItem(verzekering
				.getVerzekeringmaatschappij());
		voegBehandelingenToe(verzekering.getGedekteBehandeltrajecten());
		setVerzekeringType(verzekering.getType());
		txt_Naam.setText(verzekering.getNaam());

		txta_Omschrijving.setText(verzekering.getOmschrijving());
		lbl_ModeLabel.setText("Wijzigen van verzekering '"
				+ verzekering.getNaam() + "' (id: " + verzekering.getId()
				+ ").");

	}

	/**
	 * Inits the.
	 *
	 * 
	 */
	private void init() {
		try {
			controllerVerzekeringen = new ControllerVerzekeringen();
			controllerMaatschappijen = new ControllerVerzekeringMaatschappijen();
			controllerBehandelingen = new ControllerBehandelTrajecten();
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							SwingUtilities.getRootPane(this),
							"Er is een fout opgetreden tijden het laden van de data, u wordt nu teruggestuurd naar het hoofdscherm.");
		}

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
		panel_content
				.setLayout(new MigLayout(
						"",
						"[150:150:150,right]1%[:100%:100%,fill]1%[150:150:150,center]",
						"[25,fill][25,fill][25,fill][25,fill][30%,grow][30%,grow,fill][20,fill]"));

		lbl_ModeLabel = new JLabel("Verzekering ...");
		panel_content.add(lbl_ModeLabel, "cell 1 0,alignx center");

		JLabel lbl_Naam = new JLabel("Naam");
		panel_content.add(lbl_Naam, "cell 0 1");

		txt_Naam = new JTextField();
		panel_content.add(txt_Naam, "cell 1 1,growx");
		txt_Naam.setColumns(10);

		JLabel lbl_Type = new JLabel("Type");
		panel_content.add(lbl_Type, "cell 0 2,alignx trailing");

		cmb_Type = new JComboBox<>();
		cmb_Type.setEnabled(false);
		cmb_Type.setModel(new DefaultComboBoxModel<>(
				VerzekeringSettings.verZekeringTypes));
		panel_content.add(cmb_Type, "cell 1 2,growx");

		JPanel panel_aanvulledType = new JPanel();
		panel_content.add(panel_aanvulledType, "flowx,cell 2 1 1 3,grow");
		panel_aanvulledType.setLayout(new BoxLayout(panel_aanvulledType,
				BoxLayout.Y_AXIS));

		Component verticalGlue_4 = Box.createVerticalGlue();
		panel_aanvulledType.add(verticalGlue_4);

		lblTypeAanvulled = new JTextArea("");
		lblTypeAanvulled.setRows(3);
		lblTypeAanvulled.setBorder(null);
		lblTypeAanvulled.setBackground(new Color(UIManager.getColor(
				"Panel.background").getRGB()));
		lblTypeAanvulled.setWrapStyleWord(true);
		panel_aanvulledType.add(lblTypeAanvulled);
		lblTypeAanvulled.setLineWrap(true);
		lblTypeAanvulled.setEditable(false);
		lblTypeAanvulled.setMaximumSize(new Dimension(500, 300));

		Component verticalGlue_5 = Box.createVerticalGlue();
		panel_aanvulledType.add(verticalGlue_5);

		JLabel lbl_Verzekeraar = new JLabel("Verzekeraar");
		panel_content.add(lbl_Verzekeraar, "cell 0 3");

		cmb_Verzekeraar = new JComboBox<>();
		cmb_Verzekeraar.addKeyListener(new Cmb_VerzekeraarKeyListener());
		cmb_Verzekeraar.addActionListener(new Cmb_VerzekeraarActionListener());
		// cmb_Verzekeraar.setModel(new DefaultComboBoxModel<>());
		cmb_Verzekeraar.setRenderer(new VerzekeringmaatschappijListRenderer());
		panel_content.add(cmb_Verzekeraar, "cell 1 3,growx");

		JLabel lblGedekteBehandelingen = new JLabel("Gedekte behandelingen");
		panel_content.add(lblGedekteBehandelingen, "cell 0 4");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		panel_content.add(scrollPane, "cell 1 4,grow");

		list = new JList<>();
		// list.setModel(new DefaultListModel<>());
		list.setCellRenderer(new BehandelingListRenderer());
		list.addKeyListener(new ListKeyListener());
		scrollPane.setViewportView(list);

		JLabel lbl_Omschrijving = new JLabel("Omschrijving");
		panel_content.add(lbl_Omschrijving, "cell 0 5");

		txta_Omschrijving = new JTextArea();
		txta_Omschrijving.setLineWrap(true);
		panel_content.add(txta_Omschrijving, "cell 1 5, wmin 0");

		JButton btn_Opslaan = new JButton("Opslaan");
		btn_Opslaan.addActionListener(new Btn_OpslaanActionListener());

		panel_behandelingCardLayout = new JPanel();
		panel_behandelingCardLayout.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_behandelingCardLayout.setMaximumSize(new Dimension(150, 150));
		panel_behandelingCardLayout.setBackground(UIManager
				.getColor("Panel.background"));
		panel_content.add(panel_behandelingCardLayout,
				"cell 2 4,alignx left,growy");
		panel_behandelingCardLayout.setLayout(new CardLayout(0, 0));

		JPanel panel_behandelingButtonPanel = new JPanel();
		panel_behandelingButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_behandelingButtonPanel.setBackground(UIManager
				.getColor("Panel.background"));
		panel_behandelingCardLayout.add(panel_behandelingButtonPanel,
				behandelingButtonPanelName);
		panel_behandelingButtonPanel.setLayout(new BoxLayout(
				panel_behandelingButtonPanel, BoxLayout.Y_AXIS));

		Component verticalGlue = Box.createVerticalGlue();
		panel_behandelingButtonPanel.add(verticalGlue);

		btnBehandelingToevoegen = new JButton("Toevoegen");
		btnBehandelingToevoegen
				.addActionListener(new BtnToevoegenActionListener());
		btnBehandelingToevoegen.setMaximumSize(new Dimension(100, 30));
		btnBehandelingToevoegen.setPreferredSize(new Dimension(100, 30));
		panel_behandelingButtonPanel.add(btnBehandelingToevoegen);

		JButton btnBehandelingVerwijderen = new JButton("Verwijderen");
		btnBehandelingVerwijderen
				.addActionListener(new BtnVerwijderenActionListener());
		btnBehandelingVerwijderen.setMaximumSize(new Dimension(100, 30));
		btnBehandelingVerwijderen.setPreferredSize(new Dimension(100, 30));
		panel_behandelingButtonPanel.add(btnBehandelingVerwijderen);

		JButton btnBehandelingReset = new JButton("Reset");
		btnBehandelingReset
				.addActionListener(new BtnBehandelingResetActionListener());
		btnBehandelingReset.setMaximumSize(new Dimension(100, 30));
		btnBehandelingReset.setPreferredSize(new Dimension(100, 30));
		btnBehandelingReset.setSize(new Dimension(100, 30));
		panel_behandelingButtonPanel.add(btnBehandelingReset);

		Component verticalGlue_1 = Box.createVerticalGlue();
		panel_behandelingButtonPanel.add(verticalGlue_1);

		JPanel panel_BasisText = new JPanel();
		panel_BasisText.setMaximumSize(new Dimension(200, 200));
		panel_behandelingCardLayout.add(panel_BasisText,
				behandelingTextPanelName);
		panel_BasisText.setLayout(new BoxLayout(panel_BasisText,
				BoxLayout.Y_AXIS));

		Component verticalGlue_3 = Box.createVerticalGlue();
		panel_BasisText.add(verticalGlue_3);

		JTextArea txtrDeBehandelingVan = new JTextArea();
		txtrDeBehandelingVan.setMinimumSize(new Dimension(150, 28));
		txtrDeBehandelingVan.setPreferredSize(new Dimension(150, 80));
		txtrDeBehandelingVan.setMaximumSize(new Dimension(150, 100));
		txtrDeBehandelingVan.setWrapStyleWord(true);
		txtrDeBehandelingVan.setLineWrap(true);
		txtrDeBehandelingVan
				.setText("De gedekte behandelingen van een basisverzekering zijn wettelijk vastgesteld, het is dan ook niet mogelijk deze aan te passen.");
		txtrDeBehandelingVan.setBorder(null);
		txtrDeBehandelingVan.setEditable(false);
		txtrDeBehandelingVan.setBackground(new Color(UIManager.getColor(
				"Panel.background").getRGB()));
		panel_BasisText.add(txtrDeBehandelingVan);

		Component verticalGlue_2 = Box.createVerticalGlue();
		panel_BasisText.add(verticalGlue_2);
		panel_content.add(btn_Opslaan, "cell 1 6");

		haalVerzekeraarsOp();
		resetBehandelingenLijst();

		validatieManager = new TextValidatieManager();
		validatieManager.addValidatie(txt_Naam, true, 100, ValidatieType.TEKST,
				"naam");
		validatieManager.addValidatie(txta_Omschrijving, true, 200,
				ValidatieType.TEKST, "omschrijving");
	}

	/**
	 * Haal verzekeraars op.
	 */
	private void haalVerzekeraarsOp() {
		DefaultComboBoxModel<Verzekeringsmaatschappij> model = (DefaultComboBoxModel<Verzekeringsmaatschappij>) cmb_Verzekeraar
				.getModel();
		List<Verzekeringsmaatschappij> verzekeraars = controllerMaatschappijen
				.geefVerzekeringsmaatschappijen();
		for (Verzekeringsmaatschappij verzekeraar : verzekeraars) {
			model.addElement(verzekeraar);
		}
	}

	/**
	 * Reset behandelingen lijst.
	 */
	private void resetBehandelingenLijst() {
		// list.setModel(new DefaultListModel<>());
	}

	/**
	 * Toon behandel toevoeg scherm.
	 */
	private void toonBehandelToevoegScherm() {
		DefaultListModel<Behandelingtraject> model = (DefaultListModel<Behandelingtraject>) list
				.getModel();
		GUIBehandelingZoeken behandelingZoeken = new GUIBehandelingZoeken(this,
				controllerBehandelingen, Collections.list(model.elements()));
		behandelingZoeken.pack();
		behandelingZoeken.setLocationRelativeTo((Component) parent);
		behandelingZoeken.setModal(true);
		behandelingZoeken.setVisible(true);
	}

	/**
	 * Checks if a insurancecompany already has a basis insurance and sets the
	 * new insurance type accordingly.
	 */
	private void setVerzekeringType(String verzekeringType) {
		CardLayout layout = (CardLayout) panel_behandelingCardLayout
				.getLayout();
		if (verzekeringType.equals("basis")) {
			cmb_Type.setSelectedIndex(0);
			lblTypeAanvulled
					.setText("Er is nog geen basisverzekering aangemaakt voor deze verzekeraar.");
			resetBehandelingenLijst();
			voegBehandelingenToe(controllerBehandelingen
					.haalBasisBehandelingenOp());
			layout.show(panel_behandelingCardLayout, behandelingTextPanelName);
		} else {
			cmb_Type.setSelectedIndex(1);
			lblTypeAanvulled
					.setText("Er is al een basisverzekering aangemaakt");
			layout.show(panel_behandelingCardLayout, behandelingButtonPanelName);
		}
		panel_behandelingCardLayout.revalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Verzekeringen.Presentation.BehandelingToevoegPanel#
	 * voegBehandelingenToe(java.util.List)
	 */
	public void voegBehandelingenToe(List<Behandelingtraject> behandelingen) {

		DefaultListModel<Behandelingtraject> model = (DefaultListModel<Behandelingtraject>) list
				.getModel();
		for (Behandelingtraject behandeling : behandelingen) {
			model.addElement(behandeling);
		}
	}

	private String bepaalTypeVerzekering(Verzekeringsmaatschappij maatschappij)
			throws IOException {
		return controllerVerzekeringen.zoekVerzekeringenVanMaatschappij(
				maatschappij).size() > 0 ? "Aanvullend" : "Basis";
	}

	/**
	 * The listener interface for receiving btn_OpslaanAction events. The class
	 * that is interested in processing a btn_OpslaanAction event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addBtn_OpslaanActionListener<code> method. When
	 * the btn_OpslaanAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Btn_OpslaanActionEvent
	 */
	private class Btn_OpslaanActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			if (validatieManager.valideer() && list.getModel().getSize() != 0) {
				try {
					DefaultListModel<Behandelingtraject> model = (DefaultListModel<Behandelingtraject>) list
							.getModel();
					if (verzekering == null) {
						verzekering = new Verzekering(txt_Naam.getText(),
								cmb_Type.getSelectedItem().toString(),
								txta_Omschrijving.getText(),
								controllerVerzekeringen.getLastId(),
								cmb_Verzekeraar.getItemAt(cmb_Verzekeraar
										.getSelectedIndex()),
								Collections.list(model.elements()));
						controllerVerzekeringen
								.verzekeringToeveoegen(verzekering);
					} else {
						verzekering.setNaam(txt_Naam.getText());
						verzekering
								.setOmschrijving(txta_Omschrijving.getText());
						verzekering.setType(cmb_Type.getSelectedItem()
								.toString());
						verzekering.setVerzekeringmaatschappij(cmb_Verzekeraar
								.getItemAt(cmb_Verzekeraar.getSelectedIndex()));
						verzekering.setGedekteBehandeltrajecten(Collections
								.list(model.elements()));
						controllerVerzekeringen
								.verzekeringWijzigen(verzekering);
					}
					parent.changePanel(new GUIVerzekeringOverzicht(parent,
							controllerVerzekeringen.haalAlleVerzekeringenOp()),
							VerzekeringSettings.verzekeringTabNummer);
				} catch (IOException ex) {
					JOptionPane
							.showMessageDialog(
									(JFrame) parent,
									"Er is een probleem met het opslaan van de gegevens in het verzekering xml bestand.");
				}
			} else {
				list.setBorder(list.getModel().getSize() == 0 ? new LineBorder(
						Color.RED) : new LineBorder(Color.BLACK));
				JOptionPane
						.showMessageDialog(
								(Component) e.getSource(),
								"Er zijn enkele velden niet goed ingevuld, voor de exacte foutmeldingen zie de tooltip van het veld.");
			}
		}
	}

	/**
	 * The listener interface for receiving btnTerugAction events. The class
	 * that is interested in processing a btnTerugAction event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addBtnTerugActionListener<code> method. When
	 * the btnTerugAction event occurs, that object's appropriate
	 * method is invoked
	 *
	 * @see BtnTerugActionEvent
	 */
	private class BtnTerugActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (verzekering == null) {
				parent.changePanel(new GUIVerzekeringZoeken(parent),
						VerzekeringSettings.verzekeringTabNummer);
			} else {
				parent.changePanel(new GUIVerzekeringWeergeven(parent,
						verzekering), VerzekeringSettings.verzekeringTabNummer);
			}
		}
	}

	/**
	 * The listener interface for receiving btnToevoegenAction events. The class
	 * that is interested in processing a btnToevoegenAction event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addBtnToevoegenActionListener<code> method. When
	 * the btnToevoegenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnToevoegenActionEvent
	 */
	private class BtnToevoegenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			toonBehandelToevoegScherm();
		}
	}

	/**
	 * The listener interface for receiving listKey events. The class that is
	 * interested in processing a listKey event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addListKeyListener<code> method. When
	 * the listKey event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ListKeyEvent
	 */
	private class ListKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (cmb_Type.getSelectedIndex() > 0) {
				if (list.getSelectedIndex() != -1) {
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						DefaultListModel<Behandelingtraject> model = (DefaultListModel<Behandelingtraject>) list
								.getModel();
						model.remove(list.getSelectedIndex());
					}
				}
			}
		}
	}

	/**
	 * Button behandelingreset The listener interface for receiving
	 * btnBehandelingResetAction events. The class that is interested in
	 * processing a btnBehandelingResetAction event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's
	 * <code>addBtnBehandelingResetActionListener<code> method. When
	 * the btnBehandelingResetAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnBehandelingResetActionEvent
	 */
	private class BtnBehandelingResetActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			resetBehandelingenLijst();
		}
	}

	/**
	 * Chech verzekrintype The listener interface for receiving
	 * cmb_VerzekeraarAction events. The class that is interested in processing
	 * a cmb_VerzekeraarAction event implements this interface, and the object
	 * created with that class is registered with a component using the
	 * component's <code>addCmb_VerzekeraarActionListener<code> method. When
	 * the cmb_VerzekeraarAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Cmb_VerzekeraarActionEvent
	 */
	private class Cmb_VerzekeraarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				setVerzekeringType(bepaalTypeVerzekering(cmb_Verzekeraar
						.getModel().getElementAt(
								cmb_Verzekeraar.getSelectedIndex())));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 ======= The listener interface for receiving cmb_VerzekeraarKey events.
	 * The class that is interested in processing a cmb_VerzekeraarKey event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addCmb_VerzekeraarKeyListener<code> method. When
	 * the cmb_VerzekeraarKey event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Cmb_VerzekeraarKeyEvent
	 */
	private class Cmb_VerzekeraarKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("test");
		}
	}

	private class BtnVerwijderenActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmb_Type.getSelectedIndex() > 0) {
				if (list.getSelectedIndex() != -1) {
					DefaultListModel<Behandelingtraject> model = (DefaultListModel<Behandelingtraject>) list
							.getModel();
					model.remove(list.getSelectedIndex());
				}
			}
		}

	}
}
