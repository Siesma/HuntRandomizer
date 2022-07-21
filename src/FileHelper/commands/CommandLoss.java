package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;

public class CommandLoss extends AbstractCommand {
    public CommandLoss (String... args) {

        super(args);
    }

    @Override
    public void updateInformationData() {
        ArrayList list = new ArrayList();
        setInformationLists(list);
    }

    @Override
    public void setInformationLists(ArrayList list) {
        super.setInformationLists(list);
    }
    @Override
    public void fire(Runnable run, String arguments, boolean suppress) {
        String[] type = arguments.split(" ");
        this.run = run;
        new CommandGenerate().fire(run, "", false);
    }

    @Override
    public String getHelp(String... type) {
        return null;
    }
}
