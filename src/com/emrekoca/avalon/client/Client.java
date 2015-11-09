package com.emrekoca.avalon.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.avalon.data.BookNotFoundException;
import com.emrekoca.avalon.domain.Book;
import com.emrekoca.avalon.services.BookService;
import com.emrekoca.avalon.services.CustomerCreditExceedException;
import com.emrekoca.avalon.services.PurchasingService;

public class Client {
	private static ClassPathXmlApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("application.xml");
		try {
			PurchasingService ps = appCon.getBean(PurchasingService.class);
			BookService bs = appCon.getBean("bookService", BookService.class);
			// begin
			bs.registerNewBook(new Book("12345", "Test", "Test", 10));
			// commit
			// begin
			ps.buyBook("12345");
			// commit
		} catch (BookNotFoundException e) {
			System.out.println("Book not found!");
		} catch (CustomerCreditExceedException e) {
			System.out.println("You need more credits!");
		} finally {
			appCon.close();
		}
	}
}
