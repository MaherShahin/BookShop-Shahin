import org.example.Book;
import org.example.Customer;
import org.example.Genre;
import org.example.Shop;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCustomer {

    Customer testCustomer;
    
    Book testBook;

    Shop testShop1;
    Shop testShop2;

    HashMap<Book, Integer> testOwnedBooks;

    @BeforeEach
    void setUp(){
        testOwnedBooks = new HashMap<>();
        testCustomer = new Customer("TestCustomer", testOwnedBooks, BigDecimal.valueOf(100));
        testBook = new Book("TestBook", BigDecimal.valueOf(10), Genre.Comic, "9781861972712");
        testShop1 = new Shop("TestShop1");
        testShop2 = new Shop("TestShop2");

        testShop1.addBook(testBook, 10);
        testShop2.addBook(testBook, 5);


    }

    //Test getters and setters for Customer
    @Test
    @DisplayName("Test setter and getters")
    void testCustomerGettersSetters(){
        testCustomer.setName("TestCustomer2");
        assertEquals("TestCustomer2", testCustomer.getName());

        testCustomer.setTotalMoney(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(200), testCustomer.getTotalMoney());

        testCustomer.setBooksOwned(testOwnedBooks);
        assertEquals(testOwnedBooks, testCustomer.getBooksOwned());

    }

    //Test addBookToOwnedBooks
    @Test
    @DisplayName("Test addBookToOwnedBooks")
    void testAddBookToOwnedBooks(){
        testCustomer.addBookToCustomer(testBook, 1);
        assertEquals(1, testCustomer.getBooksOwned().get(testBook));
    }

    //Test buyBook
    @Test
    @DisplayName("Test buyBook")
    void testBuyBook(){
        testCustomer.buyBook(testShop1,testBook,1);
        assertEquals(1, testCustomer.getBooksOwned().get(testBook));
        assertEquals(9, testShop1.getBookStock().get(testBook));
        assertEquals(BigDecimal.valueOf(90), testCustomer.getTotalMoney());
        assertEquals(BigDecimal.valueOf(10), testShop1.getRevenue());
    }

    //Test buyBook with insufficient funds
    @Test
    @DisplayName("Test buyBook with insufficient funds")
    void testBuyBookInsufficientFunds(){
        testCustomer.setTotalMoney(BigDecimal.valueOf(0));
        assertThrows(IllegalArgumentException.class, () -> testCustomer.buyBook(testShop1,testBook,1));
        assertEquals(null, testCustomer.getBooksOwned().get(testBook));
        assertEquals(5, testShop2.getBookStock().get(testBook));
        assertEquals(BigDecimal.valueOf(0), testCustomer.getTotalMoney());
        assertEquals(BigDecimal.valueOf(0), testShop2.getRevenue());
    }

    //Test buyBook with insufficient stock
    @Test
    @DisplayName("Test buyBook with insufficient stock")
    void testBuyBookInsufficientStock(){
        assertThrows(IllegalArgumentException.class, () -> testCustomer.buyBook(testShop2,testBook,6));
        assertNull(testCustomer.getBooksOwned().get(testBook));
        assertEquals(5, testShop2.getBookStock().get(testBook));
        assertEquals(BigDecimal.valueOf(100), testCustomer.getTotalMoney());
        assertEquals(BigDecimal.valueOf(0), testShop2.getRevenue());
    }

    //Test buyBook with invalid quantity
    @Test
    @DisplayName("Test buyBook with invalid quantity")
    void testBuyBookInvalidQuantity(){
        assertThrows(IllegalArgumentException.class, () -> testCustomer.buyBook(testShop1,testBook,43));
        assertNull(testCustomer.getBooksOwned().get(testBook));
        assertEquals(10, testShop1.getBookStock().get(testBook));
        assertEquals(BigDecimal.valueOf(100), testCustomer.getTotalMoney());
        assertEquals(BigDecimal.valueOf(0), testShop1.getRevenue());
    }

    //Test buyBook with null book
    @Test
    @DisplayName("Test buyBook with null book")
    void testBuyBookNullBook(){
        assertThrows(IllegalArgumentException.class, () -> testCustomer.buyBook(testShop1,null,1));
        assertNull(testCustomer.getBooksOwned().get(testBook));
        assertEquals(10, testShop1.getBookStock().get(testBook));
        assertEquals(BigDecimal.valueOf(100), testCustomer.getTotalMoney());
        assertEquals(BigDecimal.valueOf(0), testShop1.getRevenue());
    }


    //Test addBookToCustomer
    @Test
    @DisplayName("Test addBookToCustomer")
    void testAddBookToCustomer(){
        testCustomer.addBookToCustomer(testBook, 1);
        assertEquals(1, testCustomer.getBooksOwned().get(testBook));
    }

    //Test addBookToCustomer with invalid quantity
    @Test
    @DisplayName("Test addBookToCustomer with invalid quantity")
    void testAddBookToCustomerInvalidQuantity(){
        assertThrows(IllegalArgumentException.class, () -> testCustomer.addBookToCustomer(testBook, -1));
        assertNull(testCustomer.getBooksOwned().get(testBook));
    }

    //Test addBookToCustomer with null book
    @Test
    @DisplayName("Test addBookToCustomer with null book")
    void testAddBookToCustomerNullBook(){
        assertThrows(NullPointerException.class, () -> testCustomer.addBookToCustomer(null, 1));
        assertNull(testCustomer.getBooksOwned().get(testBook));
    }

    @Test
    @DisplayName("Test hasSufficientFunds")
    void testHasSufficientFunds(){
        assertTrue(testCustomer.hasSufficientFunds(testBook, 1));
        assertFalse(testCustomer.hasSufficientFunds(testBook, 11));
    }
    
    @Test
    @DisplayName("Test bookAvailableInShops")
    void testBookAvailableInShops(){
        assertTrue(testCustomer.bookAvailableInShops(testBook, testShop1, testShop2));
        Book testBook2 = new Book("TestBook2", BigDecimal.valueOf(10), Genre.Comic, "978-3841335180 ");
        testShop1.addBook(testBook2, 10);
        assertFalse(testCustomer.bookAvailableInShops(testBook2, testShop1, testShop2));

    }



}
