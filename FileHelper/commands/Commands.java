package FileHelper.commands;

import FileHelper.data.InformationData;

public enum Commands {
    help(new CommandHelp(),
            new InformationData("\"help\"", "Gives help"),
            new InformationData("\t\"-get\"", "Returns all possible subset values of the following selection. Those can either be commands themself, \"attribute\"(Name, Cost, etc.) or \"object type\"(Weapon, Tool, etc)."),
            new InformationData("\t\"-count\"", "Returns the amount of subset values of the following selection. this can either be commands themself, \"attribute\"(Name, Cost, etc.) or \"object type\"(Weapon, Tool, etc).")
    ),
    generate(new CommandGenerate(),
            new InformationData("\"generate\"", "Generates the next hunter.")
    ),
    rule(new CommandRule(),
            new InformationData("\"rule\"", "Defines a new rule on which the upcoming loadout is defined.")
    ),
    exit(new CommandExit(),
            new InformationData("\"exit\"", "Exits the program.")
    ),
    won(new CommandWon(),
            new InformationData("\"won\"", "Tells the generator that the last match was won and should now provide next perks.")
    ),
    loss(new CommandLoss(),
            new InformationData("\"loss\"", "Tells the generator that the last match was lost and shall generate a new fresh hunter.")
    ),
    info(new CommandInfo(),
            new InformationData("\"info\"", "Returns whether an object has a specific attribute or not.")
    ),
    challenge(new CommandChallenge(),
            new InformationData("\"challenge\"", "Generates a new challenge run. Those have a pre determined set of rules that cannot be altered.")
    ),
    hunter(new CommandHunter(), new InformationData("\"hunter\"", "reports information about the current hunter."));

    InformationData[] s;
    AbstractCommand command;

    Commands(AbstractCommand command, InformationData... s) {
        this.s = s;
        this.command = command;
    }

    public InformationData[] getData() {
        return s;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
