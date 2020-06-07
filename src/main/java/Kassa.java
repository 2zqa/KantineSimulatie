import java.util.Iterator;

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
        int aantalArtikelen = getAantalArtikelenOpDienblad(klant);
        double totaalPrijs = getTotaalPrijsOpDienblad(klant);

        // Probeer het geld van de klant af te schrijven.
        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
        boolean betaald = betaalwijze.betaal(totaalPrijs);

        if (betaald) {
            // Tel aantal producten en de totaalprijs toe op bij klassenvelden
            totaalAantalVerkochteArtikelen += aantalArtikelen;
            totaalHoeveelheidGeld += totaalPrijs;
        } else {
            System.out.println("Klant heeft niet genoeg geld!");
        }
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelenOpDienblad(Dienblad klant) {
        Iterator<Artikel> it = klant.getLijstVanAlleArtikelen();
        int aantal = 0;
        while (it.hasNext()) {
            Artikel artikel = it.next();
            aantal++;
        }
        return aantal;
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijsOpDienblad(Dienblad klant) {
        Iterator<Artikel> it = klant.getLijstVanAlleArtikelen();
        int totaalPrijs = 0;
        while (it.hasNext()) {
            Artikel artikel = it.next();
            totaalPrijs += artikel.getPrijs();
        }
        return totaalPrijs;
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
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
        totaalHoeveelheidGeld = 0;
    }
}
