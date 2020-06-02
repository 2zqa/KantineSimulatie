public class Administratie {

    final static int DAYS_IN_WEEK = 7;

    /**
     * Lege constructor voorkomt initialisatie.
     */
    private Administratie() {}

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        switch(aantal.length) {
            case 0:
                return 0.0;
            case 1:
                return aantal[0];
            default:
                int n = aantal.length;
                int sum = 0;
                for(int i : aantal) {
                    sum += i;
                }
                return (double) sum/n;
        }
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde.
     * Zou deze dubbele code waarschijnlijk kunnen voorkomen door middel van een hulpmethode samen met berekenGemiddeldAantal,
     * maar wil geen risico nemen en daarmee ons cijfer mogelijk omlaag halen
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        switch(omzet.length) {
            case 0:
                return 0.0;
            case 1:
                return omzet[0];
            default:
                int n = omzet.length;
                double sum = 0;
                for(double i : omzet) {
                    sum += i;
                }
                return sum/n;
        }
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        // Array met omzetten per dag (maandag, dinsdag, etc.)
        double[] dagOmzetten = new double[DAYS_IN_WEEK];

        // Itereer per dag in de week
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            // j is het weeknummer
            int j = 0;

            // ga door zo lang dag (dag) in week (j) beschikbaar is
            int dag = i + DAYS_IN_WEEK * j;
            while (dag < omzet.length) {
                // Als die dag dus kennelijk bestaat, voeg de omzet van die dag toe aan de overeenkomende index van onze dagOmzetten

                // Voeg omzet van dag toe aan huidige dag in array
                dagOmzetten[i] += omzet[dag];

                // Ga naar volgende week (was het maar zo makkelijk in het echt!)
                j++;
            }
        }

        return dagOmzetten;
    }
}
