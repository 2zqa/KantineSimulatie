import javax.persistence.*;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.time.LocalDateTime;

@Entity
@Table(name="factuur")
public class Factuur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id", unique = true)
    private Long id;

    @Column(name= "datum_tijd", nullable = false)
    private LocalDateTime datum;

    @Column(name= "totale_korting", nullable = false)
    private double totaalKorting;

    @Column(name= "totaalprijs", nullable = false)
    private double totaalPrijs;

    public Factuur(Dienblad klant){
        this.datum = LocalDateTime.now();
        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen
     *
     * @param klant is de huidige klant bij de kasse
     */
    private void verwerkBestelling(Dienblad klant){
        Iterator<Artikel> it = klant.getLijstVanAlleArtikelen();

        double totaalPrijs = 0;
        while (it.hasNext()) {
            Artikel artikel = it.next();
            
            double artikelKorting = artikel.getKorting();
            double artikelPrijs = artikel.getPrijs() - artikelKorting;

            /*
             NOTE: hieronder is een afweging gemaakt tussen:
                   dubbele code (van te voren checken of klant instanceof KortingskaartHouder is) en theoretisch betere performance
                   óf dubbele code voorkomen en een if-statement in de for-loop die steeds hetzelfde teruggeeft.
                   In dit geval is gekozen om dubbele code te voorkomen ten koste van betere performance.

                   Stackoverflow threads over het probleem:
                   C++: https://stackoverflow.com/questions/16871471/avoiding-if-statement-inside-a-for-loop
                   Java: https://stackoverflow.com/questions/12224132/nested-if-statement-in-loop-vs-two-separate-loops
            */

            // Als klant kortinghouder is én het artikel géén dagaanbieding/korting heeft
            if(klant instanceof KortingskaartHouder && artikelKorting == 0) {
                KortingskaartHouder kortinghouder = (KortingskaartHouder) klant;

                // Als de kortinghouder een limiet op zijn korting heeft én
                // de korting boven die limiet uitkomt, betaal gelimiteerd uit.
                // Betaal anders regulier uit.
                double prijsMetKaarthoudersKorting = (1 - kortinghouder.geefKortingsPercentage()) * artikelPrijs;
                totaalKorting = artikelPrijs - prijsMetKaarthoudersKorting;

                if (kortinghouder.heeftMaximum() && totaalKorting > kortinghouder.geefMaximum()) {
                    artikelPrijs -= kortinghouder.geefMaximum();
                } else {
                    artikelPrijs = prijsMetKaarthoudersKorting;
                }
            }
            totaalPrijs += artikelPrijs;
            totaalKorting += artikelKorting;
        }
        this.totaalPrijs = totaalPrijs;
    }

    /**
     * @return het totaalbedrag
     */
    public double getTotaal(){
        return totaalPrijs;
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return totaalKorting;
    }

    public String getDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return datum.format(formatter);
    }

    /**
     * @return een printbaar bonnetje
     */
    @Override
    public String toString(){
            return ("Datum: " + getDatum() + "\nKorting: " +
                    getKorting() + "\nTotaalprijs: " + getTotaal());
        }
}