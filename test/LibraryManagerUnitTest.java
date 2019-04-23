import Util.DateTime;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.Book;
import models.Dvd;
import models.LibraryItem;
import org.junit.Test;
import services.LibraryManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryManagerUnitTest extends TestBase {

    @Inject
    LibraryManager libraryManager;

    @Test
    public void testAddBook() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "book"));

        assertThat(libraryManager.getLibraryItem("123"));
    }

    @Test
    public void testAddDvd() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("987", "dvd"));

        assertThat(libraryManager.getLibraryItem("987")).isNotNull();
    }

    @Test
    public void testDeleteBook() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "book"));

        String isbn = libraryManager.deleteLibraryItem("123");

        assertThat(isbn).isEqualTo("123");
        assertThat(libraryManager.getLibraryItems()).isEmpty();
    }

    @Test
    public void testDeleteDvd() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("987", "dvd"));

        String isbn = libraryManager.deleteLibraryItem("987");

        assertThat(isbn).isEqualTo("987");
        assertThat(libraryManager.getLibraryItems()).isEmpty();
    }

    @Test
    public void testGetAllItems() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "book"));
        libraryManager.addLibraryItem(getDummyLibraryItem("234", "dvd"));
        libraryManager.addLibraryItem(getDummyLibraryItem("345", "book"));

        List<LibraryItem> libraryItems = libraryManager.getLibraryItems();

        assertThat(libraryItems.size()).isEqualTo(3);
        assertThat(libraryItems.get(0).getIsbn()).isEqualTo("123");
        assertThat(libraryItems.get(1).getIsbn()).isEqualTo("234");
        assertThat(libraryItems.get(2).getIsbn()).isEqualTo("345");
    }

    @Test
    public void testGetItem() throws Exception {
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "book"));

        assertThat(libraryManager.getLibraryItem("123")).isNotNull();
    }

    @Test
    public void testGetAllItemsFromEmptyList() throws Exception {
        List<LibraryItem> libraryItems = libraryManager.getLibraryItems();

        assertThat(libraryItems).isEmpty();
    }

    @Test(expected = IdNotFoundException.class)
    public void testGetItemFromEmptyList() throws IdNotFoundException {
        libraryManager.getLibraryItem("123");
    }

    @Test(expected = IdNotFoundException.class)
    public void testDeleteInvalidItem() throws Exception {
        libraryManager.deleteLibraryItem("111");
    }

    @Test(expected = IsbnAlreadyInUseException.class)
    public void testAddItemWithExistingIsbn() throws IsbnAlreadyInUseException {
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "book"));
        libraryManager.addLibraryItem(getDummyLibraryItem("123", "dvd"));
    }

    private LibraryItem getDummyLibraryItem(String isbn, String type) {
        List<String> dummyList = new ArrayList<>();
        dummyList.add("dummy1");
        dummyList.add("dummy2");

        LibraryItem libraryItem = null;

        switch (type.toLowerCase()) {
            case "book":
                libraryItem = new Book(isbn, "dummy", "dummy",
                        new DateTime("20180912", "1654"), new DateTime("20181212", "1145"),
                        1, dummyList,
                        "dummy", 500);
                break;

            case "dvd":
                libraryItem = new Dvd(isbn, "dummy", "dummy",
                        new DateTime("20180912", "1654"), new DateTime("20181212", "1145"),
                        1, dummyList,
                        dummyList, dummyList);
                break;
        }
        return libraryItem;
    }
}
