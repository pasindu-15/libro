package com.pasindu15.demo.library.domain.usecase;

import com.pasindu15.demo.library.domain.boundary.repositories.BookRepositoryInterface;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;

import com.pasindu15.demo.library.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        logger.info("START ADD BOOK..." );
        try {
            bookRepository.save(book);
        }catch (Exception ex){
            throw  domainError("Book Add operation Failure","0001");
        }

        bookResponse.setCode("0000");
        bookResponse.setMessage("Success");
        bookResponse.setData("BookId :"+book.getId());

        logger.info("END ADD BOOK..." );
        return bookResponse;
    }

    public Book findBookById(Integer id)throws DomainException{
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
            throw  domainError("Book not found","0002");
        }
        return book.get();
    }
    public List<Book> getAllBooks()throws DomainException{
        return bookRepository.findAll();
    }

    private DomainException domainError(String code, String msg){
        return new DomainException(msg,code);
    }

}
