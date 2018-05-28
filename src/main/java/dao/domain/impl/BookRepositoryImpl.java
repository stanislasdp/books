package dao.domain.impl;

import dao.domain.Book;
import dao.domain.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Book.class)));
        Long count = em.createQuery(countQuery).getSingleResult();

        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = criteriaQuery.from(Book.class);
        CriteriaQuery<Book> select = criteriaQuery.select(from);

        TypedQuery<Book> typedQuery = em.createQuery(select);
        int pageNumber = 0;
        while (pageNumber < count.intValue()) {
            typedQuery.setFirstResult(pageNumber - 1);
            typedQuery.setMaxResults(PAGE_SIZE);
            pageNumber += PAGE_SIZE;
        }
        return typedQuery.getResultList();
    }

    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    public void deleteBook(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }
}