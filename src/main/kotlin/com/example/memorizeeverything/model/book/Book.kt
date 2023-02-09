package com.example.memorizeeverything.model.book

import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @Column
    val title: String,

    @Column
    val summary: String,

    @Column
    val category: String
)