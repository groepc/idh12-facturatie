/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.rmi.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Systeem.BusinessDomain.Klant;
import Systeem.Klanten.Businesslogic.ControllerKlant;
import Systeem.rmi.domain.RmiKlant;
import Systeem.rmi.domain.RmiKlantIRead;

/**
 * @author Gregor
 *
 */
public class RemoteKlant implements RemoteKlantenInterface, Serializable {
	private ControllerKlant controllerKlant;

	public RemoteKlant() {
		try {
			controllerKlant = new ControllerKlant();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.rmi.business.RemoteKlantenInterface#geefKlant(java.lang.String)
	 */
	@Override
	public RmiKlantIRead geefKlant(String bsn) {
		try {
			System.out.println("RMI_SERVER>>> Geefklant aangeroepen.");
			System.out.println("RMI_SERVER>>> Ingevoerde bsn = " + bsn);
			Klant klant = controllerKlant.geefKlant(bsn);
			if (klant == null) {
				System.out.println("RMI_SERVER>>> Geen klant gevonden.");
				return null;
			} else {
				System.out.println("RMI_SERVER>>> klant gevonden.");
				RmiKlant rmiklant = new RmiKlant(klant.getBsn(), klant.getVoornaam(), klant.getAchternaam(), klant.getAdres(), klant.getPostcode(), klant.getPlaats(), klant.getGeboortedatum(), klant.getTelefoonnummer(), klant.getEmail(), klant.getGeslacht());
				return rmiklant;
			}
		} catch (IOException ex) {
			System.out.println("IOException.");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Systeem.rmi.business.RemoteKlantenInterface#zoekKlanten(java.lang.String)
	 */
	@Override
	public List<RmiKlantIRead> geefAlleKlanten() {
		try {
			System.out.println("RMI_SERVER>>> geefAlleKlanten aangeroepen.");
			List<Klant> klantenLijst = controllerKlant.geefAlleKlanten();
			List<RmiKlantIRead> rmiKlantenLijst = new ArrayList<>();
			for (Klant klant : klantenLijst) {
				rmiKlantenLijst.add(new RmiKlant(klant.getBsn(), klant.getVoornaam(), klant.getAchternaam(), klant.getAdres(), klant.getPostcode(), klant.getPlaats(), klant.getGeboortedatum(), klant.getTelefoonnummer(), klant.getEmail(), klant.getGeslacht()));
			}
			System.out.println("RMI_SERVER>>> " + rmiKlantenLijst.size() + "klanten gevonden.");
			return rmiKlantenLijst;
		} catch (IOException ex) {
			System.out.println("IOException.");
			return null;
		}
	}

	/*
	 * private RmiKlantIRead createRmiKlantFromKlant(Klant klant) { String
	 * geslacht = klant.getGeslacht().equals("Man") ? "M" : "V";
	 * 
	 * return new RmiKlant(klant.getBsn(), klant.getVoornaam(),
	 * klant.getAchternaam(), klant.getAdres(), klant.getPostcode(),
	 * klant.getPlaats(), klant.getGeboortedatum(), klant.getTelefoonnummer(),
	 * klant.getEmail(), geslacht); }
	 */
}
