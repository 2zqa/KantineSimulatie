public class KantineMedewerker extends Persoon {
    private int medewerkersnummer;
    private boolean magAchterKassaWerken;

    public KantineMedewerker (String bsn, String voornaam, String achternaam, Datum geboortedatum,
                              char geslacht, int medewerkersnummer, boolean magAchterKassaWerken) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.magAchterKassaWerken = magAchterKassaWerken;
    }

    public int getMedewerkersnummer() {
        return medewerkersnummer;
    }

    public void setMedewerkersnummer(int medewerkersnummer) {
        this.medewerkersnummer = medewerkersnummer;
    }

    public boolean isMagAchterKassaWerken() {
        return magAchterKassaWerken;
    }

    public void setMagAchterKassaWerken(boolean magAchterKassaWerken) {
        this.magAchterKassaWerken = magAchterKassaWerken;
    }
}
