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
import Systeem.BusinessDomain.Diagnose;
import Systeem.Datastorage.Settings;
import Systeem.Datastorage.BaseClasses.XMLEditableDAOBase;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAODiagnose. Is currently used as a IreadableDAOBase but we by
 * fault created it a IEditableDAOBase and we are keeping it like that while we
 * know it should be a IreadableDAOBase extend
 *
 * @author Jermey
 */
public class DAODiagnose extends XMLEditableDAOBase<Diagnose, String> {

	/**
	 * Instantiates a new DAO diagnose.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAODiagnose() throws IOException {
		this(Settings.DAODIAGNOSEPAD, Settings.DAODIAGNOSENAAM);
	}

	/**
	 * Instantiates a new DAO diagnose.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DAODiagnose(final String bestandpad, final String naam) throws IOException {
		super(bestandpad, naam);
		try {
			super.document = super.read();
		}
		catch (DocumentException e) {
			super.create(naam);
		}
		if (Settings.BUGGING) {
			System.out.println("DAO Diagnose class is aangeroepen");
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
	 * @return the diagnose
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	final public Diagnose geef(final String key) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName() + "/Diagnose[@diagnoseCode=\"" + key + "\"]");
		final List<Diagnose> result = parse(nodes);
		if (Settings.BUGGING) {
			System.out.println("geef functie van DAODiagnose is aangeroepen");
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
	 *            is the field that gets searched on FIRST : diagnoseCode SECOND
	 *            : elementBehandelingtrajecten -> behandelCode THIRD : SECOND
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Diagnose objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public List<Diagnose> geefMeerdere(final String zoekString, final IReadDAO.SearchField field, final IReadDAO.SearchLevel level) throws IOException {
		// by
		// Warlord
		// on
		// 25-10-14
		// 6:35
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName() + "/Diagnose");
		final List<Diagnose> diagnoses = parse(nodes, zoekString, field, level);
		if (Settings.BUGGING) {
			System.out.println("geefMeerdere functie van DAO Diagnose is aangeroepen");
			for (final Diagnose d : diagnoses) {
				System.out.println(d + " is gevonden en mee gegeven");
			}
		}
		return diagnoses;
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
	public List<Diagnose> geefAlles() throws IOException {
		@SuppressWarnings("unchecked")
		final List<Node> nodes = document.selectNodes("//" + super.getName() + "/Diagnose");
		final List<Diagnose> diagnoses = parse(nodes);
		if (Settings.BUGGING) {
			System.out.println("geefAlles functie van DAO Diagnose is aangeroepen");
			for (final Diagnose d : diagnoses) {
				System.out.println(d + " is gevonden en mee gegeven");
			}
		}
		return diagnoses;
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
	final private List<Diagnose> parse(final List<Node> nodes) throws IOException {
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
	 *            is the field that gets searched on FIRST : diagnoseCode SECOND
	 *            : elementBehandelingtrajecten -> behandelCode THIRD : SECOND
	 * @param level
	 *            is the lvl of detail
	 * @return list with parsed Diagnose objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	final private List<Diagnose> parse(final List<Node> nodes, final String zoeken, final IReadDAO.SearchField field, final IReadDAO.SearchLevel level) throws IOException {
		final ArrayList<Diagnose> diagnoses = new ArrayList<Diagnose>();
		DAOBehandelingtraject daoBehandelingtraject = null;
		for (final Iterator<Node> iter = nodes.iterator(); iter.hasNext();) {
			final Element current = (Element) iter.next();
			@SuppressWarnings("unchecked")
			final List<Element> currentNodes = current.elements();
			boolean add = false;
			if (zoeken == null)
				add = true;
			else if (level == IReadDAO.SearchLevel.CONTAINS) {
				switch (field) {
					case FIRST :
						if (current.attributeValue("diagnoseCode").toLowerCase(Locale.ENGLISH).contains(zoeken.toLowerCase(Locale.ENGLISH)))
							add = true;
						break;
					case SECOND :
					case THIRD :
						@SuppressWarnings("unchecked")
						final List<Element> elementBehandelingtrajecten = currentNodes.get(0).elements();
						for (final Element v : elementBehandelingtrajecten) {
							if (v.toString().toLowerCase(Locale.ENGLISH).contains(zoeken.toLowerCase(Locale.ENGLISH)))
								add = true;
						}
						break;

				}
			}
			else if (level == IReadDAO.SearchLevel.STARTSWITH) {
				switch (field) {
					case FIRST :
						if (current.attributeValue("diagnoseCode").toLowerCase(Locale.ENGLISH).startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
							add = true;
						break;
					case SECOND :
					case THIRD :
						@SuppressWarnings("unchecked")
						final List<Element> elementBehandelingtrajecten = currentNodes.get(0).elements();
						for (final Element v : elementBehandelingtrajecten) {
							if (v.toString().toLowerCase(Locale.ENGLISH).startsWith(zoeken.toLowerCase(Locale.ENGLISH)))
								add = true;
						}
						break;
				}
			}
			else if (level == IReadDAO.SearchLevel.COMPLETE) {
				switch (field) {
					case FIRST :
						if (current.attributeValue("diagnoseCode").toLowerCase(Locale.ENGLISH).equals(zoeken.toLowerCase(Locale.ENGLISH)))
							add = true;
						break;
					case SECOND :
					case THIRD :
						@SuppressWarnings("unchecked")
						final List<Element> elementBehandelingtrajecten = currentNodes.get(0).elements();
						for (final Element v : elementBehandelingtrajecten) {
							if (v.toString().toLowerCase(Locale.ENGLISH).equals(zoeken.toLowerCase(Locale.ENGLISH)))
								add = true;
						}
						break;
				}
			}

			if (add) {

				final String diagnoseCode = current.attributeValue("diagnoseCode");

				final List<Behandelingtraject> behandelingtrajectenLijst = new ArrayList<Behandelingtraject>();

				final Element sessiesRoot = currentNodes.get(0);
				@SuppressWarnings("unchecked")
				final List<Element> elementBehandelingtrajecten = sessiesRoot.elements();
				if (daoBehandelingtraject == null && !elementBehandelingtrajecten.isEmpty())
					daoBehandelingtraject = new DAOBehandelingtraject(super.getBestandpad(), "Behandelingtrajecten");
				for (final Element v : elementBehandelingtrajecten) {
					behandelingtrajectenLijst.add(daoBehandelingtraject.geef(v.getText()));
				}

				final Diagnose diagnose = new Diagnose(diagnoseCode, behandelingtrajectenLijst);
				diagnoses.add(diagnose);
			}
		}
		if (Settings.BUGGING) {
			System.out.println("parse functie van DAOVerzekering is aangeroepen");
			for (final Diagnose d : diagnoses) {
				System.out.println(d + " is gevonden en mee gegeven");
			}
		}
		return diagnoses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.Datastorage.Interfaces.IEditableDAO#opslaan(java.lang.Object)
	 */
	@Override
	final public void opslaan(final Diagnose object) throws IOException {
		final Element root = super.getDocument().getRootElement();
		if (geef(object.getDiagnoseCode()) != null) {
			final Element rootK = (Element) document.selectNodes(
					"//" + super.getName() + "/Diagnose[@diagnoseCode=\"" + object.getDiagnoseCode()
							+ "\"]/descendant-or-self::*").get(0);
			rootK.detach();
		}

		final Element diagnoseRoot = root.addElement("Diagnose").addAttribute("diagnoseCode", object.getDiagnoseCode());
		final Element behandeltrajectenRoot = diagnoseRoot.addElement("Behandelingtrajecten");
		if (object.getBehandelingstrajecten() != null) {
			for (final Behandelingtraject b : object.getBehandelingstrajecten()) {
				behandeltrajectenRoot.addElement("behandeltraject").addText(b.getBehandelcode());
			}
		}
		if (Settings.BUGGING) {
			System.out.println("opslaan functie van DAO Diagnose is aangeroepen");
			System.out.println(object + " is opgeslagen");
		}
	}
}
