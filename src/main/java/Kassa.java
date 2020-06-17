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

        // LET OP: Korting wordt nu in getTotaalPrijsOpDienblad berekend!

        // Sla betaalwijze op
        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();

        // Probeer te betalen
        try {
            betaalwijze.betaal(totaalPrijs);

            // Tel aantal producten en de totaalprijs toe op bij klassenvelden.
            // N.B. Dit gebeurt niet als de betaling hierboven niet gelukt is, omdat de foutmelding de uitvoering stopt.
            totaalAantalVerkochteArtikelen += aantalArtikelen;
            totaalHoeveelheidGeld += totaalPrijs;
        } catch (TeWeinigGeldException e) {
            Persoon schuldige = klant.getKlant();
            double schuld = totaalPrijs - betaalwijze.saldo;
            System.out.println("OPGELET! Individu \"" + schuldige.getVoornaam() + " " + schuldige.getAchternaam() + "\" kon zijn rekening niet betalen: " + e.getMessage());
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
    public double getTotaalPrijsOpDienblad(Dienblad dienblad) {
        Iterator<Artikel> it = dienblad.getLijstVanAlleArtikelen();
        Persoon klant = dienblad.getKlant();

        double totaalPrijs = 0;
        while (it.hasNext()) {
            Artikel artikel = it.next();
            double dagAanbiedingKorting = artikel.getKorting();
            double artikelPrijs = artikel.getPrijs() - dagAanbiedingKorting;


            /*
             NOTE: hieronder is een afweging gemaakt tussen:
                   dubbele code (van te voren checken of klant instanceof KortingskaartHouder is) en theoretisch betere performance
                   óf dubbele code voorkomen en een if-statement in de for-loop die steeds hetzelfde teruggeeft.
                   In dit geval is gekozen om dubbele code te voorkomen ten koste van betere performance.

                   Stackoverflow threads over het probleem:
                   C++: https://stackoverflow.com/questions/16871471/avoiding-if-statement-inside-a-for-loop
                   Java: https://stackoverflow.com/questions/12224132/nested-if-statement-in-loop-vs-two-separate-loops
            */

             // Als klant kortinghouder is én het artikel géén dagaanbieding heeft
            if(klant instanceof KortingskaartHouder && dagAanbiedingKorting == 0) {
                KortingskaartHouder kortinghouder = (KortingskaartHouder) klant;

                // Als de kortinghouder een limiet op zijn korting heeft én
                // de korting boven die limiet uitkomt, betaal gelimiteerd uit.
                // Betaal anders regulier uit.
                double prijsMetKorting = (1 - kortinghouder.geefKortingsPercentage()) * artikelPrijs;
                double personeelsKorting = artikelPrijs - prijsMetKorting;

                if (kortinghouder.heeftMaximum() && personeelsKorting > kortinghouder.geefMaximum()) {
                    artikelPrijs -= kortinghouder.geefMaximum();
                } else {
                    // Mijn hersenen zijn numb
                    artikelPrijs = prijsMetKorting;
                }
            }

            totaalPrijs += artikelPrijs;
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