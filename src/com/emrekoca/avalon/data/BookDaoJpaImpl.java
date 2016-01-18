package com.emrekoca.avalon.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emrekoca.avalon.domain.Book;

@Repository
public class BookDaoJpaImpl implements BookDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> allBooks() {
		return em.createQuery("select book from Book as book").getResultList();
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		try {
			return (Book) em.createQuery("select book from Book as book where book.isbn=:isbn")
					.setParameter("isbn", isbn)
					.getSingleResult();
		} catch (NoResultException ex) {
			throw new BookNotFoundException();
		}
	}

	@Override
	public void create(Book newBook) {
		em.persist(newBook);
	}

	@Override
	public void delete(Book redundantBook) {
		Book bookFound = em.find(Book.class, redundantBook.getId());
		em.remove(bookFound);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBooksByAuthor(String author) {
		return em.createQuery("select book from Book as book where book.author=:author")
				.setParameter("author", author)
				.getResultList();
	}
}