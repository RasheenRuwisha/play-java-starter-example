package models;

import Util.DateTime;
import io.ebean.Finder;
import io.ebean.annotation.DbArray;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dvd extends LibraryItem {

    public static Finder<String, Dvd> find = new Finder<>(Dvd.class);

    @DbArray
    private List<String> languages;

    @DbArray
    private List<String> subtitles;

    @DbArray
    private List<String> cast;

    public Dvd(String isbn, String title, String sector, DateTime publishedDate, DateTime dateTime, Integer readerId,
               List<String> languages, List<String> subtitles, List<String> cast) {
        super(isbn, title, sector, publishedDate, dateTime, readerId);
        this.languages = languages;
        this.subtitles = subtitles;
        this.cast = cast;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }
}
