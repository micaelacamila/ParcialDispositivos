package com.example.parcial

class BookRepository (private val bookDao: IBookDao) {

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun getListBooks(): List<Book> {
        return bookDao.getList()
    }
}
