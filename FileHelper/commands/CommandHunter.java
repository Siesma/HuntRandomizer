package FileHelper.commands;

import application.Runnable;

public class CommandHunter extends AbstractCommand {

    @Override
    public void fire(Runnable run, String arguments) {
        run.printHunter();
    }

    @Override
    public String getHelp(String... type) {
        return null;
    }
}
