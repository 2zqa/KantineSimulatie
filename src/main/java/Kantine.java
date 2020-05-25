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
     * @param dienblad
     * @param artikelnamen
     * @param artikelprijzen
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen, double[] artikelprijzen) {
        Persoon persoon = new Persoon();
        dienblad.setKlant(persoon);

        for(int i=0; i<artikelnamen.length;i++) {
            dienblad.voegToe(new Artikel(artikelnamen[i], artikelprijzen[i]));
        }
        kassarij.sluitAchteraan(dienblad);
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
            Dienblad klant = kassarij.eerstePersoonInRij();
            kassa.rekenAf(klant);
        }
    }

    public Kassa getKassa() {
        return kassa;
    }

    public KantineAanbod getKantineaanbod() {
        return kantineaanbod;
    }

    public void setKantineaanbod(KantineAanbod kantineaanbod) {
        this.kantineaanbod = kantineaanbod;
    }
}
