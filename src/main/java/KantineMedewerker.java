public class KantineMedewerker extends Persoon implements KortingskaartHouder {
    private int medewerkersnummer;
    private boolean magAchterKassaWerken;

    public KantineMedewerker (String bsn, String voornaam, String achternaam, Datum geboortedatum,
                              char geslacht, int medewerkersnummer, boolean magAchterKassaWerken) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.magAchterKassaWerken = magAchterKassaWerken;
    }

    public KantineMedewerker() {
        super();
    }

    public int getMedewerkersnummer() {
        return medewerkersnummer;
    }

    public void setMedewerkersnummer(int medewerkersnummer) {
        this.medewerkersnummer = medewerkersnummer;
    }

    public boolean getMagAchterKassaWerken() {
        return magAchterKassaWerken;
    }

    public void setMagAchterKassaWerken(boolean magAchterKassaWerken) {
        this.magAchterKassaWerken = magAchterKassaWerken;
    }

    /**
     * Methode welke alle velden afdrukt
     * @return alle velden
     */
    @Override
    public String toString(){
        return super.toString() + "Medewerkersnummer: " + getMedewerkersnummer() + "\n"
                + "Mag achter de kasse werken: " + getMagAchterKassaWerken();
    }

    /**
     * Methode om kortingspercentage op te vragen
     */
    @Override
    public double geefKortingsPercentage() {
        return 0.35;
    }

    /**
     * Methode om op te vragen of er maximum per keer aan de korting zit
     */
    @Override
    public boolean heeftMaximum() {
        double maximum = geefMaximum();
        return maximum != 0;
    }

    /**
     * Methode om het maximum kortingsbedrag op te vragen
     */
    @Override
    public double geefMaximum() {
        return 0;
    }
}
