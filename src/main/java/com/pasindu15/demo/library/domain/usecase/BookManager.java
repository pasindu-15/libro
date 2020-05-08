package com.pasindu15.demo.library.domain.usecase;

import com.pasindu15.demo.library.domain.boundary.repositories.BookRepositoryInterface;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;

import com.pasindu15.demo.library.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookManager {
    private static final Logger logger = Logger.getLogger(BookManager.class.getName());

    @Autowired
    BookRepositoryInterface bookRepository;

    @Autowired
    BookResponseCoreEntity bookResponse;

    public BookResponseCoreEntity add(Book book) throws DomainException {
        bookRepository.save(book);

        bookResponse.setCode("0000");
        bookResponse.setMessage("Success");
        bookResponse.setData("BookId :"+book.getId());

        return bookResponse;
    }

    public Book findBookById(Integer id)throws DomainException{
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
            throw  domainError("Book not found","0001");
        }
        return book.get();
    }
    public List<Book> getAllBooks()throws DomainException{
        return bookRepository.findAll();
    }

    public BookResponseCoreEntity deleteBook(Integer id) throws Exception{
        try {
            bookRepository.deleteById(id);
        }catch (Exception ex){
            throw domainError("0002","Book not found for given id");
        }

        bookResponse.setCode("0000");
        bookResponse.setMessage("Success");
        bookResponse.setData("BookId :"+id);
        return bookResponse;
    }

    private DomainException domainError(String code, String msg){
        return new DomainException(msg,code);
    }

}
