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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.Facturatie.Businesslogic.ControllerFactuur;
import Systeem.Facturatie.Presentation.GUIFacturenOverzicht;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Klanten.Businesslogic.ControllerKlant;

/**
 * @author Gregor
 * @author Mark
 */
public class GUIKlantWeergeven extends JPanel {

	/** Klantinfo van de Klant */
	private Klant klantInfo;

	/** Hoofdscherm */
	private ParentFrame parent;

	/**
	 * Create the panel.
	 * 
	 * @param parent
	 * @param klant
	 */
	public GUIKlantWeergeven(final ParentFrame parent, final Klant klant) {
		klantInfo = klant;
		this.parent = parent;
		DecimalFormat df = new DecimalFormat("€ ###,##0.00");

		setVisible(true);
		setSize(750, 550);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTerug = new JButton("Terug naar zoeken");
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		JButton btnOverzicht = new JButton("Terug naar overzicht");
		btnOverzicht.addActionListener(new BtnOverzichtActionListener());
		panel_menu.add(btnOverzicht);

		JPanel panel_content = new JPanel();
		add(panel_content, BorderLayout.CENTER);
		panel_content
				.setLayout(new MigLayout("", "[:150:150][grow][:150:150]",
						"[25][25][25][][25][25][25][25][25][25][25][25][][25][25,grow][grow]"));
		panel_content
				.setLayout(new MigLayout("", "[:150:150][grow][:150:150]",
						"[25][25][25][][25][25][25][25][25][25][25][25][][25][25,grow][grow]"));

		JLabel lblKlantWeergeven = new JLabel("Klant weergeven");
		panel_content.add(lblKlantWeergeven,
				"cell 1 0,alignx center,aligny top");

		JLabel lblNaam = new JLabel("Naam:");
		panel_content.add(lblNaam, "cell 0 1,alignx trailing");

		JTextField txtNaam = new JTextField();
		txtNaam.setEditable(false);
		panel_content.add(txtNaam, "cell 1 1,growx");
		txtNaam.setColumns(10);

		JButton btnFactuurOverzicht = new JButton("Factuur Overzicht");
		panel_content.add(btnFactuurOverzicht, "cell 2 1");
		btnFactuurOverzicht.setMaximumSize(new Dimension(130, 25));
		btnFactuurOverzicht.setPreferredSize(new Dimension(130, 25));
		btnFactuurOverzicht.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFactuurOverzicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.changePanel(new GUIFacturenOverzicht(klant, parent), 0);
				// TODO Uitleggen hoe dit werkt.
			}
		});

		JButton btnFactureren = new JButton("Factureren");
		btnFactureren.addActionListener(new BtnFacturerenActionListener());
		panel_content.add(btnFactureren, "cell 2 2");
		btnFactureren.setMaximumSize(new Dimension(130, 25));
		btnFactureren.setPreferredSize(new Dimension(130, 25));
		btnFactureren.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnWijzigen = new JButton("Wijzigen");
		btnWijzigen.addActionListener(new BtnWijzigenActionListener());

		JLabel lblGeslacht = new JLabel("Geslacht");
		panel_content.add(lblGeslacht, "cell 0 3,alignx trailing");

		JTextField txtGeslacht = new JTextField();
		txtGeslacht.setEditable(false);
		panel_content.add(txtGeslacht, "cell 1 3,growx");
		txtGeslacht.setColumns(10);
		panel_content.add(btnWijzigen, "cell 2 4");
		btnWijzigen.setMaximumSize(new Dimension(130, 25));
		btnWijzigen.setPreferredSize(new Dimension(130, 25));
		btnWijzigen.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new BtnVerwijderenActionListener());
		panel_content.add(btnVerwijderen, "cell 2 5");
		btnVerwijderen.setMaximumSize(new Dimension(130, 25));
		btnVerwijderen.setPreferredSize(new Dimension(130, 25));
		btnVerwijderen.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblAchternaam = new JLabel("Achternaam:");
		panel_content.add(lblAchternaam, "cell 0 2,alignx trailing");

		JTextField txtAchternaam = new JTextField();
		txtAchternaam.setEditable(false);
		panel_content.add(txtAchternaam, "cell 1 2,growx");
		txtAchternaam.setColumns(10);

		JLabel lblGeboortedatum = new JLabel("Geboortedatum:");
		panel_content.add(lblGeboortedatum, "cell 0 4,alignx trailing");

		JTextField txtGeboortedatum = new JTextField();
		txtGeboortedatum.setEditable(false);
		panel_content.add(txtGeboortedatum, "cell 1 4,growx");
		txtGeboortedatum.setColumns(10);

		JLabel lblBsn = new JLabel("bsn:");
		panel_content.add(lblBsn, "cell 0 5,alignx trailing");

		JTextField txtBsn = new JTextField();
		txtBsn.setEditable(false);
		panel_content.add(txtBsn, "cell 1 5,growx");
		txtBsn.setColumns(10);

		JLabel lblAdres = new JLabel("Adres:");
		panel_content.add(lblAdres, "cell 0 6,alignx trailing");

		JTextField txtAdres = new JTextField();
		txtAdres.setEditable(false);
		panel_content.add(txtAdres, "cell 1 6,growx");
		txtAdres.setColumns(10);

		JLabel lblPostcode = new JLabel("Postcode:");
		panel_content.add(lblPostcode, "cell 0 7,alignx trailing");

		JTextField txtPostcode = new JTextField();
		txtPostcode.setEditable(false);
		panel_content.add(txtPostcode, "cell 1 7,growx");
		txtPostcode.setColumns(10);

		JLabel lblPlaats = new JLabel("Plaats:");
		panel_content.add(lblPlaats, "cell 0 8,alignx trailing");

		JTextField txtPlaats = new JTextField();
		txtPlaats.setEditable(false);
		panel_content.add(txtPlaats, "cell 1 8,growx");
		txtPlaats.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		panel_content.add(lblEmail, "cell 0 9,alignx trailing");

		JTextField txtEmail = new JTextField();
		txtEmail.setEditable(false);
		panel_content.add(txtEmail, "cell 1 9,growx");
		txtEmail.setColumns(10);

		JLabel lblTelefoonnummer = new JLabel("Telefoonnummer:");
		panel_content.add(lblTelefoonnummer, "cell 0 10,alignx trailing");

		JTextField txtTelefoonnummer = new JTextField();
		txtTelefoonnummer.setEditable(false);
		panel_content.add(txtTelefoonnummer, "cell 1 10,growx");
		txtTelefoonnummer.setColumns(10);

		JLabel lblEigenRisico = new JLabel("Eigen risico:");
		panel_content.add(lblEigenRisico, "cell 0 11,alignx trailing");

		JTextField txtEiegnrisico = new JTextField();
		txtEiegnrisico.setEditable(false);
		panel_content.add(txtEiegnrisico, "cell 1 11,growx");
		txtEiegnrisico.setColumns(10);

		JLabel lblRestandEigenRisico = new JLabel("Restand eigen risico:");
		panel_content.add(lblRestandEigenRisico, "cell 0 12,alignx trailing");

		JTextField textFieldRestand = new JTextField();
		textFieldRestand.setEditable(false);
		panel_content.add(textFieldRestand, "cell 1 12,growx");
		textFieldRestand.setColumns(10);

		JLabel lblFactuurontvangst = new JLabel("Factuurontvangst:");
		panel_content.add(lblFactuurontvangst, "cell 0 13,alignx trailing");

		JTextField txtFactuurontvangst = new JTextField();
		txtFactuurontvangst.setEditable(false);
		panel_content.add(txtFactuurontvangst, "cell 1 13,growx");
		txtFactuurontvangst.setColumns(10);

		JLabel lblVerzekering = new JLabel("Verzekering:");
		panel_content.add(lblVerzekering, "cell 0 14,alignx trailing");

		JScrollPane scrollPane = new JScrollPane();
		panel_content.add(scrollPane, "cell 1 14,grow");

		JList<Verzekering> list = new JList<>();
		list.setBorder(null);
		scrollPane.setViewportView(list);

		txtNaam.setText(klantInfo.getVoornaam());
		txtAchternaam.setText(klantInfo.getAchternaam());
		txtGeslacht.setText(klantInfo.getGeslacht());
		txtAdres.setText(klantInfo.getAdres());
		txtPostcode.setText(klantInfo.getPostcode());
		txtPlaats.setText(klantInfo.getPlaats());
		txtBsn.setText(klantInfo.getBsn());
		txtGeboortedatum.setText(new SimpleDateFormat("dd-MM-yyyy")
				.format(klantInfo.getGeboortedatum()));
		txtEiegnrisico.setText(df.format(klantInfo.getEigenRisico()));
		txtEmail.setText(klantInfo.getEmail());
		txtFactuurontvangst.setText(klantInfo.getFactuurOntvangst() ? "Ja"
				: "Nee");
		txtTelefoonnummer.setText(klantInfo.getTelefoonnummer());
		textFieldRestand.setText(df.format(klantInfo.getRestantEigenRisico()));

		DefaultListModel<Verzekering> model = new DefaultListModel<>();
		for (Verzekering verzekering : klantInfo.getVerzekeringen()) {
			model.addElement(verzekering);
		}
		list.setModel(model);
	}

	/**
	 * Klant factureren
	 */
	private class BtnFacturerenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ControllerFactuur controller = new ControllerFactuur();
			JOptionPane.showMessageDialog((Component) e.getSource(),
					controller.maakFactuur(klantInfo));
		}
	}

	/**
	 * Terug button
	 */
	private class BtnTerugActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			parent.changePanel(new GUIKlantZoeken(parent), 0);
		}
	}

	/**
	 * Button Factuur overzicht
	 */
	private class BtnOverzichtActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			parent.changePanel(new GUIKlantenOverzicht(parent), 0);
		}
	}

	/**
	 * Button klant wijzigen
	 */
	private class BtnWijzigenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new GUIKlantInvoerenWijzigen(parent, klantInfo),
					0);
		}

	}

	/**
	 * Button klant verwijderen
	 */
	private class BtnVerwijderenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String feedback = "";
			ControllerFactuur fManager = new ControllerFactuur();
			List<Factuur> flist = fManager.geefFacturen(klantInfo);
			for (int i = 0; i < flist.size(); i++) {
				if (flist.get(i).getBetaald() == false) {

					feedback = "Kan "
							+ klantInfo.getVoornaam()
							+ " "
							+ klantInfo.getAchternaam()
							+ " niet verwijderen.\nDeze klant heeft nog onbetaalde facturen openstaan";
					System.out.println(flist.get(i).getNaam());
					break;
				}
			}
			if (feedback.equals("")) {
				feedback = "Weet u zeker dat u "
						+ klantInfo.getVoornaam()
						+ " "
						+ klantInfo.getAchternaam()
						+ " wilt verwijderen?\nDit kan niet ongedaan gemaakt worden!";
				int userInput = JOptionPane.showConfirmDialog(
						GUIKlantWeergeven.this, feedback, "Klant verwijderen",
						JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {
					System.out.println("del klant");
					try {
						ControllerKlant kManager = new ControllerKlant();
						kManager.verwijderKlant(klantInfo);
						parent.changePanel(new GUIKlantenOverzicht(parent), 0);
						JOptionPane.showMessageDialog((JFrame) parent,
								"De klant is verwijderd");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			} else {
				JOptionPane.showMessageDialog(GUIKlantWeergeven.this, feedback,
						"Klant verwijderen", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}