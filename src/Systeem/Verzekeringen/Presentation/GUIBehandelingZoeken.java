/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten;
import Systeem.Verzekeringen.Presentation.customModels.CustomObjectTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIBehandelingZoeken.
 */
public class GUIBehandelingZoeken extends JDialog {

	/** The txt_ zoeken. */
	private JTextField txt_Zoeken;

	/** The tbl_ resultaten. */
	private JTable tbl_Resultaten;

	/** The controller. */
	private ControllerBehandelTrajecten controller;

	/** The parent. */
	private BehandelingToevoegPanel parent;

	private List<Behandelingtraject> eerderGekozenTrajecten;

	/**
	 * Instantiates a new GUI behandeling zoeken.
	 *
	 * @param frame the frame
	 * @param panel the panel
	 * @param modalDialog the modal dialog
	 * @param controller the controller
	 */
	public GUIBehandelingZoeken(BehandelingToevoegPanel panel, ControllerBehandelTrajecten controller, List<Behandelingtraject> eerderGekozenTrajecten) {
		this.controller = controller;
		this.parent = panel;
		this.eerderGekozenTrajecten = eerderGekozenTrajecten;

		init();

		vulBehandelingenTabel(zoekAlleBehandelingen());
	}

	private void init() {
		setTitle("Behandeling selecteren");
		getContentPane().setLayout(new MigLayout("", "[grow][100][60%:60%][100][grow]", "[][][][100px:n,grow][]"));

		JLabel lbl_Titel = new JLabel("Zoek naar behandelingen, laat leeg om alle behandelingen weer te geven.");
		getContentPane().add(lbl_Titel, "cell 0 0 5 1,alignx center");

		txt_Zoeken = new JTextField();
		getContentPane().add(txt_Zoeken, "flowx,cell 1 1 2 1,grow");
		txt_Zoeken.setColumns(10);

		JButton btn_Zoeken = new JButton("Zoeken");
		btn_Zoeken.addActionListener(new Btn_ZoekenActionListener());
		btn_Zoeken.setPreferredSize(new Dimension(100, 23));
		getContentPane().add(btn_Zoeken, "cell 3 1,alignx right,aligny center");

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		getContentPane().add(separator, "cell 0 2 5 1,growx");

		JButton btn_Terug = new JButton("Terug");
		btn_Terug.addActionListener(new Btn_TerugActionListener());

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 3 3 1,growx");

		tbl_Resultaten = new JTable();
		tbl_Resultaten.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setViewportView(tbl_Resultaten);
		tbl_Resultaten.setModel(new CustomBehandelTableModel(new String[] { "Behandelcode", "Naam" }));
		tbl_Resultaten.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl_Resultaten.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl_Resultaten.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int start, int end) {
				if (start != end) {
					super.setSelectionInterval(start, end);
				} else if (isSelectedIndex(start)) {
					removeSelectionInterval(start, end);
				} else {
					addSelectionInterval(start, end);
				}
			};
		});
		tbl_Resultaten.getTableHeader().setReorderingAllowed(false);
		tbl_Resultaten.setToolTipText("Selecteer de behandelingen die je wilt toeveoegen en klik op de knop toevoegen.");
		tbl_Resultaten.setBorder(null);
		btn_Terug.setPreferredSize(new Dimension(100, 23));
		getContentPane().add(btn_Terug, "cell 1 4,pushx ,alignx left");

		JButton btn_Opslaan = new JButton("Toevoegen");
		btn_Opslaan.addActionListener(new Btn_OpslaanActionListener());
		btn_Opslaan.setPreferredSize(new Dimension(100, 23));
		getContentPane().add(btn_Opslaan, "flowx,cell 2 4,alignx center");
	}

	private List<Behandelingtraject> zoekAlleBehandelingen() {
		return zoekBehandelingen(null);
	}

	private List<Behandelingtraject> zoekBehandelingen(String zoektekst) {
		return zoektekst == null ? controller.haalBehandelingTrajectenOp(true) : controller.zoekBehandelTrajecten(zoektekst, true);
	}

	private void vulBehandelingenTabel(List<Behandelingtraject> behandelingenLijst) {
		CustomBehandelTableModel model = (CustomBehandelTableModel) tbl_Resultaten.getModel();
		List<Behandelingtraject> eerderGeselecteerdeBehandelingen = new ArrayList<Behandelingtraject>();

		if (tbl_Resultaten.getSelectedRowCount() > 0) {
			int[] geselecteerdeRijen = tbl_Resultaten.getSelectedRows();
			for (int x = 0; x < geselecteerdeRijen.length; x++) {
				eerderGeselecteerdeBehandelingen.add(model.getRowItem(x));
			}
		}

		model.clear();
		model.addBehandelingen(eerderGeselecteerdeBehandelingen);

		for (Behandelingtraject behandeling : behandelingenLijst) {
			if (!(eerderGeselecteerdeBehandelingen.contains(behandeling) || eerderGekozenTrajecten.contains(behandeling))) {
				model.addBehandeling(behandeling);
			}
		}

		tbl_Resultaten.setModel(model);
		tbl_Resultaten.getSelectionModel().setSelectionInterval(0, eerderGeselecteerdeBehandelingen.size());
	}

	private class CustomBehandelTableModel extends CustomObjectTableModel<Behandelingtraject> {
		public CustomBehandelTableModel(String[] collumNames) {
			super(collumNames);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Behandelingtraject behandeling = lijst.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return behandeling.getBehandelcode();
			case 1:
				return behandeling.getNaam();
			default:
				return "";
			}
		}

	}

	private class Btn_ZoekenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!txt_Zoeken.getText().isEmpty()) {
				vulBehandelingenTabel(zoekBehandelingen(txt_Zoeken.getText()));
			}
		}
	}

	private class Btn_TerugActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class Btn_OpslaanActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (tbl_Resultaten.getSelectedRowCount() > 0) {
				CustomBehandelTableModel model = (CustomBehandelTableModel) tbl_Resultaten.getModel();
				List<Behandelingtraject> geslecteerdeBehandelingen = new ArrayList<>();

				for (int x = 0; x < tbl_Resultaten.getSelectedRows().length; x++) {
					geslecteerdeBehandelingen.add(model.getRowItem(tbl_Resultaten.getSelectedRows()[x]));
				}
				parent.voegBehandelingenToe(geslecteerdeBehandelingen);
				dispose();
			}
		}
	}
}