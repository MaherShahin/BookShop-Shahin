import org.example.Book;
import org.example.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBookISBN13Validator {

    Book book;

    @BeforeEach
    void setUp(){
        book = new Book("TestBook",BigDecimal.valueOf(41),Genre.Comic, "9781861972712");
    }

    @Test
    @DisplayName("Valid ISBN-13 - no special characters")
    void testISBN13Validator(){
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Valid ISBN-13 with extra hyphens")
    void testISBN13ValidatorHyphenated(){
        book.setISBN("978-1861972712");
        assertTrue(book.isISBN13Valid(book.getISBN()));
        book.setISBN("978-186-1972712-");
        assertTrue(book.isISBN13Valid(book.getISBN()));
        book.setISBN("-978-186-1972712");
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Valid ISBN-13 with extra whitespace")
    void testISBN13ValidatorWhitespace(){
        book.setISBN("  9781861972712  ");
        assertTrue(book.isISBN13Valid(book.getISBN()));
        book.setISBN("97818   61972712  ");
        assertTrue(book.isISBN13Valid(book.getISBN()));
        book.setISBN("  97  81861 972712  ");
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Invalid ISBN-13 - length = 13 - numbers only")
    void testAssertInvalidISBN13(){
        book.setISBN("9781861972713");
        assertTrue(!book.isISBN13Valid(book.getISBN()));

    }

    @Test
    @DisplayName("Invalid ISBN-13 - length != 13 - numbers only")
    void testAssertInvalidISBN13Unequal(){
        book.setISBN("9781861972713413");
        assertTrue(!book.isISBN13Valid(book.getISBN()));
        book.setISBN("978186197");
        assertTrue(!book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Invalid ISBN-13 - length = 13 - numbers and letters")
    void testAssertInvalidISBN13Letters(){
        book.setISBN("97818619727d12");
        assertTrue(!book.isISBN13Valid(book.getISBN()));
        book.setISBN("97818-y-61972712");
        assertTrue(!book.isISBN13Valid(book.getISBN()));
    }
    //Further tests for ISBN-13
    ////    Test the following ISBNS individually
    //978-3841335180 → correct
    //
    //978-3442267819 → Incorrect
    //
    //978-758245159 → Inc
    //
    //978-3442267747 → Incorrect
    //
    //978-3608963762 → Correct
    //
    //978-1861972712 → Correct
    //

    @Test
    @DisplayName("Test for ISBN 978-3841335180")
    void testISBN3841335180(){
        book.setISBN("978-3841335180");
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Test for ISBN 978-3442267819")
    void testISBN3442267819(){
        book.setISBN("978-3442267819");
        assertFalse(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Test for ISBN 978-758245159")
    void testISBN758245159(){
        book.setISBN("978-758245159");
        assertFalse(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Test for ISBN 978-3442267747")
    void testISBN3442267747(){
        book.setISBN("978-3442267747");
        assertFalse(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Test for ISBN 978-3608963762")
    void testISBN3608963762(){
        book.setISBN("978-3608963762");
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    @Test
    @DisplayName("Test for ISBN 978-1861972712")
    void testISBN1861972712(){
        book.setISBN("978-1861972712");
        assertTrue(book.isISBN13Valid(book.getISBN()));
    }

    

}
