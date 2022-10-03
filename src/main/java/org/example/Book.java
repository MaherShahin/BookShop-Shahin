package org.example;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    public Book(String name, BigDecimal price, Genre genre, String ISBN) {
        isValidBook(name,price,genre,ISBN);
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.ISBN = ISBN;
    }
    private String name;
    private BigDecimal price; // BigDecimal to avoid floating point problems
    private Genre genre;
    private String ISBN;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public boolean equals(Object o) { //If two books share the same ISBN then they are the same
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    // Checking if ISBN Valid
    public Boolean isISBN13Valid(String ISBN) {
        if (ISBN == null) {
            return false;
        } // remove any hyphens
        ISBN = ISBN.replaceAll("-", "");
        ISBN = ISBN.replaceAll(" ","");
        if (ISBN.length() != 13) { // must be a 13 digit ISBN
            return false;
        }
        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(ISBN.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            } // checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }
            return checksum == Integer.parseInt(ISBN.substring(12));
        } catch (NumberFormatException nfe) {
            // to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }

    public void printBook() {
        System.out.println("Book Name: " + this.name + " | Price: " + this.price + " | Genre: " + this.genre + " | ISBN: " + this.ISBN);
    }
    
    public void isValidBook(String name, BigDecimal price, Genre genre, String ISBN) {
        if (name == null || name.equals("") || name.replace(" ","").equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("Price cannot be null or less than or equal zero!");
        }
        if (genre == null) {
            throw new IllegalArgumentException("Invalid Genre Type, Genre cant be null");
        }
        if (!isISBN13Valid(ISBN)) {
            throw new IllegalArgumentException("Invalid ISBN");
        } 
    }
}
