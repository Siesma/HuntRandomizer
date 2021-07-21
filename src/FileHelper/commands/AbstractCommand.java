package FileHelper.commands;

import application.Runnable;

public abstract class AbstractCommand {

    private String[] args;
    protected Runnable run;
    /**
     * Args that start with a dash ("-") refer to an instructional operation
     * A list of those operations:
     *      -get
     *      -count
     *      -list
     *
     * Other commands can have different operations with multiple arguments
     * An example of those operations is given in each of the children classes.
     */
    public AbstractCommand(String... args) {
        this.args = args;
    }


    public abstract void fire(Runnable run, String arguments);

    public String[] getArgs() {
        return args;
    }

    public void error (AbstractCommand abstractCommand, String errorInformation) {
        System.err.println(abstractCommand.getClass() + " experienced an issue!\n" + errorInformation);
    }

    public abstract String getHelp(String... type);

}
