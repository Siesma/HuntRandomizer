package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;

public class CommandChallenge extends AbstractCommand {


    private ArrayList<String> challenges = new ArrayList<>();

    public CommandChallenge(String... args) {
        super(args);
        challenges.add("Fireman");
        challenges.add("Bomberman");
        challenges.add("Naked");
        challenges.add("Useless");
    }

    @Override
    public void fire(Runnable run, String arguments) {
        String[] type = arguments.split(" ");

        System.out.println("the challenge \"" + challenges.get((int) (Math.random() * challenges.size())) + "\" has been chosen.");


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

    public ArrayList<String> getChallenges() {
        return challenges;
    }

    public CommandChallenge setChallenges(ArrayList<String> challenges) {
        this.challenges = challenges;
        return this;
    }


}


