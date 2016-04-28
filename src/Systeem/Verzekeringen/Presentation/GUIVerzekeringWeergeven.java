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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Verzekering;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;
import Systeem.Verzekeringen.Presentation.customRenderers.BehandelingListRenderer;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt de detailweergave van een verzekering.
 * 
 * @author Gregor
 */
public class GUIVerzekeringWeergeven extends JPanel {

	/** The lbl_ mode label. */
	private JLabel lbl_ModeLabel;

	/** The txt verzekeraar. */
	private JTextField txtVerzekeraar;

	/** The parent. */
	private ParentFrame parent;

	/** The txt naam. */
	private JTextField txtNaam;

	/** The txt type. */
	private JTextField txtType;

	/** The txta omschrijving. */
	private JTextArea txtaOmschrijving;

	/** The list behandelingen. */
	private JList<Behandelingtraject> listBehandelingen;

	/** The verzekering. */
	private Verzekering verzekering;

	/**
	 * Maakt het weergaven scherm aan en vult deze met de gegevens van een
	 * verzekering.
	 *
	 * @param parent
	 *            the parent
	 * @param verzekering
	 *            De verzekering die wordt weergegeven.
	 */
	public GUIVerzekeringWeergeven(ParentFrame parent, Verzekering verzekering) {
		super();
		this.parent = parent;
		this.verzekering = verzekering;
		init();

		txtNaam.setText(verzekering.getNaam());
		txtType.setText(verzekering.getType());
		txtVerzekeraar.setText(verzekering.getVerzekeringmaatschappij().getNaam());
		txtaOmschrijving.setText(verzekering.getOmschrijving());
	}

	/**
	 * Inits the.
	 */
	private void init() {
		this.setMinimumSize(VerzekeringSettings.panelSize);
		this.setPreferredSize(VerzekeringSettings.panelSize);
		this.setLayout(new BorderLayout(0, 0));

		JPanel panel_menu = new JPanel();
		panel_menu.setBackground(SystemColor.controlShadow);
		panel_menu.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		this.add(panel_menu, BorderLayout.NORTH);
		panel_menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTerug = new JButton("Terug");
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		JPanel panel_content = new JPanel();
		this.add(panel_content, BorderLayout.CENTER);
		panel_content.setLayout(new MigLayout("", "[15%,right]1%[grow,center]1%[15%]", "[50][25,fill][25,fill][25,fill][25,fill][grow,fill][20,grow,fill][50]"));

		lbl_ModeLabel = new JLabel("Verzekering ...");
		panel_content.add(lbl_ModeLabel, "cell 1 1,alignx center");

		JLabel lbl_Naam = new JLabel("Naam");
		panel_content.add(lbl_Naam, "cell 0 2,alignx trailing");

		txtNaam = new JTextField();
		txtNaam.setEditable(false);
		panel_content.add(txtNaam, "cell 1 2,growx");
		txtNaam.setColumns(10);

		JButton btnWijzigen = new JButton("Wijzigen");
		btnWijzigen.addActionListener(new BtnWijzigenActionListener());
		panel_content.add(btnWijzigen, "cell 2 2,growx");

		JLabel lbl_Type = new JLabel("Type");
		panel_content.add(lbl_Type, "cell 0 3,alignx trailing");

		txtType = new JTextField();
		txtType.setEditable(false);
		panel_content.add(txtType, "cell 1 3,growx");
		txtType.setColumns(10);

		JButton btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new BtnVerwijderenActionListener());
		panel_content.add(btnVerwijderen, "cell 2 3,growx");

		JLabel lbl_Verzekeraar = new JLabel("Verzekeraar");
		panel_content.add(lbl_Verzekeraar, "cell 0 4");

		txtVerzekeraar = new JTextField();
		txtVerzekeraar.setEditable(false);
		panel_content.add(txtVerzekeraar, "cell 1 4,growx");

		JLabel lbl_Omschrijving = new JLabel("Omschrijving");
		panel_content.add(lbl_Omschrijving, "cell 0 5,alignx trailing");

		txtaOmschrijving = new JTextArea();
		txtaOmschrijving.setLineWrap(true);
		panel_content.add(txtaOmschrijving, "cell 1 5,grow");
		txtaOmschrijving.setEditable(false);
		txtaOmschrijving.setBorder(txtVerzekeraar.getBorder());

		JLabel lblGedekteTrajecten = new JLabel("Gedekte trajecten");
		panel_content.add(lblGedekteTrajecten, "cell 0 6,growy");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		panel_content.add(scrollPane, "cell 1 6,grow");

		listBehandelingen = new JList<>(verzekering.getGedekteBehandeltrajecten().toArray(new Behandelingtraject[verzekering.getGedekteBehandeltrajecten().size()]));
		listBehandelingen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBehandelingen.setCellRenderer(new BehandelingListRenderer());
		listBehandelingen.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.LIGHT_GRAY, Color.WHITE));
		scrollPane.setViewportView(listBehandelingen);
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
			parent.changePanel(new GUIVerzekeringZoeken(parent), VerzekeringSettings.verzekeringTabNummer);
		}
	}

	/**
	 * The listener interface for receiving btnWijzigenAction events.
	 * The class that is interested in processing a btnWijzigenAction
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addBtnWijzigenActionListener<code> method. When
	 * the btnWijzigenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnWijzigenActionEvent
	 */
	private class BtnWijzigenActionListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			parent.changePanel(new GUIVerzekeringInvoerenWijzigen(parent, verzekering), VerzekeringSettings.verzekeringTabNummer);
		}
	}

	/**
	 * The listener interface for receiving btnVerwijderenAction events.
	 * The class that is interested in processing a btnVerwijderenAction
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addBtnVerwijderenActionListener<code> method. When
	 * the btnVerwijderenAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnVerwijderenActionEvent
	 */
	private class BtnVerwijderenActionListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			try {
				ControllerVerzekeringen controller = new ControllerVerzekeringen();
				if (controller.checkInGebruik(verzekering.getId()) == false) {

					if (JOptionPane.showConfirmDialog((JFrame) parent, "Wilt u de verzekering echt verwijderen", "Bevestiging verwijderen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						controller.verwijderVerzekering(verzekering);
						parent.changePanel(new GUIVerzekeringOverzicht(parent), VerzekeringSettings.verzekeringTabNummer);
						JOptionPane.showMessageDialog((JFrame) parent, "De verzekering is verwijderd");
					}
					return;
				} else {
					JOptionPane.showMessageDialog((JFrame) parent, "De verzekering is in gebruik en kan niet verwijderd worden.", "Kan verzekering niet verwijdern", JOptionPane.ERROR_MESSAGE);
					return;

				}

			} catch (IOException ex) {

			}

		}
	}
}
