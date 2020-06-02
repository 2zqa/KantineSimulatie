public class Docent extends Persoon{
    private String afkorting;
    private String afdeling;

    public Docent(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    /**
     * Lege constructor
     */
    public Docent() {
        super();
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    /**
     * Methode welke alle velden afdrukt
     * @return alle velden
     */
    @Override
    public String toString(){
        return super.toString() + "Afkorting: " + getAfkorting() + "\n" + "Afdeling: " + getAfdeling();
    }
}
