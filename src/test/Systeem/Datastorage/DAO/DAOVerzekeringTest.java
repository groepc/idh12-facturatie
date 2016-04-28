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
import Systeem.BusinessDomain.Verzekering;
import Systeem.Datastorage.DAO.DAOVerzekering;
import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOVerzekeringTest.
 *
 * @author Jermey
 */
public class DAOVerzekeringTest {

	/** The dao verzekering. */
	static DAOVerzekering		daoVerzekering;

	/** The test data. */
	static List<Verzekering>	testData;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			daoVerzekering = new DAOVerzekering("xmlFilesTest/", "Verzekeringen");
			daoVerzekering.deleteDocument();
			daoVerzekering.create("Verzekeringen");

			testData = new ArrayList<>();

			List<Behandelingtraject> btL = new ArrayList<Behandelingtraject>();
			btL.add(Factories.behandelingTraject("2"));

			testData.add(Factories.verzekeringFactory("1", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.add(Factories.verzekeringFactory("2", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.add(Factories.verzekeringFactory("3", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.add(Factories.verzekeringFactory("4", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.add(Factories.verzekeringFactory("5", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.add(Factories.verzekeringFactory("6", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.get(5).setNaam("6test");
			testData.add(Factories.verzekeringFactory("7", Factories.verzekeringmaatschappijFactory("1"), btL));
			testData.get(6).setNaam("test7");

			for (Verzekering v : testData) {
				daoVerzekering.opslaan(v);
			}
			daoVerzekering.saveDocument();
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOVerzekering#geef(java.lang.String)}.
	 */
	@Test
	public void testShouldGeef() {
		Verzekering expectedResult = testData.get(0);

		Verzekering result = null;

		try {

			result = daoVerzekering.geef(expectedResult.getId());
		}
		catch (IOException e) {
			fail(e.toString());
		}

		assertEquals(expectedResult, result);
	}
	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOVerzekering#geefMeerdere(java.lang.String, Systeem.Datastorage.Interfaces.IReadDAO.SearchField, Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel)}
	 * .
	 */
	@Test
	public void testShouldGeefMeerdereOpNaamStartwith() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(6));
		List<Verzekering> result = null;

		try {

			result = daoVerzekering.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op id startwith.
	 */
	@Test
	public void testShouldGeefMeerdereOpIDStartwith() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));

		List<Verzekering> result = null;

		try {

			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getId(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.STARTSWITH);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op kvk startwith.
	 */
	@Test
	public void testShouldGeefMeerdereOpKVKStartwith() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));

		List<Verzekering> result = null;

		try {

			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getVerzekeringmaatschappij().getKvkNr(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.STARTSWITH);
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
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));

		List<Verzekering> result = null;

		try {

			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op id contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpIDContains() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));

		List<Verzekering> result = null;

		try {

			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getId(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op verzekering contains.
	 */
	@Test
	public void testShouldGeefMeerdereOpVerzekeringContains() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));
		List<Verzekering> result = null;

		try {
			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getVerzekeringmaatschappij().getKvkNr(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.CONTAINS);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op naam.
	 */
	@Test
	public void testShouldGeefMeerdereOpNaam() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));

		List<Verzekering> result = null;

		try {
			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getNaam(), IReadDAO.SearchField.SECOND, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op id.
	 */
	@Test
	public void testShouldGeefMeerdereOpID() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));

		List<Verzekering> result = null;

		try {

			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getId(), IReadDAO.SearchField.FIRST, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}

	/**
	 * Test should geef meerdere op verzekering.
	 */
	@Test
	public void testShouldGeefMeerdereOpVerzekering() {
		List<Verzekering> expectedResult = new ArrayList<Verzekering>();
		expectedResult.add(testData.get(0));
		expectedResult.add(testData.get(1));
		expectedResult.add(testData.get(2));
		expectedResult.add(testData.get(3));
		expectedResult.add(testData.get(4));
		expectedResult.add(testData.get(5));
		expectedResult.add(testData.get(6));

		List<Verzekering> result = null;

		try {
			result = (ArrayList<Verzekering>) daoVerzekering.geefMeerdere(expectedResult.get(0).getVerzekeringmaatschappij().getKvkNr(), IReadDAO.SearchField.THIRD, IReadDAO.SearchLevel.COMPLETE);
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
	/**
	 * Test method for {@link Systeem.Datastorage.DAO.DAOVerzekering#Wijzigen()}
	 * .
	 */
	@Test
	public void testShouldWijzigen() {
		Verzekering expectedResult = testData.get(6);
		expectedResult.setNaam("Gewijzigd");

		Verzekering result = null;

		try {
			daoVerzekering.opslaan(expectedResult);
			result = daoVerzekering.geef(expectedResult.getId());
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOVerzekering#geefAlles()}.
	 */
	@Test
	public void testShouldGeefAlles() {
		List<Verzekering> expectedResult = testData;

		List<Verzekering> result = null;

		try {

			daoVerzekering.saveDocument();
			result = daoVerzekering.geefAlles();
		}
		catch (IOException e) {
			fail(e.toString());
		}
		assertEquals(expectedResult, result);
	}
	/**
	 * Test method for
	 * {@link Systeem.Datastorage.DAO.DAOVerzekering#verwijderen(Systeem.BusinessDomain.Verzekering)}
	 * 
	 */
	@Test
	public void testShouldGeefVerwijderen() {

		Verzekering expectedResult = null;

		Verzekering result = null;
		try {
			daoVerzekering.verwijderen(testData.get(0));
			result = daoVerzekering.geef(testData.get(0).getId());
		}
		catch (IOException e) {
			System.out.println("Geef werkt niet correct");
		}

		assertEquals(expectedResult, result);
	}

}
