package nl.rocvantwente.rsk01.booklistingapp;

public class Book {
    public String get_coverUrl() {
        return _coverUrl;
    }

    public void set_coverUrl(String _coverUrl) {
        this._coverUrl = _coverUrl;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String[] get_author() {
        return _authors;
    }

    public void set_author(String[] _authors) {
        this._authors = _authors;
    }

    public String get_releaseYear() {
        return _releaseYear;
    }

    public void set_releaseYear(String _releaseYear) {
        this._releaseYear = _releaseYear;
    }

    public String get_publisher() {
        return _publisher;
    }

    public void set_publisher(String _publisher) {
        this._publisher = _publisher;
    }

    public String get_language() {
        return _language;
    }

    public void set_language(String _language) {
        this._language = _language;
    }

    public String get_isbn() {
        return _isbn;
    }

    public void set_isbn(String _isbn) {
        this._isbn = _isbn;
    }

    private String _coverUrl;

    public Book(String _coverUrl, String _title, String[] _authors, String _releaseYear, String _publisher, String _language, String _isbn) {
        this._coverUrl = _coverUrl;
        this._title = _title;
        this._authors = _authors;
        this._releaseYear = _releaseYear;
        this._publisher = _publisher;
        this._language = _language;
        this._isbn = _isbn;
    }

    public Book() {
    }

    private String _title;
    private String[] _authors;
    private String _releaseYear;
    private String _publisher;
    private String _language;
    private String _isbn;
}
