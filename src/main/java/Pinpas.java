public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        // Als saldo met de kosten eraf getrokken onder de kredietlimiet uitkomt, geef foutmelding.
        if (saldo - tebetalen < kredietlimiet) {
            throw new TeWeinigGeldException("Kredietlimiet overschreden :(");
        } else {
            // Werk saldo bij
            saldo -= tebetalen;
        }
    }
}
