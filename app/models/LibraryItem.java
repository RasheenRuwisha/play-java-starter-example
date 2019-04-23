package models;

import Util.DateTime;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class LibraryItem extends Model {

    @Id
    private String isbn;

    private String title;
    private String sector;
    private String publishedDate;
    private String dateTime;

    private Integer readerId;
//    @ManyToOne
//    private Reader reader;

    public LibraryItem() {
    }

    public LibraryItem(String isbn, String title, String sector, DateTime publishedDate, DateTime dateTime, Integer readerId) {
        this.isbn = isbn;
        this.title = title;
        this.sector = sector;
        this.publishedDate = publishedDate.toString();
        this.dateTime = dateTime.toString();
        this.readerId = readerId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getReader() {
        return readerId;
    }

    public void setReader(Integer readerId) {
        this.readerId = readerId;
    }
}
