package com.emrekoca.avalon.services;

import com.emrekoca.avalon.data.BookNotFoundException;

public interface PurchasingService 
{
	public void buyBook(String isbn) throws BookNotFoundException;
}