package com.pasindu15.demo.library.domain.usecase;

import com.pasindu15.demo.library.domain.boundary.repositories.BookRepositoryInterface;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;
import com.pasindu15.demo.library.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookManagerTest {

    @MockBean
    BookRepositoryInterface bookRepository;

    @Autowired
    BookManager bookManager;

    @Test
    void testAdd() throws DomainException {
        Book book = new Book("Test Name","Test Type","Test Author");
        when(bookRepository.save(book)).thenReturn(book);
        BookResponseCoreEntity bookResponseCoreEntity = bookManager.add(book);
        assertEquals(bookResponseCoreEntity.getCode(),"0000");
        DomainException e = bookManager.createDomainError("0001","Add Book failure");
        try {
            when(bookRepository.save(book)).thenReturn(null);
            bookManager.add(book);
        }catch (DomainException ex){
            assertEquals(ex,e);
        }
    }

    @Test
    void testFindBookById() throws DomainException {
        Book book1  = new Book();
        book1.setId(1);
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book1));
        Book book2 = bookManager.findBookById(1);
        assertEquals(book2.getId(),1);
        DomainException e = bookManager.createDomainError("0002","Book not found");
        try {
            when(bookRepository.findById(2)).thenReturn(Optional.empty());
            bookManager.findBookById(2);
        }catch (DomainException ex){
            assertEquals(ex,e);
        }
    }

    @Test
    void testGetAllBooks() throws DomainException {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Test Name1","Test Type1","Test Author1"));
        books.add(new Book("Test Name2","Test Type2","Test Author2"));
        books.add(new Book("Test Name3","Test Type3","Test Author3"));

        when(bookRepository.findAll()).thenReturn(books);
        List<Book> booksRes = bookManager.getAllBooks();

        assertEquals(booksRes,books);
    }

    @Test
    void testDeleteBook() throws Exception {

        doNothing().when(bookRepository).deleteById(any(Integer.class));
        doAnswer((i)->{
            return null;
        }).when(bookRepository).deleteById(any(Integer.class));
        BookResponseCoreEntity bookResponseCoreEntity = bookManager.deleteBook(any(Integer.class));
        assertEquals(bookResponseCoreEntity.getCode(),"0000");

    }

}

