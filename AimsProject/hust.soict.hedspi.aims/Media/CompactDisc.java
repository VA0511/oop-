package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.playable.Playable;
import java.util.ArrayList;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, float cost, String artist) {
        super(title, category, cost);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    @Override
    public void play() {
        if (tracks.isEmpty()) {
            System.out.println("CD has no tracks to play.");
        } else {
            System.out.println("Playing CD: " + title + " by " + artist);
            for (Track track : tracks) {
                track.play();
            }
        }
    }

    @Override
    public String toString() {
        return "CD - " + super.toString() + " - Artist: " + artist + " - Tracks: " + tracks.size();
    }
}
