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
import views.html.libraryItems.dvd.burrow;
import views.html.libraryItems.dvd.create;
import views.html.libraryItems.dvd.edit;
import views.html.libraryItems.dvd.show;
import views.html.libraryItems.index;

import java.util.List;

public class DvdController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    LibraryManager libraryManager;

    public Result create() {
        Form<Dvd> dvdForm = formFactory.form(Dvd.class);
        return ok(create.render(dvdForm));
    }

    public Result index() {
//        List<Dvd> dvds = Dvd.find.all();
//        List<LibraryItem> libraryItems = new ArrayList<>(dvds);
//        return ok(index.render(libraryItems));
        try {
            List<LibraryItem> dvds = libraryManager.getLibraryItems();
            return ok(index.render(dvds));
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
            Form<Dvd> dvdForm = formFactory.form(Dvd.class).bindFromRequest();
            Dvd dvd = dvdForm.get();
            libraryManager.addLibraryItem(dvd);
            return redirect(routes.MainController.index());
        } catch (IsbnAlreadyInUseException e) {
            return notAcceptable(e.getMessage());
        }
    }

    public Result show(String isbn) {
        try {
            Dvd dvd = (Dvd) libraryManager.getLibraryItem(isbn);
            return ok(show.render(dvd));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result edit(String isbn) {
        try {
            Dvd dvd = (Dvd) libraryManager.getLibraryItem(isbn);
            Form<Dvd> dvdForm = formFactory.form(Dvd.class).fill(dvd);
            return ok(edit.render(dvdForm));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result update() {
        try {
            Dvd dvd = formFactory.form(Dvd.class).bindFromRequest().get();
            Dvd oldDvd = (Dvd) libraryManager.getLibraryItem(dvd.getIsbn());
            oldDvd.setTitle(dvd.getTitle());
            oldDvd.setSector(dvd.getSector());
            oldDvd.setPublishedDate(dvd.getPublishedDate());
            oldDvd.setDateTime(dvd.getDateTime());
            oldDvd.setReader(dvd.getReader());
            oldDvd.setCast(dvd.getCast());
            oldDvd.setLanguages(dvd.getLanguages());
            oldDvd.setSubtitles(dvd.getSubtitles());

            return redirect(routes.MainController.index());
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result burrow(String isbn) {
        try {
            Dvd dvd = (Dvd) libraryManager.getLibraryItem(isbn);
            Form<Dvd> dvdForm = formFactory.form(Dvd.class).fill(dvd);
            return ok(burrow.render(dvdForm));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

//    public Result delete(String isbn) {
//        try {
//            String type = libraryManager.deleteLibraryItem(isbn);
//            return redirect(routes.MainController.index());
//        } catch (Exception e) {
//            return notFound(e.getMessage());
//        }
//    }
}
