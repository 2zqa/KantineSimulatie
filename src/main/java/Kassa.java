import javax.persistence.EntityManager;
import java.util.Iterator;

public class Kassa {
    private int totaalAantalVerkochteArtikelen;
    private double totaalHoeveelheidGeld;
    private double totaalHoeveelheidKorting;
    private EntityManager manager;

    /**
     * Constructor - stelt de klassevelden in
     */
    public Kassa(KassaRij kassarij, EntityManager manager) {
        totaalAantalVerkochteArtikelen = 0;
        totaalHoeveelheidGeld = 0;
        this.manager = manager;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur();
        int aantalArtikelen = getAantalArtikelenOpDienblad(klant);
        double totaalPrijs = factuur.getTotaal();
        double korting = factuur.getKorting();

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
            totaalHoeveelheidKorting += korting;
            System.out.println(factuur.toString());

            //opslaan van de factuur in de database
            save(factuur);
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
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double getTotaalHoeveelheidKorting() {
        return totaalHoeveelheidKorting;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalAantalVerkochteArtikelen = 0;
        totaalHoeveelheidGeld = 0;
        totaalHoeveelheidKorting = 0;
    }

    /**
     * slaat de factuur op in de database, dit is opgesplits in de onderdelen datum, korting en totaal
     * @param factuur is de factuur van de klant
     */
    public void save(Factuur factuur) {
        manager.getTransaction().begin();
        manager.persist(factuur.getDatum());
        manager.persist(factuur.getKorting());
        manager.persist(factuur.getTotaal());
        manager.getTransaction().commit();
    }
}