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
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLReadableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOBehandelingtraject. Is currently used as a IreadableDAOBase but
 * we by fault created it a IEditableDAOBase and we are keeping it like that
 * while we know it should be a IreadableDAOBase extend
 *
 * @author Jermey
 */
public class DAOBehandelingtraject extends
		XMLReadableDAOBase<Behandelingtraject, String> {

	/**
	 * Instantiates a new DAO behandelingtraject.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOBehandelingtraject() throws IOException {
		this(Settings.DAOBEHANDELINGTRAJECTPAD,
				Settings.DAOBEHANDELINGTRAJECTNAAM);
	}

	/**
	 * Instantiates a new DAO behandelingtraject.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOBehandelingtraject(final String bestandpad, final String naam)
			throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		} catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out.println("DAO Behandelingtraject class is aangeroepen");
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
	 * @return the behandelingtraject
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Behandelingtraject geef(final String key) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Behandeltraject[@behandelCode=\"" + key + "\"]");
		final List<Behandelingtraject> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geef functie van DAOBehandelingtraject is aangeroepen");
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
	 *            Zoekstring
	 * @param field
	 *            is the field that gets searched on FIRST : behandelCode SECOND
	 *            : naam THIRD : SECOND
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Behandelingtraject objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Behandelingtraject> geefMeerdere(String zoekString,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Behandeltraject");
		final List<Behandelingtraject> behandelingtrajecten = parse(nodes,
				zoekString, field, level);
		if (Settings.BUGGING) {
			System.out
					.println("geefMeerdere functie van DAO Behandelingtraject is aangeroepen");
			for (final Behandelingtraject b : behandelingtrajecten) {
				System.out.println(b + " is gevonden en mee gegeven");
			}
		}
		return behandelingtrajecten;
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
	public List<Behandelingtraject> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Behandeltraject");
		final List<Behandelingtraject> behandelingtrajecten = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geefAlles functie van DAO Behandelingtraject is aangeroepen");
			for (final Behandelingtraject b : behandelingtrajecten) {
				System.out.println(b + " is gevonden en mee gegeven");
			}
		}
		return behandelingtrajecten;
	}

	/**
	 * Parses the.
	 *
	 * @param nodes
	 *            the nodes
	 * @return the array list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Behandelingtraject> parse(final List<Node> nodes)
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
	 *            is the field that gets searched on FIRST : behandelCode SECOND
	 *            : naam THIRD : SECOND
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Behandelingtraject objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Behandelingtraject> parse(final List<Node> nodes,
			final String zoeken, final IReadDAO.SearchField field,
			final IReadDAO.SearchLevel level) throws IOException {
		final List<Behandelingtraject> behandelingtrajecten = new ArrayList<Behandelingtraject>();
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
					if (current.attributeValue("behandelCode")
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
				case THIRD:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.STARTSWITH) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("behandelCode")
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
				case THIRD:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.COMPLETE) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("behandelCode")
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				case SECOND:
				case THIRD:
					if (currentNodes.get(0).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			}

			if (add) {
				final String behandelcode = current
						.attributeValue("behandelCode");
				final String naam = currentNodes.get(0).getText();
				final double tarief = Double.valueOf(currentNodes.get(1)
						.getText());
				final double eigenBijdrage = Double.valueOf(currentNodes.get(2)
						.getText());
				final int aantalSessies = Integer.valueOf(currentNodes.get(3)
						.getText());

				final Behandelingtraject behandelingtraject = new Behandelingtraject(
						behandelcode, naam, tarief, eigenBijdrage,
						aantalSessies);
				behandelingtrajecten.add(behandelingtraject);
			}
		}
		if (Settings.BUGGING) {
			System.out
					.println("parse functie van DAO Behandelingtraject is aangeroepen");
			for (final Behandelingtraject b : behandelingtrajecten) {
				System.out.println(b + " is gevonden en mee gegeven");
			}
		}
		return behandelingtrajecten;
	}
}