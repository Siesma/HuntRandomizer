package FileHelper;

import FileHelper.attribute.Attribute;
import FileHelper.data.InformationData;
import application.Runnable;
import utils.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportHelper {

    public ArrayList<AttributeObject> get(Runnable run, String filePath) {
        String DIRECTORY = System.getProperty("user.dir");
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DIRECTORY + "/" + filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException fnfe) {
            fnfe.printStackTrace();
        }
        String[] argumentTypes;
        if (run == null) {
            argumentTypes = new String[]{
                    "Weapon", "Tool", "Consumable", "Perk"
            };
        } else {
            argumentTypes = run.getObjectIdentifier();
        }

        ArrayList<AttributeObject> objects = new ArrayList<>();
        for (String s : stringBuilder.toString().split("@")) {
            for (String identifier : argumentTypes) {
                if (!s.startsWith(identifier))
                    continue;
                AttributeObject obj = null;
                if (identifier.equalsIgnoreCase("Weapon")) {
                    obj = new Weapon();
                } else if (identifier.equalsIgnoreCase("Tool")) {
                    obj = new Tool();
                } else if (identifier.equalsIgnoreCase("Consumable")) {
                    obj = new Consumable();
                } else if (identifier.equalsIgnoreCase("Perk")) {
                    obj = new Perk();
                }
                if (obj == null)
                    return null;
                s = s.substring(identifier.length());


                for (int i = 0; i < ValueType.values().length; i++) {
                    ValueType upcomingAttributeTypeData = ValueType.values()[i];
                    for (InformationData id : upcomingAttributeTypeData.getIdentifier()) {
                        String upcomingPossibleDataIdentifier = id.getInformation();
                        if (s.contains(upcomingPossibleDataIdentifier)) {
                            String copy = s;
                            copy = copy.substring(copy.indexOf(upcomingPossibleDataIdentifier));
                            for (int j = 0; j < ValueType.values().length; j++) {
                                ValueType reducingTypeIdentifier = ValueType.values()[j];
                                if (reducingTypeIdentifier == upcomingAttributeTypeData)
                                    continue;
                                for (InformationData ida : reducingTypeIdentifier.getIdentifier()) {
                                    String reducingDataIdentifier = ida.getInformation();
                                    if (copy.contains(reducingDataIdentifier)) {
                                        copy = copy.substring(0, copy.indexOf(reducingDataIdentifier));
                                    }
                                }
                            }
                            Attribute attribute;
                            try {
                                attribute = new Attribute(upcomingPossibleDataIdentifier, copy.substring(upcomingPossibleDataIdentifier.length() + 1));
                            } catch (Exception e) {
                                continue;
                            }
                            obj.addAttribute(attribute);
                        }
                    }
                }
                objects.add(obj);
            }
        }
        return objects;


    }


}
