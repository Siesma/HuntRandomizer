package FileHelper.commands;

import FileHelper.ValueType;
import application.Runnable;

public class CommandWon extends AbstractCommand {

    public CommandWon(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        this.run = run;
        System.out.println("---");
        run.getFiltered_attributeObjects().forEach(e -> System.out.println("\t" + e.hasGivenAttribute(ValueType.Name).getAttribute().toString()));
        System.out.println("---");
    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\twon {amount_of_points}\n");
        out.append("\t\"Won\" generates a new set of perks for the next round.\n");
        out.append("\t\"Won\" is affected by rules.\n");
        out.append("---\n");
        return out.toString();
    }
}
