package nmd.tagger.application.state;

/**
 * Created by igu on 16-Mar-18.
 */
public enum Step {
    SCAN_MP3,
    SCAN_JPG,
    READ,
    VERIFY,
    PROCESS,
    END,
    ERROR
}
