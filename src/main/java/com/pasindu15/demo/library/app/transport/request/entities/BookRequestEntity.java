package com.pasindu15.demo.library.app.transport.request.entities;

import com.pasindu15.demo.library.app.validator.RequestEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestEntity implements RequestEntityInterface {

    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String author;
    @NotNull
    @NotEmpty
    private String type;

}
