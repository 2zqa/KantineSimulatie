public class Kantine {
    private Kassa kassa;
    private KassaRij kassarij;

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
    public void loopPakSluitAan() {
        Persoon persoon = new Persoon();
        Dienblad dienblad = new Dienblad();
        dienblad.setKlant(persoon);

        Artikel artikel1 = new Artikel();
        Artikel artikel2 = new Artikel();

        dienblad.voegToe(artikel1);
        dienblad.voegToe(artikel2);

        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            Dienblad klant = kassarij.eerstePersoonInRij(); //Hier wordt de klant uit de rij gehaald en gereturnd
            kassa.rekenAf(klant);
        }
    }

    /**
     * methode welke de kassa van de kantine opvraagt
     *
     * @return kassa
     */
    public Kassa getKassa() {
        return kassa;
    }
}