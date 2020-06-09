import java.util.*;

public class KantineSimulatie_2 {

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
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineaanbod(kantineaanbod);
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

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {
            System.out.println("Dag " + (i+1));

            ArrayList<Persoon> personen = new ArrayList<>();

            // Stap 1. Bepaal hoeveel personen vandaag binnenkomen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);

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
                portemonnee.setSaldo(getRandomValue(4, 50));
                portemonnee.setKredietLimiet(getRandomValue(4, 50));
                netAangemaaktePersoon.setBetaalwijze(portemonnee);

                // Stap 2.3. Bepaal hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // Stap 2.4. genereer de "artikelnummers", dit zijn indexen van de artikelnamen
                // Ofwel: kies een willekeurig artikel uit de schappen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);
                // vind de artikelnamen op basis van de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // Stap 2.5. Persoon gaat in de rij staan met artikelen
                Dienblad dienblad = new Dienblad(netAangemaaktePersoon);
                kantine.loopPakSluitAan(dienblad, artikelnamen, artikelprijzen);
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
    }

    public static void main(String[] args) {
        KantineSimulatie_2 sim = new KantineSimulatie_2();
        sim.simuleer(30);
    }
}