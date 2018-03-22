package nmd.tagger;

import nmd.tagger.application.Main;
import nmd.tagger.application.Parameters;
import nmd.tagger.application.command.CommandFactory;
import nmd.tagger.application.console.Console;
import nmd.tagger.application.state.State;
import nmd.tagger.application.state.Step;
import nmd.tagger.operations.DirectoryOperations;
import nmd.tagger.operations.path.PathOperations;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        final Parameters parameters = new Parameters("D:\\");
        final State state = new State();
        final DirectoryOperations directoryOperations = new PathOperations();
        final CommandFactory commandFactory = new CommandFactory(directoryOperations, state);

        final Main main = new Main(parameters, commandFactory, state);
        final Console console = new Console(state);

        final Thread consoleThread = new Thread(console);
        consoleThread.start();

        final Thread mainThread = new Thread(main);
        mainThread.start();

        while (!(state.getStep().equals(Step.END) || state.getStep().equals(Step.ERROR))) {
            Thread.sleep(250);
        }

    }

}
