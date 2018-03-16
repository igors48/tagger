package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.Step;
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
            Step step;

            do {
                step = state.getStep();
                Renderer.render(state);
                Thread.sleep(1000);
            } while (!step.equals(Step.END));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
