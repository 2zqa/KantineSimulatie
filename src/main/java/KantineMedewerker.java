public class KantineMedewerker extends Persoon {
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
}
