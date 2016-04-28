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

import org.junit.Before;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.Datastorage.DAO.DAOBehandelingtraject;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class DAOBehandelingtrajectTest.
 */
public class DAOBehandelingtrajectTest {

	/** The dao. */
	static DAOBehandelingtraject			dao;

	/** The test data. */
	static ArrayList<Behandelingtraject>	testData;
	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			dao = new DAOBehandelingtraject("xmlFilesTest/", "Behandelingtrajecten");
			testData = new ArrayList<Behandelingtraject>();
			testData.add(Factories.behandelingTraject("1"));
			testData.add(Factories.behandelingTraject("2"));
			testData.add(Factories.behandelingTraject("3"));
			testData.add(Factories.behandelingTraject("4"));
			testData.add(Factories.behandelingTraject("5"));
			testData.add(Factories.behandelingTraject("6"));
			testData.get(5).setNaam("6naam");
			testData.add(Factories.behandelingTraject("7"));
			testData.get(6).setNaam("naam7");
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Test should geef.
	 */
	@Test
	public void testShouldGeef() {
		Behandelingtraject expectedResult = testData.get(0);
		Behandelingtraject result = null;

		try {
			result = dao.geef(testData.get(0).getBehandelcode());
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamComplete() {
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Behandelingtraject> result = null;

		try {

			result = dao.geefMeerdere(String.valueOf(expectedResult.get(0).getNaam()), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE);
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
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(6));
		List<Behandelingtraject> result = null;

		try {

			result = (ArrayList<Behandelingtraject>) dao.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.STARTSWITH);
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
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));
		List<Behandelingtraject> result = null;

		try {

			result = (ArrayList<Behandelingtraject>) dao.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op behandelcode complete.
	 */
	@Test
	public void testShouldGeefMeerdereOpBehandelcodeComplete() {
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));
		List<Behandelingtraject> result = null;

		try {

			result = (ArrayList<Behandelingtraject>) dao.geefMeerdere(String.valueOf(expectedResult.get(0)
					.getBehandelcode()), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op behandelcode sw.
	 */
	@Test
	public void testShouldGeefMeerdereOpBehandelcodeSW() {
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));

		List<Behandelingtraject> result = null;

		try {

			result = (ArrayList<Behandelingtraject>) dao.geefMeerdere(expectedResult.get(0)
					.getBehandelcode(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op behandelcode contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpBehandelcodeContains() {
		List<Behandelingtraject> expectedResult = new ArrayList<Behandelingtraject>();
		expectedResult.add(testData.get(0));

		List<Behandelingtraject> result = null;

		try {

			result = (ArrayList<Behandelingtraject>) dao.geefMeerdere(expectedResult.get(0)
					.getBehandelcode(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.CONTAINS);
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
		List<Behandelingtraject> expectedResult = testData;

		List<Behandelingtraject> result = null;

		try {

			result = dao.geefAlles();
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
}
