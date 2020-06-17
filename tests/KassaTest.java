import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KassaTest {

    @Test
    void getTotaalPrijsOpDienblad() {
        // Kassa
        KassaRij kassaRij = new KassaRij();
        Kassa kassa = new Kassa(kassaRij);

        // Klant
        Docent docent = new Docent();
        System.out.println("Kortingspercentage van docent is: " + ((KortingskaartHouder)docent).geefKortingsPercentage() + " ofwel " + (((KortingskaartHouder)docent).geefKortingsPercentage()*100) + "%");
        System.out.println("Maximumkorting van deze knaap is: " + ((KortingskaartHouder)docent).geefMaximum());
        Dienblad dienblad = new Dienblad(docent);

        // Producten
        ArrayList<Artikel> producten = new ArrayList<>();
        producten.add(new Artikel("Hagelslag", 3));
        producten.add(new Artikel("Luxe Broodje", 100));
        producten.add(new Artikel("Melk", 1.50));
        Artikel vla = new Artikel("[dagaanbieding] vla", 100);
        vla.setKorting(3.5); // ofwel: er gaat 3.50 af van de prijs: 100-3.5
        producten.add(vla);

        for (Artikel artikel : producten) {
            dienblad.voegToe(artikel);
            System.out.println("Prijs van " + artikel.getNaam() + " is " + artikel.getPrijs());
        }

        // hagelslag  Luxe broodje            Melk          Dagaanbieding vla
        // (3*0.75) + (100-1 !max korting!) + (1.50*0.75) + (100-3.5 !over maxkorting!) = 198.875
        assertEquals(198.875, kassa.getTotaalPrijsOpDienblad(dienblad));
    }
}