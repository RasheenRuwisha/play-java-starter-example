package controllers;

import com.google.inject.Inject;
import exceptions.IdNotFoundException;
import models.Book;
import models.Dvd;
import models.LibraryItem;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.LibraryManager;
import views.html.libraryItems.index;

import java.util.List;

public class MainController extends Controller {

    @Inject
    LibraryManager libraryManager;

    @Inject
    FormFactory formFactory;


    public Result index() {
//        List<LibraryItem> libraryItems = new ArrayList<>();
//        libraryItems.addAll(Book.find.all());
//        libraryItems.addAll(Dvd.find.all());
//
//        return ok(index.render(libraryItems));

        try {
            List<LibraryItem> libraryItems = libraryManager.getLibraryItems();
            return ok(index.render(libraryItems));
        } catch (Exception e) {
            return notAcceptable(e.getMessage());
        }
    }


    public Result show(String isbn) {
        try {

            LibraryItem libraryItem = libraryManager.getLibraryItem(isbn);
            if (libraryItem.getClass() == Book.class) {
                return ok(views.html.libraryItems.book.show.render((Book) libraryItem));
            } else if (libraryItem.getClass() == Dvd.class) {
                return ok(views.html.libraryItems.dvd.show.render((Dvd) libraryItem));
            } else {
                return notFound();
            }
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result delete(String isbn) {
        try {
            String type = libraryManager.deleteLibraryItem(isbn);
            return redirect(routes.MainController.index());
        } catch (Exception e) {
            return notFound(e.getMessage());
        }
    }
}
