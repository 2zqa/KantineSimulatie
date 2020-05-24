public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;

    private KantineAanbod kantineaanbod;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        Persoon persoon = new Persoon();
        dienblad.setKlant(persoon);

        for(String artikelnaam : artikelnamen) {
            // Waar haal ik de prijs vandaan? Het staat niet in de aangegeven header. Nu maar placeholder gebruikt.
            dienblad.voegToe(new Artikel(artikelnaam, 1.50));
        }

        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            Dienblad klant = kassarij.eerstePersoonInRij();
            kassa.rekenAf(klant);
        }
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    public double hoeveelheidGeldInKassa() {
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public int aantalArtikelen() {
        return kassa.aantalArtikelen()
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
    public void resetKassa() {
        kassa.resetKassa();
    }

    public KantineAanbod getKantineaanbod() {
        return kantineaanbod;
    }

    public void setKantineaanbod(KantineAanbod kantineaanbod) {
        this.kantineaanbod = kantineaanbod;
    }
}
