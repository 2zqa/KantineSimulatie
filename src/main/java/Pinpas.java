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
        // Als de kosten meer zijn dan er geld beschikbaar is,
        // OF de rekening overschrijft (is meer dan) de kredietlimiet,
        // gooi foutmelding
        if (saldo < tebetalen) {
            throw new TeWeinigGeldException("Te weinig geld :(");
        } else if (tebetalen > kredietlimiet) {
            throw new TeWeinigGeldException("Kredietlimiet overschreden :(");
        } else {
            // Werk saldo bij
            saldo -= tebetalen;

            // Werk kredietlimiet bij - anders zou je 10x 499 euro kunnen afschrijven als je limiet bijvoorbeeld 500 euro is
            kredietlimiet -= tebetalen;
        }
    }
}