/**
 * Class Datum - Slaat een datum op
 *
 * @author Marijn Kok
 * @version 14-05-2020
 */
public class Datum {
	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Stelt de datum in. Als de datum niet geldig is, zal de datum naar 00-00-0 zijn.
	 * @param dag dag van de maand
	 * @param maand maand van het jaar
	 * @param jaar jaartal
	 */
	public Datum(int dag, int maand, int jaar) {
		this();
		// Check of datum correct is
		if(bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}

	/**
	 * Stelt de datum in als er geen parameters gegeven zijn
	 */
	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	/**
	 * Retourneert de dag
	 * @return dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * Stelt de dag in
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	public int getMaand() {
		return maand;
	}

	/**
	 * Stelt de maand in
	 * @param maand maand van het jaar
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * Retourneert het jaar
	 * @return jaar
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 * Stelt het jaar in
	 * @param jaar jaartal
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * Dagnummers moeten altijd groter dan of gelijk zijn aan 1;
	 * De maanden liggen tussen 1 en 12;
	 * De jaren liggen tussen 1900 en 2100;
	 * De dag/maand combinatie moet bestaan.
	 * (de geboortedata 34 januari 1987 en 31 april 2002 zijn niet mogelijk, terwijl 31 maart 2000 wel een geldig datum is)
	 * In een schrikkeljaar heeft de maand februari 29 in plaats van 28 dagen.
	 * Een jaar is eenschrikkeljaar als het jaartal deelbaar is door 4, maar als het jaar deelbaar is door 100 is het
	 * geen schrikkeljaar, tenzij het jaar deelbaar is door 400. Het jaar 1900 is dus geen schrikkel-jaar,
	 * de jaren 2000, 2008, 2012 en 2016 zijn dat wel.
	 * @param dag - dag van de maand
	 * @param maand - maandnummer
	 * @param jaar - jaartal
	 * @return of het een geldige datum is
	 */
	public boolean bestaatDatum(int dag, int maand, int jaar) {

		// Check jaar
		if(jaar < 1900 || jaar > 2100) {
			return false;
		}

		// Check maand/dag combinatie, geldige dagen, geldige maanden
		// Even maand: niet meer dan 30 dagen
		int dagenInMaand;
		switch (maand) {
			case 1: case 3: case 5:
			case 7: case 8: case 10:
			case 12:
				dagenInMaand = 31;
				break;
			case 4: case 6:
			case 9: case 11:
				dagenInMaand = 30;
				break;
			case 2:
				// Het is alleen een schrikkeljaar als het deelbaar door 4 en niet deelbaar is door 100.
				// Het is verder verzekerd een schrikkeljaar als het deelbaar is door 400.
				if (((jaar % 4 == 0) && !(jaar % 100 == 0)) || (jaar % 400 == 0))
					dagenInMaand = 29;
				else
					dagenInMaand = 28;
				break;
			default:
				// Dit is voor maanden die niet in de cases staan, ofwel waardes die niet 1-12 zijn
				return false;
		}
		// Dag moet in de maand zitten. Als dit goed is, zijn alle checks volstaan, en returnt het true.
		if (dag >= 1 && dag <= dagenInMaand) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum in Nederlands formaat dd-mm-jjjj
	 */
		public String getDatumAsString() {
			String dagString = "";
			String maandString = "";

			// Dag
			if(dag < 10) {
				dagString = "0";
			}
			dagString += ((Integer)dag).toString();

			// Maand
			if(maand < 10) {
				maandString = "0";
			}
			maandString += ((Integer)maand).toString();

			String jaarString = ((Integer)jaar).toString();

			return dagString + "-" + maandString + "-" + jaarString;
		}
}
