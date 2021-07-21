package application;

import FileHelper.ImportHelper;
import FileHelper.ValueType;
import FileHelper.attribute.AttributeIdentifier;
import FileHelper.commands.CommandRule;
import FileHelper.commands.Commands;
import FileHelper.commands.Rule;
import FileHelper.data.InformationData;
import utils.AttributeObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Runnable {

    private ArrayList<Rule> rules = new ArrayList<>();
    private ArrayList<AttributeObject> unfiltered_attributeObjects;
    private ArrayList<AttributeObject> filtered_attributeObjects = new ArrayList<>();

    public Runnable() {
        init();
        /*
        rules.add(new Rule("Tier", CommandRule.operationTypes.getType(">"), "3"));
        rules.add(new Rule("Name", CommandRule.operationTypes.getType("="), "winfield"));
        rules.add(new Rule("Cost", CommandRule.operationTypes.getType("<"), "30"));
        rules.add(new Rule("Meleeable", CommandRule.operationTypes.getType("="), "true"));
        */
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] commands = command.split(" ");

            try {
                Commands nextCommand = Commands.valueOf(commands[0]);
                String arguments = command.substring(commands[0].length());
                nextCommand.getCommand().fire(this, arguments);
            } catch (IllegalArgumentException e) {
                System.err.println("The command \"" + commands[0] + "\" could not be found.");
                System.err.println("A list of all possible commands: ");
                for (Commands cmd : Commands.values()) {
                    for (InformationData informationData : cmd.getData()) {
                        System.out.println("\"" + informationData.getInformation() + "\" \t" + informationData.getData());
                    }
                }
            }

        }

    }


    private void init() {
        System.out.println("Reinitializing object list (weapons, perks, tools, consumables)");
        this.unfiltered_attributeObjects = (new ImportHelper()).get(this, "DataFile");
        System.out.println("Initialize finalized. Proceed.");
    }


    public ArrayList<AttributeObject> getUnfiltered_attributeObjects() {
        return this.unfiltered_attributeObjects = (new ImportHelper()).get(this, "DataFile");
    }

    public void adjustFilteredList() {
        filtered_attributeObjects.clear();
        for (AttributeObject attributeObject : unfiltered_attributeObjects) {
            for (Rule r : rules) {
                AttributeIdentifier attributeIdentifier = attributeObject.hasGivenAttribute(ValueType.valueOf(r.getAttribute()));
                if (!attributeIdentifier.doesExist())
                    continue;
                if (attributeObject.compareTo(attributeIdentifier.getAttribute(), r.getOperation(), r.getOperand()))
                    filtered_attributeObjects.add(attributeObject);
            }
        }
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public String[] getObjectIdentifier() {
        String[] argumentTypes = {
                "Weapon", "Tool", "Consumable", "Perk"
        };
        return argumentTypes;
    }
}