import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="factuurregel")
public class FactuurRegel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur")
    private Factuur factuur;

    @Embedded
    private Artikel artikel;

    public FactuurRegel() {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel =artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    @Override
    public String toString(){
        return artikel.getNaam() + ": " + artikel.getPrijs();
    }
}