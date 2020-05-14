/**
 * Class Artikel -
 *
 * @author Naomi Verkade
 * @version 14-05-2020
 */
public class Artikel {
    private String name;
    private float prijs;

    /**
     * Constructor van de klasse Artikel
     *
     * @param name is de naam van het artikel
     * @param prijs is de prijs van het artikel
     */
    public Artikel(String name, float prijs) {
        this.name = name;
        this.prijs = prijs;
    }
    /**
     * Constructor van de klasse Artikel
     */
        public Artikel(){
            name = null;
            prijs = 0;
        }
        /**
     * methode welke de prijs van het artikel opvraagt
     *
     * @return returns de prijs van het artikel
     */
    public float getPrijs() {
        return prijs;
    }

    /**
     * Methode welke de prijs van een artikel set
     *
     * @param prijs is de prijs van een artikel
    */
    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    /**
     * methode welke de naam van een artikel opvraagt
     *
     * @return returns de naam van het artikel
     */
    public String getName() {
        return name;
    }

    /**
     * Methode welke de naam van een artikel set
     *
     * @param name is de naam van het artikel
     */
    public void setName(String name) {
        this.name = name;
    }
}
