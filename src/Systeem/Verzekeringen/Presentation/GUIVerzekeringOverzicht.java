/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Verzekeringen.Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.border.MatteBorder;

import Systeem.BusinessDomain.Verzekering;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;
import Systeem.Verzekeringen.Presentation.customModels.CustomObjectTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIVerzekeringOverzicht.
 */
public class GUIVerzekeringOverzicht extends JPanel {

	/** The verzekeringen. */
	private List<Verzekering> verzekeringen;

	/** The parent. */
	private ParentFrame parent;
	private JTable tbl_Verzekering;
	private VerzekeringTableModel tableModel;

	/**
	 * Instantiates a new GUI verzekering overzicht.
	 *
	 * @param parent
	 *            the parent
	 * @param verzekeringen
	 *            the verzekeringen
	 * @wbp.parser.constructor
	 */
	public GUIVerzekeringOverzicht(ParentFrame parent, List<Verzekering> verzekeringen) {
		this.parent = parent;
		this.verzekeringen = verzekeringen;
		init();
		this.tableModel.addBehandelingen(this.verzekeringen);
		tbl_Verzekering.getRowSorter().setSortKeys(Arrays.asList(new SortKey[] { new SortKey(2, SortOrder.ASCENDING), new SortKey(1, SortOrder.DESCENDING), new SortKey(0, SortOrder.ASCENDING) }));
	}

	/**
	 * Instantiates a new GUI verzekering overzicht.
	 *
	 * @param parent
	 *            the parent
	 */
	public GUIVerzekeringOverzicht(ParentFrame parent) {
		this.parent = parent;
		ControllerVerzekeringen controller;
		init();
		try {
			controller = new ControllerVerzekeringen();
			this.verzekeringen = controller.haalAlleVerzekeringenOp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tableModel.addBehandelingen(verzekeringen);
		tbl_Verzekering.getRowSorter().setSortKeys(Arrays.asList(new SortKey[] { new SortKey(2, SortOrder.ASCENDING), new SortKey(1, SortOrder.DESCENDING), new SortKey(0, SortOrder.ASCENDING) }));
	}

	/**
	 * GUIVerzekeringOverzicht aanmaken
	 */
	public void init() {
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

		Component verticalStrut = Box.createVerticalStrut(30);
		panel_menu.add(verticalStrut);

		JPanel panel_southMenu = new JPanel();
		add(panel_southMenu, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Verzekering invoeren");
		btnNewButton.addActionListener(new BtnNewButtonActionListener());
		panel_southMenu.add(btnNewButton);

		JPanel panel_Content = new JPanel();
		add(panel_Content, BorderLayout.CENTER);
		panel_Content.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_Content.add(scrollPane);

		tableModel = new VerzekeringTableModel(new String[] { "Naam", "Type", "Verzekeraar", "Omschrijving" });
		tbl_Verzekering = new JTable();
		tbl_Verzekering.setModel(tableModel);
		tbl_Verzekering.getTableHeader().setReorderingAllowed(false);
		tbl_Verzekering.getTableHeader().setResizingAllowed(false);
		tbl_Verzekering.setAutoCreateRowSorter(true);
		tbl_Verzekering.getRowSorter().setSortKeys(Arrays.asList(new SortKey[] { new SortKey(2, SortOrder.ASCENDING), new SortKey(0, SortOrder.ASCENDING) }));
		tbl_Verzekering.getColumn("Naam").setPreferredWidth(120);
		tbl_Verzekering.getColumn("Type").setPreferredWidth(120);
		tbl_Verzekering.getColumn("Verzekeraar").setPreferredWidth(150);
		tbl_Verzekering.getColumn("Omschrijving").setPreferredWidth(350);
		tbl_Verzekering.addMouseListener(new MuisMonitor());
		scrollPane.setViewportView(tbl_Verzekering);
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
	 * The Class MuisMonitor.
	 */
	private class MuisMonitor extends MouseAdapter {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				parent.changePanel(new GUIVerzekeringWeergeven(parent, tableModel.getRowItem(tbl_Verzekering.convertRowIndexToModel(tbl_Verzekering.getSelectedRow()))), VerzekeringSettings.verzekeringTabNummer);
			}
		}
	}

	/**
	 * The listener interface for receiving btnNewButtonAction events. The class
	 * that is interested in processing a btnNewButtonAction event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's
	 * <code>addBtnNewButtonActionListener<code> method. When
	 * the btnNewButtonAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnNewButtonActionEvent
	 */
	private class BtnNewButtonActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent arg0) {
			parent.changePanel(new GUIVerzekeringInvoerenWijzigen(parent), VerzekeringSettings.verzekeringTabNummer);
		}
	}

	private class VerzekeringTableModel extends CustomObjectTableModel<Verzekering> {

		/**
		 * @param columnNames
		 */
		public VerzekeringTableModel(String[] columnNames) {
			super(columnNames);
		}

		/**
		 * @param columnNames
		 */
		public VerzekeringTableModel(String[] columnNames, List<Verzekering> verzekeringen) {
			super(columnNames);
			this.addBehandelingen(verzekeringen);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Verzekering verzekering = this.lijst.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return verzekering.getNaam();
			case 1:
				return verzekering.getType();
			case 2:
				return verzekering.getVerzekeringmaatschappij().getNaam();
			case 3:
				return verzekering.getOmschrijving();
			default:
				return "";
			}
		}

	}
}