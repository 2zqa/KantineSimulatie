public class Student extends Persoon {
    private int studentnummer;
    private String studierichting;

    public Student(String bsn, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int studentnummer, String studierichting) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    /**
     * Lege constructor
     */
    public Student() {
        super();
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    /**
     * Methode welke alle velden afdrukt
     * @return alle velden
     */
    @Override
    public String toString(){
        return super.toString() + "Studentnummer: " + getStudentnummer() + "\n" + "Studierichting: " + getStudierichting();
    }
}


