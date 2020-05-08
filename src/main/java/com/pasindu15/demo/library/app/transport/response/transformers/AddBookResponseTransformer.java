package com.pasindu15.demo.library.app.transport.response.transformers;

import com.pasindu15.demo.library.app.transformer.ResponseEntityInterface;
import com.pasindu15.demo.library.domain.entities.dto.BookResponseCoreEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class AddBookResponseTransformer implements ResponseEntityInterface {

    @Override
    public Map transform(Object entity) {
        BookResponseCoreEntity bookResponse = (BookResponseCoreEntity)entity;
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("code",bookResponse.getCode());
        mapping.put("message",bookResponse.getMessage());
        mapping.put("data",bookResponse.getData());

        return mapping;
    }
}
