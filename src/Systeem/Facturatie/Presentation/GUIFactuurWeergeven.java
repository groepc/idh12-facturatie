/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Facturatie.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import Systeem.BusinessDomain.Factuur;
import Systeem.Facturatie.Businesslogic.ControllerFactuur;

// TODO: Auto-generated Javadoc
/**
 * Deze klasse verzorgt de detail weergave van een factuur.
 * 
 * @author Mark
 */
public class GUIFactuurWeergeven extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Instantiates a new GUI factuur weergeven.
	 *
	 * @param factuur
	 *            De factuur die moet worden weergegeven.
	 * @param overzicht
	 *            the overzicht
	 */
	public GUIFactuurWeergeven(final Factuur factuur,
			final GUIFacturenOverzicht overzicht) {
		// Stelt groottee en layout in
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		// Maakt het label dat het factuurNaam weergeeft
		JLabel lblNewLabel = new JLabel("U bekijkt de details van factuur: "
				+ factuur.getNaam());
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 20,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 300,
				SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);

		// Lees het HTML bestand
		String htmlLine = "";
		String htmlText = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"facturenHTML\\" + factuur.getNaam() + ".html"));

			try {

				while ((htmlLine = in.readLine()) != null) {
					System.out.println(htmlLine);
					htmlText = htmlText + htmlLine;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(htmlText);
		// Maak de EditotPane
		JEditorPane textArea = new JEditorPane();

		// Maakt hem niet editbaar
		textArea.setEditable(false);

		// Voegt de HTMLkit toe aan de editorPane
		HTMLEditorKit kit = new HTMLEditorKit();
		textArea.setEditorKit(kit);

		// Voegt een aantal CSS regels toe
		StyleSheet styleSheet = kit.getStyleSheet();

		styleSheet.addRule("h1 {font-size: 22px;}");
		// styleSheet.addRule("p.big {font-size: 10px;}");

		// Stel de positie in van de Editorpane
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 54,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, 600,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, 822,
				SpringLayout.WEST, contentPane);
		// Voegt de HTMLtext toe aan de EditorPane
		textArea.setText(htmlText);
		// System.out.println(htmlText);

		// Maakt een Scrollpane
		JScrollPane scrollPane = new JScrollPane(textArea);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 54,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 600,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 822,
				SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);

		// Maak de sluitknop
		JButton btnNewButton = new JButton("Sluit detail weergave");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Sluit dit frame
				dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -4,
				SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 0,
				SpringLayout.WEST, scrollPane);
		contentPane.add(btnNewButton);

		// Maakt de betaalknop
		final JButton betaaldKnop = new JButton("Zet deze factuur op betaald");

		// Wanneer betaald == true, maakt de betaalknop onklikbaar
		if (factuur.getBetaald() == true) {
			betaaldKnop.setEnabled(false);
		}

		betaaldKnop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Voegt een bevestiging toe dmv JOptionPane
				int userInput = JOptionPane
						.showConfirmDialog(
								GUIFactuurWeergeven.this,
								"Zet de status van deze factuur op betaald: \n\nWeet u zeker dat u deze factuur op betaald wilt zetten?\nDit kan niet ongedaan gemaakt worden!",
								"Status factuur wijzigen",
								JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {

					// Roept methode in controller aan om betaald aan te passen
					// in factuur object
					ControllerFactuur controller = new ControllerFactuur();
					controller.setBetaald(factuur);

					// Maakt betaalknop onzichtbaar
					betaaldKnop.setEnabled(false);

					// Roept methode aan in overzicht om de tabel bij te werken
					overzicht.updateSelf();
				}

			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, betaaldKnop, 4,
				SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, betaaldKnop, 0,
				SpringLayout.EAST, contentPane);
		contentPane.add(betaaldKnop);

	}
}