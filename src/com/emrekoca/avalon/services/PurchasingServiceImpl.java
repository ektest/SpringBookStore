package com.emrekoca.avalon.services;

import com.emrekoca.avalon.data.BookNotFoundException;
import com.emrekoca.avalon.domain.Book;

public class PurchasingServiceImpl implements PurchasingService {
	private AccountsService accounts;
	private BookService books;

	public PurchasingServiceImpl() {

	}

	// Wiring via contructor
	public PurchasingServiceImpl(AccountsService aService, BookService bService) {
		this.accounts = aService;
		this.books = bService;
	}

	// Wiring via property
	public void setAccountService(AccountsService accounts) {
		this.accounts = accounts;
	}

	public void setBookService(BookService books) {
		this.books = books;
	}

	public void buyBook(String isbn) throws BookNotFoundException {
		// find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);

		// now raise the invoice
		accounts.raiseInvoice(requiredBook);
	}
}
