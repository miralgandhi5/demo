import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by miral on 7/4/2017.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSession() throws HibernateException {
        return ourSessionFactory;
    }

    public static void updateData(int id) {
        try (Session session = getSession().openSession()) {
            Author author = session.get(Author.class, id);
            author.setFirstName("pawandeep");
            author.setLastName("Singh");
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
            System.out.println("Successfully updated ");
        }

    }

    public static void deleteData(int id) {
        try (Session session = getSession().openSession()) {
            Author author = session.get(Author.class, id);

            session.beginTransaction();
            session.delete(author);
            session.getTransaction().commit();
            System.out.println("Successfully deleted ");
        }

    }

    public static void createData(Author author) {


        Session session = getSession().openSession();
        session.beginTransaction();

        session.saveOrUpdate(author);

        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created ");
    }


    public static void queryData() {
        final Session session = getSession().openSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from Author ");

                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }



    public static void main(final String[] args) throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Author author1 = new Author("miral", "Gandhi", 23);
        author1.setDob(ft.parse("1994-06-15"));
        Address address = new Address();
        address.setLocation("pitampura");
        address.setState("delhi");
        address.setStreetNumber(42);
        author1.setAddress(address);


        Book book =new Book("Pearson");
        author1.book=book;
        createData(author1);
        queryData();
        getSession().close();


    }
}