package com.memorize.everything.book

import com.memorize.everything.common.BOOK_ALREADY_REGISTERED
import com.memorize.everything.common.model.MemorizeException
import com.memorize.everything.model.book.Book
import com.memorize.everything.model.book.RequestBook
import com.memorize.everything.repository.BookRepository
import com.memorize.everything.service.BookService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookTest {

    @Mock
    private lateinit var bookRepository: BookRepository

    @InjectMocks
    private lateinit var bookService: BookService

    private val book1 = Book(1, "책 1", "책 1 내용", "경제")
    private val book2 = Book(2, "책 2", "책 2 내용", "개발")
    private val book3 = Book(3, "책 2", "책 3 내용", "소설")

    private val books = listOf(book1, book2, book3)

    @BeforeEach
    fun setup() {
        // TODO: 필요시 추가
    }

    @Test
    @DisplayName("전체 책 조회")
    fun fetch_all_book() {
        //stubs
        Mockito.`when`(bookRepository.findAll()).thenReturn(books)
        Mockito.`when`(bookService.findAll())
            .thenReturn(books)

        //execute
        val result = bookService.findAll()

        //then
        assertEquals(3, result?.size)
    }

    @Test
    @DisplayName("아이디로 책 조회")
    fun find_book_by_id() {
        //given
        val id = 2

        //stubs
        Mockito.`when`(bookRepository.findBookById(id)).thenReturn(book2)
        Mockito.`when`(bookService.findBookById(id))
            .thenReturn(book2)

        //execute
        val result = bookService.findBookById(2)

        //then
        assertEquals("책 2", result.title)
    }

    @Test
    @DisplayName("책 입력 - 성공")
    fun insert_book_success() {
        //given
        val requestBook = RequestBook("책 4", "책 4 내용", "경제")
        val book = Book.from(requestBook)

        //stubs
        Mockito.`when`(bookRepository.findBookByTitle(requestBook.title)).thenReturn(null)
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)
        Mockito.`when`(bookService.insertBook(requestBook))
            .thenReturn(book)

        //execute
        val result = bookService.insertBook(requestBook)

        //then
        assertEquals("책 4", result.title)
    }

    @Test
    @DisplayName("책 입력 - 실패")
    fun insert_book_fail() {
        //given
        val requestBook = RequestBook("책 4", "책 4 내용", "경제")
        val book = Book.from(requestBook)

        //stubs
        Mockito.`when`(bookRepository.findBookByTitle(requestBook.title)).thenReturn(book)

        //execute
        val exception = assertThrows(MemorizeException::class.java) {
            bookService.insertBook(requestBook)
        }

        //then
        assertEquals(BOOK_ALREADY_REGISTERED, exception.message)
    }

    @Test
    @DisplayName("책 삭제")
    fun delete_book_by_title() {
        //given
        val title = "책 3"

        //stubs
        Mockito.`when`(bookRepository.deleteBookByTitle(title)).thenReturn(1)

        //execute
        val result = bookService.deleteBookByTitle(title)

        //then
        assertEquals(true, result)
    }

    @Test
    @DisplayName("책 업데이트")
    fun upate_book() {
        //given
        val requestBook = RequestBook("책 2", "책 2 내용 업데이트", "경제")
        val book = Book.putAllById(2, requestBook)

        //stubs
        Mockito.`when`(bookRepository.findBookByTitle(requestBook.title)).thenReturn(book)
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)
        //execute
        val result = bookService.updateBook(requestBook)

        //then
        assertEquals("책 2 내용 업데이트", result.summary)
    }
}