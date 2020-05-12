package com.pasindu15.demo.library.domain.usecase;

import com.pasindu15.demo.library.domain.boundary.repositories.BookRepositoryInterface;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;

import com.pasindu15.demo.library.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookManager {

    @Autowired
    BookRepositoryInterface bookRepository;

    public BookResponseCoreEntity add(Book book) throws DomainException {
        Book bookRes = bookRepository.save(book);
        if(bookRes == null)
            throw  createDomainError("0001","Add Book failure");

        BookResponseCoreEntity bookResponse = new BookResponseCoreEntity("0000","Success");
        bookResponse.setData("BookId :"+bookRes.getId());

        return bookResponse;
    }

    public Book findBookById(Integer id)throws DomainException{
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
            throw  createDomainError("0002","Book not found");
        }
        return book.get();
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookResponseCoreEntity deleteBook(Integer id){
        bookRepository.deleteById(id);

        BookResponseCoreEntity bookResponse = new BookResponseCoreEntity();
        bookResponse.setCode("0000");
        bookResponse.setMessage("Success");
        bookResponse.setData("BookId :"+id);
        return bookResponse;
    }

    public DomainException createDomainError(String code, String msg){
        return new DomainException(msg,code);
    }

}
