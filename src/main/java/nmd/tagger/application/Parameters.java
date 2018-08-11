package nmd.tagger.application;

import java.util.Objects;

public class Parameters {

    private final String musicPath;
    private final String imagesPath;

    public Parameters(String musicPath, String imagesPath) {
        this.musicPath = musicPath;
        this.imagesPath = imagesPath;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Objects.equals(musicPath, that.musicPath) &&
                Objects.equals(imagesPath, that.imagesPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicPath, imagesPath);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "musicPath='" + musicPath + '\'' +
                ", imagesPath='" + imagesPath + '\'' +
                '}';
    }
}
