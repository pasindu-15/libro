package com.pasindu15.demo.library.app.transport.response.transformers;

import com.pasindu15.demo.library.app.transformer.ResponseEntityInterface;
import com.pasindu15.demo.library.domain.entities.Book;

import java.util.HashMap;
import java.util.Map;

public class BookResponseTransformer implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) {
        Book book = (Book)entity;
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("id",book.getId());
        mapping.put("name",book.getName());
        mapping.put("dateTime",book.getDateTime());
        mapping.put("type",book.getType());
        mapping.put("author",book.getAuthor());

        return mapping;
    }
}
