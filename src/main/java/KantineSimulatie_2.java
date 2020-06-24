import java.util.*;
import javax.persistence.*;

public class KantineSimulatie_2 {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineaanbod(kantineaanbod);
    }

    public void runVoorbeeld() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        //transactionsomitted

        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Genereert arrayList van artikelen
     * @param artikelnamen namen van artikelen
     * @param artikelprijzen prijzen van artikelen
     * @return arraylijst van artikelen
     */
    private ArrayList<Artikel> genereerArtikelArray(String[] artikelnamen, double[] artikelprijzen) {
        // Check of prijzen en artikelnamen array wel zelfde lengte heeft
        if(artikelnamen.length != artikelprijzen.length) {
            throw new IllegalArgumentException("arrays moeten zelfde aantal elementen hebben.");
        }

        ArrayList<Artikel> artikelen = new ArrayList<>();

        // Genereer artikelarray
        for(int i=0;i<artikelnamen.length;i++) {
            artikelen.add(new Artikel(artikelnamen[i], artikelprijzen[i]));
        }

        return artikelen;
    }

    /**
     * Stelt korting in op een willekeurig artikel uit de arraylist.
     *
     * @param artikelen arraylist van artikelen
     * @param korting percentage van 0 tot 100 - 0 is geen korting, 100 is 100%.
     * @return
     */
    private ArrayList<Artikel> stelKortingInOpWillekeurigAantalArtikelen(ArrayList<Artikel> artikelen, int korting) {
        int maxAantalArtikelenMetKorting = random.nextInt(artikelen.size());

        for(int i=0;i<maxAantalArtikelenMetKorting;i++) {
            // Pak willekeurig artikel uit lijst
            int artikelIndex = random.nextInt(artikelen.size());
            Artikel artikel = artikelen.get(artikelIndex);

            // Stel korting in
            double kortingBedrag = artikel.getPrijs() * (((double)korting)/100);
            artikel.setKorting(kortingBedrag);

            // Vervang oude artikel zónder korting met artikel mét korting
            artikelen.set(artikelIndex, artikel);
        }

        return artikelen;
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte de lengte van de array
     * @param min de minimale lengte
     * @param max de maximale lengte
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }
        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min de minimale lengte
     * @param max de maximale lengte
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen de indexen van de array
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];
        }
        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen in het verloop van de kantine
     */
    public void simuleer(int dagen) {
        // Voor de statistiek: Maak een lijst voor omzet en aantal verkochte artikelen per dag
        double[] omzet = new double[dagen];
        int[] aantalArtikelenPerDag = new int[dagen];

        // Aanbod
        ArrayList<Artikel> aanbod = genereerArtikelArray(artikelnamen, artikelprijzen);

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {
            System.out.println("Dag " + (i+1));

            ArrayList<Persoon> personen = new ArrayList<>();

            // Stap 1. Bepaal hoeveel personen vandaag binnenkomen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

            // Stap 2. Stel korting in op willekeurig aantal artikelen
            ArrayList<Artikel> aanbodMetKorting = stelKortingInOpWillekeurigAantalArtikelen(aanbod, 20);

            // Stap 2. laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {
                // Stap 2.1. Maak persoon
                int kans = random.nextInt(101); // random integer van 0 tot en met 100
                if (kans <= 89) { // 89/100e kans
                    Student student = new Student();
                    personen.add(student);
                } else if (kans == 90) { // 1/100e kans
                    KantineMedewerker kantineMedewerker = new KantineMedewerker();
                    personen.add(kantineMedewerker);
                } else { // (100-1-89)/100e kans, ofwel 10/100e kans, ofwel 1/10e kans
                    Docent docent = new Docent();
                    personen.add(docent);
                }

                // Stap 2.2. Geef dit nieuwe persoon wat geld
                Persoon netAangemaaktePersoon = personen.get(j);
                // Maak portemonnee en geef het aan persoon
                Pinpas portemonnee = new Pinpas();
                portemonnee.setSaldo(getRandomValue(-5, 20));
                portemonnee.setKredietLimiet(getRandomValue(-20, 0));
                netAangemaaktePersoon.setBetaalwijze(portemonnee);

                // Stap 2.3. Bepaal hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // Stap 2.4. Dienblad wordt gemaakt
                Dienblad dienblad = new Dienblad(netAangemaaktePersoon);

                // Stap 2.5. Pak artikelen uit aanbodMetKorting. TODO: gebruik KantineAanbod i.p.v. lokale variabele 'aanbod(MetKorting)'
                // pak k aantal keer een willekeurig artikel uit array 'aanbodMetKorting' en voeg deze artikelen toe aan artikelenOpDienblad
                for (int k=0;k<aantalartikelen;k++) {
                    int artikelIndex = random.nextInt(aanbodMetKorting.size());
                    Artikel artikel = aanbodMetKorting.get(artikelIndex);
                    dienblad.voegToe(artikel);
                }

                // Stap 2.6. Voeg artikelen toe aan dienblad.
                // Note: loopPakSluitAan is deprecated omdat we nu zelf artikelen toevoegen
                // aan het dienblad en dan het dienblad zelf aan de kassarij toevoegen
                kantine.getKassarij().sluitAchteraan(dienblad); // TODO: bekijken of dit in soortgelijke looppaksluitaan methode kan? samen met die k-forloop
            }

            // Stap 3. verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // Druk per dag de winst en hoeveel personen binnen zijn gekomen af
            System.out.println("Aantal artikelen vandaag verkocht: " + kantine.getKassa().aantalArtikelen() +
                    "\nHoeveelheid geld in de kassa: " + kantine.getKassa().hoeveelheidGeldInKassa() + "\n");
            // Voor de statistiek: Voeg geld toe aan totaalwinst in deze dag (deze loop gaat per persoon)
            omzet[i] += kantine.getKassa().hoeveelheidGeldInKassa();
            aantalArtikelenPerDag[i] += kantine.getKassa().aantalArtikelen();

            // Stap 4. (Laatste stap) reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
        // Na de for-loop: print wat statistieken
        System.out.println("\n---------- Statistiek -----------");

        System.out.println("Gemiddelde aantal artikelen per dag: " + Administratie.berekenGemiddeldAantal(aantalArtikelenPerDag));
        System.out.println("Gemiddelde omzet: " + Administratie.berekenGemiddeldeOmzet(omzet));
        double[] dagOmzetten = Administratie.berekenDagOmzet(omzet);
        for(int i=0; i<dagOmzetten.length;i++) {
            System.out.println("Totaalwinst van alle " +(i+1) + "ste dagen van de week: " + dagOmzetten[i]);
        }
        System.out.println("\naantal dagen gesimuleerd: " + dagen);

        // Database statistieken

        System.out.println("\n---6.3---");
        //1
        Query query = manager.createNativeQuery("SELECT sum(totaalprijs) FROM factuur");
        double totaleTotaalPrijs = (double) query.getSingleResult();

        query = manager.createNativeQuery("SELECT sum(totale_korting) FROM factuur");
        double totaleToegepasteKorting = (double) query.getSingleResult();
        System.out.println(" 1. totale omzet: " + rondGeldAf(totaleTotaalPrijs) + ", totale toegepaste korting: " + rondGeldAf(totaleToegepasteKorting));

        //2
        query = manager.createNativeQuery("SELECT avg(totaalprijs) FROM factuur");
        double gemiddeldeOmzet = (double) query.getSingleResult();

        query = manager.createNativeQuery("SELECT totale_korting FROM factuur");
        List<Double> toegepasteKortingLijst = query.getResultList();
        System.out.println(" 2. Gemiddelde omzet: " + rondGeldAf(gemiddeldeOmzet) + ", alle toegepaste kortingen: ");
        String kortingen = "";
        for(double toegepasteKorting : toegepasteKortingLijst) {
            kortingen += toegepasteKorting + ", ";
        }
        // Print kortingen en verwijdert de "trailing komma"
        System.out.println("     " + kortingen.replaceAll(", $", ""));

        // 3
        // Factuur heeft hoofdletter omdat dit moet, het pakt de klassenaam ipv de tabelnaam voor een of andere reden grrr
        query = manager.createQuery("SELECT f FROM Factuur f ORDER BY totaalprijs", Factuur.class);
        query.setMaxResults(3); // Vervangt LIMIT 3
        List<Factuur> factuurLijst = query.getResultList();
        System.out.println(" 3. Top 3 facturen met de hoogste omzet: ");
        for(int i=0; i<3;i++) {
            System.out.println((i+1) + ". " + factuurLijst.get(i) + "\n");
        }

        System.out.println("---6.5---");
        //a
        // Dit kan waarschijnlijk efficienter maar ik ben te lui om mijn eigen native query parser dingen te maken
        query = manager.createNativeQuery("SELECT naam FROM factuurregel GROUP BY naam");
        List<String> artikelNamen = (List<String>) query.getResultList();

        query = manager.createNativeQuery("SELECT sum(prijs) FROM factuurregel GROUP BY naam");
        List<Double> prijsTotalenPerArtikel = (List<Double>) query.getResultList();

        query = manager.createNativeQuery("SELECT sum(korting) FROM factuurregel GROUP BY naam");
        List<Double> kortingTotalenPerArtikel = (List<Double>) query.getResultList();

        System.out.println(" a. totalen en toegepaste korting per artikel: ");
        for(int i=0;i<artikelNamen.size();i++) {
            System.out.println("     " + artikelNamen.get(i) + ": prijstotaal: " + prijsTotalenPerArtikel.get(i) + ", kortingtotaal: " + kortingTotalenPerArtikel.get(i));
        }

        //b
        // Dit kan waarschijnlijk efficienter maar ik ben te lui om mijn eigen native query parser dingen te maken
        query = manager.createNativeQuery("SELECT DAY(f.datum_tijd) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id=fr.factuur GROUP BY DAY(f.datum_tijd), naam");
        List<Integer> dagNummers = (List<Integer>) query.getResultList();

        query = manager.createNativeQuery("SELECT fr.naam FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id=fr.factuur GROUP BY DAY(f.datum_tijd), naam");
        List<String> artikelNamenPerDag = (List<String>) query.getResultList();

        query = manager.createNativeQuery("SELECT sum(fr.prijs) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id=fr.factuur GROUP BY DAY(f.datum_tijd), naam");
        List<Double> prijsTotalenPerArtikelPerDag = (List<Double>) query.getResultList();

        query = manager.createNativeQuery("SELECT sum(fr.korting) FROM factuurregel AS fr LEFT OUTER JOIN factuur AS f ON f.id=fr.factuur GROUP BY DAY(f.datum_tijd), naam");
        List<Double> kortingTotalenPerArtikelPerDag = (List<Double>) query.getResultList();

        System.out.println(" a. totalen en toegepaste korting per artikel per dag: ");
        for(int i=0;i<dagNummers.size();i++) {
            System.out.println("     Dag " + dagNummers.get(i) + ": " + artikelNamenPerDag.get(i) + ": prijstotaal: " + prijsTotalenPerArtikelPerDag.get(i) + ", kortingtotaal: " + kortingTotalenPerArtikelPerDag.get(i));
        }

        //c
        query = manager.createNativeQuery("SELECT naam FROM factuurregel GROUP BY naam ORDER BY count(naam)");
        query.setMaxResults(3); // Vervangt LIMIT 3
        List<String> artikelLijstPopulair = (List<String>) query.getResultList();
        System.out.println(" c. Top 3 meest voorkomende artikelen: ");
        for(int i=0; i<3;i++) {
            System.out.println("     " + (i+1) + ". " + artikelLijstPopulair.get(i));
        }

        //d
        query = manager.createNativeQuery("SELECT naam FROM factuurregel GROUP BY naam ORDER BY sum(prijs) DESC");
        query.setMaxResults(3); // Vervangt LIMIT 3
        List<String> artikelLijstOmzet = (List<String>) query.getResultList();
        System.out.println(" d. Top 3 artikelen met hoogste omzet: ");
        for(int i=0; i<3;i++) {
            System.out.println("     " + (i+1) + ". " + artikelLijstOmzet.get(i));
        }
    }

    private double rondGeldAf(double getal) {
        return Math.round(getal*100.0)/100.0;
    }

    public static void main(String[] args) {
        KantineSimulatie_2 sim = new KantineSimulatie_2();
        sim.simuleer(30);
    }
}