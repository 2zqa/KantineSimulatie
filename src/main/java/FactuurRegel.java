import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="factuurregel")
public class FactuurRegel implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "factuur", nullable = false)
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
    public String toString(){
        //methode body
        return null;
    }
}
