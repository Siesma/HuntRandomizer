package FileHelper.commands;

import application.Runnable;

public class CommandLoss extends AbstractCommand {
    public CommandLoss (String... args) {

        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        this.run = run;
        new CommandGenerate().fire(run, "");
    }

    @Override
    public String getHelp(String... type) {
        return null;
    }
}
