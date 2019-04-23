package controllers;

import com.google.inject.Inject;
import exceptions.IsbnAlreadyInUseException;
import exceptions.IdNotFoundException;
import models.Reader;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.LibraryManager;
import views.html.reader.*;

import java.util.List;

public class ReaderController extends Controller {

    @Inject
    LibraryManager libraryManager;

    @Inject
    FormFactory formFactory;

    private static Integer id = 0;

    public Result create() {
        Form<Reader> readerForm = formFactory.form(Reader.class);
        return ok(create.render(readerForm));
    }

    public Result index() {
        try {
            List<Reader> readers = libraryManager.getAllReaders();
            return ok(index.render(readers));
        } catch (Exception e) {
            return notAcceptable(e.getMessage());
        }
    }

    public Result save() {
        try {
            Form<Reader> readerForm = formFactory.form(Reader.class).bindFromRequest();
            Reader reader = readerForm.get();
            reader.setId(id++);
            libraryManager.addReader(reader);
            return redirect(routes.MainController.index());
        } catch (IsbnAlreadyInUseException e) {
            return notAcceptable(e.getMessage());
        } catch (Exception e) {
            return notAcceptable(e.getMessage());
        }
    }

    public Result delete(Integer id) {
        try {
            libraryManager.deleteReader(id);
            return redirect(routes.MainController.index());
        } catch (Exception e) {
            return notFound(e.getMessage());
        }
    }

    public Result show(Integer id) {
        try {
            Reader reader = libraryManager.getReader(id);
            return ok(show.render(reader));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result edit(Integer id) {
        try {
            Reader reader = libraryManager.getReader(id);
            Form<Reader> readerForm = formFactory.form(Reader.class).fill(reader);
            return ok(edit.render(readerForm));
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    public Result update() {
        try {
            Reader reader = formFactory.form(Reader.class).bindFromRequest().get();
            Reader oldReader = libraryManager.getReader(reader.getId());
            oldReader.setId(reader.getId());
            oldReader.setName(reader.getName());
            oldReader.setEmail(reader.getEmail());
            oldReader.setNumber(reader.getNumber());
            libraryManager.addReader(reader);
            return redirect(routes.MainController.index());
        } catch (IdNotFoundException e) {
            return notFound(e.getMessage());
        } catch (Exception e) {
            return notAcceptable(e.getMessage());
        }
    }
}
