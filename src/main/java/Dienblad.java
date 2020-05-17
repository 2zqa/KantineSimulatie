import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private ArrayList<Artikel> artikelen;
    private Persoon klant;

    /**
     * Voeg dienblad toe met klant
     * @param klant de klant
     */
    public Dienblad(Persoon klant) {
        this.klant = klant;
        this.artikelen = new ArrayList<>();
    }

    /**
     * Voeg dienblad toe zonder klant
     */
    public Dienblad() {
        this(null);
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel het artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        int totaalPrijs = 0;
        for (Artikel artikel : artikelen) {
            totaalPrijs += artikel.getPrijs();
        }

        return totaalPrijs;
    }

    /**
     * Retourneert de klant
     * @return de klant
     */
    public Persoon getKlant() {
        return klant;
    }

    /**
     * Stelt de klant in
     * @param klant de klant
     */
    public void setKlant(Persoon klant) {
        this.klant = klant;
    }
}

