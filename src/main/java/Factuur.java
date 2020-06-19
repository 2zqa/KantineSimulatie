import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {
    private Long id;
    private LocalDate datum;
    private double korting;
    private double totaal;

    public Factuur(){
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant){
        Iterator<Artikel> it = klant.getLijstVanAlleArtikelen();

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
                korting = artikelPrijs - prijsMetKorting;

                if (kortinghouder.heeftMaximum() && korting > kortinghouder.geefMaximum()) {
                    artikelPrijs -= kortinghouder.geefMaximum();
                } else {
                    artikelPrijs = prijsMetKorting;
                }
            }
            totaalPrijs += artikelPrijs;
        }
        totaal = totaalPrijs;
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotal(){
        return totaal;
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return een printbaar bonnetje
     */
    public String toString(){
       return null;
    }
}
