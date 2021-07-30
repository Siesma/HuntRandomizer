package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;

public class CommandRule extends AbstractCommand {


    /**
     * A possible list of commands can contain instructional operations given by the parent class.
     * <p>
     * The usage of the given command contains multiple arguments.
     * Those are based on the following:
     * rule {attribute, operation, operand}
     * "attribute" refers to the attribute that is checked against.
     * "operation" refers to the logic operations (>, <, >=, <=, =, !, "==" is treated equally to "=") or the implicit operator \"~\" which means \"Contains\".
     * "operand" refers to the checked value. String operations only have the equality operations and the contains operation.
     */
    public CommandRule(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");

        this.run = run;
        /*
        rule tier > 3
         */

        String[] newType = new String[type.length - 1];
        System.arraycopy(type, 1, newType, 0, type.length - 1);
        type = newType;

        if (type.length == 0) {
            Commands.help.getCommand().fire(run, " rule");
            return;
        }
        if (!type[0].equalsIgnoreCase("-clear") && type.length <= 1) {
            error(this, "Not enough arguments. 3 arguments were expected but only " + type.length + " were provided.");
            return;
        }

        if (type[0].equalsIgnoreCase("-clear")) {
            System.out.println("---");
            System.out.println("\tAll rules have been removed");
            System.out.println("---");
            run.getRules().clear();
            run.adjustFilteredList();
            return;
        }

        if (type.length <= 2) {
            error(this, "Not enough arguments. 3 or 4 arguments were expected but only " + type.length + " were provided.");
            return;
        }

        if (type.length >= 5) {
            error(this, "Too many arguments. 3 or 4 arguments were expected but " + type.length + " were provided.");
            return;
        }
        String applicableType = "all";
        try {
            applicableType = type[3];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        if (operationTypes.getType(type[1]) == null) {
            System.out.println("The operation \"" + type[1] + "\" could not be found.");
            return;
        }
        Rule r = new Rule(type[0], operationTypes.getType(type[1]), type[2].replaceAll("_", " "), applicableType);

        System.out.println("---");
        System.out.println(run.addRule(r));
        System.out.println("---");
        run.adjustFilteredList();

    }

    public enum operationTypes {
        Greater(">"), Lower("<"), Equal("=", "=="), GreaterNEqual(">="), LowerNEqual("<="), Unequal("!", "!="), Contains("~");
        String[] data;

        operationTypes(String... data) {
            this.data = data;
        }

        public String[] getData() {
            return data;
        }

        public static operationTypes getType(String data) {
            for (operationTypes value : operationTypes.values())
                for (String valueData : value.getData())
                    if (data.equalsIgnoreCase(valueData))
                        return value;
            return null;
        }

    }

    public ArrayList<Rule> getRules() {
        return run.getRules();
    }

    public CommandRule addRules(Rule rule) {
        run.getRules().add(rule);
        return this;
    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"rule -clear resets the rules.\" \n");
        out.append("\t\"rule {attribute, operation, operand}\" \n");
        out.append("\t\"attribute\" refers to the attribute that is checked against. \n");
        out.append("\t\"operation\" refers to the logic operations (>, <, >=, <=, =, !) or the implicit operator \"~\" which means \"Contains\". \n");
        out.append("\t\"operand\" refers to the checked value. String operations only have an equality operation. \n");
        out.append("---\n");

        return out.toString();
    }

}
