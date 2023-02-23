package com.memorize.everything.model.book

import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column
    val title: String,

    @Column
    val summary: String,

    @Column
    val category: String
) {
    companion object {
        fun from(requestBook: RequestBook): Book {
            return Book(
                id = null,
                title = requestBook.title,
                summary = requestBook.summary,
                category = requestBook.category
            )
        }

        fun putAllById(id: Int?, requestBook: RequestBook): Book {
            return Book(
                id = id,
                title = requestBook.title,
                summary = requestBook.summary,
                category = requestBook.category
            )
        }
    }
}