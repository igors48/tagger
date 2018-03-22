package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.Step;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        final Parameters parameters = new Parameters("D:\\");
        final State state = new State();

        final Main main = new Main(parameters, state);
        final Console console = new Console(state);

        final Thread consoleThread = new Thread(console);
        consoleThread.start();

        final Thread mainThread = new Thread(main);
        mainThread.start();

        while (!state.getStep().equals(Step.END)) {
            Thread.sleep(250);
        }

    }

}
