package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = (m1, m2) -> {
        int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleCompare != 0) return titleCompare;
        return Float.compare(m2.getCost(), m1.getCost());
    };

    public static final Comparator<Media> COMPARE_BY_COST_TITLE = (m1, m2) -> {
        int costCompare = Float.compare(m2.getCost(), m1.getCost());
        if (costCompare != 0) return costCompare;
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    };

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        return title.equalsIgnoreCase(media.title);
    }

    @Override
    public String toString() {
        return title + " - " + category + ": " + cost + "$";
    }
}
