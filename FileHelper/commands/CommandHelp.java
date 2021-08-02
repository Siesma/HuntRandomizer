package FileHelper.commands;

import FileHelper.ValueType;
import FileHelper.data.InformationData;
import application.Runnable;
import utils.AttributeObject;

public class CommandHelp extends AbstractCommand {


    public CommandHelp(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        if (type.length == 0 || (type.length == 1 && type[0].equalsIgnoreCase(""))) {
            System.out.println(getHelp(type));
            return;
        }
        String[] argumentTypes = run.getObjectIdentifier();
        if (type[1].equalsIgnoreCase("-get")) {
            for (Commands commands : Commands.values()) {
                AbstractCommand abstractCommand = commands.getCommand();
                try {
                    if (abstractCommand instanceof CommandRule && type[2].matches("( |\t|\n|)+rule( |\t|\n|)+")) {
                        System.out.println("---");
                        try {
                            System.out.print(((CommandRule) abstractCommand).getRules().isEmpty() ? "\tThere are no brooked rules\n" : "");
                            ((CommandRule) abstractCommand).getRules().forEach(e -> System.out.println("\t" + e.toString()));
                        } catch (Exception e) {
                            System.out.println("\tThere were no rules found.");
                        }
                        System.out.println("---");
                        return;
                    } else if (abstractCommand instanceof CommandChallenge && type[2].matches("( |\t|\n|)+challenge( |\t|\n|)+")) {
                        System.out.println("---");
                        ((CommandChallenge) abstractCommand).getChallenges().forEach(e -> System.out.println("\t" + e.toString()));
                        System.out.print(((CommandChallenge) abstractCommand).getChallenges().isEmpty() ? "There are no brooked rules\n" : "");
                        System.out.println("---");
                        return;
                    } else if (abstractCommand instanceof CommandInfo && type[2].equalsIgnoreCase("info")) {
                        System.out.println("---");
                        System.out.print(run.getFiltered_attributeObjects().isEmpty() ? "There are no objects that fit the given rules." : "");
                        run.getFiltered_attributeObjects().forEach(e -> System.out.println("\t" + e.toString()));
                        System.out.println("---");
                        return;
                    }
                } catch (Exception e) {
                    error(this, "An unexpected error occured\n" + (type.length == 1 ? "Not enough arguments provided" : ""));
                    return;
                }
            }
            if (type[2].equalsIgnoreCase("attribute") || type[2].equalsIgnoreCase("attributes")) {
                System.out.println("---");
                for (ValueType valueType : ValueType.values()) {
                    for (InformationData informationData : valueType.getIdentifier()) {
                        System.out.println("\t" + informationData.getInformation());
                    }
                }
                System.out.println("---");
            } else {
                for (AttributeObject attributeObject : run.getUnfiltered_attributeObjects()) {
                    boolean hasName = attributeObject.hasGivenAttribute(ValueType.Name).doesExist();
                    if (!hasName) {
                        System.out.println("\t" + attributeObject);
                        continue;
                    }
                    String name = attributeObject.hasGivenAttribute(ValueType.Name).getAttribute().toString();
                    for (String str : argumentTypes) {
                        if (attributeObject.getClass().toString().endsWith(str) && (type[2].equalsIgnoreCase(str) || type[2].equalsIgnoreCase(str + "s"))) {
                            System.out.println("\t" + name);
                        }
                    }
                }
            }
        } else if (type[1].equalsIgnoreCase("-count")) {
            for (Commands commands : Commands.values()) {
                AbstractCommand abstractCommand = commands.getCommand();
                try {
                    if (abstractCommand instanceof CommandRule && type[2].equalsIgnoreCase("rule")) {
                        System.out.println("---");
                        try {
                            System.out.println("\tThere are currently " + ((CommandRule) abstractCommand).getRules().size() + " rules active.");
                        } catch (Exception e) {
                            System.out.println("\tThere are 0 rules active.");
                        }
                        System.out.println("---");
                        return;
                    } else if (abstractCommand instanceof CommandChallenge && type[2].equalsIgnoreCase("challenge")) {
                        System.out.println("---");
                        System.out.println("\tThere are currently " + ((CommandChallenge) abstractCommand).getChallenges().size() + " challenges available.");
                        System.out.println("---");
                        return;
                    } else if (abstractCommand instanceof CommandInfo && type[2].equalsIgnoreCase("info")) {
                        System.out.println("---");
                        System.out.println("\tThere are currently " + (run.getFiltered_attributeObjects().size()) + " objects that can be chosen from.");
                        System.out.println("---");
                        return;
                    }
                } catch (Exception e) {
                    error(this, "An unexpected error occurred\n");
                    return;
                }
            }

            int count = 0;
            for (AttributeObject attributeObject : run.getUnfiltered_attributeObjects()) {
                for (String str : argumentTypes) {
                    if (attributeObject.getClass().toString().endsWith(str) && (type[2].equalsIgnoreCase(str) || type[2].equalsIgnoreCase(str + "s"))) {
                        count++;
                    }
                }
            }
            System.out.println("\tThere currently are " + count + " active " + type[2] + (count > 1 ? "s" : ""));
        } else {
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
                (new CommandHelp()).fire(run, "");
                return;
            }
            System.out.println(abstractCommand.getHelp(type));
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
