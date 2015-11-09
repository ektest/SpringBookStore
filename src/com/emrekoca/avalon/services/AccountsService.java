package com.emrekoca.avalon.services;

import com.emrekoca.avalon.domain.Book;

public interface AccountsService 
{
	public void raiseInvoice(Book requiredBook) throws CustomerCreditExceedException;
}
