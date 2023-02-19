package com.example.memorizeeverything.controller

import com.example.memorizeeverything.model.book.Book
import com.example.memorizeeverything.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(private val bookService: BookService) {

    @GetMapping("")
    fun getBookInfo() : ResponseEntity<List<Book>> {
        return ResponseEntity.ok(bookService.findAll())
    }

    @GetMapping("/info")
    fun getBookInfoByTitle(
        @RequestParam("title") title: String
    ): Book {
        return bookService.findAllByTitle(title)
    }
}