package com.pasindu15.demo.library.external.repositories;

import com.pasindu15.demo.library.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
