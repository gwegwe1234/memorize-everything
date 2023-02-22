package com.memorize.everything.repository

import com.memorize.everything.model.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface BookRepository : JpaRepository<Book, Int> {
    fun findBookById(id: Int): Book?

    fun findBookByTitle(title: String): Book?

    fun save(book: Book): Book

    @Transactional
    fun deleteBookByTitle(title: String): Int
}
