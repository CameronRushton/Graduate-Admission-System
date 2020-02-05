import com.myApp.model.BuddyInfo;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {

    @Test
    public void performJPA() {

        // Creating objects representing some products
        BuddyInfo bInfo1 = new BuddyInfo();
        bInfo1.setId(1L);
        bInfo1.setName("AAA");
        bInfo1.setAddress("Address1");
        bInfo1.setPhoneNumber("345135");
        bInfo1.setAge(20);

        BuddyInfo bInfo2 = new BuddyInfo();
        bInfo2.setId(2L);
        bInfo2.setName("BBB");
        bInfo2.setAddress("Address2");
        bInfo2.setPhoneNumber("3451354");
        bInfo2.setAge(25);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(bInfo1);
        em.persist(bInfo2);
        tx.commit();

//        EntityTransaction tx2 = em.getTransaction();
//        com.myApp.model.AddressBook book = new com.myApp.model.AddressBook();
//        book.setMyId(9);
//        book.addBuddy(bInfo1);
//        book.addBuddy(bInfo2);
//
//        tx2.begin();
//        em.persist(book);
//        tx2.commit();

//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT b FROM com.myApp.model.BuddyInfo b");
//
//        @SuppressWarnings("unchecked")
//        List<com.myApp.model.BuddyInfo> results = q.getResultList();
//
//        System.out.println("List of buddy info\n----------------");
//
//        for (com.myApp.model.BuddyInfo b : results) {
//
//            System.out.println(b.getName() + " (age=" + b.getAge() + ")");
//        }

//        // Querying the contents of the database using JPQL query
//        Query query = em.createQuery("SELECT b FROM com.myApp.model.AddressBook b");
//
//        @SuppressWarnings("unchecked")
//        List<com.myApp.model.AddressBook> results2 = query.getResultList();
//
//        System.out.println("List of buddy info\n----------------");
//
//        for (com.myApp.model.AddressBook b : results2) {
//            for (com.myApp.model.BuddyInfo buddyInfo : b.getBuddies()) {
//                System.out.println(buddyInfo.toString());
//            }
//
//
//        }

        // Closing connection
        em.close();

        emf.close();
    }

}
