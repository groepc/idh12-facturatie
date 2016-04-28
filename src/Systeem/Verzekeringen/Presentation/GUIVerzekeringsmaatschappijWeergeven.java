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
import java.util.Collections;
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
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt de detail weergaven van een verzekeringsmaatschappij.
 * 
 * @author Gregor
 */
public class GUIVerzekeringsmaatschappijWeergeven extends JPanel {

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

	/** The txt iban nr. */
	private JTextField txtIbanNr;

	/** The parent. */
	private ParentFrame parent;

	/** The txt btw. */
	private JTextField txtBtw;

	/** The verzekeringsmaatschappij. */
	private Verzekeringsmaatschappij verzekeringsmaatschappij;

	/** The controller verzekeringen. */
	private ControllerVerzekeringen controllerVerzekeringen;

	/** The controller verzekering maatschappijen. */
	private ControllerVerzekeringMaatschappijen controllerVerzekeringMaatschappijen;

	/**
	 * Maakt het weergaven scherm aan en vult deze met de gegevens van een
	 * verzekeringsmaatschappij.
	 *
	 * @param parent
	 *            the parent
	 * @param verzekeringsmaatschappij
	 *            De verzekeringsmaatschappij die wordt weergegeven.
	 */
	public GUIVerzekeringsmaatschappijWeergeven(ParentFrame parent, Verzekeringsmaatschappij verzekeringsmaatschappij) {
		this.parent = parent;
		try {
			controllerVerzekeringen = new ControllerVerzekeringen();
			controllerVerzekeringMaatschappijen = new ControllerVerzekeringMaatschappijen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init(verzekeringsmaatschappij);
	}

	/**
	 * Inits the.
	 *
	 * @param verzekeringsmaatschappij
	 *            the verzekeringsmaatschappij
	 */
	public void init(Verzekeringsmaatschappij verzekeringsmaatschappij) {
		this.verzekeringsmaatschappij = verzekeringsmaatschappij;
		this.setMinimumSize(VerzekeringSettings.panelSize);
		this.setPreferredSize(VerzekeringSettings.panelSize);
		this.setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTerug = new JButton("Terug");
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		JPanel panel_content = new JPanel();
		panel_content.setLayout(new MigLayout("", "[15%,right]1%[grow,center]1%[15%]", "[25,top][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][25,fill][grow][grow]"));

		JLabel lblModus = new JLabel("Verzekeringmaatschappij bekijken");
		panel_content.add(lblModus, "cell 1 0");

		JLabel lblNaam = new JLabel("Naam");
		panel_content.add(lblNaam, "cell 0 1,alignx trailing");

		txtNaam = new JTextField();
		txtNaam.setBackground(Color.WHITE);
		txtNaam.setEditable(false);
		panel_content.add(txtNaam, "cell 1 1,growx");
		txtNaam.setColumns(10);

		JButton btnWijzigen = new JButton("Wijzigen");
		btnWijzigen.addActionListener(new BtnWijzigenActionListener());
		panel_content.add(btnWijzigen, "cell 2 1,grow");

		JLabel lblAdres = new JLabel("Adres");
		panel_content.add(lblAdres, "cell 0 2,alignx trailing");

		txtAdres = new JTextField();
		txtAdres.setBackground(Color.WHITE);
		txtAdres.setEditable(false);
		panel_content.add(txtAdres, "cell 1 2,growx");
		txtAdres.setColumns(10);

		JButton btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new BtnVerwijderenActionListener());
		panel_content.add(btnVerwijderen, "cell 2 2,grow");

		JLabel lblPostcode = new JLabel("Postcode");
		panel_content.add(lblPostcode, "cell 0 3,alignx trailing");

		txtPostcode = new JTextField();
		txtPostcode.setBackground(Color.WHITE);
		txtPostcode.setEditable(false);
		panel_content.add(txtPostcode, "cell 1 3,growx");
		txtPostcode.setColumns(10);

		JLabel lblPlaats = new JLabel("Plaats");
		panel_content.add(lblPlaats, "cell 0 4,alignx trailing");

		txtPlaats = new JTextField();
		txtPlaats.setBackground(Color.WHITE);
		txtPlaats.setEditable(false);
		panel_content.add(txtPlaats, "cell 1 4,growx");
		txtPlaats.setColumns(10);

		JLabel lblTelefoonnummer = new JLabel("Telefoonnummer");
		panel_content.add(lblTelefoonnummer, "cell 0 5,alignx trailing");

		txtTelefoonnummer = new JTextField();
		txtTelefoonnummer.setBackground(Color.WHITE);
		txtTelefoonnummer.setEditable(false);
		panel_content.add(txtTelefoonnummer, "cell 1 5,growx");
		txtTelefoonnummer.setColumns(10);

		JLabel lblKvkNr = new JLabel("Kvk nr");
		panel_content.add(lblKvkNr, "cell 0 6,alignx trailing");

		txtKvkNr = new JTextField();
		txtKvkNr.setBackground(Color.WHITE);
		txtKvkNr.setEditable(false);
		panel_content.add(txtKvkNr, "cell 1 6,growx");
		txtKvkNr.setColumns(10);

		JLabel lblIbanNr = new JLabel("Iban nr");
		panel_content.add(lblIbanNr, "cell 0 7,alignx trailing");

		txtIbanNr = new JTextField();
		txtIbanNr.setBackground(Color.WHITE);
		txtIbanNr.setEditable(false);
		panel_content.add(txtIbanNr, "cell 1 7,growx");
		txtIbanNr.setColumns(10);

		JLabel lblBtwNr = new JLabel("Btw nr");
		panel_content.add(lblBtwNr, "cell 0 8,alignx trailing");

		txtBtw = new JTextField();
		txtBtw.setBackground(Color.WHITE);
		txtBtw.setEditable(false);
		panel_content.add(txtBtw, "cell 1 8,growx");
		txtBtw.setColumns(10);

		JLabel lblVerzekeringen = new JLabel("Verzekeringen");
		panel_content.add(lblVerzekeringen, "cell 0 9");

		JScrollPane scrollPane = new JScrollPane();
		panel_content.add(scrollPane, "cell 1 9,grow");

		txtNaam.setText(verzekeringsmaatschappij.getNaam());
		txtAdres.setText(verzekeringsmaatschappij.getAdres());
		txtIbanNr.setText(verzekeringsmaatschappij.getIban());
		txtKvkNr.setText(verzekeringsmaatschappij.getKvkNr());
		txtPlaats.setText(verzekeringsmaatschappij.getPlaats());
		txtPostcode.setText(verzekeringsmaatschappij.getPostcode());
		txtTelefoonnummer.setText(verzekeringsmaatschappij.getTelefoonnummer());
		txtBtw.setText(verzekeringsmaatschappij.getBtwNr());

		JList<Verzekering> listVerzekeringen;
		try {
			listVerzekeringen = new JList<>(controllerVerzekeringen.zoekVerzekeringenVanMaatschappij(verzekeringsmaatschappij).toArray(new Verzekering[0]));
		} catch (IOException e) {
			e.printStackTrace();
			listVerzekeringen = new JList<>();
		}
		listVerzekeringen.setBackground(Color.WHITE);
		scrollPane.setViewportView(listVerzekeringen);

		this.add(panel_menu, BorderLayout.NORTH);
		this.add(panel_content, BorderLayout.CENTER);
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
			parent.changePanel(new GUIVerzekeringsmaatschappijOverzicht(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);

		}
	}

