package com.pasindu15.demo.library.app.controller;


import com.pasindu15.demo.library.app.transformer.ResponseEntityTransformer;
import com.pasindu15.demo.library.app.transport.response.transformers.AddBookResponseTransformer;
import com.pasindu15.demo.library.app.transport.response.transformers.BookResponseTransformer;
import com.pasindu15.demo.library.app.validator.RequestEntityValidator;
import com.pasindu15.demo.library.app.transport.request.entities.BookRequestEntity;
import com.pasindu15.demo.library.domain.entities.Book;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;
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
@RequestMapping(value = "${base-url.context}")
public class BookController extends BaseController {
    private static final Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    private RequestEntityValidator validator;

    @Autowired
    BookManager bookManager;

    @Autowired
    ResponseEntityTransformer transformer;

    @GetMapping(value = "/findBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> findBook(@Validated @PathVariable Integer id,HttpServletRequest request)throws Exception{
        setLogIdentifier(request);
        Book book = bookManager.findBookById(id);
        Map trBook = transformer.transform(book,new BookResponseTransformer());
        return ResponseEntity.status(HttpStatus.OK).body(trBook);
    }

    @PostMapping(value="/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> integration(@RequestBody(required = true) BookRequestEntity bookRequestEntity, HttpServletRequest request)throws Exception{

        setLogIdentifier(request);
        validator.validate(bookRequestEntity);
        logger.info("Request validation success");

        Book book = new ModelMapper().map(bookRequestEntity, Book.class);
        BookResponseCoreEntity bookResponseCoreEntity = bookManager.add(book);

        Map trResponse = transformer.transform(bookResponseCoreEntity,new AddBookResponseTransformer());
        logger.info("Transformed response : "+trResponse.toString());

        return ResponseEntity.status(HttpStatus.OK).body(trResponse);

    }
    @GetMapping(value = "/findAllBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map>> findAllBook(HttpServletRequest request)throws Exception{
        setLogIdentifier(request);
        List<Book> books = bookManager.getAllBooks();
        List<Map> trBooks = transformer.transform(books,new BookResponseTransformer());
        return ResponseEntity.status(HttpStatus.OK).body(trBooks);
    }
}
