package com.example.memorizeeverything.service

import com.example.memorizeeverything.model.book.Book
import com.example.memorizeeverything.repository.BookRepository
import org.springframework.stereotype.Component

@Component
class BookService(private val bookRepository: BookRepository) {

    fun findAll(): List<Book>? {
        return bookRepository.findAll();
    }

    fun findAllByTitle(title: String) : Book {
        return bookRepository.findAllByTitle(title)
    }


}