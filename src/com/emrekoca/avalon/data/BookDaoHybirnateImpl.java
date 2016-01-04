package com.emrekoca.avalon.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.emrekoca.avalon.domain.Book;

//@Repository
public class BookDaoHybirnateImpl implements BookDao {

	@Autowired
	private HibernateTemplate template;

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> allBooks() {
		return (List<Book>) template.find("from Book");
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		@SuppressWarnings("unchecked")
		List<Book> results = (List<Book>) template.find("from Book where isbn=?", isbn);
		if (results.isEmpty())
			throw new BookNotFoundException();
		return results.get(0);
	}

	@Override
	public void create(Book newBook) {
		template.save(newBook);
	}

	@Override
	public void delete(Book redundantBook) {
		Book foundBook = template.get(Book.class, redundantBook.getId());
		template.delete(foundBook);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBooksByAuthor(String author) {
		return (List<Book>) template.findByNamedParam("from Book where author=:author", "author", author);
	}
}