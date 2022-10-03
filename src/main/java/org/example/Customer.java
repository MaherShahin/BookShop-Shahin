package org.example;

import java.math.BigDecimal;
import java.util.HashMap;

public class Customer {

    public Customer(String name, HashMap<Book, Integer> booksOwned, BigDecimal totalMoney) {
        this.name = name;
        this.booksOwned = booksOwned;
        this.totalMoney = totalMoney;
    }

    public Customer(String name, BigDecimal totalMoney) {
        this.name = name;
        this.booksOwned = new HashMap<Book, Integer>();
        this.totalMoney = totalMoney;
    }

    private String name;

    private HashMap<Book, Integer> booksOwned;

    private BigDecimal totalMoney;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Book, Integer> getBooksOwned() {
        return booksOwned;
    }

    public void setBooksOwned(HashMap<Book, Integer> booksOwned) {
        this.booksOwned = booksOwned;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    // Adding a new book to a customer!! (Handles Shop side - i.e. shop.sell invoked as well)
    public void buyBook(Shop shop, Book book, Integer quantity) {

        if (!shop.getBookStock().containsKey(book)){
            throw new IllegalArgumentException("Book not in stock");
        }
        if (shop.getBookStock().get(book) < quantity){
            throw new IllegalArgumentException("Not enough books in stock");
        }
        shop.sellBook(book, quantity);
        addBookToCustomer(book, quantity);
    }

    public void addBookToCustomer(Book book, Integer quantity) {
        if (quantity<=0){
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
        if (this.totalMoney.compareTo(book.getPrice().multiply(BigDecimal.valueOf(quantity))) >= 0) {
            if (this.booksOwned.containsKey(book)) {
                this.booksOwned.put(book, this.booksOwned.get(book) + quantity);
            } else {
                this.booksOwned.put(book, quantity);
            }
            this.totalMoney = this.totalMoney.subtract(book.getPrice().multiply(BigDecimal.valueOf(quantity)));
        } else {
            throw new IllegalArgumentException("Not enough money");
        }

    }

    public Boolean hasSufficientFunds(Book book, Integer quantity) {
        if ((book.getPrice().multiply(BigDecimal.valueOf(quantity))).compareTo(this.totalMoney) > 0) {
            return false;
        }
        return true;
    }

    public void compareShops( Shop shop1, Shop shop2, Book book) {
        System.out.println("Comparing " + shop1.getName() + " and " + shop2.getName() + " for " + book.getName());
        System.out.println("--------------------------------------------------");
        System.out.println(shop2.getName() + " has " + shop2.getBookStock().get(book) + " copies of " + book.getName() + " at a price of " + shop2.findBookByISBN(book.getISBN()).getPrice());
        System.out.println(shop1.getName() + " has " + shop1.getBookStock().get(book) + " copies of " + book.getName() + " at a price of " + shop1.findBookByISBN(book.getISBN()).getPrice());
    }

    public boolean bookAvailableInShops(Book book, Shop shop1, Shop shop2){
        return shop1.getBookStock().containsKey(book) && shop2.getBookStock().containsKey(book);
    }
    // Console Commands

}
