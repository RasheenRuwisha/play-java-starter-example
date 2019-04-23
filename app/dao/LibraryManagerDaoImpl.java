package dao;

import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.LibraryItem;
import models.Reader;
import response.Response;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagerDaoImpl implements LibraryManagerDao {

    private static List<LibraryItem> libraryItemList;
    private static List<Reader> readerList;

    public LibraryManagerDaoImpl() {
        libraryItemList = new ArrayList<>();
        readerList = new ArrayList<>();
    }

    @Override
    public String addLibraryItem(LibraryItem libraryItem) throws IsbnAlreadyInUseException {
        List<LibraryItem> libraryItems = getAllLibraryItems();
        for (LibraryItem item : libraryItems) {
            if (item.getIsbn().equals(libraryItem.getIsbn())) {
                throw new IsbnAlreadyInUseException(libraryItem.getIsbn());
            }
        }
        libraryItemList.add(libraryItem);
        return libraryItem.getIsbn();
    }

    @Override
    public String deleteLibraryItem(String isbn) throws IdNotFoundException {
        LibraryItem libraryItem = findLibraryItem(isbn);
        libraryItemList.remove(libraryItem);
        return isbn;
    }

    @Override
    public LibraryItem getLibraryItem(String isbn) throws IdNotFoundException {
        List<LibraryItem> libraryItems = getAllLibraryItems();
        for (LibraryItem item : libraryItems) {
            if (item.getIsbn().equals(isbn)) {
                return item;
            }
        }
        throw new IdNotFoundException(isbn);
    }

    @Override
    public List<LibraryItem> getAllLibraryItems() {
        return libraryItemList;
    }

    @Override
    public Response borrowLibraryItem(Reader reader, String isbn) {
        return null;
    }

    @Override
    public Response returnLibraryItem(Reader reader, LibraryItem libraryItem) {
        return null;
    }

    @Override
    public String addReader(Reader reader) throws Exception {
        readerList.add(reader);
        return reader.getName();
    }

    @Override
    public String deleteReader(Integer id) throws IdNotFoundException {
        String type = getReader(id).getClass().getTypeName();
        readerList.remove(id);
        return type;
    }

    @Override
    public Reader getReader(Integer id) throws IdNotFoundException {
        for (Reader reader : readerList) {
            if (reader.getId().equals(id)) {
                return reader;
            }
        }
        throw new IdNotFoundException(id.toString());
    }

    @Override
    public List<Reader> getAllReaders() throws Exception {
        return readerList;
    }

    private LibraryItem findLibraryItem(String isbn) throws IdNotFoundException {
        for (LibraryItem libraryItem : libraryItemList) {
            if (libraryItem.getIsbn().equals(isbn)) {
                return libraryItem;
            }
        }
        throw new IdNotFoundException(isbn);
    }

}