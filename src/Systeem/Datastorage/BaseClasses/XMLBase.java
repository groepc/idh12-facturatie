/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.BaseClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import Systeem.Settings.Settings;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class XMLBase.
 *
 * @param <T>
 *            the generic type
 */
public class XMLBase<T> {

	/** The bestandpad. */
	private final String	bestandpad;

	/** The name. */
	final private String	name;

	/** The document. */
	protected Document		document;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the bestandpad.
	 *
	 * @return the bestandpad
	 */
	public String getBestandpad() {
		return this.bestandpad;
	}

	/**
	 * Gets the document.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		if (Settings.BUGGING) {
			System.out.println("Document getter aangeroepen");
		}
		return document;

	}

	// wordt later in settings bestand opgeslagen
	/**
	 * Instantiates a new XML base.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param name
	 *            the name
	 */
	public XMLBase(final String bestandpad, final String name) {
		this.bestandpad = bestandpad;
		this.name = name.split(".xml")[0];
		if (Settings.BUGGING) {
			System.out.println("XML base class aangeroepen");
		}
	}

	/**
	 * Creates the document.
	 *
	 * @param root
	 *            the root
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final void create(final String root) throws IOException {
		final Document document = DocumentHelper.createDocument();
		document.addElement(root);

		final XMLWriter output = new XMLWriter(new FileWriter(new File(this.bestandpad + this.name + ".xml")));
		output.write(document);
		output.close();
		this.document = document;
		if (Settings.BUGGING) {
			System.out.println(this.bestandpad + this.name + ".xml is aangemaakt en toegekend aan variable document");
		}
	}

	/**
	 * Reads the document.
	 *
	 * @return the document
	 * @throws DocumentException
	 *             the document exception
	 */
	public Document read() throws DocumentException {
		final SAXReader reader = new SAXReader();
		final Document document = reader.read(this.bestandpad + this.name + ".xml");

		if (Settings.BUGGING) {
			System.out.println(this.bestandpad + this.name + ".xml is ingeladen");
		}
		return document;
	}

	/**
	 * Saves the document.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveDocument() throws IOException {
		final XMLWriter output = new XMLWriter(new FileWriter(new File(this.bestandpad + this.name + ".xml")));
		output.write(document);
		output.close();
		if (Settings.BUGGING) {
			System.out.println(this.bestandpad + this.name + ".xml is opgeslagen");
		}
	}
	/**
	 * Deletes the document.
	 */
	public void deleteDocument() {
		final File file = new File(this.bestandpad + this.name + ".xml");
		file.delete();
	}
}