	/**
	 * The listener interface for receiving btnWijzigenAction events. The class
	 * that is interested in processing a btnWijzigenAction event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addBtnWijzigenActionListener<code> method. When
	 * the btnWijzigenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnWijzigenActionEvent
	 */
	private class BtnWijzigenActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new GUIVerzekeringsmaatschappijInvoerenWijzigen(parent, verzekeringsmaatschappij), VerzekeringSettings.verzekeringmaatschappijTabNummer);
		}
	}

	/**
	<<<<<<< HEAD
	 * Button verwijderen
	=======
	 * The listener interface for receiving btnVerwijderenAction events.
	 * The class that is interested in processing a btnVerwijderenAction
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addBtnVerwijderenActionListener<code> method. When
	 * the btnVerwijderenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnVerwijderenActionEvent
	>>>>>>> Dao-goed
	 */
	private class BtnVerwijderenActionListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				if (JOptionPane.showConfirmDialog((JFrame) parent, "Wilt u de maatschappij echt verwijderen", "Bevestiging verwijderen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (controllerVerzekeringMaatschappijen.verzekeringmaatschappijVerwijderen(verzekeringsmaatschappij)) {
						parent.changePanel(new GUIVerzekeringsmaatschappijOverzicht(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
						JOptionPane.showMessageDialog((JFrame) parent, "De maatschappij is verwijderd");
						return;
					} else {
						JOptionPane.showMessageDialog((JFrame) parent, "De maatschappij heeft nog verzekeringen aan zich gekoppeld en kan daarom niet verwijderd worden.");
						return;
					}
				} else {
					return;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
