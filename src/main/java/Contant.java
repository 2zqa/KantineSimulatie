public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        // Als de kosten meer zijn dan er geld beschikbaar is, gooi foutmelding
        if (saldo < tebetalen) {
            throw new TeWeinigGeldException("Te weinig geld! :(");
        } else {
            // Volgens mij kan ik deze else-statement weghalen en deze code simpelweg achter de if-statement zetten
            // omdat er toch een exception gegooid wordt. Maar dit is ook beter leesbaar. Dus ik laat het staan.
            saldo -= tebetalen;
        }
    }
}
