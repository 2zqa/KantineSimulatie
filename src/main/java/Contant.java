public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if (saldo > tebetalen) {
            throw new TeWeinigGeldException("Te weinig geld! :(");
        } else {
            setSaldo(saldo - tebetalen);
        }
    }
}
