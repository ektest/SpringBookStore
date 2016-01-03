package com.emrekoca.avalon.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.avalon.domain.Book;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class AccountsServiceMockImpl implements AccountsService {
	public void raiseInvoice(Book requiredBook) throws CustomerCreditExceedException {
		// a very basic implementation for testing
		System.out.println("Raised the invoice for " + requiredBook);
	}
}
