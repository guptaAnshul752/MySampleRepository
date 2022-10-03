package com.uday;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.uday.SpringBootTestingMokitoApplication;
import com.uday.entity.Book;
import com.uday.repository.BookRepository;
import com.uday.service.BookService;

@SpringBootTest(classes=SpringBootTestingMokitoApplication.class)
public class TestClass {
	
	@Mock 
	BookRepository bookRepo;
	
	@InjectMocks
	BookService bookService = new BookService();
	
	@Test
	public void testGetAllBooks() {
		
		List<Book> books = new ArrayList<>();
		Book b = new Book(1, "Learn Java", "Abc", 100, "borrowed");
		Book b1 = new Book(2, "Advanced Java and Java 8","Vaishali", 10000 , "borrowed");
		books.add(b);
		books.add(b1);
		
		when(bookRepo.findAll()).thenReturn(books);
		List<Book> result = bookService.getAllBooks();
		
		int size = books.size();
		
		for (int i = 0; i< size ; i++)
		{
			Assertions.assertNotEquals(null, result);
			Assertions.assertTrue(result.get(0).getAuthor().endsWith("-b"));
			Assertions.assertEquals("Vaishali-b", result.get(i).getAuthor());
		}
		
	}
}