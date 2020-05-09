package com.pasindu15.demo.library.app.controller;


import com.pasindu15.demo.library.app.transformer.ResponseEntityTransformer;
import com.pasindu15.demo.library.app.transport.response.transformers.BookOperationResponseTransformer;
import com.pasindu15.demo.library.app.transport.response.transformers.BookResponseTransformer;
import com.pasindu15.demo.library.app.validator.RequestEntityValidator;
import com.pasindu15.demo.library.app.transport.request.entities.BookRequestEntity;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;
import com.pasindu15.demo.library.domain.exception.DomainException;
import com.pasindu15.demo.library.domain.usecase.BookManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "${base-url.context}"+"/book")
public class BookController extends BaseController {
    private static final Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    private RequestEntityValidator validator;

    @Autowired
    BookManager bookManager;

    @Autowired
    ResponseEntityTransformer transformer;

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> findBook(@Validated @PathVariable Integer id,HttpServletRequest request)throws DomainException {
        setLogIdentifier(request);
        Book book = bookManager.findBookById(id);
        Map trBook = transformer.transform(book,new BookResponseTransformer());
        return ResponseEntity.status(HttpStatus.OK).body(trBook);
    }

    @PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> integration(@RequestBody(required = true) BookRequestEntity bookRequestEntity, HttpServletRequest request)throws DomainException{

        setLogIdentifier(request);
        validator.validate(bookRequestEntity);
        logger.info("Request validation success");

        Book book = new ModelMapper().map(bookRequestEntity, Book.class);
        BookResponseCoreEntity bookResponseCoreEntity = bookManager.add(book);

        Map trResponse = transformer.transform(bookResponseCoreEntity,new BookOperationResponseTransformer());
        logger.info("Transformed response : "+trResponse.toString());

        return ResponseEntity.status(HttpStatus.OK).body(trResponse);

    }
    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map>> findAllBook(HttpServletRequest request){
        setLogIdentifier(request);
        List<Book> books = bookManager.getAllBooks();
        List<Map> trBooks = transformer.transform(books,new BookResponseTransformer());
        return ResponseEntity.status(HttpStatus.OK).body(trBooks);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> deleteBook(@Validated @PathVariable Integer id,HttpServletRequest request)throws DomainException{
        setLogIdentifier(request);

        BookResponseCoreEntity deleteBookResponse = bookManager.deleteBook(id);
        Map trDeleteBookResponse = transformer.transform(deleteBookResponse,new BookOperationResponseTransformer());
        logger.info("Transformed response : "+trDeleteBookResponse.toString());

        return ResponseEntity.status(HttpStatus.OK).body(trDeleteBookResponse);
    }
}
