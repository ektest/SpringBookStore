package com.emrekoca.avalon.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.avalon.domain.Book;
import com.emrekoca.avalon.services.BookService;

public class Client {
	private static ClassPathXmlApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("application.xml");
		BookService bs = appCon.getBean("bookService", BookService.class);
		//System.out.println(bs.getBookByIsbn("ISBN1"));
		bs.registerNewBook(new Book("123", "Love and Sex", "Public", 0.00));
		for(Book book : bs.getEntireCatalogue()){
			System.out.println(book);
		}
		appCon.close();
	}
}
