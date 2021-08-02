package application;

import FileHelper.ImportHelper;
import FileHelper.ValueType;
import FileHelper.attribute.AttributeIdentifier;
import FileHelper.commands.CommandRule;
import FileHelper.commands.Commands;
import FileHelper.commands.Rule;
import FileHelper.data.InformationData;
import utils.AttributeObject;
import utils.Hunter;

import java.util.ArrayList;
import java.util.Scanner;

public class Runnable {

    private ArrayList<Rule> rules = new ArrayList<>();
    private ArrayList<AttributeObject> unfiltered_attributeObjects;
    private ArrayList<AttributeObject> filtered_attributeObjects = new ArrayList<>();

    private Hunter currentHunter;

    public Runnable(String parameter) {
        if (parameter.equalsIgnoreCase("TestingGround")) {
            init();
            rules.add(new Rule("Custom_Ammo", CommandRule.operationTypes.Contains, "Dragon Breath", "Weapon"));
        } else {
            init();
            Scanner sc = new Scanner(System.in);
            while (true) {
                String command = sc.nextLine();
                String[] commands = command.split(" ");

                try {
                    Commands nextCommand = Commands.valueOf(commands[0]);
                    String arguments = command.substring(commands[0].length());
                    System.out.println("->");
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
    }


    private void init() {
        System.out.print("\n\n\n");
        System.out.println("Reinitializing object list (weapons, perks, tools, consumables)");
        this.unfiltered_attributeObjects = (new ImportHelper()).get(this, "DataFile");
        System.out.println("Initialize finalized. Proceed.");
    }


    public ArrayList<AttributeObject> getUnfiltered_attributeObjects() {
        return this.unfiltered_attributeObjects = (new ImportHelper()).get(this, "DataFile");
    }

    public ArrayList<AttributeObject> getFiltered_attributeObjects() {
        adjustFilteredList();
        return this.filtered_attributeObjects;
    }

    public Runnable adjustFilteredList() {
        filtered_attributeObjects.clear();


//        for (AttributeObject attributeObject : unfiltered_attributeObjects) {
//            System.out.println("\n\t" + attributeObject.hasGivenAttribute(ValueType.Name).getAttribute().getData() + ":\t");
//            for (Rule r : rules) {
//                AttributeIdentifier attributeIdentifier = attributeObject.hasGivenAttribute(ValueType.valueOf(r.getAttribute()));
//                System.out.print(r.getAttribute() + ", ");
//                if (!attributeIdentifier.doesExist())
//                    continue;
//                if (attributeObject.compareTo(attributeIdentifier.getAttribute().getLookup(), r.getOperation(), r.getOperand())) {
//                    if (filtered_attributeObjects.contains(attributeObject))
//                        continue;
//                    filtered_attributeObjects.add(attributeObject);
//                }
//            }
//        }
        ArrayList<AttributeObject> unfilteredList = getUnfiltered_attributeObjects();

        ArrayList<AttributeObject> output = new ArrayList<>(unfilteredList);

        for (AttributeObject attributeObject : unfilteredList) {
            for (Rule r : getRules()) {
                AttributeIdentifier identifier = attributeObject.hasGivenAttribute(ValueType.valueOf(r.getAttribute()));
                if (!identifier.doesExist()) {
                    output.remove(attributeObject);
                    continue;
                }
                boolean compares = attributeObject.compareTo(r);

                if (!compares) {
                    output.remove(attributeObject);
                }
            }
        }

        setFiltered_attributeObjects(output);
        return this;
    }

    public void printHunter() {
        if (currentHunter == null) {
            System.out.println("There currently is no hunter generated. Generate one with \"generate\"");
            return;
        }
        System.out.println("Your \"Tier " + currentHunter.getTier() + "\" hunter has the following attributes: ");
        System.out.println("Weapon: " + currentHunter.getWeaponSlot1().hasGivenAttribute(ValueType.Name).getAttribute().getData() + (currentHunter.isPrimaryDual() ? "Dual-wield" : "") + (" " + currentHunter.getPrimaryCustomAmmo()));
        System.out.println("Weapon: " + currentHunter.getWeaponSlot2().hasGivenAttribute(ValueType.Name).getAttribute().getData() + (currentHunter.isSecondaryDual() ? "Dual-wield" : "") + (" " + currentHunter.getSecondaryCustomAmmo()));
        for (AttributeObject attributeObject : currentHunter.getFullList()) {
            if (attributeObject == null)
                continue;
            AttributeIdentifier hasName = attributeObject.hasGivenAttribute(ValueType.Name);
            if (!hasName.doesExist())
                continue;
            String[] splitted = attributeObject.getClass().toString().split("\\.");
            if (splitted.length == 0)
                System.out.println(hasName.getAttribute().toString());
            else {
                if (splitted[splitted.length - 1].equalsIgnoreCase("Weapon"))
                    continue;
                System.out.println(splitted[splitted.length - 1] + ": " + hasName.getAttribute().toString());
            }
        }


    }

    public void setFiltered_attributeObjects(ArrayList<AttributeObject> others) {
        filtered_attributeObjects.clear();
        filtered_attributeObjects.addAll(others);
    }

    public void updateHunter(Hunter currentHunter) {
        this.currentHunter = currentHunter;
    }

    public Hunter getCurrentHunter() {
        return currentHunter;
    }

    public String addRule(Rule r) {
        for (Rule rule : rules)
            if (r.toString().equalsIgnoreCase(rule.toString()))
                return "\tThe rule " + r.toString() + " already exists!";


        rules.add(r);
        return "\tThe rule " + r.toString() + " has been added.";
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