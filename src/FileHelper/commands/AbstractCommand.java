package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private String[] args;
    protected Runnable run;

    /**
     * Args that start with a dash ("-") refer to an instructional operation
     * A list of those operations:
     * -get
     * -count
     * -list
     * <p>
     * Other commands can have different operations with multiple arguments
     * An example of those operations is given in each of the children classes.
     */
    public AbstractCommand(String... args) {
        this.args = args;
    }


    private ArrayList<ArrayList<String>> informationLists;

    public abstract void fire(Runnable run, String arguments, boolean suppress);

    public String[] getArgs() {
        return args;
    }

    public void setInformationLists(ArrayList<ArrayList<String>> list) {
        this.informationLists = list;
    }

    public List<ArrayList<String>> getInformationLists() {
        if (informationLists == null || informationLists.isEmpty()) {
            ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
            ArrayList<String> information = new ArrayList<>();
            information.add((String) "No displayable items");
            arrayLists.add(information);
            return arrayLists;
        }
        return informationLists;
    }

    public void error(AbstractCommand abstractCommand, String errorInformation) {
        System.err.println(abstractCommand.getClass() + " experienced an issue!\n" + errorInformation);
    }

    public abstract String getHelp(String... type);

    public abstract void updateInformationData();
}
