package nmd.tagger;

import java.nio.file.Path;

public class Track {

    private final Path path;

    private TrackInfo info;

    public Track(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public TrackInfo getInfo() {
        return info;
    }

    public void setInfo(TrackInfo info) {
        this.info = info;
    }

}
