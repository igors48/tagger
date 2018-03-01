package nmd.tagger.operations.mp3agic;

import com.mpatric.mp3agic.*;
import nmd.tagger.operations.Mp3Operations;

import java.io.IOException;

/**
 * Created by igu on 28-Feb-18.
 */
public class Mp3agicOperations implements Mp3Operations {

    private final Mp3File mp3File;

    public Mp3agicOperations(Mp3File mp3File) {
        this.mp3File = mp3File;
    }

    @Override
    public boolean hasId3v1Tag() {
        return mp3File.hasId3v1Tag();
    }

    @Override
    public boolean hasId3v2Tag() {
        return mp3File.hasId3v2Tag();
    }

    @Override
    public void createId3v1Tag() {
        ID3v1Tag id3v1Tag = new ID3v1Tag();
        mp3File.setId3v1Tag(id3v1Tag);
    }

    @Override
    public void createId3v2Tag() {
        ID3v2 id3v2Tag = new ID3v24Tag();
        mp3File.setId3v2Tag(id3v2Tag);
    }

    @Override
    public void setArtistToId3v1Tag(String artist) {
        ID3v1 id3v1Tag = mp3File.getId3v1Tag();
        id3v1Tag.setArtist(artist);
    }

    @Override
    public void setTitleToId3v1Tag(String title) {
        ID3v1 id3v1Tag = mp3File.getId3v1Tag();
        id3v1Tag.setTitle(title);
    }

    @Override
    public void setArtistToId3v2Tag(String artist) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();
        id3v2Tag.setArtist(artist);
        id3v2Tag.setAlbumArtist(artist);
    }

    @Override
    public void setAlbumArtistToId3v2Tag(String artist) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();
        id3v2Tag.setAlbumArtist(artist);
    }

    @Override
    public void setTitleToId3v2Tag(String title) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();
        id3v2Tag.setTitle(title);
    }

    @Override
    public void setAlbumToId3v1Tag(String album) {
        ID3v1 id3v1Tag = mp3File.getId3v1Tag();
        id3v1Tag.setAlbum(album);
    }

    @Override
    public void setAlbumToId3v2Tag(String album) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();
        id3v2Tag.setAlbum(album);
    }

    @Override
    public void setTrackToId3v1Tag(String track) {
        ID3v1 id3v1Tag = mp3File.getId3v1Tag();
        id3v1Tag.setTrack(track);
    }

    @Override
    public void setTrackToId3v2Tag(String track) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();
        id3v2Tag.setTrack(track);
    }

    @Override
    public void store(String fileName) throws IOException, NotSupportedException {
        mp3File.save(fileName);
    }

}
