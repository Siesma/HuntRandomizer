package FileHelper.commands;

import FileHelper.ValueType;
import FileHelper.attribute.AttributeIdentifier;
import application.Runnable;
import utils.AttributeObject;

import java.util.ArrayList;

public class CommandInfo extends AbstractCommand {
    public CommandInfo(String... args) {
        super(args);
    }

    @Override
    public void updateInformationData() {
        ArrayList list = new ArrayList();
        setInformationLists(list);
    }

    @Override
    public void setInformationLists(ArrayList list) {
        super.setInformationLists(list);
    }
    @Override
    public void fire(Runnable run, String arguments, boolean suppress) {
        String[] type = arguments.split(" ");
        this.run = run;
        if (type.length == 1 || type[1].equalsIgnoreCase("-all")) {
            run.getFiltered_attributeObjects().forEach(e -> {
                System.out.println("\t" + e);
            });
            return;
        }
        if (run.getFiltered_attributeObjects().isEmpty()) {
            run.printInformation("There were no applicable objects found");
        }
        int count = 0;
        for (AttributeObject attributeObject : run.getFiltered_attributeObjects()) {
            for (ValueType v : ValueType.values()) {
                if (attributeObject.hasGivenAttribute(v).doesExist()) {
                    AttributeIdentifier identifier = attributeObject.hasGivenAttribute(v);
                    if ((identifier.getAttribute().getLookup().contains(type[1]) || identifier.getAttribute().getData().contains(type[1])) && attributeObject.getClass().toString().endsWith(type[2])) {
                        AttributeIdentifier name = attributeObject.hasGivenAttribute(ValueType.Name);

                        count++;
                        if (!name.doesExist()) {
                            System.out.println("Generic object with " + attributeObject.getAttributes().size() + " attributes.");
                            continue;
                        }
                        System.out.println("\t\"" + name.getAttribute().getData() + "\":\t" + identifier.getAttribute().getData());
                    }

                }
            }
        }
        if (count == 0) {
            run.printInformation("There were no applicable objects found");
        }

    }

    @Override
    public String getHelp(String... type) {
        StringBuilder out = new StringBuilder();
        out.append("---\n");
        out.append("\tUsage: \n");
        out.append("\t\"info\" prints information according to the current arguments.\n");
        out.append("\t\"info\" requires two arguments. An attribute and an object (Weapon, Tool, etc)\n");
        out.append("\tExample: \"info Name Knife\" will print whether the object \"Knife\" has the \"Name\" attribute or not.\n");
        out.append("---\n");
        return out.toString();
    }
}
