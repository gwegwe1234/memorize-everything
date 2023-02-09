package com.example.memorizeeverything.controller

import com.example.memorizeeverything.model.book.Book
import com.example.memorizeeverything.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookService: BookService) {

    @GetMapping("/book-info")
    fun getBookInfo(
        @RequestParam("title") title: String
    ): Book {
        return bookService.findAllByTitle(title)
    }
}