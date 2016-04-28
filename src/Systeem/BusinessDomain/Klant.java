/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.BusinessDomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * De klasse klant.
 */
public class Klant {

	/** De verzekeringen van de klant. */
	private List<Verzekering>			verzekeringen;

	/** Behandel trajecten van de klant. */
	private List<Behandelingtraject>	behandeltrajecten;

	/** Diagnoses van de klant. */
	private List<Diagnose>				diagnoses;

	/** Burger service nummer van de klant. */
	private String						bsn;

	/** Voornaam van de klant. */
	private String						voornaam;

	/** Achternaam van de klant. */
	private String						achternaam;

	/** Adres van de klant. */
	private String						adres;

	/** Postcode van de klant. */
	private String						postcode;

	/** plaats van de klant. */
	private String						plaats;

	/** Geboorte datum van de klant. */
	private Date						geboortedatum;

	/** Email van de klant. */
	private String						email;

	/** Eigen risico van de klant. */
	private Double						eigenRisico;

	/** De eigen risico dat nog niet verbruikt is van de klant. */
	private Double						restantEigenRisico;

	/** AutomatischeIncasso verzending of niet? van de klant. */
	private boolean						automatischIncasso;

	/** The telefoonnummer. */
	private String						telefoonnummer;

	/** The factuur ontvangst. */
	private boolean						factuurOntvangst;

	/** The laatste factuur datum. */
	private Date						laatsteFactuurDatum;

	/** The geslacht. */
	private String						geslacht;

	/**
	 * Constructor normal.
	 *
	 * @param bsn            Burger service nummer
	 * @param voornaam            Voornaam van de klant
	 * @param achternaam            Achternaam van de klant
	 * @param adres            Adres van de klant
	 * @param postcode            Postcode van de klant
	 * @param plaats            the plaats
	 * @param geboortedatum            Geboorteddatum van de klant
	 * @param telefoonnummer            the telefoonnummer
	 * @param email            Email van de klant
	 * @param eigenRisico            Eigen risico van de klant
	 * @param restandEigenRisico the restand eigen risico
	 * @param automatischIncasso            Wilt de klant automatische incasso of niet?
	 * @param verzekeringen            the verzekeringen
	 * @param factuurOntvangst            the factuur ontvangst
	 * @param geslacht the geslacht
	 */
	public Klant(String bsn, String voornaam, String achternaam, String adres, String postcode, String plaats, Date geboortedatum, String telefoonnummer, String email, Double eigenRisico, Double restandEigenRisico,
			boolean automatischIncasso, List<Verzekering> verzekeringen,
			boolean factuurOntvangst, String geslacht) {
		this.bsn = bsn;// String
		this.voornaam = voornaam;// String
		this.achternaam = achternaam;// String
		this.adres = adres;// String
		this.plaats = plaats;// String
		this.postcode = postcode;// String
		this.geboortedatum = geboortedatum;// Date
		this.telefoonnummer = telefoonnummer;// String
		this.email = email;// String
		this.restantEigenRisico = restandEigenRisico;
		this.eigenRisico = eigenRisico;// Double
		this.automatischIncasso = automatischIncasso;// Boolean
		this.verzekeringen = verzekeringen != null ? verzekeringen : new ArrayList<Verzekering>();// List
		this.factuurOntvangst = factuurOntvangst;// Boolean
		this.laatsteFactuurDatum = new Date();
		this.geslacht = geslacht;
	}

