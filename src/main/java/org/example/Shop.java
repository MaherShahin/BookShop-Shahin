package org.example;

import java.math.BigDecimal;
import java.util.HashMap;

public class Shop {

    public Shop(String name) {
        this.name = name;
        this.bookStock = new HashMap<>();
        this.revenue = BigDecimal.ZERO;
    }

    private String name;

    private HashMap<Book, Integer> bookStock;

    private BigDecimal revenue;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Book, Integer> getBookStock() {
        return bookStock;
    }

    public void setBookStock(HashMap<Book, Integer> bookStock) {
        this.bookStock = bookStock;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    // No need to worry about duplicates when dealing with Hashmap

    // Class Methods
    public void addBook(Book book, Integer quantity) {

        if (bookStock.containsKey(book)) {
            bookStock.put(book, bookStock.get(book) + quantity);
        } else {
            bookStock.put(book, quantity);
        }

    }

    public void removeBook(Book book, Integer quantity) {
        this.getBookStock().put(book, (this.getBookStock().get(book) - quantity));
    }

    //Must be called after buyBook method from Customer class
    public void sellBook(Book book, Integer quantity) {
        if (!this.getBookStock().containsKey(book)) {
            throw new RuntimeException("No such book is available in Stock!");
        }
        if (this.getBookStock().get(book) < quantity) {
            throw new RuntimeException("Insufficient amount of books in Stock!");
        }
        removeBook(book, quantity);
        this.setRevenue(this.getRevenue().add(book.getPrice().multiply(BigDecimal.valueOf(quantity))));
    }

    public HashMap<Book, Integer> filterByGenre(Genre genre) {
        HashMap<Book, Integer> filteredBooks = new HashMap<>();
        for (Book book : this.getBookStock().keySet()) {
            if (book.getGenre() == genre) {
                filteredBooks.put(book, this.getBookStock().get(book));
            }
        }
        return filteredBooks;
    }

    public Book findBookByISBN(String ISBN){
        for (Book book : this.getBookStock().keySet()) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        throw new RuntimeException("No such book is available in Stock!"); 
    }

}
