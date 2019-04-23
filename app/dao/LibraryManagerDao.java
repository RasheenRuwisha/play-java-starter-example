package dao;

import com.google.inject.ImplementedBy;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.LibraryItem;
import models.Reader;
import response.Response;

import java.util.List;

/**
 * LibraryManagerDao is the interface containing the functions of the library management system.
 * This serves in the data access layer
 */
@ImplementedBy(LibraryManagerDaoImpl.class)
public interface LibraryManagerDao {

    String addLibraryItem(LibraryItem libraryItem) throws IsbnAlreadyInUseException;

    String deleteLibraryItem(String isbn) throws IdNotFoundException;

    LibraryItem getLibraryItem(String isbn) throws IdNotFoundException;

    List<LibraryItem> getAllLibraryItems();

    Response borrowLibraryItem(Reader reader, String isbn);

    Response returnLibraryItem(Reader reader, LibraryItem libraryItem);

    String addReader(Reader reader) throws Exception;

    String deleteReader(Integer id) throws IdNotFoundException;

    Reader getReader(Integer id) throws IdNotFoundException;

    List<Reader> getAllReaders() throws Exception;
}
