package nmd.tagger.operations;

import com.mpatric.mp3agic.NotSupportedException;

import java.io.IOException;

public interface Mp3Operations {

    boolean hasId3v1Tag();

    boolean hasId3v2Tag();

    void createId3v1Tag();

    void createId3v2Tag();

    void setArtistToId3v1Tag(String artist);

    void setTitleToId3v1Tag(String title);

    void setArtistToId3v2Tag(String artist);

    void setAlbumArtistToId3v2Tag(String artist);

    void setTitleToId3v2Tag(String title);

    void setAlbumToId3v1Tag(String album);

    void setAlbumToId3v2Tag(String album);

    void setTrackToId3v1Tag(String track);

    void setTrackToId3v2Tag(String track);

    void store(String fileName) throws IOException, NotSupportedException;

    void replaceFrontCover() throws IOException;

}
