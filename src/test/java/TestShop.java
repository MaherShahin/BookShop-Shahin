import org.example.Book;
import org.example.Genre;
import org.example.Shop;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestShop {

    Shop shop1;
    Shop shop2;

    @BeforeEach
    void setUp() {
        // Set up 2 shops and populate them with books and quantities
        shop1 = new Shop("shop1");
        shop2 = new Shop("shop2");

        Book book1 = new Book("Book1", BigDecimal.valueOf(10), Genre.Comic, "978-3-16-148410-0");
        Book book2 = new Book("Book2", BigDecimal.valueOf(10), Genre.Comic, "978-3608963762");
        Book book3 = new Book("Book3", BigDecimal.valueOf(10), Genre.Comic, "978-3841335180");
        //New Book w/ new ISBN


        shop1.addBook(book1, 4);
        shop1.addBook(book2, 3);
        shop1.addBook(book3, 5);

        shop2.addBook(book1, 16);
        shop2.addBook(book2, 1);
        shop2.addBook(book3, 3);

    }

    //Test getters and setters for Shop
    @Test
    @DisplayName("Test setter and getters")
    void testShopGettersSetters() {
        shop1.setName("shop-test");
        assertEquals("shop-test", shop1.getName());

        shop1.setBookStock(shop1.getBookStock());
        assertEquals(shop1.getBookStock(), shop1.getBookStock());

        shop1.setRevenue(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), shop1.getRevenue());

    }



    //Test addBook
    @Test
    @DisplayName("Test addBook")
    void testAddBook() {
        Book book4 = new Book("Book4", BigDecimal.valueOf(10), Genre.Comic, "978-1861972712");
        shop1.addBook(book4, 13);
        assertEquals(13, shop1.getBookStock().get(book4));
        assertEquals(4, shop1.getBookStock().keySet().size());
    }


    //Test removeBook
    @Test
    @DisplayName("Test removeBook")
    void testRemoveBook() {
        Book book4 = new Book("Book4", BigDecimal.valueOf(10), Genre.Comic, "978-1861972712");
        shop1.addBook(book4, 13);
        shop1.removeBook(book4, 10);
        assertEquals(3, shop1.getBookStock().get(book4));
        assertEquals(4, shop1.getBookStock().keySet().size());
    }

    //Test sellBook
    @Test
    @DisplayName("Test sellBook")
    void testSellBook() {
        Book book4 = new Book("Book4", BigDecimal.valueOf(10), Genre.Comic, "978-1861972712");
        shop1.addBook(book4, 13);
        shop1.sellBook(book4, 10);
        assertEquals(3, shop1.getBookStock().get(book4));
        assertEquals(4, shop1.getBookStock().keySet().size());
        assertEquals(BigDecimal.valueOf(100), shop1.getRevenue());
    }

    //Test filterByGenre
    @Test
    @DisplayName("Test filterByGenre")
    void testFilterByGenre() {
        assertEquals(3, shop1.filterByGenre(Genre.Comic).size());
        assertEquals(0, shop1.filterByGenre(Genre.Fantasy).size());
    }


    //Test findBookByISBN
    @Test
    @DisplayName("Test findBookByISBN")
    void testFindBookByISBN() {
        Book book4 = new Book("Book4", BigDecimal.valueOf(10), Genre.Comic, "978-1861972712");
        shop1.addBook(book4, 13);
        assertEquals(book4, shop1.findBookByISBN("978-1861972712"));
    }




}
