package nmd.tagger.application.console;

import nmd.tagger.application.state.State;
import nmd.tagger.application.state.Step;

/**
 * Created by igu on 16-Mar-18.
 */
public class Renderer {

    public static boolean render(State state) {
        final Step step = state.getStep();
        final int current = state.getCount();
        final int filesCount = state.getFilesCount();

        switch (step) {
            case SCAN_MP3:
                System.out.println(step);
                return true;
            case READ:
                System.out.println(step + " " + current + " of " + filesCount);
                return true;
            case VERIFY:
                System.out.println(step + " " + current + " of " + filesCount);
                return true;
            case PROCESS:
                return true;
            default:
                return false;
        }
    }

}
