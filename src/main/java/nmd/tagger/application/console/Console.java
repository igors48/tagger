package nmd.tagger.application.console;

import nmd.tagger.application.state.State;

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
                Thread.sleep(5);
            } while (Renderer.render(state));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