	/**
	 * Constructor for XML file.
	 *
	 * @param bsn            Burger service nummer
	 * @param voornaam            Voornaam van de klant
	 * @param achternaam            Achternaam van de klant
	 * @param adres            Adres van de klant
	 * @param plaats            the plaats
	 * @param postcode            Postcode van de klant
	 * @param geboortedatum            Geboorteddatum van de klant
	 * @param email            Email van de klant
	 * @param eigenRisico            Eigen risico van de klant
	 * @param restantEigenRisico            Restant van het eigen risico van de klant
	 * @param automatischIncasso            Wilt de klant automatische incasso of niet?
	 * @param diagnoses            the diagnoses
	 * @param behandelingtrajecten            the behandelingtrajecten
	 * @param verzekeringen            the verzekeringen
	 * @param telefoonNummer            the telefoon nummer
	 * @param factuurOntvangst            the factuur ontvangst
	 * @param laatsteFactuurDatum            the laatste factuur datum
	 * @param geslacht the geslacht
	 */
	public Klant(String bsn, String voornaam, String achternaam, String adres, String plaats, String postcode, Date geboortedatum, String email, Double eigenRisico, Double restantEigenRisico, boolean automatischIncasso,
			List<Diagnose> diagnoses, List<Behandelingtraject> behandelingtrajecten,
			List<Verzekering> verzekeringen, String telefoonNummer, boolean factuurOntvangst, Date laatsteFactuurDatum, String geslacht) {
		this(bsn, voornaam, achternaam, adres, plaats, postcode, geboortedatum, telefoonNummer, email, eigenRisico, restantEigenRisico, automatischIncasso, verzekeringen, factuurOntvangst, geslacht);
		this.diagnoses = diagnoses != null ? diagnoses : new ArrayList<Diagnose>();
		this.behandeltrajecten = behandelingtrajecten != null ? behandelingtrajecten : new ArrayList<Behandelingtraject>();
		this.laatsteFactuurDatum = laatsteFactuurDatum;
	}

	/**
	 * Getter voor Burgerservicenummer.
	 *
	 * @return bsn
	 */
	public String getBsn() {
		return this.bsn;
	}

	/**
	 * Settter voor bsn.
	 *
	 * @param bsn
	 *            the new bsn
	 */
	public void setBsn(String bsn) {
		this.bsn = bsn;
	}

	/**
	 * Getter voor voornaam.
	 *
	 * @return voornaam
	 */
	public String getVoornaam() {
		return this.voornaam;
	}

	/**
	 * Setter voor voornaam.
	 *
	 * @param voornaam
	 *            the new voornaam
	 */
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	/**
	 * Getter voor voornaam.
	 *
	 * @return voornaam
	 */
	public String getAchternaam() {
		return this.achternaam;
	}

	/**
	 * Setter voor achternaam.
	 *
	 * @param achternaam
	 *            the new achternaam
	 */
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	/**
	 * Getter voor adres.
	 *
	 * @return adres
	 */
	public String getAdres() {
		return this.adres;
	}

	/**
	 * Setter voor adres.
	 *
	 * @param adres
	 *            the new adres
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * Getter voor postcode.
	 *
	 * @return postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Setter voor postcode.
	 *
	 * @param postcode
	 *            the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Sets the plaats.
	 *
	 * @param plaats
	 *            the new plaats
	 */
	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	/**
	 * Gets the plaats.
	 *
	 * @return the plaats
	 */
	public String getPlaats() {
		return this.plaats;
	}

	/**
	 * Getter voor geboortedatum.
	 *
	 * @return geboortedatum
	 */
	public Date getGeboortedatum() {
		return this.geboortedatum;
	}

	/**
	 * Setter voor geboortedatum.
	 *
	 * @param geboortedatum
	 *            the new geboortedatum
	 */
	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	/**
	 * Getter voor Email.
	 *
	 * @return Email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter voor Email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter voor eigenRisico.
	 *
	 * @return EigenRisico
	 */
	public Double getEigenRisico() {
		return this.eigenRisico;
	}

	/**
	 * Setter voor eigen risico.
	 *
	 * @param eigenRisico
	 *            the new eigen risico
	 */
	public void setEigenRisico(Double eigenRisico) {
		this.eigenRisico = eigenRisico <= 0 ? 0 : eigenRisico;
	}

	/**
	 * Setter voor eigen risico.
	 *
	 * @return the restant eigen risico
	 */
	public Double getRestantEigenRisico() {
		return this.restantEigenRisico;
	}

	/**
	 * Setter voor Restant eigen risico.
	 *
	 * @param restantEigenRisico
	 *            the new restant eigen risico
	 */
	public void setRestantEigenRisico(Double restantEigenRisico) {
		this.restantEigenRisico = restantEigenRisico;
	}

