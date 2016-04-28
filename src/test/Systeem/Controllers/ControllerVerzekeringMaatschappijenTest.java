/**
 * @creator B4
 * @author Leendert
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
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Factuur;
import Systeem.BusinessDomain.Verzekeringsmaatschappij;
import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchField;
import Systeem.Datastorage.Interfaces.IReadDAO.SearchLevel;
import Systeem.Facturatie.Businesslogic.ControllerFactuur;
import Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen;
import Systeem.Verzekeringen.Presentation.customRenderers.VerzekeringmaatschappijListRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerVerzekeringMaatschappijenTest.
 */
public class ControllerVerzekeringMaatschappijenTest {

	static ArrayList<Verzekeringsmaatschappij> testDataVerzekeringsmaakschapij;
	static ControllerVerzekeringMaatschappijen contoller;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ControllerVerzekeringMaatschappijen controller = new ControllerVerzekeringMaatschappijen();
		ControllerVerzekeringMaatschappijenTest.contoller = controller;

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

			DAOVerzekeringmaatschappij dao = new DAOVerzekeringmaatschappij(
					"xmlFilesTest/", "Verzekeringmaatschappijen");

			dao.deleteDocument();
			dao.create("Verzekeringmaatschappijen");
			testDataVerzekeringsmaakschapij = new ArrayList<Verzekeringsmaatschappij>();

			// Factuur nr word mee gegeven en klant bij de dummy data.

			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525113"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525114"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525115"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525116"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525117"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525118"));
			testDataVerzekeringsmaakschapij.add(Factories
					.verzekeringmaatschappijFactory("525119"));

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
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen#geefVerzekeringsmaatschappijen()}
	 * .
	 */
	@Test
	public void testGeefVerzekeringsmaatschappij() {

		ControllerVerzekeringMaatschappijen controller = null;
		DAOVerzekeringmaatschappij mockedDAO = mock(DAOVerzekeringmaatschappij.class);
		try {
			when(mockedDAO.geefAlles()).thenReturn(
					testDataVerzekeringsmaakschapij);
			when(mockedDAO.geef("525114")).thenReturn(
					testDataVerzekeringsmaakschapij.get(1));

			controller = new ControllerVerzekeringMaatschappijen(); // set dao

			Field field = ControllerVerzekeringMaatschappijen.class
					.getDeclaredField("daoVerzekeringmaatschappij");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			Verzekeringsmaatschappij eindRes = controller
					.geefVerzekeringsmaatschappij("525114");
			// assert eaqual result.
			assertEquals(testDataVerzekeringsmaakschapij.get(1), eindRes);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen#geefVerzekeringsmaatschappij(java.lang.String)}
	 * .
	 */
	@Test
	public void testGeefVerzekeringsmaatschappijen() {

		ControllerVerzekeringMaatschappijen controller = null;
		DAOVerzekeringmaatschappij mockedDAO = mock(DAOVerzekeringmaatschappij.class);
		try {
			when(mockedDAO.geefAlles()).thenReturn(
					testDataVerzekeringsmaakschapij);

			controller = new ControllerVerzekeringMaatschappijen(); // set dao

			Field field = ControllerVerzekeringMaatschappijen.class
					.getDeclaredField("daoVerzekeringmaatschappij");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			ArrayList<Verzekeringsmaatschappij> eindRes = new ArrayList<Verzekeringsmaatschappij>(
					controller.geefVerzekeringsmaatschappijen());
			// assert eaqual result.
			assertEquals(testDataVerzekeringsmaakschapij, eindRes);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen#verzekeringsmaatschappijToevoegen(Systeem.BusinessDomain.Verzekeringsmaatschappij)}
	 * .
	 */
	@Test
	public void testVerzekeringsmaatschappijToevoegen() {
		ControllerVerzekeringMaatschappijen controller = null;
		Verzekeringsmaatschappij testmaatschap = new Verzekeringsmaatschappij(
				"naam", "233222", "adres", "plaats", "0005460", "ibn",
				"postcode", "tt");
		DAOVerzekeringmaatschappij mockedDAO = mock(DAOVerzekeringmaatschappij.class);

		try {
			controller = new ControllerVerzekeringMaatschappijen(); // set dao
																	// Field
																	// field =
			Field field = ControllerVerzekeringMaatschappijen.class
					.getDeclaredField("daoVerzekeringmaatschappij");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
			controller.verzekeringsmaatschappijToevoegen(testmaatschap);
			verify(mockedDAO).opslaan(testmaatschap);
		} catch (Exception e) {
			fail(e.toString());
		}

	}

	/**
	 * Test method for
	 * {@link Systeem.Verzekeringen.Businesslogic.ControllerVerzekeringMaatschappijen#verzekeringsmaatschappijWijzigen(Systeem.BusinessDomain.Verzekeringsmaatschappij)}
	 * .
	 */
	@Test
	public void testVerzekeringsmaatschappijWijzigen() {
		ControllerVerzekeringMaatschappijen controller;
		DAOVerzekeringmaatschappij mockedDAO = mock(DAOVerzekeringmaatschappij.class);
		try {
			when(mockedDAO.geefAlles()).thenReturn(
					testDataVerzekeringsmaakschapij);

			controller = new ControllerVerzekeringMaatschappijen(); // set dao
																	// Field
																	// field =

			Field field = ControllerVerzekeringMaatschappijen.class
					.getDeclaredField("daoVerzekeringmaatschappij");
			field.setAccessible(true);
			field.set(controller, mockedDAO);
		} catch (Exception e) {
			fail(e.toString());
		}

		try {
			Verzekeringsmaatschappij testVerz = testDataVerzekeringsmaakschapij
					.get(2);
			testVerz.setPlaats("Beverland");
			contoller.verzekeringsmaatschappijWijzigen(testVerz);

			String resultWerkelijk = testDataVerzekeringsmaakschapij.get(2)
					.getPlaats();
			assertEquals(testVerz.getPlaats(), resultWerkelijk);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
