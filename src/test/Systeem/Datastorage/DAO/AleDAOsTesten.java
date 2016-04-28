/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package test.Systeem.Datastorage.DAO;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Systeem.Datastorage.DAO.DAOFactuur;
import Systeem.Datastorage.DAO.DAOKlant;
import Systeem.Datastorage.DAO.DAOVerzekering;
import Systeem.Datastorage.DAO.DAOVerzekeringmaatschappij;

// TODO: Auto-generated Javadoc
/**
 * The Class AleDAOsTesten.
 *
 * @author Jermey
 */

@RunWith(Suite.class)
@SuiteClasses({ DAOVerzekeringmaatschappijTest.class,
		DAOBehandelingtrajectTest.class, DAOVerzekeringTest.class,
		DAOKlantTest.class, DAOFactuurTest.class })
public class AleDAOsTesten {

	/**
	 * Sets the up.
	 */
	@BeforeClass
	public static void setUp() {
		try {
			DAOFactuur daoFacturen = new DAOFactuur("xmlFilesTest/", "Facturen");
			daoFacturen.deleteDocument();

			DAOKlant daoKlant = new DAOKlant("xmlFilesTest/", "Klanten");
			daoKlant.deleteDocument();

			DAOVerzekeringmaatschappij daoMaatschappij = new DAOVerzekeringmaatschappij(
					"xmlFilesTest/", "Verzekeringmaatschappijen");
			daoMaatschappij.deleteDocument();

			DAOVerzekering daoVerzekering = new DAOVerzekering("xmlFilesTest/",
					"Verzekeringen");
			daoVerzekering.deleteDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Break up.
	 */
	@AfterClass
	public static void breakUp() {
		AleDAOsTesten.setUp();
	}

}
