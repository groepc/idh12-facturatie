/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Klanten.Businesslogic.ControllerKlant;
import static org.mockito.Mockito.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerKlantTest.
 */
public class ControllerKlantTest {

	static List<Klant> testData;

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
		// setup
		List<Behandelingtraject> bAL = new ArrayList<Behandelingtraject>();
		bAL.add(Factories.behandelingTraject("1"));

		List<Verzekering> vAL = new ArrayList<Verzekering>();
		vAL.add(Factories.verzekeringFactory("1",
				Factories.verzekeringmaatschappijFactory("1"), bAL));

		List<Diagnose> dAL = new ArrayList<Diagnose>();
		dAL.add(Factories.diagnoseFactory("22", bAL));

		testData = new ArrayList<Klant>();
		testData.add(Factories.klantFactory("1", vAL, dAL, bAL));
		testData.add(Factories.klantFactory("2", vAL, dAL, bAL));
		testData.add(Factories.klantFactory("3", vAL, dAL, bAL));
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
	 * {@link Systeem.Klanten.Businesslogic.ControllerKlant#geefKlant(java.lang.String)}
	 * .
	 */
	@Test
	public void testGeefKlant() {

		ControllerKlant controller = null;

		DAOKlant mockedDAO = mock(DAOKlant.class);

		try {
			when(mockedDAO.geef("1")).thenReturn(testData.get(0));
			controller = new ControllerKlant(); // set dao Field field =
			Field field = ControllerKlant.class.getDeclaredField("daoKlant");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		Klant result = null;
		Klant expectedResult = testData.get(0);

		try {
			result = controller.geefKlant("1");
		} catch (IOException e) {
			fail(e.toString());
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Klanten.Businesslogic.ControllerKlant#zoekKlanten(java.lang.String)}
	 * .
	 * 
	 * @param SECOND
	 * @param COMPLETE
	 * 
	 * @param FIRST
	 * @param COMPLETE
	 * @throws IOException
	 */
	@Test
	public void testZoekKlanten() throws IOException {
		ControllerKlant controller = null;

		DAOKlant mockedDAO = mock(DAOKlant.class);

		try {
			when(
					mockedDAO.geefMeerdere("1", IReadDAO.SearchField.FIRST,
							IReadDAO.SearchLevel.STARTSWITH)).thenReturn(
					testData);
			when(
					mockedDAO.geefMeerdere("1", IReadDAO.SearchField.SECOND,
							IReadDAO.SearchLevel.CONTAINS)).thenReturn(
					new ArrayList<Klant>());
			when(
					mockedDAO.geefMeerdere("1", IReadDAO.SearchField.THIRD,
							IReadDAO.SearchLevel.CONTAINS)).thenReturn(
					new ArrayList<Klant>());
			controller = new ControllerKlant(); // set dao Field field =
			Field field = ControllerKlant.class.getDeclaredField("daoKlant");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		List<Klant> result = null;
		List<Klant> expectedResult = testData;

		try {
			result = controller.zoekKlanten("1");
		} catch (IOException e) {
			fail(e.toString());
		}

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Klanten.Businesslogic.ControllerKlant#klantOpslaan(Systeem.BusinessDomain.Klant)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testKlantOpslaan() throws IOException {

		ControllerKlant controller = new ControllerKlant();
		Klant klant = new Klant("125819846", "Willem", "van Strien",
				"Vaartweg 186", "5106 NG", "Dongen", new Date(), "06-51851124",
				"wvstrien@jalem.nl", 360.00, 50.00, true, null, false, "Man");
		DAOKlant mockedDAO = mock(DAOKlant.class);

		try {
			controller = new ControllerKlant(); // set dao Field field =
			Field field = ControllerKlant.class.getDeclaredField("daoKlant");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}
		try {
			controller.klantOpslaan(klant);
		} catch (Exception e) {
		}
		verify(mockedDAO).opslaan(klant);
	}

	/**
	 * Test method for
	 * {@link Systeem.Klanten.Businesslogic.ControllerKlant#geefAlleKlanten()}.
	 */
	@Test
	public void testGeefAlleKlanten() {

		ControllerKlant controller = null;

		DAOKlant mockedDAO = mock(DAOKlant.class);

		try {
			when(mockedDAO.geefAlles()).thenReturn(testData);
			controller = new ControllerKlant(); // set dao Field field =
			Field field = ControllerKlant.class.getDeclaredField("daoKlant");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		List<Klant> result = null;
		List<Klant> expectedResult = testData;

		try {
			result = controller.geefAlleKlanten();
		} catch (IOException e) {
			fail(e.toString());
		}

		assertEquals(expectedResult, result);

	}
}
