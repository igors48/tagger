package nmd.tagger.tracks;

import java.nio.file.Path;

public class Track {

    private final Path path;
    private final TrackInfo trackInfo;

    private boolean artistDefined;
    private boolean titleDefined;
    private boolean readable;

    private boolean processed;

    public Track(Path path, TrackInfo trackInfo) {
        this.path = path;
        this.trackInfo = trackInfo;
    }

    public Path getPath() {
        return path;
    }

    public TrackInfo getTrackInfo() {
        return trackInfo;
    }

    public boolean isArtistDefined() {
        return artistDefined;
    }

    public void setArtistDefined(boolean artistDefined) {
        this.artistDefined = artistDefined;
    }

    public boolean isTitleDefined() {
        return titleDefined;
    }

    public void setTitleDefined(boolean titleDefined) {
        this.titleDefined = titleDefined;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isValid() {
        return readable && artistDefined && titleDefined;
    }

}
