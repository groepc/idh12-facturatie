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

import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLDeletableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOVerzekering.
 *
 * @author Jermey
 */
public class DAOVerzekering extends XMLDeletableDAOBase<Verzekering, String> {

	/**
	 * Instantiates a new DAO verzekering.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOVerzekering() throws IOException {
		this(Settings.DAOVERZEKERINGPAD, Settings.DAOVERZEKERINGNAAM);
	}

	/**
	 * Instantiates a new DAO verzekering.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOVerzekering(final String bestandpad, final String naam)
			throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		} catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out.println("DAO Verzekering class is aangeroepen");
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
	 * @return the verzekering
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Verzekering geef(final String key) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekering[@id=\"" + key + "\"]");

		final List<Verzekering> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geef functie van DAOVerzekering is aangeroepen");
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
	 *            is the field that gets searched on FIRST : id SECOND : naam
	 *            THIRD : Verzekeringmaatschappij -> kvkNr
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Verzekering objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Verzekering> geefMeerdere(final String zoekString,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekering");
		final List<Verzekering> verzekeringen = parse(nodes, zoekString, field,
				level);
		if (Settings.BUGGING) {
			System.out
					.println("geefMeerdere functie van DAOVerzekering is aangeroepen");
			for (final Verzekering v : verzekeringen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringen;
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
	public List<Verzekering> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekering");
		final List<Verzekering> verzekeringen = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geefAlles functie van DAOVerzekering is aangeroepen");
			for (final Verzekering v : verzekeringen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringen;
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
	public void opslaan(final Verzekering object) throws IOException {
		final Element root = super.getDocument().getRootElement();

		if (geef(object.getId()) != null) {
			final Element rootV = (Element) document.selectNodes(
					"//" + super.getName() + "/Verzekering[@id=\""
							+ object.getId() + "\"]/descendant-or-self::*")
					.get(0);
			rootV.detach();
		}

		final Element verzekeringRoot = root.addElement("Verzekering")
				.addAttribute("id", object.getId());
		verzekeringRoot.addElement("naam").addText(object.getNaam());
		verzekeringRoot.addElement("omschrijving").addText(
				object.getOmschrijving());
		verzekeringRoot.addElement("type").addText(object.getType());
		if (object.getVerzekeringmaatschappij() != null) {
			verzekeringRoot.addElement("verzekeringMaatschappij").addText(
					object.getVerzekeringmaatschappij().getKvkNr());
		} else {
			verzekeringRoot.addElement("verzekeringMaatschappij").addText("NA");
		}
		final Element gedekteBehandelingtrajectenRoot = verzekeringRoot
				.addElement("GedekteBehandelingtrajecten");
		if (object.getGedekteBehandeltrajecten() != null) {
			for (final Behandelingtraject b : object
					.getGedekteBehandeltrajecten()) {
				gedekteBehandelingtrajectenRoot.addElement(
						"gedeketeBehandeltraject").addText(b.getBehandelcode());
			}
		}
		if (Settings.BUGGING) {
			System.out
					.println("opslaan functie van DAOVerzekering is aangeroepen");
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
	/**
	 * Verwijderen.
	 *
	 * @param object
	 *            the object
	 */
	@Override
	public void verwijderen(final Verzekering object) {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Verzekering[@id=" + object.getId()
				+ "]/descendant-or-self::*");
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			current.detach();
		}

		if (Settings.BUGGING) {
			System.out
					.println("verwijderen functie van DAOVerzekering is aangeroepen");
			System.out.println(object + " is verwijderd");
		}
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            Nodes that needs to be searched and then parsed
	 * @return list with parsed Verzekering objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Verzekering> parse(final List<Node> nodes) throws IOException {
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
	 *            is the field that gets searched on FIRST : id SECOND : naam
	 *            THIRD : Verzekeringmaatschappij -> kvkNr
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Verzekering objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Verzekering> parse(final List<Node> nodes,
			final String zoeken, final IReadDAO.SearchField field,
			final IReadDAO.SearchLevel level) throws IOException {
		final List<Verzekering> verzekeringen = new ArrayList<Verzekering>();
		DAOBehandelingtraject daoBehandeltraject = null;
		DAOVerzekeringmaatschappij daoVerzekeringmaatschappij = null;
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
					if (current.attributeValue("id")
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
					if (current.attributeValue("id")
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
					if (current.attributeValue("id")
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

				final String id = current.attributeValue("id");
				final String naam = (String) currentNodes.get(0).getText();
				final String omschrijving = (String) currentNodes.get(1)
						.getText();
				final String type = (String) currentNodes.get(2).getText();
				Verzekeringsmaatschappij verzekeringmaatschappij = null;
				final List<Behandelingtraject> gedekteBehandelingstrajecten = new ArrayList<Behandelingtraject>();

				if (!currentNodes.get(3).getText().equals("NA")) {
					if (daoVerzekeringmaatschappij == null)
						daoVerzekeringmaatschappij = new DAOVerzekeringmaatschappij(
								super.getBestandpad(),
								"Verzekeringmaatschappijen");
					verzekeringmaatschappij = daoVerzekeringmaatschappij
							.geef((String) currentNodes.get(3).getText());
				}
				if (!currentNodes.get(4).getText().equals("NA")) {

					final Element gedekteBehandelingstrajectenRoot = (Element) currentNodes
							.get(4);
					@SuppressWarnings("unchecked")
					final List<Element> gedekteBehandelingstrajectenLijst = gedekteBehandelingstrajectenRoot
							.elements();
					if (daoBehandeltraject == null
							&& !gedekteBehandelingstrajectenLijst.isEmpty())
						daoBehandeltraject = new DAOBehandelingtraject(
								super.getBestandpad(), "Behandelingtrajecten");

					for (final Element b : gedekteBehandelingstrajectenLijst) {
						gedekteBehandelingstrajecten.add(daoBehandeltraject
								.geef(b.getText()));
					}
				}
				final Verzekering verzekering = new Verzekering(naam, type,
						omschrijving, id, verzekeringmaatschappij,
						gedekteBehandelingstrajecten);
				verzekeringen.add(verzekering);
			}

		}
		if (Settings.BUGGING) {
			System.out
					.println("parse functie van DAOVerzekering is aangeroepen");
			for (final Verzekering v : verzekeringen) {
				System.out.println(v + " is gevonden en mee gegeven");
			}
		}
		return verzekeringen;
	}
}
