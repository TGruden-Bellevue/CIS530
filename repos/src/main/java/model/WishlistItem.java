package model;

public class WishlistItem {

    @NotNull(message = "isbn is a required field.")
    @NotEmpty(message = "isbn is a required field.")
    private String isbn;

    @NotNull(message = "title is a required field.")
    @NotEmpty(message = "title is a required field.")
    private String title;

    public WishlistItem() {
    }

    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
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

    @Override
    public String toString() {
        return "WishlistItem{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
