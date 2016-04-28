/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLDeletableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOVerzekeringmaatschappij.
 *
 * @author Jermey
 */
public class DAOVerzekeringmaatschappij extends
		XMLDeletableDAOBase<Verzekeringsmaatschappij, String> {

	/**
	 * Instantiates a new DAO verzekeringmaatschappij.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOVerzekeringmaatschappij() throws IOException {
		this(Settings.DAOVERZEKERINGMAATSCHAPPIJPAD,
				Settings.DAOVERZEKERINGMAATSCHAPPIJNAAM);
	}

	/**
	 * Instantiates a new DAO verzekeringmaatschappij.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOVerzekeringmaatschappij(final String bestandpad, final String naam)
			throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		} catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out
					.println("DAO Verzekeringmaatschappij class is aangeroepen");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Datastorage.Interfaces.IReadDAO#geef(java.lang.Object)
	 */
	/**
	 * Geef.
	 *
	 * @param key
	 *            the key
	 * @return the verzekeringsmaatschappij
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Verzekeringsmaatschappij geef(final String key) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekeringmaatschappij[@kvkNr=\"" + key + "\"]");
		final List<Verzekeringsmaatschappij> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geef functie van DAOVerzekeringsmaatschappij is aangeroepen");
			System.out.println(result.size() + " zijn er gevonden");
		}
		return result.isEmpty() ? null : result.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IReadDAO#geefMeerdere(java.lang.String)
	 */
	/**
	 * Geef meerdere.
	 *
	 * @param zoekString
	 *            the zoek string
	 * @param field
	 *            is the field that gets searched on FIRST : kvkNr SECOND : naam
	 *            THIRD : plaats
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Verzekeringmaatschappij objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Verzekeringsmaatschappij> geefMeerdere(final String zoekString,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekeringmaatschappij");
		final List<Verzekeringsmaatschappij> verzekeringenmaatschapijen = parse(
				nodes, zoekString, field, level);
		if (Settings.BUGGING) {
			System.out
					.println("geefMeerdere functie van DAOVerzekeringmaatschapijen is aangeroepen");
			for (final Verzekeringsmaatschappij v : verzekeringenmaatschapijen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringenmaatschapijen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Systeem.Datastorage.Interfaces.IReadDAO#geefAlles()
	 */
	/**
	 * Geef alles.
	 *
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Verzekeringsmaatschappij> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekeringmaatschappij");
		final List<Verzekeringsmaatschappij> verzekeringsmaatschappijen = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geefAlles functie van DAOVerzekeringmaatschappijen is aangeroepen");
			for (final Verzekeringsmaatschappij v : verzekeringsmaatschappijen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringsmaatschappijen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IEditableDAO#opslaan(java.lang.Object)
	 */
	/**
	 * Opslaan.
	 *
	 * @param object
	 *            the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public void opslaan(final Verzekeringsmaatschappij object)
			throws IOException {
		final Element root = super.getDocument().getRootElement();
		if (geef(object.getKvkNr()) != null) {
			final Element rootV = (Element) document.selectNodes(
					"//" + super.getName()
							+ "/Verzekeringmaatschappij[@kvkNr=\""
							+ object.getKvkNr() + "\"]/descendant-or-self::*")
					.get(0);
			rootV.detach();
		}

		final Element verzekeringMRoot = root.addElement(
				"Verzekeringmaatschappij").addAttribute("kvkNr",
				object.getKvkNr());
		verzekeringMRoot.addElement("naam").addText(object.getNaam());
		verzekeringMRoot.addElement("telefoonNummer").addText(
				object.getTelefoonnummer());
		verzekeringMRoot.addElement("adres").addText(object.getAdres());
		verzekeringMRoot.addElement("plaats").addText(object.getPlaats());
		verzekeringMRoot.addElement("iban").addText(object.getIban());
		verzekeringMRoot.addElement("postcode").addText(object.getPostcode());
		verzekeringMRoot.addElement("btwNr").addText(object.getBtwNr());
		if (Settings.BUGGING) {
			System.out
					.println("opslaan functie van DAOVerzekeringmaatschappij is aangeroepen");
			System.out.println(object + " is opgeslagen");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IDeletableDAO#verwijderen(java.lang.Object
	 * )
	 */
	@Override
	public void verwijderen(Verzekeringsmaatschappij object) {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekeringmaatschappij[@kvkNr=" + object.getKvkNr()
				+ "]/descendant-or-self::*");
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			current.detach();
		}

		if (Settings.BUGGING) {
			System.out
					.println("verwijderen functie van DAOVerzekeringmaatschappij is aangeroepen");
			System.out.println(object + " is verwijderd");
		}
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            the nodes
	 * @return list with parsed Verzekeringmaatschappij objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Verzekeringsmaatschappij> parse(final List<Node> nodes)
			throws IOException {
		return parse(nodes, null, null, null);
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            Nodes that needs to be searched and then parsed
	 * @param zoeken
	 *            Zoekstring
	 * @param field
	 *            is the field that gets searched on FIRST : kvkNr SECOND : naam
	 *            THIRD : plaats
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Verzekeringmaatschappij objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Verzekeringsmaatschappij> parse(final List<Node> nodes,
			final String zoeken, final IReadDAO.SearchField field,
			final IReadDAO.SearchLevel level) throws IOException {
		final List<Verzekeringsmaatschappij> verzekeringsmaatschappijen = new ArrayList<Verzekeringsmaatschappij>();

		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			@SuppressWarnings("unchecked")
			final List<Element> currentNodes = current.elements();

			boolean add = false;

			if (zoeken == null)
				add = true;
			else if (level == IReadDAO.SearchLevel.CONTAINS) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("kvkNr")
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(3).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.STARTSWITH) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("kvkNr")
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(3).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.COMPLETE) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("kvkNr")
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case THIRD:
					if (currentNodes.get(3).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			}

			if (add) {

				final String kvkNr = current.attributeValue("kvkNr");
				final String naam = (String) currentNodes.get(0).getText();
				final String telefoonNummer = (String) currentNodes.get(1)
						.getText();
				final String adres = (String) currentNodes.get(2).getText();
				final String plaats = (String) currentNodes.get(3).getText();
				final String iban = (String) currentNodes.get(4).getText();
				final String postcode = (String) currentNodes.get(5).getText();
				final String btwNr = (String) currentNodes.get(6).getText();

				final Verzekeringsmaatschappij verzekeringsmaatschappij = new Verzekeringsmaatschappij(
						naam, telefoonNummer, adres, plaats, kvkNr, iban,
						postcode, btwNr);
				verzekeringsmaatschappijen.add(verzekeringsmaatschappij);
			}

		}
		if (Settings.BUGGING) {
			System.out
					.println("parse functie van DAOVerzekeringmaatschappij is aangeroepen");
			for (final Verzekeringsmaatschappij v : verzekeringsmaatschappijen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringsmaatschappijen;
	}

}
