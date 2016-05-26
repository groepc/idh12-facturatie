/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Facturatie.Presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Klant;
import Systeem.Facturatie.Businesslogic.ControllerFactuur;
import Systeem.Hoofdscherm.ParentFrame;
import Systeem.Klanten.Presentation.GUIKlantWeergeven;

// TODO: Auto-generated Javadoc
/**
 * The Class GUIFacturenOverzicht.
 */
public class GUIFacturenOverzicht extends JPanel {

	/** The date format. */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	/** The df. */
	private DecimalFormat df = new DecimalFormat("€ ###,##0.00");

	/** The list. */
	List<Factuur> fList = null;

	/** The table_1. */
	private JTable table_1 = new JTable();

	/** The unpaid. */
	private int unpaid = 0;

	/**
	 * 
	 * @param klant
	 * @param parent
	 * @param facturen
	 *            De lijst met facturen die moeten worden weergegeven.
	 */
	public GUIFacturenOverzicht(final Klant klant, final ParentFrame parent) {
		// Stelt de grootte van het panel in
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setPreferredSize(new Dimension(750, 550));

		// Maakt een manager object aan
		ControllerFactuur manager = new ControllerFactuur();

		// Roept geefFacturen aan
		fList = manager.geefFacturen(klant);

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		// Laad het resultaat van de mehtode createTableModel in de tabel
		table_1 = new JTable(createTableModel(fList)) {
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				// Color row based on a cell value

				int modelRow = convertRowIndexToModel(row);
				String paid = (String) getModel().getValueAt(modelRow, 7);
				String date = (String) getModel().getValueAt(modelRow, 4);
				Date result = null;
				try {
					result = dateFormat.parse(date);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date now = new Date();
				c.setBackground(new Color(88, 134, 48));
				if ("Nee".equals(paid)) {
					c.setBackground(new Color(149, 141, 22));
					if (now.compareTo(result) > 0) {
						c.setBackground(new Color(149, 6, 6));

					}
				}
				if (!isRowSelected(row)) {

					c.setBackground(new Color(227, 255, 202));
					if ("Nee".equals(paid)) {
						c.setBackground(new Color(255, 245, 87));
						if (now.compareTo(result) > 0) {
							c.setBackground(new Color(255, 70, 70));

						}
					}
				}
				return c;
			}
		};

		// Maakt een label dit het aantal gevonden facturen bij een klant geeft
		String labelInfo = "Error";
		if (fList.size() == 1) {
			labelInfo = "Er is " + fList.size() + " factuur gevonden bij "
					+ klant.getVoornaam() + " " + klant.getAchternaam() + "("
					+ klant.getBsn() + ")";
		}
		if (fList.size() == 0) {

			labelInfo = "Er zijn geen facturen gevonden bij "
					+ klant.getVoornaam() + " " + klant.getAchternaam() + "("
					+ klant.getBsn() + ")";
		}
		if (fList.size() >= 2) {
			labelInfo = "Er zijn " + fList.size() + " facturen gevonden bij "
					+ klant.getVoornaam() + " " + klant.getAchternaam() + "("
					+ klant.getBsn() + ")";
		}

		JLabel label = new JLabel(labelInfo);
		springLayout.putConstraint(SpringLayout.NORTH, label, 45,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 180,
				SpringLayout.WEST, this);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(label);

		Date result = null;
		// Maak een label, alleen voor onbetaalde schulden
		for (int i = 0; i < table_1.getRowCount(); i++) {
			String paidBill = (String) table_1.getValueAt(i, 7);
			String date = (String) table_1.getValueAt(i, 4);

			System.out.println(paidBill);
			try {
				result = dateFormat.parse(date);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(date);
			Date now = new Date();
			if ("Nee".equals(paidBill)) {
				System.out.println("onbetaalde rekening");
				if (now.compareTo(result) > 0) {
					System.out.println("datum verlopen!");
					unpaid = unpaid + 1;
					System.out.println(unpaid);
				}
			}
		}
		if (unpaid >= 1) {
			System.out.println("Maak debt label");
			String warningLabel = unpaid == 1 ? unpaid
					+ " Factuur vereist uw aandacht!" : unpaid
					+ " Facturen vereisen uw aandacht!";
			JLabel warning = new JLabel(warningLabel);
			springLayout.putConstraint(SpringLayout.NORTH, warning, 66,
					SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, warning, 190,
					SpringLayout.WEST, this);
			warning.setFont(new Font("Tahoma", Font.BOLD, 18));
			add(warning);
		}

		// Maakt de help knop
		JButton btnNewButton = new JButton("Help");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Geef de tekst voor het help scherm
				JOptionPane
						.showMessageDialog(
								GUIFacturenOverzicht.this,
								"Factrurenoverzicht: \n- Dubbelklik op een factuur in de tabel om deze weer te geven\n- Klik terug om terug te gaan naar de klant\n- De gele lijnen geven aan dat een factuur nog niet betaalt is, de rode lijnen dat ze over de vervaldatum heen zitten",
								"Help", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 680,
				SpringLayout.WEST, this);
		add(btnNewButton);

		// Maakt de terug knop
		JButton terugKnop = new JButton("Terug naar Klant");
		add(terugKnop);
		terugKnop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.changePanel(new GUIKlantWeergeven(parent, klant), 0);

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, terugKnop, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, terugKnop, 10,
				SpringLayout.WEST, this);

		// Maakt de JScrollpane
		JScrollPane scrollPane_1 = new JScrollPane();

		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 65,
				SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, -10,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 740,
				SpringLayout.WEST, this);
		add(scrollPane_1);

