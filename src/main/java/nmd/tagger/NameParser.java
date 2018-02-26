package nmd.tagger;

public class NameParser {

    private static final String DIVIDER = "--";
    private static final int DIVIDER_LENGTH = "--".length();

    public static TrackInfo parse(String name) {
        String artist = "";
        String title = "";

        int dividerIndex = name.indexOf(DIVIDER);

        if (dividerIndex != -1) {
            artist = restoreSpaces(name.substring(0, dividerIndex));
            title = restoreSpaces(name.substring(dividerIndex + DIVIDER_LENGTH, name.length()));
        }

        return new TrackInfo(artist, title);
    }

    private static String restoreSpaces(String source) {
        return source.replace("-", " ");
    }

}
