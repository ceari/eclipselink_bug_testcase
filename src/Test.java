import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Testcase for EclipseLink Bug
 *
 * EntityA has a OneToOne(cascade={CascadeType.ALL}) relationship to EntityB mapped with a JoinTable.
 * When deleting an instance of EntityA I get a foreign key constraint violation in the
 * join table when it tries to delete from the EntityB table.
 *
 * This does not happen when EntityA is renamed to EntityC. Then the delete on the join table
 * happens before the delete on the EntityB table as expected.
 *
 */
public class Test {
    public static void main(String[] args) {
        new Test().test();
    }

    void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        EntityA a = new EntityA();

        a.rel = new EntityB();

        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
    }

}
