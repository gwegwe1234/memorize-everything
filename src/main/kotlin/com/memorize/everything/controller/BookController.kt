package com.memorize.everything.controller

import com.memorize.everything.common.support.ApiResponse
import com.memorize.everything.model.book.Book
import com.memorize.everything.model.book.RequestBook
import com.memorize.everything.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(private val bookService: BookService) {

    @GetMapping("")
    fun getBookInfo(): ApiResponse<List<Book>?> {
        return ApiResponse.ok(bookService.findAll())
    }

    @GetMapping("/info")
    fun getBookInfoByTitle(@RequestParam("id") id: Int): ApiResponse<Book> {
        return ApiResponse.ok(bookService.findBookById(id))
    }

    @PostMapping("/info")
    fun insertBookInfo(@RequestBody requestBook: RequestBook): ApiResponse<Book> {
        return ApiResponse.ok(bookService.insertBook(requestBook))
    }

    @PutMapping("/info")
    fun updateBookInf(@RequestBody requestBook: RequestBook): ApiResponse<Book> {
        return ApiResponse.ok(bookService.updateBook(requestBook))
    }

    @DeleteMapping("/info")
    fun deleteBookInfo(@RequestParam("title") title: String) : ApiResponse<Boolean> {
        return ApiResponse.ok(bookService.deleteBookByTitle(title))
    }
}