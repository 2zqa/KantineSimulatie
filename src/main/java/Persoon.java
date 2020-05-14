/**
 * Class Persoon -
 *
 * @author Naomi Verkade
 * @version 14-05-2020
 */
public class Persoon {
    private String bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;

    /**
     * Constructor van de klasse Persoon
     *
     * @param bsn is het Burger Service Nummer van een persoon als String
     * @param voornaam is de voornaam van een persoon als String
     * @param achternaam is de achternaam van een persoon als String
     * @param geboortedatum is de geboortedatum van een persoon als String
     * @param geslacht is het geslacht van een persoon als char
     */
    public Persoon(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        setGeslacht(geslacht);
    }

    /**
     * Constructor van de klasse Persoon
     */
    public Persoon() {
        bsn = null;
        voornaam = null;
        achternaam = null;
        geboortedatum = new Datum();
        geslacht = '\u0000';
    }

    /**
     * Methode welke het BSN van een persoon set als String
     * @param bsn is het BSN van een persoon
     */
    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    /**
     * methode welke het BSN van een persoon opvraagt
     * @return bsn als String
     */
    public String getBsn() {
        return bsn;
    }

    /**
     * Methode welke de voornaam van een persoon set als String
     * @param voornaam is de voornaam van een persoon
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * Methode welke de voornaam van een persoon opvraagt
     * @return voornaam als String
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Methode welke de achternaam van een persoon set als String
     * @param achternaam is de achternaam van een persoon
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Methode welke de achternaam van een persoon opvraagt
     * @return achternaam als String
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Methode welke de geboortedatum van een persoon set als String
     * @param geboortedatum is de geboortedatum van een persoon
     */
    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * Methode welke de geboortedatum van een persoon opvraagt
     * @return de geboortedatum als String
     */
    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    /**
     * Methode welke het geslacht van een persoon set als char
     * @param geslacht is het geslacht van een persoon, dit is 'M' of 'V'
     */
    public void setGeslacht(char geslacht) {
            if(checkGeslacht(geslacht) == true) {
                this.geslacht = geslacht;
            } else {
                System.out.println("Ongeldig geslacht");
            }
    }

    /**
     * Methode welke het geslacht van een persoon opvraagt
     * @return het geslacht als String
     */
    public String getGeslacht() {
        switch (geslacht) {
            case 'M':
                return "Man";
            case 'V':
                return "Vrouw";
            default:
                return "Onbekend";
        }
    }

    /**
     * Methode welke het geslacht checkt op juistheid
     * @param geslacht is het geslacht van een persoon als char
     * @return true als het geslacht 'M' of 'V' is, anders false en een foutmelding
     */
    public boolean checkGeslacht(char geslacht){
        if (geslacht == 'M' || geslacht == 'V') {
            return true;
        } else {
            System.out.println("Ongeldig geslacht, enkel man 'M' of vrouw 'V' mogelijk");
            return false;
        }
    }

    /**
     * Methode welke alle velden afdrukt
     * @return alle velden
     */
    @Override
    public String toString(){
        return "Voornaam: " + getVoornaam() + "\n" + "Achternaam: " + getAchternaam() + "\n" + "BSN: " + getBsn() + "\n" + "Geboortedatum: " + getGeboortedatum() + "\n" + "Geslacht: " + getGeslacht();
    }
}