import org.example.Book;
import org.example.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestBook {


    @Test
    @DisplayName("Testing book constructor")
    public void testBook() {
        Book book = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        assertEquals("The Hobbit", book.getName());
        assertEquals(new BigDecimal("10.00"), book.getPrice());
        assertEquals(Genre.Fantasy, book.getGenre());
        assertEquals("978-3-16-148410-0", book.getISBN());
    }

    @Test
    @DisplayName("Testing Book Setters")
    public void testBookSetters() {
        Book book = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        book.setName("The Lord of the Rings");
        book.setPrice(new BigDecimal("20.00"));
        book.setGenre(Genre.Adventure);
        book.setISBN("978-3-16-148410-1");
        assertEquals("The Lord of the Rings", book.getName());
        assertEquals(new BigDecimal("20.00"), book.getPrice());
        assertEquals(Genre.Adventure, book.getGenre());
        assertEquals("978-3-16-148410-1", book.getISBN());
    }

    @Test
    @DisplayName("Testing books are equal")
    public void testBookEquals() {
        Book book1 = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        Book book2 = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        assertEquals(book1, book2);
    }

    //IRL there are no two books sharing the same ISBN, thus ISBN equality is sufficient
    @Test
    @DisplayName("Testing books are equal ISBN only")
    public void testBookEqualsISBNOnly(){
        Book book1 = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        Book book2 = new Book("The Hobbit 2", new BigDecimal("130.00"), Genre.Comic, "978-3-16-148410-0");
    }



    @Test
    @DisplayName("Testing books are not equal ISBN only")
    public void testBookNotEqualsDifferentISBN() {
        Book book1 = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0");
        Book book2 = new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "9781861972712");
        assertNotEquals(book1, book2);
    }


    //Further tests for data types and ISBN validation

    @Test
    @DisplayName("Testing book constructor with invalid ISBN")
    public void constructorThrowsExceptionWhenInvalidISBN() {
        assertThrows(IllegalArgumentException.class, () -> new Book("The Hobbit", new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-1"));
    }

    @Test
    @DisplayName("Testing book constructor with invalid Genre")
    public void constructorThrowsExceptionWhenInvalidGenre() {
        assertThrows(IllegalArgumentException.class, () -> new Book("The Hobbit", new BigDecimal("10.00"), null, "978-3-16-148410-0"));
    }

    @Test
    @DisplayName("Testing book constructor with invalid price")
    public void constructorThrowsExceptionWhenInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Book("The Hobbit", null, Genre.Fantasy, "978-3-16-148410-0"));
    }

    @Test
    @DisplayName("Testing book constructor with invalid name")
    public void constructorThrowsExceptionWhenInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, new BigDecimal("10.00"), Genre.Fantasy, "978-3-16-148410-0"));
    }

    
}
