package com.emrekoca.avalon.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.emrekoca.avalon.data.BookNotFoundException;
import com.emrekoca.avalon.domain.Book;

@Transactional
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

	// @Transactional -> only this method is Transactional
	public void setBookService(BookService books) {
		this.books = books;
	}

	@Transactional(rollbackFor={CustomerCreditExceedException.class, BookNotFoundException.class})
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceedException {
		// find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);

		// delete book from stock
		books.deleteFromStock(requiredBook);

		// now raise the invoice
		// second way to rollback
		accounts.raiseInvoice(requiredBook);
		// First way to rollback
		/*
		try {
			accounts.raiseInvoice(requiredBook);
		} catch (CustomerCreditExceedException e) {
			// tell spring to rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// throw exception again
			throw e;
		}
		*/
	}
}
