package controllers;

import com.google.inject.Inject;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.Book;
import models.Dvd;
import models.LibraryItem;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.LibraryManager;
import views.html.libraryItems.book.burrow;
import views.html.libraryItems.index;
import views.html.libraryItems.book.create;
import views.html.libraryItems.book.edit;
import views.html.libraryItems.book.show;

import java.util.List;

public class BookController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    LibraryManager libraryManager;

    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result index() {
//        List<Book> books = Book.find.all();
        try {
            List<LibraryItem> libraryItems = libraryManager.getLibraryItems();
            return ok(index.render(libraryItems));
        } catch (Exception e) {
            return notAcceptable(e.getMessage());
        }
    }

    public Result save() {
//        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
//        Book book = bookForm.get();
//        book.save();
//        return redirect(routes.MainController.index());
        try {
            Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
            Book book = bookForm.get();
            libraryManager.addLibraryItem(book);
            return redirect(routes.MainController.index());
        } catch (IsbnAlreadyInUseException e) {
            return notAcceptable(e.getMessage());
        }
    }

    public Result show(String isbn) {
//        Book book = Book.find.byId(isbn);
//        if (book == null) {
//            return notFound("Book Not Found");
//        }
//
//        return ok(show.render(book));
        try {
            Book book = (Book) libraryManager.getLibraryItem(isbn);
            return ok(show.render(book));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result edit(String isbn) {
//        Book book = Book.find.byId(isbn);
//        if (book == null) {
//            return notFound("Book Not Found");
//        }
//        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
//
//        return ok(edit.render(bookForm));
        try {
            Book book = (Book) libraryManager.getLibraryItem(isbn);
            Form<Book> bookForm = formFactory.form(Book.class).fill(book);
            return ok(edit.render(bookForm));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result update() {
//        Book book = formFactory.form(Book.class).bindFromRequest().get();
//        Book oldBook = Book.find.byId(book.getIsbn());
//
//        if (oldBook == null) {
//            return notFound("Book Not Found");
//        }
//
//        oldBook.setTitle(book.getTitle());
//        oldBook.setSector(book.getSector());
//        oldBook.setPublishedDate(book.getPublishedDate());
//        oldBook.setDateTime(book.getDateTime());
//        oldBook.setReader(book.getReader());
//        oldBook.setAuthors(book.getAuthors());
//        oldBook.setPublisher(book.getPublisher());
//        oldBook.setPages(book.getPages());
//        oldBook.update();
//        return redirect(routes.MainController.index());
        try {
            Book book = formFactory.form(Book.class).bindFromRequest().get();
            Book oldBook = (Book) libraryManager.getLibraryItem(book.getIsbn());
            oldBook.setTitle(book.getTitle());
            oldBook.setSector(book.getSector());
            oldBook.setPublishedDate(book.getPublishedDate());
            oldBook.setDateTime(book.getDateTime());
            oldBook.setReader(book.getReader());
            oldBook.setAuthors(book.getAuthors());
            oldBook.setPublisher(book.getPublisher());
            oldBook.setPages(book.getPages());

            return redirect(routes.MainController.index());
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result burrow(String isbn) {
        try {
            Book book = (Book) libraryManager.getLibraryItem(isbn);
            Form<Book> bookForm = formFactory.form(Book.class).fill(book);
            return ok(burrow.render(bookForm));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }
}
