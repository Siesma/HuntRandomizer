package FileHelper.commands;

import FileHelper.data.InformationData;
import application.Runnable;

import java.util.ArrayList;
import java.util.Locale;

public class CommandHelp extends AbstractCommand {


    @Override
    public void updateInformationData() {
        ArrayList<String> list = new ArrayList<>();
        setInformationLists(list);
    }

    @Override
    public void setInformationLists(ArrayList list) {
        super.setInformationLists(list);
    }

    public CommandHelp(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments, boolean suppress) {
        String[] type = arguments.split(" ");
        if (type.length == 0 || (type.length == 1 && type[0].equalsIgnoreCase(""))) {
            System.out.println(getHelp(type));
            return;
        }
        if (type.length == 2) {
            String command = type[1];
            AbstractCommand abstractCommand = null;
            for (Commands commands : Commands.values()) {
                for (InformationData ida : commands.getData()) {
                    if (ida.getInformation().replace("\"", "").equalsIgnoreCase(command)) {
                        abstractCommand = commands.getCommand();
                        break;
                    }
                }
            }
            if (abstractCommand == null) {
                (new CommandHelp()).fire(run, "", false);
                return;
            }
            System.out.println(abstractCommand.getHelp(type));
            return;
        }
        AbstractCommand currentCommand = Commands.valueOf(type[2].toLowerCase(Locale.ROOT)).getCommand();
        if (type[1].equalsIgnoreCase("-get")) {
            currentCommand.getInformationLists().forEach(list -> list.forEach(element -> System.out.println("\t" + element)));
        } else if (type[1].equalsIgnoreCase("-count")) {
            int size = currentCommand.getInformationLists().stream().mapToInt(ArrayList::size).sum();
            System.out.println("\tThere are currently " + size + " active modifiers");
        }
    }

    @Override
    public String getHelp(String... type) {
        System.err.println("A list of all possible commands:");
        for (Commands cmd : Commands.values()) {
            for (InformationData informationData : cmd.getData()) {
                System.out.println("" + informationData.getInformation() + " \t" + informationData.getData());
            }
        }
        return "";
    }

}
