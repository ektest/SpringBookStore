package com.emrekoca.test.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.emrekoca.avalon.domain.Book;

public class BookTest {
	@Test
	public void testIsSameProduct() {
		Book first = new Book("1234", "title a", "EK", 23.0);
		Book second = new Book("1234", "title a", "EK", 23.0);
		assertTrue(first.equals(second));
	}

	@Test
	public void testFormatOfTheToStringIsCorrect() {
		Book book = new Book("1234", "title", "EK", 23.0);
		assertEquals("title by EK", book.toString());
	}
}
