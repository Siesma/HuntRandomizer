import FileHelper.commands.Commands;
import application.Runnable;
import utils.AttributeObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Handler {

    private ArrayList<AttributeObject> attributeObjects = new ArrayList<>();

    public static void main(final String[] args) {
        System.out.println("Trying to load the file \"" + args[0] + "\"");
        Runnable run = new Runnable(args[0]);
    }
}
