/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.DAO.DAOBehandelingtraject;
import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.DAO.DAOVerzekering;
import Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Klanten.Businesslogic.ControllerKlant;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerVerzekeringenTest.
 */
public class ControllerVerzekeringenTest {

	static DAOVerzekeringmaatschappij			daoVMP;
	static DAOVerzekering						daoVZR;
	static DAOBehandelingtraject				daoBHT;

	/** The test data. */
	static ArrayList<Verzekering>				testVZR;
	static ArrayList<Behandelingtraject>		testBHT;
	static ArrayList<Verzekeringsmaatschappij>	testVMP;
	static ControllerVerzekeringen				controller;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {

		// create mocked DAO
		controller = new ControllerVerzekeringen();
		// create arraylist of Verzekeringsmaatschappij and add one from factory
		List<Verzekeringsmaatschappij> vmp = new ArrayList<Verzekeringsmaatschappij>();
		vmp.add(Factories.verzekeringmaatschappijFactory("12345678"));

		List<Behandelingtraject> bht = new ArrayList<Behandelingtraject>();
		bht.add(Factories.behandelingTraject("666"));

		// create arraylist of Verzekering and add one from factory
		List<Verzekering> vzr = new ArrayList<Verzekering>();
		vzr.add(Factories.verzekeringFactory("1234", vmp.get(0), bht));
		vzr.add(Factories.verzekeringFactory("1235", vmp.get(0), bht));

		try {
			daoVMP = new DAOVerzekeringmaatschappij("xmlFilesTest/",
					"Verzekeringmaatschapijen");
			daoVZR = new DAOVerzekering("xmlFilesTest/", "Verzekeringen");
			daoBHT = new DAOBehandelingtraject("xmlFilesTest/",
					"Behandelingtrajecten");

			testVZR = new ArrayList<Verzekering>();
			testBHT = new ArrayList<Behandelingtraject>();
			testVMP = new ArrayList<Verzekeringsmaatschappij>();

			testVMP.add(Factories.verzekeringmaatschappijFactory("12345678"));
			testVMP.add(Factories.verzekeringmaatschappijFactory("12345679"));
			testBHT.add(Factories.behandelingTraject("666"));
			testVZR.add(Factories.verzekeringFactory("1", testVMP.get(0),
					testBHT));
			testVZR.add(Factories.verzekeringFactory("1", testVMP.get(1),
					testBHT));

			for (Verzekeringsmaatschappij a : testVMP) {
				daoVMP.opslaan(a);
			}
			for (Verzekering c : testVZR) {
				daoVZR.opslaan(c);
			}
			daoVMP.saveDocument();
			daoVZR.saveDocument();

			DAOVerzekering mockedDAO = mock(DAOVerzekering.class);

			try {
				when(mockedDAO.geefAlles()).thenReturn(testVZR);
				when(
						mockedDAO.geefMeerdere("12345678", SearchField.THIRD,
								SearchLevel.CONTAINS)).thenReturn(testVZR);
				when(mockedDAO.geef("1")).thenReturn(testVZR.get(0));
				Field field = ControllerVerzekeringen.class
						.getDeclaredField("daoVerzekering");
				field.setAccessible(true);
				field.set(controller, mockedDAO);
			}
			catch (Exception e) {
				fail(e.toString());
			}

		}
		catch (IOException e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#geefVerzekering(java.lang.String)}
	 * .
	 */
	@Test
	public void testGeefVerzekering() {
		Verzekering expectedResult = testVZR.get(0);
		Verzekering result = null;
		try {
			result = controller.geefVerzekering("1");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#zoekVerzekeringen(java.lang.String)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testZoekVerzekeringen() throws IOException {
		DAOVerzekering mockedDAO = mock(DAOVerzekering.class);
		List<Verzekering> expectedResult;

		expectedResult = mockedDAO.geefMeerdere("test", SearchField.SECOND,
				SearchLevel.CONTAINS);
		List<Verzekering> result = null;
		try {
			result = controller.zoekVerzekeringen("test");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#zoekVerzekeringenVanMaatschappij(Systeem.BusinessDomain.Verzekeringsmaatschappij)}
	 * .
	 */
	@Test
	public void testZoekVerzekeringenVanMaatschappij() {
		List<Verzekering> expectedResult = testVZR;
		List<Verzekering> result = null;
		try {
			result = controller
					.zoekVerzekeringenVanMaatschappij(testVMP.get(0));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#haalAlleVerzekeringenOp()}
	 * .
	 */
	@Test
	public void testHaalAlleVerzekeringenOp() {
		List<Verzekering> expectedResult = testVZR;
		List<Verzekering> result = null;
		try {
			result = controller.haalAlleVerzekeringenOp();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#verzekeringToeveoegen(Systeem.BusinessDomain.Verzekering)}
	 * .
	 */
	@Test
	public void testVerzekeringToeveoegen() {
		Verzekering expectedResult = testVZR.get(0);
		Verzekering result = null;
		try {
			controller.verzekeringToeveoegen(testVZR.get(0));
			result = controller.geefVerzekering("1");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);

	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#verzekeringWijzigen(Systeem.BusinessDomain.Verzekering)}
	 * .
	 */
	@Test
	public void testVerzekeringWijzigen() {
		Verzekering expectedResult = testVZR.get(0);
		Verzekering result = null;
		try {
			controller.verzekeringWijzigen(testVZR.get(0));
			result = controller.geefVerzekering("1");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringen#soteerOpType(java.util.List)}
	 * .
	 */
	@Test
	public void testSoteerOpType() {
		List<Verzekering> expectedResult = testVZR;
		List<Verzekering> result = null;
		try {
			result = controller.soteerOpType(testVZR);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(expectedResult, result);
	}

}
