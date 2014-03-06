import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class EntityA {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = EntityB.class, cascade={CascadeType.ALL})
    @JoinTable(name="A_TO_B")
    public EntityB rel;
}
