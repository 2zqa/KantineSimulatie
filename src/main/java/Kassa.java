public class Kassa {
    private int totaalAantalVerkochteArtikelen;
    private double totaalHoeveelheidGeld;

    /**
     * Constructor - stelt de klassevelden in
     */
    public Kassa(KassaRij kassarij) {
        totaalAantalVerkochteArtikelen = 0;
        totaalHoeveelheidGeld = 0;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        int aantalArtikelen = klant.getAantalArtikelen();
        double totaalPrijs = klant.getTotaalPrijs();

        // Tel aantal producten en de totaalprijs toe op bij klassenvelden
        totaalAantalVerkochteArtikelen += aantalArtikelen;
        totaalHoeveelheidGeld += totaalPrijs;
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return totaalAantalVerkochteArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalHoeveelheidGeld;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalAantalVerkochteArtikelen = 0;
    }
}
