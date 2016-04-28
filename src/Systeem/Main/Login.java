/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Systeem.Hoofdscherm.Hoofdscherm;
import Systeem.Settings.Settings;

/**
 * @author Mark
 *
 */
public class Login extends JFrame {
	/** Tekstveld van gebruikersnaam */
	private JTextField usernameField;
	/** Tekstveld van wachtwoord */
	private JPasswordField passwordField;
	/** Breedte van het JFrame 700px */
	private int sizeX = 700;
	/** Hoogte van het JFrame 450px */
	private int sizeY = 450;
	private boolean debug = false;

	/**
	 * Aanmaken van Login frame
	 */
	public Login() {
		// Set size, title, and properties
		this.setSize(new Dimension(sizeX, sizeY));
		this.setTitle("Facturatie & Incasso: Authenticatie vereist");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setLocationRelativeTo(null);

		// Create Spring Layout
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		// Create Panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 192));
		panel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(70, 70, 70)));
		panel.setVisible(true);

		// Add Panel to contentPane
		getContentPane().add(panel);

		// Set location of the Panel
		springLayout.putConstraint(SpringLayout.NORTH, panel, 140, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 190, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -140, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -190, SpringLayout.EAST, getContentPane());

		// Create labels en fields
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		JLabel passWord = new JLabel("Wachtwoord: ");
		JLabel userName = new JLabel("Gebruikernaam: ");
		JButton btnInloggen = new JButton("Inloggen");
		JButton btnSluiten = new JButton("Sluiten");

		// Add label and field to the JPanel
		panel.add(passWord);
		panel.add(passwordField);
		panel.add(usernameField);
		panel.add(userName);
		panel.add(btnInloggen);
		panel.add(btnSluiten);

		// Set fonts
		Font font = new Font("Tahoma", Font.PLAIN, 16);
		passWord.setFont(font);
		passwordField.setFont(font);
		userName.setFont(font);
		usernameField.setFont(font);
		btnInloggen.setFont(font);
		btnSluiten.setFont(font);

		// Add Springlayout for JPanel
		SpringLayout sl_panel = new SpringLayout();

		// Set location off all fields, labels and buttons
		sl_panel.putConstraint(SpringLayout.NORTH, btnInloggen, 90, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnInloggen, 70, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnSluiten, 90, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnSluiten, 240, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, userName, 15, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, userName, 125, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, usernameField, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, usernameField, 135, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, usernameField, 280, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, usernameField, 40, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, passwordField, 50, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, passwordField, 135, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, passwordField, 280, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, passwordField, 80, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, passWord, 55, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, passWord, 125, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);

		btnInloggen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().equals(Settings.DEFAULTGEBRUIKERSNAAM) & Arrays.equals(passwordField.getPassword(), Settings.DEFAULTPASSWORD.toCharArray())) {

					// Sluit dit inlogscherm
					dispose();

					// Start de hoofdapplicatie..
					Hoofdscherm frame = new Hoofdscherm();
					frame.setVisible(true);
				} else {

					// Geef fout melding (incorrte validatie)
					JOptionPane.showMessageDialog(Login.this, "Authenticatie gefaald\nMogelijk is uw gebruikersnaam en/of wachtwoord incorrect", "Authenticatie", JOptionPane.ERROR_MESSAGE);

					// Omlijn de textvelden rood
					usernameField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					passwordField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

					// Reset de waardes
					usernameField.setText(null);
					passwordField.setText(null);
				}
			}

		});

		// Action listener voor de sluitknop
		btnSluiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sluit dit scherm en de applicatie
				System.exit(0);
			}
		});

		// Voegt image toe aan JFrame
		try {
			getContentPane().add(image());
		} catch (Exception e) {

		}
	}

	/**
	 * Achtergrond image
	 * 
	 * @return d
	 */
	private JLabel image() {

		String path = Settings.PICTUREURL;
		if (debug) {
			System.out.println("Get Image from " + path);
		}

		try {
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel label = new JLabel(new ImageIcon(ScaledImage(image, sizeX, sizeY)));
			return label;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Achtergornd image vaste waarde geven
	 * 
	 * @param img
	 * @param w
	 * @param h
	 * @return d
	 */
	private Image ScaledImage(Image img, int w, int h) {
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		return resizedImage;
	}
}