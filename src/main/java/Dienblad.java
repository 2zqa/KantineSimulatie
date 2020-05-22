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
     * Dit roept de andere constructor "public Dienblad(Persoon klant)" aan, met de parameter "Persoon klant" null
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
     *
     * @return artikelen.iterator()
     */
    public Iterator<Artikel> getLijstVanAlleArtikelen(){
        return artikelen.iterator();
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


