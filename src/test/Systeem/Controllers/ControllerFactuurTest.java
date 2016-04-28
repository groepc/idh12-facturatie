/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

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
import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Facturatie.Businesslogic.ControllerFactuur;
import Systeem.Klanten.Businesslogic.ControllerKlant;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerFactuurTest.
 */
public class ControllerFactuurTest {

	static DAOFactuur dao;
	static ControllerFactuur controller;
	static ArrayList<Factuur> testDataFactuur;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ControllerFactuur controller = new ControllerFactuur();
		ControllerFactuurTest.controller = controller;
		DAOFactuur mockedDAO = mock(DAOFactuur.class);

		try {
			when(mockedDAO.geefAlles()).thenReturn(testDataFactuur);
			controller = new ControllerFactuur(); // set dao Field field =
			Field field = ControllerFactuur.class.getDeclaredField("dao");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		try {

			dao = new DAOFactuur("xmlFilesTest/", "Facturen");

			dao.deleteDocument();
			dao.create("Facturen");
			testDataFactuur = new ArrayList<Factuur>();

			// Factuur nr word mee gegeven en klant bij de dummy data.
			testDataFactuur.add(Factories.factuurFactory(1, "1"));
			testDataFactuur.add(Factories.factuurFactory(2, "2"));
			testDataFactuur.add(Factories.factuurFactory(3, "3"));
			testDataFactuur.add(Factories.factuurFactory(4, "4"));
			testDataFactuur.add(Factories.factuurFactory(5, "5"));
			testDataFactuur.add(Factories.factuurFactory(6, "6"));
			testDataFactuur.get(5).setNaam("Jermey");
			testDataFactuur.add(Factories.factuurFactory(7, "7"));
			testDataFactuur.get(6).setNaam("naam7");
			for (Factuur v : testDataFactuur) {
				dao.opslaan(v);
			}
			dao.saveDocument();
		} catch (IOException e) {
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
	 * {@link Systeem.Facturatie.Businesslogic.ControllerFactuur#geefFacturen(Systeem.BusinessDomain.Klant)}
	 * .
	 */
	@Test
	public void testGeefFacturen() {
		// klant aanmken
		List<Behandelingtraject> bAL = new ArrayList<Behandelingtraject>();
		bAL.add(Factories.behandelingTraject("1"));

		List<Verzekering> vAL = new ArrayList<Verzekering>();
		vAL.add(Factories.verzekeringFactory("1",
				Factories.verzekeringmaatschappijFactory("1"), bAL));

		List<Diagnose> dAL = new ArrayList<Diagnose>();
		dAL.add(Factories.diagnoseFactory("22", bAL));

		List<Klant> kAL = new ArrayList<Klant>();
		kAL.add(Factories.klantFactory("1", vAL, dAL, bAL));
		kAL.add(Factories.klantFactory("2", vAL, dAL, bAL));
		kAL.add(Factories.klantFactory("3", vAL, dAL, bAL));

		// controller gebruiken klant megeven.

		Klant tKlant = kAL.get(1);

		// System.out.println(klant.getBsn());
		List<Factuur> factRes = new ArrayList<Factuur>();
		try {

			// Haalt de facturen voor de juiste klant uit de DAO
			factRes = dao.geefMeerdere(tKlant.getVoornaam(),
					SearchField.SECOND, SearchLevel.COMPLETE);

		} catch (IOException e) {
			// System.out.println("ERROR IN DAO");
			e.printStackTrace();
		}

		// assert eaqual result.
		assertEquals(1, factRes.size());

	}

	/**
	 * Test method for
	 * {@link Systeem.Facturatie.Businesslogic.ControllerFactuur#setBetaald(Systeem.BusinessDomain.Factuur)}
	 * .
	 */
	@Test
	public void testSetBetaald() {

		try {
			controller.setBetaald(testDataFactuur.get(3));
			assertEquals(true, testDataFactuur.get(3).getBetaald());
		} catch (Exception e) {
			fail(e.toString());
		}

	}

	/**
	 * Test method for
	 * {@link Systeem.Facturatie.Businesslogic.ControllerFactuur#maakFactuur(Systeem.BusinessDomain.Klant)}
	 * .
	 */
	@Test
	public void testMaakFactuur() {
		// klant aanmaken
		List<Behandelingtraject> bAL = new ArrayList<Behandelingtraject>();
		bAL.add(Factories.behandelingTraject("1"));

		List<Verzekering> vAL = new ArrayList<Verzekering>();
		vAL.add(Factories.verzekeringFactory("1",
				Factories.verzekeringmaatschappijFactory("1"), bAL));

		List<Diagnose> dAL = new ArrayList<Diagnose>();
		dAL.add(Factories.diagnoseFactory("22", bAL));

		List<Klant> kAL = new ArrayList<Klant>();
		kAL.add(Factories.klantFactory("1", vAL, dAL, bAL));

		String result = controller.maakFactuur(kAL.get(0));
		boolean eindresult = false;
		if (result.contains("Geslaagd")) {
			eindresult = true;
		} else {
			eindresult = false;
		}
		assertTrue(eindresult);

	}
}
