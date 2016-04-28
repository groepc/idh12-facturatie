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
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * @author Jermey The Class DAOKlantTest.
 */
public class DAOKlantTest {

	/** The dao. */
	static DAOKlant		dao;

	/** The test data. */
	static List<Klant>	testData;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			dao = new DAOKlant("xmlFilesTest/", "Klanten");
			dao.deleteDocument();
			dao.create("Klanten");

			List<Behandelingtraject> btL = new ArrayList<Behandelingtraject>();
			btL.add(Factories.behandelingTraject("1"));

			List<Verzekering> vL = new ArrayList<Verzekering>();
			vL.add(Factories.verzekeringFactory("1", Factories.verzekeringmaatschappijFactory("1"), btL));

			List<Diagnose> dL = new ArrayList<Diagnose>();
			dL.add(Factories.diagnoseFactory("1", btL));

			testData = new ArrayList<Klant>();
			testData.add(Factories.klantFactory("1", vL, dL, btL));
			testData.add(Factories.klantFactory("2", vL, dL, btL));
			testData.add(Factories.klantFactory("3", vL, dL, btL));
			testData.add(Factories.klantFactory("4", vL, dL, btL));
			testData.add(Factories.klantFactory("5", vL, dL, btL));
			testData.add(Factories.klantFactory("6", vL, dL, btL));
			testData.get(5).setVoornaam("6vnaam");
			testData.get(5).setAchternaam("6anaam");
			testData.get(5).setPlaats("6plaats");
			testData.add(Factories.klantFactory("7", vL, dL, btL));
			testData.get(6).setVoornaam("vnaam7");
			testData.get(6).setAchternaam("anaam7");
			testData.get(6).setPlaats("plaats7");
			for (Klant v : testData) {
				dao.opslaan(v);
			}
			dao.saveDocument();
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
		Klant expectedResult = testData.get(0);

		Klant result = null;

		try {
			result = dao.geef(expectedResult.getBsn());
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
	public void testShouldGeefMeerdereOpBSNComplete() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getBsn(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.COMPLETE);
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
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getVoornaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op achternaam complete.
	 */
	@Test
	public void testShouldGeefMeerdereOpAchternaamComplete() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getAchternaam(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op bsnsw.
	 */
	@Test
	public void testShouldGeefMeerdereOpBSNSW() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getBsn(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.STARTSWITH);
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
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getVoornaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op achternaam sw.
	 */
	@Test
	public void testShouldGeefMeerdereOpAchternaamSW() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getAchternaam(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op bsn contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpBSNContains() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getBsn(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op naam comtains.
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamComtains() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getVoornaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op achternaam contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpAchternaamContains() {
		List<Klant> expectedResult = new ArrayList<Klant>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Klant> result = null;

		try {
			result = dao.geefMeerdere(expectedResult.get(0).getAchternaam(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
	/**
	 * Test should wijzigen. TODO
	 */
	@Test
	public void testShouldWijzigen() {

		Klant expectedResult = testData.get(6);
		expectedResult.setVoornaam("Gewijzigd");

		Klant result = null;

		try {
			dao.opslaan(expectedResult);
			result = dao.geef(expectedResult.getBsn());
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

		List<Klant> expectedResult = testData;

		List<Klant> result = null;
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
	 * {@link Systeem.Datastorage.DAO.DAOKlant#verwijderen(Systeem.BusinessDomain.Klant)}
	 * 
	 */
	@Test
	public void testShouldGeefVerwijderen() {

		Klant expectedResult = null;

		Klant result = null;
		try {
			dao.verwijderen(testData.get(0));
			result = dao.geef(testData.get(0).getBsn());
		}
		catch (IOException e) {
			System.out.println("Geef werkt niet correct");
		}

		assertEquals(expectedResult, result);
	}
}