public class KantineSimulatie {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen aantal dagen dat gesimuleerd moet worden
     */
    public void simuleer(int dagen) {

        // herhaal voor elke dag
        for (int i = 0; i< DAGEN; i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int j = 0; j < 10 + i; j++) {
                kantine.loopPakSluitAan();
            }

            // verwerk rij voor de kassa

            // toon dagtotalen (artikelen en geld in kassa)
            System.out.println("Aantal artikelen: " + kantine.aantalArtikelen() + "\nHoeveelheid geld in de kassa: " + kantine.hoeveelheidGeldInKassa());
            // reset de kassa voor de volgende dag
            kantine.resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        simulate(dagen);
    }
}
