package models;

import Util.DateTime;
import io.ebean.Finder;
import io.ebean.annotation.DbArray;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Book extends LibraryItem {

    private String publisher;
    private int pages;

    @DbArray
    private List<String> authors;

    public static Finder<String, Book> find = new Finder<>(Book.class);

    public Book(String isbn, String title, String sector, DateTime publishedDate, DateTime dateTime, Integer readerId,
                List<String> authors, String publisher, int pages) {
        super(isbn, title, sector, publishedDate, dateTime, readerId);
        this.authors = authors;
        this.publisher = publisher;
        this.pages = pages;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
