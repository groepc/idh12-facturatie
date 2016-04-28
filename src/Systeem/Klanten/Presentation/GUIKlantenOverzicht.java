/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Klanten.Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import Systeem.BusinessDomain.Klant;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Klanten.Businesslogic.ControllerKlant;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIKlantenOverzicht.
 * 
 * @author Mark
 */
public class GUIKlantenOverzicht extends JPanel {

	/** The klist. */
	private List<Klant> klist;

	/** The parent. */
	private ParentFrame parent;

	/**
	 * Manager van klant wordt aangemaakt
	 * 
	 * @param parent
	 * @param parent
	 *            the parent
	 * @wbp.parser.constructor
	 */
	public GUIKlantenOverzicht(ParentFrame parent) {
		this.parent = parent;
		try {
			ControllerKlant manager = new ControllerKlant();
			klist = manager.geefAlleKlanten();
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
		if (klist == null || klist.isEmpty()) {
			JLabel label = new JLabel("Er zijn geen klanten in het systeem.");
			label.setFont(new Font("Tahoma", Font.PLAIN, 21));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.NORTH);
			add(label);
		}
	}

	/**
	 * @param parent
	 * @param klanten
	 *            Instantiates a new GUI klanten overzicht.
	 *
	 * @param parent
	 *            the parent
	 * @param klanten
	 *            the klanten
	 */
	public GUIKlantenOverzicht(ParentFrame parent, List<Klant> klanten) {
		this.parent = parent;
		this.klist = klanten;
		init();
	}

	/**
	 * GUI Klanten Overzicht wordt aangemaakt Inits the.
	 */
	private void init() {
		ArrayList<Boolean> klaar = new ArrayList<>();
		for (int i = 0; i < klist.size(); i++) {
			Klant kl = klist.get(i);
			try {

				kl.getVerzekeringen().get(0).getVerzekeringmaatschappij()
						.getNaam();
				klaar.add(true);

			} catch (Exception f) {
				klaar.add(false);
			}
		}
		setLayout(new BorderLayout(0, 0));
		final JTable table = new Table(createTable(), klaar);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);

		// Maakt de JScrollpane
		JScrollPane scrollPane_1 = new JScrollPane();

		add(scrollPane_1);

		// Voegt deze toe aan de tabel
		scrollPane_1.setViewportView(table);

		// Voeg een Tableheader toe
		table.getTableHeader().setReorderingAllowed(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				// Logica die de juiste Klant vind en meegeeft aan weergeven

				if (event.getClickCount() == 2) {
					String bsnToGet = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					for (int i = 0; i < klist.size(); i++) {

						Klant gevondenKlant = klist.get(i);
						if (gevondenKlant.getBsn().equals(bsnToGet)) {
							parent.changePanel(new GUIKlantWeergeven(parent,
									gevondenKlant), 0);

						}
					}

				}
			}
		});
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton terugKnop = new JButton("Terug naar zoeken");
		terugKnop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.changePanel(new GUIKlantZoeken(parent), 0);
			}
		});

		JButton helpKnop = new JButton("Help knop");
		helpKnop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								GUIKlantenOverzicht.this,
								"Klantenoverzicht: \n- Dubbelklik op een klant om deze weer te geven\n- Klik op terug naar zoeken om terug te gaan\n -De licht groen gemarkeerde klanten zijn volledig aan een of meerdere verzekeringen gekoppeld",
								"Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(helpKnop);
		panel.add(terugKnop);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		panel.add(verticalStrut_1);
	}

	/**
	 * @return model Creates the table.
	 */
	private DefaultTableModel createTable() {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int rom, int column) {
				// Maakt de tabel niet editbaar
				return false;
			}
		};

		// Maakt de kolommen
		model.addColumn("BSN");
		model.addColumn("Naam");
		model.addColumn("Adres");
		model.addColumn("Postcode");
		model.addColumn("Email");

		// Loopt door de klanten en zet ze in het model
		for (int i = 0; i < klist.size(); i++) {
			Klant klantinfo = klist.get(i);
			model.addRow(new Object[] { klantinfo.getBsn(),
					klantinfo.getVoornaam() + " " + klantinfo.getAchternaam(),
					klantinfo.getAdres(), klantinfo.getPostcode(),
					klantinfo.getEmail() });

		}
		return model;
	}

	/**
	 * 
	 * Klasse Table The Class Table.
	 */
	private class Table extends JTable {

		/** The klaar. */
		List<Boolean> klaar;

		/**
		 * Instantiates a new table.
		 *
		 * @param model
		 *            the model
		 * @param klaar
		 *            the klaar
		 */
		Table(TableModel model, List<Boolean> klaar) {
			super(model);
			this.klaar = klaar;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.JTable#prepareRenderer(javax.swing.table.TableCellRenderer
		 * , int, int)
		 */
		public Component prepareRenderer(TableCellRenderer renderer, int row,
				int column) {

			Component c = super.prepareRenderer(renderer, row, column);
			int modelRow = convertRowIndexToModel(row);
			// Color row based on a cell value
			// c.setBackground(getBackground());
			// c.setForeground(Color.PINK);
			if (klaar.get(modelRow) == true) {
				c.setBackground(new Color(17, 82, 20));
			} else {
				c.setBackground(new Color(107, 107, 23));
			}
			if (!isRowSelected(row)) {

				if (klaar.get(modelRow) == true) {
					// c.setForeground(Color.RED);
					if (modelRow % 2 == 0) {
						c.setBackground(new Color(227, 255, 202));

					} else {
						c.setBackground(new Color(207, 235, 182));
					}

				} else {
					if (modelRow % 2 == 0) {
						c.setBackground(new Color(253, 255, 238));
					} else {
						c.setBackground(new Color(243, 245, 228));
					}
				}
			}
			return c;
		}
	};
}