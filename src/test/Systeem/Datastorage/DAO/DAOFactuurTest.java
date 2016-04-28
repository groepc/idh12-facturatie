/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Datastorage.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Factuur;
import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class DAOFactuurTest.
 */
public class DAOFactuurTest {

	/** The dao. */
	static DAOFactuur			dao;

	/** The test data. */
	static ArrayList<Factuur>	testData;
	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			dao = new DAOFactuur("xmlFilesTest/", "Facturen");

			dao.deleteDocument();
			dao.create("Facturen");
			testData = new ArrayList<Factuur>();

			testData.add(Factories.factuurFactory(1, "1"));
			testData.add(Factories.factuurFactory(2, "2"));
			testData.add(Factories.factuurFactory(3, "3"));
			testData.add(Factories.factuurFactory(4, "4"));
			testData.add(Factories.factuurFactory(5, "5"));
			testData.add(Factories.factuurFactory(6, "6"));
			testData.get(5).setNaam("6naam");
			testData.add(Factories.factuurFactory(7, "7"));
			testData.get(6).setNaam("naam7");
			for (Factuur v : testData) {
				dao.opslaan(v);
			}
			dao.saveDocument();
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Tear down.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@After
	public void tearDown() throws IOException {

	}

	/**
	 * Test should geef.
	 */
	@Test
	public void testShouldGeef() {

		Factuur expectedResult = testData.get(0);
		Factuur result = null;

		try {
			result = dao.geef(testData.get(0).getNr());
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
	/**
	 * Test should erdere. TODO
	 * 
	 */

	@Test
	public void testShouldGeefMeerdereOpIdContains() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = dao.geefMeerdere(String.valueOf(testData.get(0).getNr()), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op naam contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamContains() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));
		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op klant contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpKlantContains() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(String.valueOf(expectedResult.get(0).getKlant()), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op id complete.
	 */
	@Test
	public void testShouldGeefMeerdereOpIdComplete() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(String.valueOf(expectedResult.get(0).getNr()), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op naam complete.
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamComplete() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op klant complete.
	 */
	@Test
	public void testShouldGeefMeerdereOpKlantComplete() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(String.valueOf(expectedResult.get(0).getKlant()), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op id sw.
	 */
	@Test
	public void testShouldGeefMeerdereOpIdSW() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(String.valueOf(expectedResult.get(0).getNr()), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op naam sw.
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamSW() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(6));
		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op klant sw.
	 */
	@Test
	public void testShouldGeefMeerdereOpKlantSW() {
		List<Factuur> expectedResult = new ArrayList<Factuur>();
		expectedResult.add(testData.get(0));

		List<Factuur> result = null;
		try {
			result = (ArrayList<Factuur>) dao.geefMeerdere(String.valueOf(expectedResult.get(0).getKlant()), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test wijzigen. TODO
	 */
	@Test
	public void testWijzigen() {

		Factuur expectedResult = testData.get(6);
		expectedResult.setNaam("Gewijzigd");

		Factuur result = null;
		try {
			dao.opslaan(expectedResult);
			result = dao.geef(expectedResult.getNr());
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef alles.
	 */
	@Test
	public void testShouldGeefAlles() {
		List<Factuur> expectedResult = testData;

		List<Factuur> result = null;

		try {
			result = dao.geefAlles();
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOFactuur#verwijderen(Systeem.BusinessDomain.Klant)}
	 * 
	 */
	// @Test
	public void testShouldGeefVerwijderen() {

		Factuur expectedResult = null;

		Factuur result = null;
		try {
			dao.verwijderen(testData.get(0));
			result = dao.geef(testData.get(0).getNr());
		}
		catch (IOException e) {
			System.out.println("Geef werkt niet correct");
		}

		assertEquals(expectedResult, result);
	}

}