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
    public void simuleer(int dagen, int aantalMensen) {
        // Maak een lijst voor winst en voor prijzen
        double[] omzet = new double[dagen];
        int[] aantalArtikelen = new int[aantalMensen];
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            ArrayList<Persoon> personen = new ArrayList<>();

            // bedenk hoeveel personen vandaag binnen lopen

            // Bepaal hoeveel personen binnen mogen komen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG, MAX_PERSONEN_PER_DAG);



            // laat de personen maar komen...
            for (int j = 0; j < aantalpersonen; j++) {
                int kans = random.nextInt(aantalMensen);
                if (kans < 89) {
                    Student student = new Student();
                    personen.add(student);
                    //System.out.println(student.toString());
                } else if (kans == 90) {
                    KantineMedewerker kantineMedewerker = new KantineMedewerker();
                    personen.add(kantineMedewerker);
                    //System.out.println(kantineMedewerker.toString());
                } else {
                    Docent docent = new Docent();
                    personen.add(docent);
                    //System.out.println(docent.toString());
                }

                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // maak persoon en dienblad aan, koppel ze
                // loop de kantine binnen, pak de gewenste artikelen, sluit aan
                Dienblad dienblad = new Dienblad();
                kantine.loopPakSluitAan(dienblad, artikelnamen, artikelprijzen);

                System.out.println("getalletje j: "+ j);
                aantalArtikelen[j] = aantalartikelen;
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
            System.out.println("Aantal artikelen vandaag verkocht: " + kantine.getKassa().aantalArtikelen() +
                    "\nHoeveelheid geld in de kassa: " + kantine.getKassa().hoeveelheidGeldInKassa());
            // Voeg geld toe aan totaalwinst in deze dag (deze loop gaat per persoon)
            omzet[i] += kantine.getKassa().hoeveelheidGeldInKassa();


            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
        // Na de for-loop: print wat statistieken
        System.out.println("\n---------- Statistiek -----------");

        // debug
        for(int getal : aantalArtikelen) {
            System.out.println(getal);
        }



        System.out.println("Gemiddelde aantal artikelen per persoon: " + Administratie.berekenGemiddeldAantal(aantalArtikelen));
        System.out.println("Gemiddelde omzet: " + Administratie.berekenGemiddeldeOmzet(omzet));
        double[] dagOmzetten = Administratie.berekenDagOmzet(omzet);
        for(int i=0; i<dagOmzetten.length;i++) {
            System.out.println("Totaalwinst van alle " +(i+1) + "ste dagen van de week: " + dagOmzetten[i]);
        }
        System.out.println("\naantal dagen gesimuleerd: " + dagen);
    }

    public static void main(String[] args) {
        KantineSimulatie_2 sim = new KantineSimulatie_2();
        sim.simuleer(30, 100);
    }
}