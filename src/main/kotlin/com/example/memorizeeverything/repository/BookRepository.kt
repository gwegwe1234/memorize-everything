package com.example.memorizeeverything.repository

import com.example.memorizeeverything.model.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int> {
    fun findAllByTitle(title: String) : Book
}