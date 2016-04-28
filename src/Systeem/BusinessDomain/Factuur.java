/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.BusinessDomain;

import java.util.Date;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Factuur.
 *
 * @author Warlord Factuur van de facturatie systeem
 */
public class Factuur {

	/** nr van de factuur. */
	private int nr;

	/** datum van aanmaak. */
	private Date factuurDatum;

	/** The verval datum. */
	private Date vervalDatum;

	/** vergoedingde kosten. */
	private Double vergoeding;

	/** Niet vergoede kosten. */
	private Double nietVergoedeKosten;

	/** BTWPercentage. */
	private double btwPercentage;

	/** De betalingcondities van de factuur. */
	private String naam;

	/** The betalings condities. */
	private String betalingsCondities;

	/** Is de factuur betaald of niet?. */
	private boolean betaald;

	/** The klant. als string (om de load tijd te verkleinen) */
	private String klant;

	/** The begin eigen risico. */
	private double beginEigenRisico;

	/** The verzekeringmaatschappij als string (om de load tijd te verkleinen). */
	private String verzekeringmaatschappij;

	/** The fysio praktijk. */
	private int fysioPraktijk;

	/**
	 * Constructor.
	 *
	 * @param klant            De klant waar de factuur van is.
	 * @param verzekeringmaatschappij the verzekeringmaatschappij
	 */
	public Factuur(String klant, String verzekeringmaatschappij) {
		this.klant = klant;
		this.setVerzekeringmaatschappij(verzekeringmaatschappij);

	}

	/**
	 * XML Constructor.
	 *
	 * @param nr            Nr van factuur
	 * @param naam            the naam
	 * @param factuurDatum            the factuur datum
	 * @param vervalDatum            the verval datum
	 * @param vergoeding            Vergoeding van de factuur
	 * @param nietVergoedeKosten            the niet vergoede kosten
	 * @param beginEigenRisico            the begin eigen risico
	 * @param btwPercentage            BtwPercentage dat in de factuur wordt berekent
	 * @param betalingCondities            De betaling condities
	 * @param betaald            Is de factuur betaald of niet
	 * @param klant            Klant waar de factuur van is.
	 * @param verzekeringmaatschappij the verzekeringmaatschappij
	 * @param fysioPraktijk the fysio praktijk
	 */
	public Factuur(int nr, String naam, Date factuurDatum, Date vervalDatum, Double vergoeding, Double nietVergoedeKosten, Double beginEigenRisico, double btwPercentage, String betalingCondities, boolean betaald, String klant, String verzekeringmaatschappij, int fysioPraktijk) {
		this(klant, verzekeringmaatschappij);
		this.nr = nr;
		this.factuurDatum = factuurDatum;
		this.vervalDatum = vervalDatum;
		this.vergoeding = vergoeding;
		this.nietVergoedeKosten = nietVergoedeKosten;
		this.btwPercentage = btwPercentage;
		this.betalingsCondities = betalingCondities;
		this.betaald = betaald;
		this.beginEigenRisico = beginEigenRisico;
		this.naam = naam;
		this.fysioPraktijk = fysioPraktijk;
	}

	/**
	 * Gets the fysio praktijk.
	 *
	 * @return the fysioPraktijk
	 */
	public int getFysioPraktijk() {
		return fysioPraktijk;
	}

	/**
	 * Getter voor nr.
	 *
	 * @return nr
	 */
	public int getNr() {
		return this.nr;
	}

	/**
	 * Getter voor datum.
	 *
	 * @return datum
	 */
	public Date getFactuurDatum() {
		return this.factuurDatum;
	}

	/**
	 * Gets the verval datum.
	 *
	 * @return the verval datum
	 */
	public Date getVervalDatum() {
		return this.vervalDatum;
	}

	/**
	 * Getter voor vergoeding.
	 *
	 * @return vergoeding
	 */
	public Double getVergoeding() {
		return this.vergoeding;
	}

	/**
	 * Getter voor nietVergoedekosten.
	 *
	 * @return NietVergoedeKosten
	 */
	public Double getNietVergoedeKosten() {
		return this.nietVergoedeKosten;
	}

	/**
	 * Getter voor btwPercentage.
	 *
	 * @return btwPercentage
	 */
	public double getBtwPercentage() {
		return this.btwPercentage;
	}

	/**
	 * Getter voor betallingCondities.
	 *
	 * @return getBtwPercentage
	 */
	public String getBetalingsCondities() {
		return this.betalingsCondities;
	}

	/**
	 * Gets the naam.
	 *
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Gets the begin eigen risico.
	 *
	 * @return the begin eigen risico
	 */
	public double getBeginEigenRisico() {
		return this.beginEigenRisico;
	}

	/**
	 * Getter voor of het betaald is of niet.
	 *
	 * @return BetalingsCondities
	 */
	public boolean getBetaald() {
		return this.betaald;
	}

	/**
	 * Sets the betaald.
	 *
	 * @param betaald the new betaald
	 */
	public void setBetaald(Boolean betaald) {
		this.betaald = betaald;
	}

	/**
	 * Getter voor klant.
	 *
	 * @return klant
	 */
	public String getKlant() {
		return this.klant;
	}

	/**
	 * Getter voor BtwBedrag.
	 *
	 * @return btwBedrag
	 */

	public String toString() {
		String nr = String.valueOf(this.nr);
		return nr + ": " + this.factuurDatum.toString() + " - " + this.klant;
	}

	/**
	 * Gets the praktijk nr.
	 *
	 * @return the praktijk nr
	 */
	public int getPraktijkNr() {
		return this.fysioPraktijk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof Factuur) {
			Factuur other = (Factuur) obj;

			return Objects.equals(nr, other.nr) && Objects.equals(naam, other.naam) && Objects.equals(vergoeding, other.vergoeding) && Objects.equals(nietVergoedeKosten, other.nietVergoedeKosten) && Objects.equals(beginEigenRisico, other.beginEigenRisico) && Objects.equals(klant, other.klant)
					&& Objects.equals(btwPercentage, other.btwPercentage) && Objects.equals(betalingsCondities, other.betalingsCondities) && Objects.equals(verzekeringmaatschappij, other.verzekeringmaatschappij) && Objects.equals(fysioPraktijk, other.fysioPraktijk)
					&& Objects.equals(betaald, other.betaald);
		}
		return false;
	}

	/**
	 * Gets the verzekeringmaatschappij.
	 *
	 * @return f
	 */
	public String getVerzekeringmaatschappij() {
		return verzekeringmaatschappij;
	}

	/**
	 * Sets the verzekeringmaatschappij.
	 *
	 * @param verzekeringmaatschappij the new verzekeringmaatschappij
	 */
	public void setVerzekeringmaatschappij(String verzekeringmaatschappij) {
		this.verzekeringmaatschappij = verzekeringmaatschappij;
	}

	/**
	 * Sets the naam.
	 *
	 * @param string the new naam
	 */
	public void setNaam(String string) {
		this.naam = string;

	}

}