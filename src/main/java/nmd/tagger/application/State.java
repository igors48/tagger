package nmd.tagger.application;

/**
 * Created by igu on 16-Mar-18.
 */
public class State {

    private Step step;
    private int filesCount;
    private int count;

    public State() {
        step = Step.SCAN;
        filesCount = 0;
        count = 0;
    }

    public synchronized Step getStep() {
        return step;
    }

    public synchronized void setStep(Step step) {
        this.step = step;
    }

    public synchronized void scan() {
        step = Step.SCAN;
    }

    public synchronized void scanCompleted(int size) {
        step = Step.SCAN;
        filesCount = size;
    }

    public synchronized void read(int current) {
        step = Step.READ;
        count = current;
    }

    public synchronized int getFilesCount() {
        return filesCount;
    }

    public synchronized void verify(int current) {
        step = Step.VERIFY;
        count = current;
    }

    public synchronized int getCount() {
        return count;
    }

}
