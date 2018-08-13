package nmd.tagger.images;

import nmd.tagger.tracks.TrackInfo;

import java.nio.file.Path;

public class Image {

    private final Path path;
    private final TrackInfo trackInfo;

    public Image(Path path, TrackInfo trackInfo) {
        this.path = path;
        this.trackInfo = trackInfo;
    }

    public Path getPath() {
        return path;
    }

    public TrackInfo getTrackInfo() {
        return trackInfo;
    }

}