		// Voegt deze toe aan de tabel
		scrollPane_1.setViewportView(table_1);
		springLayout.putConstraint(SpringLayout.NORTH, table_1, 0,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, table_1, 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, table_1, 20,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, table_1, 0,
				SpringLayout.EAST, this);

		// Voeg een Tableheader toe
		table_1.getTableHeader().setReorderingAllowed(false);

		// Geeft par het huidige object FacturenOverzicht mee
		final GUIFacturenOverzicht par = this;
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				// Logica die de juiste factuur weergeeft en opent naar het
				// klikken
				if (event.getClickCount() == 2) {
					int idToGet = (int) table_1.getValueAt(
							table_1.getSelectedRow(), 0);
					for (int i = 0; i < fList.size(); i++) {
						Factuur gevondenFactuur = fList.get(i);
						if (gevondenFactuur.getNr() == idToGet) {
							System.out.println(idToGet);
							System.out.println(gevondenFactuur
									.getBetalingsCondities());
							JFrame frame = new GUIFactuurWeergeven(
									gevondenFactuur, par);
							frame.setPreferredSize(new Dimension(850, 550));
							String title = gevondenFactuur.getNaam();
							frame.setTitle("Detail weergave voor factuur: "
									+ title);
							frame.setVisible(true);

						}
					}

				}

			}

		});

	}

	/**
	 * @param fList
	 *            the f list
	 * @return the default table model
	 */
	private DefaultTableModel createTableModel(List<Factuur> fList) {
		// Maakt het table model aan
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int rom, int column) {
				// Maakt de tabel niet editbaar
				return false;
			}
		};

		// Render ro's red
		DefaultTableCellRenderer colortext = new DefaultTableCellRenderer();
		{
			colortext.setForeground(Color.RED);
		}

		// Maakt de kolommen
		model.addColumn("FactuurID");
		model.addColumn("FactuurNr");
		model.addColumn("Bedrijfsnaam");
		model.addColumn("Factuurdatum");
		model.addColumn("Vervaldatum");
		model.addColumn("Vergoed");
		model.addColumn("Te betalen");
		model.addColumn("Betaald?");

		// Loopt door de facturen en zet ze in het model
		for (int i = 0; i < fList.size(); i++) {
			Factuur factuurinfo = fList.get(i);
			String betaald = "Niet ingevuld";
			if (factuurinfo.getBetaald() == false) {
				betaald = "Nee";
			} else {
				betaald = "Ja";
			}

			model.addRow(new Object[] { factuurinfo.getNr(),
					factuurinfo.getNaam(),
					factuurinfo.getVerzekeringmaatschappij(),
					dateFormat.format(factuurinfo.getFactuurDatum()),
					dateFormat.format(factuurinfo.getVervalDatum()),
					df.format(factuurinfo.getVergoeding()),
					df.format(factuurinfo.getNietVergoedeKosten()), betaald });

		}
		return model;
	}

	protected void updateSelf() {
		// Aanroepbaar vanuit een andere klasse, maak en laad het model opnieuw
		table_1.setModel(createTableModel(fList));
	}
}