	/**
	 * Getter voor automatische incasso.
	 *
	 * @return automatische incasso ja of nee?
	 */
	public boolean getAutomatischIncasso() {
		return this.automatischIncasso;
	}

	/**
	 * Setter voor automatischeIncasso.
	 *
	 * @param automatischIncasso
	 *            the new automatisch incasso
	 */
	public void setAutomatischIncasso(boolean automatischIncasso) {
		this.automatischIncasso = automatischIncasso;
	}

	/**
	 * getter voor Diagnoses.
	 *
	 * @return Diagnoses
	 */
	public List<Diagnose> getDiagnoses() {
		return this.diagnoses;
	}

	/**
	 * Diagnose toevoegen aan de diagnose lijst.
	 *
	 * @param diagnose
	 *            the diagnose
	 */
	public void addDiagnose(Diagnose diagnose) {
		this.diagnoses.add(diagnose);
	}

	/**
	 * getter voor BehandelTrajecten.
	 *
	 * @return BehandelTrajecten
	 */
	public List<Behandelingtraject> getBehandelingtrajecten() {
		return this.behandeltrajecten;
	}

	/**
	 * BehandelingTraject toevoegen.
	 *
	 * @param behandelingtraject
	 *            the behandelingtraject
	 */
	public void addBehandelingtraject(Behandelingtraject behandelingtraject) {
		this.behandeltrajecten.add(behandelingtraject);
	}

	/**
	 * Sets the verzekeringen.
	 *
	 * @param v
	 *            the new verzekeringen
	 */
	public void setVerzekeringen(List<Verzekering> v) {
		this.verzekeringen = v;
	}

	/**
	 * Gets the verzekeringen.
	 *
	 * @return the verzekeringen
	 */
	public List<Verzekering> getVerzekeringen() {
		return this.verzekeringen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.bsn + ": " + this.voornaam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof Klant) {
			Klant other = (Klant) obj;

			return Objects.equals(bsn, other.bsn) && Objects.equals(voornaam, other.voornaam) && Objects.equals(achternaam, other.achternaam) && Objects.equals(adres, other.adres)
					&& Objects.equals(postcode, other.postcode) && Objects.equals(email, other.email)
					&& Objects.equals(eigenRisico, other.eigenRisico) && Objects.equals(restantEigenRisico, other.restantEigenRisico) && Objects.equals(automatischIncasso, other.automatischIncasso)
					&& Objects.equals(geslacht, other.geslacht);
		}
		return false;
	}

	/**
	 * Gets the telefoonnummer.
	 *
	 * @return the telefoonnummer
	 */
	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	/**
	 * Sets the telefoonnummer.
	 *
	 * @param telefoonnummer
	 *            the new telefoonnummer
	 */
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	/**
	 * Gets the factuur ontvangst.
	 *
	 * @return the factuur ontvangst
	 */
	public boolean getFactuurOntvangst() {
		return factuurOntvangst;
	}

	/**
	 * Sets the factuur ontvangst.
	 *
	 * @param factuurOntvangst
	 *            the new factuur ontvangst
	 */
	public void setFactuurOntvangst(boolean factuurOntvangst) {
		this.factuurOntvangst = factuurOntvangst;
	}

	/**
	 * Gets the laatste factuur datum.
	 *
	 * @return the laatste factuur datum
	 */
	public Date getLaatsteFactuurDatum() {
		return laatsteFactuurDatum;
	}

	/**
	 * Sets the laatste factuur datum.
	 *
	 * @param laatsteFactuurDatum
	 *            the new laatste factuur datum
	 */
	public void setLaatsteFactuurDatum(Date laatsteFactuurDatum) {
		this.laatsteFactuurDatum = laatsteFactuurDatum;
	}

	/**
	 * Gets the geslacht.
	 *
	 * @return the geslacht
	 */
	public String getGeslacht() {
		return geslacht;
	}

	/**
	 * Sets the geslacht.
	 *
	 * @param geslacht the new geslacht
	 */
	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}
}