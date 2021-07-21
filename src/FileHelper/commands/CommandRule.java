package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class CommandRule extends AbstractCommand {


    /**
     * A possible list of commands can contain instructional operations given by the parent class.
     * <p>
     * The usage of the given command contains multiple arguments.
     * Those are based on the following:
     * rule {attribute, operation, operand}
     * "attribute" refers to the attribute that is checked against.
     * "operation" refers to the logic operations (>, <, >=, <=, = | "==" is treated equally to "=")
     * "operand" refers to the checked value. String operations only have an equality operation.
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

        if (type.length <= 3) {
            error(this, "Not enough arguments. 3 arguments were expected but only " + getArgs().length + " were provided.");
            return;
        }

        if (type.length >= 5) {
            error(this, "Too many arguments. 3 arguments were expected but " + getArgs().length + " were provided.");
            return;
        }
        String[] newType = new String[type.length - 1];
        System.arraycopy(type, 1, newType, 0, type.length - 1);
        type = newType;
        if(operationTypes.getType(type[1]) == null) {
            System.out.println("The operation \"" + type[1] + "\" could not be found.");
            return;
        }
        Rule r = new Rule(type[0], operationTypes.getType(type[1]), type[2]);

        run.getRules().add(r);
        run.adjustFilteredList();
        System.out.println("---");
        System.out.println("\t The rule \"" + r + "\" has been added.");
        System.out.println("---");

    }

    public enum operationTypes {
        Greater(">"), Lower("<"), Equal("=", "=="), GreaterNEqual(">="), LowerNEqual("<="), Unequal("!", "!=");
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
        out.append("\t\"rule {attribute, operation, operand}\" \n");
        out.append("\t\"attribute\" refers to the attribute that is checked against. \n");
        out.append("\t\"operation\" refers to the logic operations (>, <, >=, <=, =, !). \n");
        out.append("\t\"operand\" refers to the checked value. String operations only have an equality operation. \n");
        out.append("---\n");

        return out.toString();
    }

}
