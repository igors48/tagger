package nmd.tagger;

public class TrackInfo {

    private final String artist;
    private final String title;

    public TrackInfo(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackInfo trackInfo = (TrackInfo) o;

        return artist.equals(trackInfo.artist) && title.equals(trackInfo.title);
    }

    @Override
    public int hashCode() {
        int result = artist.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

}
