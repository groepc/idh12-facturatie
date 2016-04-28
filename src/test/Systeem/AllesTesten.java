/**
 * 
 */
package test.Systeem;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.Systeem.Controllers.AlleControllersTesten;
import test.Systeem.Controllers.ControllerBehandelTrajectenTest;
import test.Systeem.Controllers.ControllerFactuurTest;
import test.Systeem.Controllers.ControllerKlantTest;
import test.Systeem.Controllers.ControllerVerzekeringMaatschappijenTest;
import test.Systeem.Controllers.ControllerVerzekeringenTest;
import test.Systeem.Datastorage.BaseClasses.XMLBaseTest;
import test.Systeem.Datastorage.DAO.AleDAOsTesten;

/**
 * @author vanStriean
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AlleControllersTesten.class, AleDAOsTesten.class,
		XMLBaseTest.class })
public class AllesTesten {
}
