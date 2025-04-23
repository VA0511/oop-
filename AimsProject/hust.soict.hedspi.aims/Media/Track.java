package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.playable.Playable;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() {
        if (length <= 0) {
            System.out.println("Cannot play track with non-positive length.");
        } else {
            System.out.println("Playing Track: " + title);
            System.out.println("Track length: " + length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Track track) {
            return this.title.equalsIgnoreCase(track.title) && this.length == track.length;
        }
        return false;
    }
}
