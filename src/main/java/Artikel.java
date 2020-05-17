/**
 * Class Artikel -
 *
 * @author Naomi Verkade
 * @version 14-05-2020
 */
public class Artikel {
    private String naam;
    private double prijs; //double?

    /**
     * Constructor van de klasse Artikel
     *
     * @param naam is de naam van het artikel als String
     * @param prijs is de prijs van het artikel als float
     */
    public Artikel(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    /**
     * Constructor van de klasse Artikel
     */
        public Artikel(){
            this(null, 0);
        }

     /**
     * methode welke de prijs van het artikel opvraagt
     *
     * @return de prijs van het artikel als float
     */
    public double getPrijs() {
        return prijs;
    }

    /**
     * Methode welke de prijs van een artikel set als float
     *
     * @param prijs is de prijs van een artikel
    */
    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    /**
     * methode welke de naam van een artikel opvraagt
     *
     * @return de naam van het artikel als String
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Methode welke de naam van een artikel set als String
     *
     * @param naam is de naam van het artikel
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Methode welke alle velden afdrukt
     * @return alle velden
     */
    @Override
    public String toString(){
        return "Naam: " + getNaam() + "\n" + "Prijs: " + getPrijs();
    }
}
