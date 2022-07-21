package FileHelper.commands;

import FileHelper.data.ChallengeData;
import application.Runnable;

import java.util.ArrayList;

public class CommandChallenge extends AbstractCommand {


    @Override
    public void updateInformationData() {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        ArrayList<String> information = new ArrayList<>();
        if (challenges == null)
            return;

        for (ChallengeData c : challenges) {
            information.add(c.toString());
        }
        list.add(information);
        setInformationLists(list);
    }

    @Override
    public void setInformationLists(ArrayList list) {
        super.setInformationLists(list);
    }

    private ArrayList<ChallengeData> challenges = new ArrayList<>();

    public CommandChallenge(String... args) {
        super(args);
        challenges.add(new ChallengeData("Shotgun Fireman", "Every generated object has to have a fire attribute.", "Use_Type ~ Fire Consumable", "Custom_Ammo ~ Dragonbreath Weapon"));
        challenges.add(new ChallengeData("Rifle Fireman", "Every generated object has to have a fire attribute.", "Use_Type ~ Fire Consumable", "Custom_Ammo ~ Incendiary Weapon"));
        challenges.add(new ChallengeData("Bomberman", "Every generated object has to have an explosive attribute.", "Use_Type ~ Explosive Consumable", "Custom_Ammo ~ Explosive Weapon"));
        challenges.add(new ChallengeData("Naked", "You will start bare handed.", "Name = None"));
        challenges.add(new ChallengeData("Useless", "Only low grade items will be chosen.", "Tier < 2"));
        challenges.add(new ChallengeData("Spam_Spam", "Only weapons will be generated that have an ROF of at least 100.", "ROF >= 100 Weapon"));
        challenges.add(new ChallengeData("Budget", "Only low budget items will be selected.", "Cost < 100"));
        challenges.add(new ChallengeData("Top", "Only high grade items will be chosen.", "Tier > 2"));
        challenges.add(new ChallengeData("Loud", "You need some more chaos bombs!", "Name ~ Chaos Consumable"));

    }

    @Override
    public void fire(Runnable run, String arguments, boolean suppress) {
        ChallengeData chosenChallenge = challenges.get((int) (Math.random() * challenges.size()));

        System.out.println("the challenge \"" + chosenChallenge.getIdentifier() + "\" has been chosen.");
        System.out.println("This will imply the following: \"" + chosenChallenge.getExplanation() + "\"");
        CommandRule commandRule = (CommandRule) Commands.rule.getCommand();
        System.out.println("---");
        for (String s : chosenChallenge.getRules()) {
            System.out.println("\tThe following rule will be applied: \"" + s + "\"");
            commandRule.fire(run, " " + s, true);
        }
        System.out.println("---");

    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"challenge\" generates a new loadout based off of the possible challenges. They can be inspected using \"help -get challenge\"\n");
        out.append("---\n");
        return out.toString();
    }

    public ArrayList<ChallengeData> getChallenges() {
        return challenges;
    }

    public CommandChallenge setChallenges(ArrayList<ChallengeData> challenges) {
        this.challenges = challenges;
        return this;
    }


}


