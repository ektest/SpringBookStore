package com.emrekoca.avalon.services;

import java.util.List;

import com.emrekoca.avalon.data.BookDao;
import com.emrekoca.avalon.data.BookNotFoundException;
import com.emrekoca.avalon.domain.Book;

/**
 * This is the production implementation - it will be calling a "real" database.
 */
public class BookServiceProductionImpl implements BookService {
	
	private BookDao dao;
	
	public BookServiceProductionImpl(BookDao dao)
	{
		this.dao = dao;
	}
	
	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		return dao.findBooksByAuthor(author);
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {
		return dao.findByIsbn(isbn);
	}

	@Override
	public List<Book> getEntireCatalogue() {
		return dao.allBooks();
	}

	@Override
	public void registerNewBook(Book newBook) 
	{
		// we want this book to be put into the database.
		dao.create(newBook);
	}

}
