/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Klant;
import Systeem.Datastorage.DAO.DAOBehandelingtraject;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.Interfaces.IReadDAO;
import Systeem.Klanten.Businesslogic.ControllerKlant;
import Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten;
import Systeem.Verzekeringen.Presentation.customRenderers.BehandelingListRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerBehandelTrajectenTest.
 */
public class ControllerBehandelTrajectenTest {

	static List<Behandelingtraject> testData;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData = new ArrayList<>();
		testData.add(Factories.behandelingTraject("001"));
		testData.add(Factories.behandelingTraject("002"));
		testData.add(Factories.behandelingTraject("003"));
		testData.add(Factories.behandelingTraject("004"));
		testData.add(Factories.behandelingTraject("005"));
		testData.add(Factories.behandelingTraject("006"));
		testData.add(Factories.behandelingTraject("007"));
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
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
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten#haalBehandelingTrajectenOp(boolean)}
	 * .
	 */
	@Test
	public void testHaalBehandelingTrajectenOp() {
		ControllerBehandelTrajecten controller = null;

		DAOBehandelingtraject mockedDAO = mock(DAOBehandelingtraject.class);

		try {
			when(mockedDAO.geefAlles()).thenReturn(testData);
			controller = new ControllerBehandelTrajecten(); // set dao Field
															// field =
			Field field = ControllerBehandelTrajecten.class
					.getDeclaredField("daoBehandelingtraject");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		List<Behandelingtraject> result = null;

		List<Behandelingtraject> expectedResult = testData;

		result = controller.haalBehandelingTrajectenOp(false);

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten#zoekBehandelTrajecten(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testZoekBehandelTrajecten() throws IOException {
		ControllerBehandelTrajecten controller = null;

		DAOBehandelingtraject mockedDAO = mock(DAOBehandelingtraject.class);

		try {
			when(
					mockedDAO.geefMeerdere("001", IReadDAO.SearchField.SECOND,
							IReadDAO.SearchLevel.CONTAINS))
					.thenReturn(testData);
			when(
					mockedDAO.geefMeerdere("005", IReadDAO.SearchField.FIRST,
							IReadDAO.SearchLevel.CONTAINS)).thenReturn(
					new ArrayList<Behandelingtraject>());
			when(
					mockedDAO.geefMeerdere("007", IReadDAO.SearchField.THIRD,
							IReadDAO.SearchLevel.COMPLETE)).thenReturn(
					new ArrayList<Behandelingtraject>());
			controller = new ControllerBehandelTrajecten(); // set dao Field
			// field =
			Field field = ControllerBehandelTrajecten.class
					.getDeclaredField("daoBehandelingtraject");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		List<Behandelingtraject> result = null;
		List<Behandelingtraject> expectedResult = testData;

		result = controller.zoekBehandelTrajecten("001", false);

		assertEquals(expectedResult, result);
	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerBehandelTrajecten#haalBasisBehandelingenOp()}
	 * .
	 */
	@Test
	public void testHaalBasisBehandelingenOp() {

		ControllerBehandelTrajecten controller = null;

		DAOBehandelingtraject mockedDAO = mock(DAOBehandelingtraject.class);

		try {
			when(mockedDAO.geefAlles()).thenReturn(testData);
			controller = new ControllerBehandelTrajecten(); // set dao Field
															// field =
			Field field = ControllerBehandelTrajecten.class
					.getDeclaredField("daoBehandelingtraject");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		List<Behandelingtraject> result = null;

		List<Behandelingtraject> basisbehandelingen = new ArrayList<Behandelingtraject>();
		basisbehandelingen.add(testData.get(0));
		basisbehandelingen.add(testData.get(4));
		basisbehandelingen.add(testData.get(6));
		List<Behandelingtraject> expectedResult = basisbehandelingen;

		result = controller.haalBasisBehandelingenOp();

		assertEquals(expectedResult, result);
	}

}
