package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Books extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    public Books(String title, String category, float cost) {
        super(title, category, cost);
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    @Override
    public String toString() {
        return "Book - " + super.toString() + " - Authors: " + String.join(", ", authors);
    }
}
