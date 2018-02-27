package nmd.tagger;

import java.nio.file.Path;

public interface TrackFileActions {

    TrackFile load(Path path);

    void setAuthorAndTitle(String author, String title, TrackFile file);

    void store(TrackFile file);

}
