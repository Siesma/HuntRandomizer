package FileHelper.commands;

import FileHelper.ValueType;
import FileHelper.attribute.AttributeIdentifier;
import application.Runnable;
import utils.AttributeObject;
import utils.Perk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommandWon extends AbstractCommand {

    public CommandWon(String... args) {
        super(args);
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        this.run = run;
        if (type.length == 1) {
            System.err.println("No amount of perk points provided.");
            return;
        }
        int amountOfPoints = Integer.parseInt(type[1].replaceAll("[^0-9]", ""));
        ArrayList<Perk> unfilteredPerkList = new ArrayList<>();
        ArrayList<Perk> perkList = new ArrayList<>();


        ArrayList<Perk> currentPerks;

        if (run.getCurrentHunter().getPerkArray() == null) {
            currentPerks = new ArrayList<>();
        } else {
            currentPerks = new ArrayList<>(Arrays.asList(run.getCurrentHunter().getPerkArray()));
        }

        for (AttributeObject attributeObject : run.getFiltered_attributeObjects()) {
            if (attributeObject.getClass().toString().endsWith("Perk")) {
                perkList.add((Perk) attributeObject);
            }
        }
        for (AttributeObject attributeObject : run.getUnfiltered_attributeObjects()) {
            if (attributeObject.getClass().toString().endsWith("Perk")) {
                unfilteredPerkList.add((Perk) attributeObject);
            }
        }

        Scanner sc = new Scanner(System.in);
        if (run.getCurrentHunter().getPerkArray() != null && run.getCurrentHunter().getPerkArray().length != 0) {
            ArrayList<Perk> helperPerkList = new ArrayList<>();
            for(Perk perk : unfilteredPerkList) {
                for(Perk other : run.getCurrentHunter().getPerkArray()) {
                    AttributeIdentifier perkIdentifier = perk.hasGivenAttribute(ValueType.Name);
                    AttributeIdentifier otherIdentifier = other.hasGivenAttribute(ValueType.Name);
                    if(!(perkIdentifier.doesExist() || otherIdentifier.doesExist())) {
                        continue;
                    }
                    if(helperPerkList.contains(perk))
                        continue;

                    if(!perkIdentifier.getAttribute().getData().equalsIgnoreCase(otherIdentifier.getAttribute().getData())) {
                        helperPerkList.add(perk);
                    }
                }
            }

            unfilteredPerkList.clear();
            unfilteredPerkList.addAll(helperPerkList);
        }
//            unfilteredPerkList.removeAll(Arrays.asList(run.getCurrentHunter().getPerkArray()));
        System.out.println("Please provide a list of all the attributes the hunter currently has.");
        for (int i = 0; i < unfilteredPerkList.size(); i++) {
            AttributeIdentifier identifier = unfilteredPerkList.get(i).hasGivenAttribute(ValueType.Name);
            if (!identifier.doesExist())
                continue;
            System.out.println("\t\"" + i + "\" -> " + identifier.getAttribute().getData());
        }
        System.out.println("---");
        System.out.println("When the list has been filled fully type \" \", otherwise type the \"id\" of the given perk.");
        System.out.println("---");
        String cur = "";
        while (!(cur = sc.nextLine()).equalsIgnoreCase(" ")) {
            currentPerks.add(unfilteredPerkList.get(Integer.parseInt(cur)));
            System.out.println("The perk \"" + unfilteredPerkList.get(Integer.parseInt(cur)).hasGivenAttribute(ValueType.Name).getAttribute().getData() + "\" has been added!");
        }

        perkList.removeAll(currentPerks);

        while (amountOfPoints > 0) {
            ArrayList<Perk> leftList = new ArrayList<>();
            for (Perk p : perkList) {
                AttributeIdentifier identifier = p.hasGivenAttribute(ValueType.Cost);
                if (!identifier.doesExist())
                    continue;
                int pCost = Integer.parseInt(identifier.getAttribute().getData().replaceAll("[^0-9]", ""));
                if (pCost <= amountOfPoints) {
                    leftList.add(p);
                }
            }
            perkList.clear();
            perkList.addAll(leftList);
            Perk randomPerk = perkList.get((int) (Math.random() * perkList.size()));
//            for (Perk p : perkList) {
//                AttributeIdentifier pName = p.hasGivenAttribute(ValueType.Name);
//                AttributeIdentifier rName = randomPerk.hasGivenAttribute(ValueType.Name);
//                if (!(pName.doesExist() || rName.doesExist())) continue;
//                if(pName.getAttribute().getData().equalsIgnoreCase(rName.getAttribute().getData())) {
//                    perkList.remove(p);
//                    break;
//                }
//
//            }
            perkList.remove(randomPerk);

            AttributeIdentifier identifier = randomPerk.hasGivenAttribute(ValueType.Cost);
            if (!identifier.doesExist())
                continue;
            int randomPerkCost = Integer.parseInt(identifier.getAttribute().getData().replaceAll("[^0-9]", ""));
            currentPerks.add(randomPerk);
            amountOfPoints -= randomPerkCost;
        }
        run.getCurrentHunter().addPerks(currentPerks);
        System.out.println("---");
        System.out.println("All of the perks that have to be added:");
        for (AttributeObject attributeObject : currentPerks) {
            if (attributeObject == null)
                continue;
            if (!attributeObject.getClass().toString().endsWith("Perk")) {
                continue;
            }
            AttributeIdentifier identifier = attributeObject.hasGivenAttribute(ValueType.Name);
            if (!identifier.doesExist()) {
                continue;
            }
            System.out.println("\t\"" + identifier.getAttribute().getData() + "\"");
        }
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
