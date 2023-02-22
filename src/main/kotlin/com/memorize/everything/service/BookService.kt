package com.memorize.everything.service

import com.memorize.everything.common.BOOK_ALREADY_REGISTERED
import com.memorize.everything.common.BOOK_NOT_EXISTED
import com.memorize.everything.common.BOOK_SEARCH_ERROR
import com.memorize.everything.common.model.MemorizeException
import com.memorize.everything.model.book.Book
import com.memorize.everything.model.book.RequestBook
import com.memorize.everything.repository.BookRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class BookService(private val bookRepository: BookRepository) {

    fun findAll(): List<Book>? {
        return bookRepository.findAll()
    }

    fun findBookById(id: Int): Book {
        return bookRepository.findBookById(id)
            ?: throw MemorizeException(BOOK_SEARCH_ERROR)
    }

    fun insertBook(requestBook: RequestBook): Book {
        return bookRepository.findBookByTitle(requestBook.title)
            ?.let { throw MemorizeException(BOOK_ALREADY_REGISTERED) }
            ?: bookRepository.save(Book.from(requestBook))
    }

    fun deleteBookByTitle(title: String): Boolean {
        return bookRepository.deleteBookByTitle(title) == 1
    }

    fun updateBook(requestBook: RequestBook): Book {
        return bookRepository.findBookByTitle(requestBook.title)
            ?.let { book -> bookRepository.save(Book.putAllById(book.id, requestBook)) }
            ?: throw MemorizeException(BOOK_NOT_EXISTED)

    }
}