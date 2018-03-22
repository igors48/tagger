package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.console.Renderer;

/**
 * Created by igu on 16-Mar-18.
 */
public class Console implements Runnable {

    private final State state;

    public Console(State state) {
        this.state = state;
    }

    @Override
    public void run() {

        try {

            do {
                Thread.sleep(10);
            } while (Renderer.render(state));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
