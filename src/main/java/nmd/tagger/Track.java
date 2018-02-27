package nmd.tagger;

import java.io.File;

public class Track {

    private final File file;

    private TrackInfo info;

    public Track(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public TrackInfo getInfo() {
        return info;
    }

    public void setInfo(TrackInfo info) {
        this.info = info;
    }

}
