package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;

public class CommandExit extends AbstractCommand {
    public CommandExit (String... args) {
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
        this.run = run;
        System.out.println("Exiting the system");
        System.exit(1);
    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"exit\" exits the program.\n");
        out.append("---\n");
        return out.toString();
    }
}
