package nmd.tagger;

import java.nio.file.Path;

public class Track {

    private final Path path;
    private final TrackInfo trackInfo;

    Track(Path path, TrackInfo trackInfo) {
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
