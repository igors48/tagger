package nmd.tagger.application.console;

import nmd.tagger.application.State;

/**
 * Created by igu on 16-Mar-18.
 */
public class Renderer {

    public static void render(State state) {
        System.out.println(state.getStep());
    }

}
