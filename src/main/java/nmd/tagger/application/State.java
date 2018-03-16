package nmd.tagger.application;

/**
 * Created by igu on 16-Mar-18.
 */
public class State {

    private Step step;

    public State() {
        this.step = Step.SCAN;
    }

    public synchronized Step getStep() {
        return step;
    }

    public synchronized void setStep(Step step) {
        this.step = step;
    }

}
