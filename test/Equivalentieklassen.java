/**
 * @creator B4
 * @date 4-nov-2014
 * @version 7.1
 */

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Systeem.Factories;
import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Diagnose;
import Systeem.BusinessDomain.Klant;
import Systeem.BusinessDomain.Verzekering;


public class Equivalentieklassen {

    static List<Klant> testData;

    /**
     * Sets the up before class.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * Sets the up.
     *
     * @throws Exception the exception
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
     * @throws Exception the exception
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
    public void testRangerUpTo350() {

        Klant klant = testData.get(1);
        
        klant.setEigenRisico(350.00);
        Double leftOver = klant.getEigenRisico() - 40;
        klant.setRestantEigenRisico(leftOver);
        Double resultExpected = 310.00;
        assertEquals(resultExpected, klant.getRestantEigenRisico());
    }
    
    /**
     * Test method for
     * {@link Systeem.Klanten.Businesslogic.ControllerKlant#geefKlant(java.lang.String)}
     * .
     */
    @Test
    public void testRangerFrom350() {

        Klant klant = testData.get(1);
        
        klant.setEigenRisico(350.00);
        klant.setRestantEigenRisico(klant.getEigenRisico() - 950);
        Double resultExpected = -600.00;
        assertEquals(resultExpected, klant.getRestantEigenRisico());
    }
    

}
