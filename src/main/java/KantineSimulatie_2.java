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
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            ArrayList<Persoon> personen = new ArrayList<>();

            // bedenk hoeveel personen vandaag binnen lopen
            final int aantalStudenten = 89;
            final int aantalDocenten = 10;

            // Maak Personen
            for (int s=0;s<aantalStudenten;s++) {
                personen.add(new Student());
            }

            for (int d=0;d<aantalDocenten;d++) {
                personen.add(new Docent());
            }

            personen.add(new KantineMedewerker("0987654321", "KAREL", "KAUWEBLOEM", new Datum(4,11,1999), 'V', 316, false));

            // laat de personen maar komen...
            //for (int j = 0; j < personen.size(); j++) {
            for (Persoon persoon : personen) {

                if (persoon instanceof Student) {
                   System.out.println(((Student)persoon).toString());
                }
                if (persoon instanceof Docent) {
                    System.out.println(((Docent)persoon).toString());
                }
                if (persoon instanceof KantineMedewerker) {
                    System.out.println(((KantineMedewerker)persoon).toString());
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
            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
            System.out.println("Aantal artikelen: " + kantine.getKassa().aantalArtikelen() +
                    "\nHoeveelheid geld in de kassa: " + kantine.getKassa().hoeveelheidGeldInKassa());
            // Roept de drie methodes aan van Administratie: gemiddelde van ???, gemiddelde omzet en de "dagtotalen"
            System.out.println("Gemiddelde: " + Administratie.berekenGemiddeldAantal(???));
            Administratie.berekenGemiddeldeOmzet(???);
            Administratie.berekenDagOmzet(???);
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();
        }
    }
}
