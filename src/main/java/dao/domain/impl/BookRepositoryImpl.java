package dao.domain.impl;

import dao.domain.Book;
import dao.domain.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    private static final int PAGE_SIZE = 10;

    private SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    public List<Book> readBooks() {
        EntityManager em = sessionFactory.getCurrentSession().getEntityManagerFactory().createEntityManager();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = criteriaQuery.from(Book.class);
        CriteriaQuery<Book> all = criteriaQuery.select(from);
        TypedQuery<Book> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Book findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    @Override
    public void deleteBookById(Long id) {
       final String hql = "delete from Book where id= :bookId";
        Query deleteQuery = sessionFactory.getCurrentSession()
            .createQuery(hql)
            .setParameter("bookId", id);
        deleteQuery.executeUpdate();
    }

    @Override
    public List<Book> searchByProperties(String... properties) {

        return null;
    }

}