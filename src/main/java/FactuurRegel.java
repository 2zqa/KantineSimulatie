import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="factuurregel")
public class FactuurRegel implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur")
    private Factuur factuur;

    @Column(name = "artikel", nullable = false)
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
        return ("Artikel: " + artikel);
    }
}