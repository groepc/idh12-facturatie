/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.DAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import Systeem.BusinessDomain.Factuur;
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLDeletableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOFactuur.
 *
 * @author Jermey
 */
public class DAOFactuur extends XMLDeletableDAOBase<Factuur, Integer> {

	/**
	 * Instantiates a new DAO factuur.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOFactuur() throws IOException {
		this(Settings.DAOFACTUURPAD, Settings.DAOFACTUURNAAM);
	}

	/**
	 * Instantiates a new DAO factuur.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAOFactuur(final String bestandpad, final String naam)
			throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		} catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out.println("DAO Factuur class is aangeroepen");
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
	 * @return the factuur
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public Factuur geef(final Integer key) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Factuur[@nr=\"" + key + "\"]");
		final List<Factuur> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out.println("geef functie van DAOFactuur is aangeroepen");
			System.out.println(result.size() + " zijn er gevonden");
		}
		return result.isEmpty() ? null : result.get(0);
	}

	/**
	 * Geef meerdere.
	 *
	 * @param zoeken
	 *            Zoekstring
	 * @param field
	 *            is the field that gets searched on FIRST : nr SECOND : naam
	 *            THIRD : factuurDatum
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Factuur objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Factuur> geefMeerdere(final String zoeken,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Factuur");
		final List<Factuur> facturen = parse(nodes, zoeken, field, level);
		if (Settings.BUGGING) {
			System.out
					.println("geefMeerdere functie van DAO Factuur is aangeroepen");
			for (final Factuur f : facturen) {
				System.out.println(f + " is gevonden en mee gegeven");
			}
		}
		return facturen;
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
	public List<Factuur> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Factuur");
		final List<Factuur> facturen = parse(nodes);
		if (Settings.BUGGING) {
			System.out
					.println("geefAlles functie van DAO Factuur  is aangeroepen");
			for (final Factuur f : facturen) {
				System.out.println(f + " is gevonden en mee gegeven");
			}
		}
		return facturen;
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
	public void opslaan(final Factuur object) throws IOException {
		final Element root = super.getDocument().getRootElement();
		if (geef(object.getNr()) != null) {
			final Element rootK = (Element) document.selectNodes(
					"//" + super.getName() + "/Factuur[@nr=\"" + object.getNr()
							+ "\"]/descendant-or-self::*").get(0);
			rootK.detach();
		}

		final Element factuurRoot = root.addElement("Factuur").addAttribute(
				"nr", String.valueOf(object.getNr()));
		factuurRoot.addElement("naam").addText(object.getNaam().toString());
		factuurRoot.addElement("factuurDatum").addText(
				object.getFactuurDatum().toString());
		factuurRoot.addElement("vervalDatum").addText(
				object.getVervalDatum().toString());
		factuurRoot.addElement("vergoeding").addText(
				object.getVergoeding().toString());
		factuurRoot.addElement("nietVergoeding").addText(
				object.getNietVergoedeKosten().toString());
		factuurRoot.addElement("beginEigenrisico").addText(
				String.valueOf(object.getBeginEigenRisico()));
		factuurRoot.addElement("btwPercentage").addText(
				Double.toString(object.getBtwPercentage()));
		factuurRoot.addElement("betalingsCondities").addText(
				object.getBetalingsCondities());
		factuurRoot.addElement("betaald").addText(
				String.valueOf(object.getBetaald()));

		factuurRoot.addElement("klant").addText(object.getKlant());
		factuurRoot.addElement("verzekeringmaatschappij").addText(
				object.getVerzekeringmaatschappij());
		factuurRoot.addElement("fysioPraktijk").addText(
				String.valueOf(object.getFysioPraktijk()));
		if (Settings.BUGGING) {
			System.out
					.println("opslaan functie van DAO Facturen is aangeroepen");
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
	public void verwijderen(Factuur object) {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Factuur[@nr=" + object.getNr() + "]/descendant-or-self::*");
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			current.detach();
		}

		if (Settings.BUGGING) {
			System.out
					.println("verwijderen functie van DAOFactuur is aangeroepen");
			System.out.println(object + " is verwijderd");
		}
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
	private List<Factuur> parse(final List<Node> nodes) throws IOException {
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
	 *            is the field that gets searched on FIRST : nr SECOND : naam
	 *            THIRD : BSN
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Factuur objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<Factuur> parse(final List<Node> nodes, final String zoeken,
			final IReadDAO.SearchField field, final IReadDAO.SearchLevel level)
			throws IOException {
		final ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		final SimpleDateFormat parserSDF = new SimpleDateFormat(
				"EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);

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
					if (current.attributeValue("nr")
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
					if (currentNodes.get(9).getText()
							.toLowerCase(Locale.ENGLISH)
							.contains(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.STARTSWITH) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("nr")
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
					if (currentNodes.get(9).getText()
							.toLowerCase(Locale.ENGLISH)
							.startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			} else if (level == IReadDAO.SearchLevel.COMPLETE) {
				switch (field) {
				case FIRST:
					if (current.attributeValue("nr")
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
					if (currentNodes.get(9).getText()
							.toLowerCase(Locale.ENGLISH)
							.equals(zoeken.toLowerCase(Locale.ENGLISH)))
						add = true;
					break;
				}
			}

			if (add) {
				final Integer nr = Integer
						.valueOf(current.attributeValue("nr"));
				final String naam = currentNodes.get(0).getText();

				Date factuurDatum = null;
				Date vervalDatum = null;
				try {
					factuurDatum = parserSDF.parse(currentNodes.get(1)
							.getText());
					vervalDatum = parserSDF
							.parse(currentNodes.get(2).getText());

				} catch (ParseException e) {
					if (Settings.BUGGING) {
						System.out.println(factuurDatum.toString()
								+ " kon niet worden geparsed");
						System.out.println(vervalDatum.toString()
								+ " kon niet worden geparsed");
					}
				}

				final double vergoeding = Double.valueOf(currentNodes.get(3)
						.getText());
				final double nietVergoedekosten = Double.valueOf(currentNodes
						.get(4).getText());
				final double beginEigenrisico = Double.valueOf(currentNodes
						.get(5).getText());
				final double btwPercentage = Double.valueOf(currentNodes.get(6)
						.getText());
				final String betalingsCondities = (String) currentNodes.get(7)
						.getText();
				final boolean betaald = Boolean.valueOf(currentNodes.get(8)
						.getText());
				final String klant = currentNodes.get(9).getText();
				final String verzekeringMaatschappij = currentNodes.get(10)
						.getText();
				final int fysioPraktijk = Integer.valueOf(currentNodes.get(11)
						.getText());

				final Factuur factuur = new Factuur(nr, naam, factuurDatum,
						vervalDatum, vergoeding, nietVergoedekosten,
						beginEigenrisico, btwPercentage, betalingsCondities,
						betaald, klant, verzekeringMaatschappij, fysioPraktijk);

				facturen.add(factuur);
			}

		}
		if (Settings.BUGGING) {
			System.out.println("parse functie van DAO Factuur is aangeroepen");
			for (final Factuur f : facturen) {
				System.out.println(f + " is gevonden en mee gegeven");
			}
		}
		return facturen;
	}

	/**
	 * Gives all the id's.
	 *
	 * @return the array list with id's
	 */
	public List<Integer> geefIds() {
		final List<Integer> idList = new ArrayList<Integer>();
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName()
				+ "/Factuur[@nr]");
		for (Node n : nodes) {
			Element e = (Element) n;
			idList.add(Integer.valueOf(e.attributeValue("nr")));
		}
		Collections.sort(idList);
		return idList;
	}

}