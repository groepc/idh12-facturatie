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
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class DAOVerzekeringmaatschappijTest.
 */
public class DAOVerzekeringmaatschappijTest {

	/** The dao. */
	static DAOVerzekeringmaatschappij dao;

	/** The test data. */
	static ArrayList<Verzekeringsmaatschappij> testData;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			// weet even niet hoe ik dit anders kan doen behalve de test data
			// steeds handmatig te vernieuwen
			dao = new DAOVerzekeringmaatschappij("xmlFilesTest/",
					"Verzekeringmaatschappijen");
			dao.deleteDocument();
			dao.create("Verzekeringmaatschappijen");
			testData = new ArrayList<Verzekeringsmaatschappij>();
			testData.add(Factories.verzekeringmaatschappijFactory("1"));
			testData.add(Factories.verzekeringmaatschappijFactory("2"));
			testData.add(Factories.verzekeringmaatschappijFactory("3"));
			testData.add(Factories.verzekeringmaatschappijFactory("4"));
			testData.add(Factories.verzekeringmaatschappijFactory("5"));
			testData.add(Factories.verzekeringmaatschappijFactory("6"));
			testData.get(5).setNaam("6naam");
			testData.get(5).setAdres("6adres");
			testData.get(5).setPlaats("6plaats");
			testData.add(Factories.verzekeringmaatschappijFactory("7"));
			testData.get(6).setNaam("naam7");
			testData.get(6).setAdres("adres7");
			testData.get(6).setPlaats("plaats7");
			for (Verzekeringsmaatschappij v : testData) {
				dao.opslaan(v);
			}
			dao.saveDocument();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Test should opslaan geef maatschappij.
	 */
	@Test
	public void testShouldGeefMaatschappij() {

		Verzekeringsmaatschappij expectedResult = testData.get(0);

		Verzekeringsmaatschappij result = null;

		try {
			result = dao.geef(expectedResult.getKvkNr());
		} catch (IOException e) {
			System.out.println("Fatal test failed system will exit");
			System.exit(0);
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test shouldopslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpkvkNrEnContains() {

		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getKvkNr(),
					IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.CONTAINS);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamEnContains() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));
		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao.geefMeerdere(
					expectedResult.get(0).getNaam(),
					IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpPlaatsEnContains() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao.geefMeerdere(
					expectedResult.get(0).getPlaats(),
					IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.CONTAINS);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpkvkNrEnStartsWith() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao
					.geefMeerdere(expectedResult.get(0).getKvkNr(),
							IReadDAO.SearchField.FIRST,
							IReadDAO.SearchLevel.STARTSWITH);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamEnStartsWith() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(6));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getNaam(),
					IReadDAO.SearchField.SECOND,
					IReadDAO.SearchLevel.STARTSWITH);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpPlaatsEnStartsWith() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(6));
		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao
					.geefMeerdere(expectedResult.get(0).getPlaats(),
							IReadDAO.SearchField.THIRD,
							IReadDAO.SearchLevel.STARTSWITH);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpkvkNrEnComplete() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao.geefMeerdere(
					expectedResult.get(0).getKvkNr(),
					IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.COMPLETE);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamEnComplete() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Verzekeringsmaatschappij> result = null;

		try {

			result = (ArrayList<Verzekeringsmaatschappij>) dao.geefMeerdere(
					expectedResult.get(0).getNaam(),
					IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef meerdere. TODO
	 */
	@Test
	public void testShouldGeefMeerdereOpPlaatsEnComplete() {
		List<Verzekeringsmaatschappij> expectedResult = new ArrayList<Verzekeringsmaatschappij>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = (ArrayList<Verzekeringsmaatschappij>) dao.geefMeerdere(
					expectedResult.get(0).getPlaats(),
					IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.COMPLETE);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should wijzigen. TODO
	 */
	@Test
	public void testShouldWijzigen() {

		Verzekeringsmaatschappij expectedResult = testData.get(6);
		expectedResult.setNaam("Gewijzigd");

		Verzekeringsmaatschappij result = null;

		try {
			dao.opslaan(expectedResult);
			result = dao.geefMeerdere(expectedResult.getNaam(),
					IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE)
					.get(0);
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should opslaan geef alles.
	 */
	@Test
	public void testShouldGeefAlles() {

		List<Verzekeringsmaatschappij> expectedResult = testData;

		List<Verzekeringsmaatschappij> result = null;

		try {
			result = dao.geefAlles();
		} catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij#verwijderen(Systeem.BusinessDomain.Klant)}
	 * 
	 */
	@Test
	public void testShouldGeefVerwijderen() {

		Verzekeringsmaatschappij expectedResult = null;

		Verzekeringsmaatschappij result = null;
		try {
			dao.verwijderen(testData.get(0));
			result = dao.geef(testData.get(0).getKvkNr());
		} catch (IOException e) {
			System.out.println("Geef werkt niet correct");
		}

		assertEquals(expectedResult, result);
	}
}
