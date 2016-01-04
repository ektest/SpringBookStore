package com.emrekoca.avalon.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.avalon.domain.Book;
import com.emrekoca.avalon.services.BookService;

public class Client3 {
	private static ClassPathXmlApplicationContext appCon;

	public static void main(String[] args) {
		appCon = new ClassPathXmlApplicationContext("application.xml");
		try {
			//PurchasingService ps = appCon.getBean(PurchasingService.class);
			BookService bs = (BookService) appCon.getBean("bookService");
			bs.registerNewBook(new Book(String.valueOf(((int) (Math.random() * 100))), "Test", "Test", 10d));
			List<Book> allBooks = bs.getEntireCatalogue();
			for (Book book : allBooks) {
				System.out.println(book);
			}
		} finally {
			appCon.close();
		}
	}
}
