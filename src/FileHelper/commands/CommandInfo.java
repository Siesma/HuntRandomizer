package FileHelper.commands;

import application.Runnable;

public class CommandInfo extends AbstractCommand {
    public CommandInfo(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        this.run = run;
    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"info\" prints information according to the current arguments.\n");
        out.append("\t\"info\" requires two arguments. An attribute and an object (Weapon, Tool, etc)\n");
        out.append("\tExample: \"info Name Knife\" will print whether the object \"Knife\" has the \"Name\" attribute or not.\n");
        out.append("---\n");
        return out.toString();
    }
}
