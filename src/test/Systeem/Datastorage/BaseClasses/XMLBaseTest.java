/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Datastorage.BaseClasses;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.dom4j.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Systeem.Datastorage.BaseClasses.XMLBase;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class XMLBaseTest.
 */
public class XMLBaseTest {

	/** The xml base. */
	@SuppressWarnings("rawtypes")
	private static XMLBase	xmlBase;

	/** The document. */
	private static Document	document;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		xmlBase = new XMLBase<Object>("xmlFilesTest/", "test");
		document = null;
	}

	/**
	 * Sets the up after class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		xmlBase = new XMLBase<Object>("xmlFilesTest/", "test");
		xmlBase.deleteDocument();
	}

	/**
	 * Test should create save read file.
	 */
	@Test
	public void testShouldCreateSaveReadFile() {
		try {
			xmlBase.create("Testen");
			xmlBase.saveDocument();
			document = xmlBase.read();
		}
		catch (Exception e) {
			fail(e.toString());
		}

		assertNotNull(document);
	}

	/**
	 * Test should delete file.
	 */
	@Test
	public void testShouldDeleteFile() {
		xmlBase.deleteDocument();
		try {
			document = xmlBase.read();
		}
		catch (Exception e) {
		}
		assertNull(document);
	}
}
