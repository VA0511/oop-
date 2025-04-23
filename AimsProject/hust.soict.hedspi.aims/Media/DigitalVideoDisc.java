package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.playable.Playable;

public class DigitalVideoDisc extends Media implements Playable {
    private static int nbDigitalVideoDiscs = 0;
    private String director;
    private int length;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
        this.id = ++nbDigitalVideoDiscs;
    }

    public String getDirector() { return director; }
    public int getLength() { return length; }

    @Override
    public void play() {
        if (length <= 0) {
            System.out.println("Cannot play DVD with non-positive length.");
        } else {
            System.out.println("Playing DVD: " + title);
            System.out.println("DVD length: " + length);
        }
    }

    @Override
    public String toString() {
        return "DVD - " + super.toString() + " - " + director + " - " + length;
    }
}
