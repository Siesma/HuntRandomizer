package FileHelper.commands;

import FileHelper.data.InformationData;
import application.Runnable;

import java.util.ArrayList;

public class CommandChallenge extends AbstractCommand {


    private ArrayList<InformationData> challenges = new ArrayList<>();

    public CommandChallenge(String... args) {
        super(args);
        challenges.add(new InformationData("Fireman", new InformationData("Use_Type ~ Fire", "every generated object has to have a fire attribute.")));
        challenges.add(new InformationData("Bomberman", new InformationData("Use_Type ~ Explosive", "every generated object has to have an explosive attribute.")));
        challenges.add(new InformationData("Naked", new InformationData("Name = None", "nothing will be generated as no object exists that has a name of none.")));
        challenges.add(new InformationData("Useless", new InformationData("Tier < 2", "only objects will be chosen that have the lowest tier.")));
        challenges.add(new InformationData("Spam_Spam", new InformationData("ROF >= 100 Weapon", "every generated object has to have an ROF of at least 100")));
        challenges.add(new InformationData("Budget", new InformationData("Cost < 100", "every generated object has to not cost more than 100$")));
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");
        InformationData chosenChallenge = challenges.get((int) (Math.random() * challenges.size()));
        System.out.println("the challenge \"" + chosenChallenge.getInformation() + "\" has been chosen.");
        System.out.println("This means that the following rule has been applied: \"" + chosenChallenge.getOther().getInformation() + "\", this means that " + chosenChallenge.getOther().getData());
        CommandRule commandRule = (CommandRule) Commands.rule.getCommand();
        commandRule.fire(run, " " + chosenChallenge.getOther().getInformation());
//        generate();

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

    public ArrayList<InformationData> getChallenges() {
        return challenges;
    }

    public CommandChallenge setChallenges(ArrayList<InformationData> challenges) {
        this.challenges = challenges;
        return this;
    }


}


