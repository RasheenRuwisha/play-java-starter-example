package services;

import dao.LibraryManagerDao;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.LibraryItem;
import models.Reader;

import javax.inject.Inject;
import java.util.List;

public class WestminsterLibraryManager implements LibraryManager {

    private final LibraryManagerDao libraryManagerDao;

    @Inject
    public WestminsterLibraryManager(LibraryManagerDao libraryManagerDao) {
        this.libraryManagerDao = libraryManagerDao;
    }

    @Override
    public String addLibraryItem(LibraryItem libraryItem) throws IsbnAlreadyInUseException {
        return libraryManagerDao.addLibraryItem(libraryItem);
    }

    @Override
    public String deleteLibraryItem(String isbn) throws IdNotFoundException {
        return libraryManagerDao.deleteLibraryItem(isbn);
    }

    @Override
    public LibraryItem getLibraryItem(String isbn) throws IdNotFoundException {
        return libraryManagerDao.getLibraryItem(isbn);
    }

    @Override
    public List<LibraryItem> getLibraryItems() {
        return libraryManagerDao.getAllLibraryItems();
    }

    @Override
    public void borrowLibraryItem(Reader reader, String isbn) {
        libraryManagerDao.borrowLibraryItem(reader, isbn);
    }

    @Override
    public void returnLibraryItem(Reader reader, LibraryItem libraryItem) {
        libraryManagerDao.returnLibraryItem(reader, libraryItem);
    }

    @Override
    public String addReader(Reader reader) throws Exception {
        return libraryManagerDao.addReader(reader);
    }

    @Override
    public String deleteReader(Integer id) throws IdNotFoundException {
        return libraryManagerDao.deleteReader(id);
    }

    @Override
    public Reader getReader(Integer id) throws IdNotFoundException {
        return libraryManagerDao.getReader(id);
    }

    @Override
    public List<Reader> getAllReaders() throws Exception {
        return libraryManagerDao.getAllReaders();
    }

    @Override
    public String generateReport() {
        return null;
    }
}
