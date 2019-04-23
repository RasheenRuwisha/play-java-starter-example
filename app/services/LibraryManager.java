package services;

import com.google.inject.ImplementedBy;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.LibraryItem;
import models.Reader;

import java.util.List;

/**
 * LibraryManager is an interface containing the functions of the LibraryManagement system
 * This interface serves as an service layer interface
 */
@ImplementedBy(WestminsterLibraryManager.class)
public interface LibraryManager {

    String addLibraryItem(LibraryItem libraryItem) throws IsbnAlreadyInUseException;

    String deleteLibraryItem(String isbn) throws Exception;

    LibraryItem getLibraryItem(String isbn) throws IdNotFoundException;

    List<LibraryItem> getLibraryItems() throws Exception;

    void borrowLibraryItem(Reader reader, String isbn);

    void returnLibraryItem(Reader reader, LibraryItem libraryItem);

    String addReader(Reader reader) throws Exception;

    String deleteReader(Integer id) throws IdNotFoundException;

    Reader getReader(Integer id) throws IdNotFoundException;

    List<Reader> getAllReaders() throws Exception;

    String generateReport();
}
