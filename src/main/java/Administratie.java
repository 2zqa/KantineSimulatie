public class Administratie {

    final static int DAYS_IN_WEEK = 7;

    /**
     * Lege constructor voorkomt initialisatie.
     */
    private Administratie() {}

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param waardes
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] waardes) {
        double result;
        switch (waardes.length) {
            case 0:
                result = 0.0;
                break;
            case 1:
                result = waardes[0];
                break;
            default:
                int n = waardes.length;
                int sum = 0;
                for (int i : waardes) {
                    sum += i;
                }
                result = (double) sum / n;
                break;
        }
        return result;
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
        double result;
        switch (omzet.length) {
            case 0:
                result = 0.0;
                break;
            case 1:
                result = omzet[0];
                break;
            default:
                int n = omzet.length;
                double sum = 0;
                for (double i : omzet) {
                    sum += i;
                }
                result = sum / n;
                break;
        }
        return result;
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
            int j=0;
            // i is de dagnummer, j is het weeknummer
            while (i + DAYS_IN_WEEK * j < omzet.length) {
                dagOmzetten[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;

            }
        }

        return dagOmzetten;
    }
}
