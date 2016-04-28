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
import java.util.ArrayList;
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

import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Settings.VerzekeringSettings;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;
import Systeem.Verzekeringen.Presentation.customModels.CustomObjectTableModel;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt het overzicht van alle verzekeringsmaatschappijen.
 * 
 * @author Gregor
 */
public class GUIVerzekeringsmaatschappijOverzicht extends JPanel {

	/** The parent. */
	private ParentFrame parent;

	/** The maatschappij lijst. */
	private List<Verzekeringsmaatschappij> maatschappijLijst;
	private JTable table;
	private VerzekeringmaatschappijTableModel tableModel;

	/**
	 * Maakt het overzicht aan.
	 *
	 * @param parent
	 *            the parent
	 */
	public GUIVerzekeringsmaatschappijOverzicht(ParentFrame parent) {
		this.parent = parent;
		this.maatschappijLijst = new ArrayList<Verzekeringsmaatschappij>();

		try {
			ControllerVerzekeringMaatschappijen controller = new ControllerVerzekeringMaatschappijen();
			maatschappijLijst = controller.geefVerzekeringsmaatschappijen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
		tableModel.addBehandelingen(maatschappijLijst);
	}

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
		btnTerug.setVisible(false);
		btnTerug.addActionListener(new BtnTerugActionListener());
		panel_menu.add(btnTerug);

		Component verticalStrut = Box.createVerticalStrut(30);
		panel_menu.add(verticalStrut);

		JPanel panel_zuidMenu = new JPanel();
		add(panel_zuidMenu, BorderLayout.SOUTH);
		JButton btnNieuw = new JButton("Verzekeringmaatschappij invoeren");
		btnNieuw.addActionListener(new BtnNieuwActionListener());
		panel_zuidMenu.add(btnNieuw);

		JPanel panel_table = new JPanel();
		add(panel_table, BorderLayout.CENTER);
		panel_table.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_table.add(scrollPane);

		tableModel = new VerzekeringmaatschappijTableModel(new String[] { "Naam", "Adres", "Postcode", "Plaats", "Kvk Nr" });
		table = new JTable();
		table.setModel(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().setSortKeys(Arrays.asList(new SortKey[] { new SortKey(0, SortOrder.ASCENDING) }));
		table.getColumn("Naam").setPreferredWidth(160);
		table.getColumn("Adres").setPreferredWidth(160);
		table.getColumn("Postcode").setPreferredWidth(100);
		table.getColumn("Plaats").setPreferredWidth(120);
		table.getColumn("Kvk Nr").setPreferredWidth(100);
		table.addMouseListener(new MuisMonitor());
		scrollPane.setViewportView(table);
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
			parent.changePanel(new GUIVerzekeringZoeken(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
		}
	}

	/**
	 * The Class MuisMonitor.
	 */
	private class MuisMonitor extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				parent.changePanel(new GUIVerzekeringsmaatschappijWeergeven(parent, tableModel.getRowItem(table.convertRowIndexToModel(table.getSelectedRow()))), VerzekeringSettings.verzekeringmaatschappijTabNummer);
			}
		}
	}

	/**
	 * The listener interface for receiving btnNieuwAction events. The class
	 * that is interested in processing a btnNieuwAction event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addBtnNieuwActionListener<code> method. When
	 * the btnNieuwAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BtnNieuwActionEvent
	 */
	private class BtnNieuwActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			parent.changePanel(new GUIVerzekeringsmaatschappijInvoerenWijzigen(parent), VerzekeringSettings.verzekeringmaatschappijTabNummer);
		}
	}

	private class VerzekeringmaatschappijTableModel extends CustomObjectTableModel<Verzekeringsmaatschappij> {

		/**
		 * @param columnNames
		 */
		public VerzekeringmaatschappijTableModel(String[] columnNames) {
			super(columnNames);
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Verzekeringsmaatschappij maatschappij = this.lijst.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return maatschappij.getNaam();
			case 1:
				return maatschappij.getAdres();
			case 2:
				return maatschappij.getPostcode();
			case 3:
				return maatschappij.getPlaats();
			case 4:
				return maatschappij.getKvkNr();
			default:
				return "";
			}
		}

	}
}