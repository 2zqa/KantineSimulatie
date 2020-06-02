import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratieTest {

    @Test
    void berekenGemiddeldAantal() {
        // -- Positieve test --

        // Reguliere check met 4 waardes
        int[] intArray = new int[]{ -10, 10, 2, 4 };
        // Verwachte output: 6/4e ofwel 1.5
        double output1 = Administratie.berekenGemiddeldAantal(intArray);
        assertEquals(1.5, output1);

        // Check met lege array
        int[] legeArray = new int[0];
        // Verwachte waarde: 0, want het is een lege array
        double output2 = Administratie.berekenGemiddeldAantal(legeArray);
        assertEquals(0, output2);

        // Check met één waarde
        int[] arrayMetEenWaarde = new int[]{4};
        // verwachte waarde: 4/1 ofwel 4
        double output3 = Administratie.berekenGemiddeldAantal(arrayMetEenWaarde);
        assertEquals(4, output3);

        // -- Negatieve test --
    }

    @Test
    void berekenGemiddeldeOmzet() {
        // -- Positieve tests --

        // Reguliere check met 4 waardes
        double[] doubleArray = new double[]{ -3.2, 3.2, 1.2, 3.8 };
        // Verwachte output: 5.0/4 ofwel 1.25
        double output1 = Administratie.berekenGemiddeldeOmzet(doubleArray);
        assertEquals(1.25, output1);

        // Check met lege array
        double[] legeArray = new double[0];
        // Verwachte waarde: 0, want het is een lege array
        double output2 = Administratie.berekenGemiddeldeOmzet(legeArray);
        assertEquals(0.0, output2);

        // Check met één waarde
        double[] arrayMetEenWaarde = new double[]{4.3};
        // verwachte waarde: 4.3/1 = 4.3
        double output3 = Administratie.berekenGemiddeldeOmzet(arrayMetEenWaarde);
        assertEquals(4.3, output3);
    }

    @Test
    void berekenDagOmzet() {
        // -- Positieve tests --

        // Reguliere check met 11 waardes (1 week en 4 dagen)
        //Dagen:                           ma      di      wo      do      vri     za      zo      ma      di      wo      do
        double[] dagTotalen = new double[]{321.35, 450.50, 210.45, 190.85, 193.25, 159.90, 214.25, 220.90, 201.90, 242.70, 260.35};
        //Dagen:                               ma      di     wo      do     vri     za      zo
        double[] verwachteArray = new double[]{542.25, 652.4, 453.15, 451.2, 193.25, 159.90, 214.25};
        double[] output1 = Administratie.berekenDagOmzet(dagTotalen);

        // Hoever een double waarde mag afwijken van de verwachte waarde
        double errorMargin = 0.0002;

        // Voor de zekerheid, check of de twee arrays hetzelfde zijn
        assertArrayEquals(verwachteArray, output1, errorMargin);
    }
}