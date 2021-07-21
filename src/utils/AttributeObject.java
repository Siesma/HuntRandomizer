package utils;

import FileHelper.ValueType;
import FileHelper.attribute.Attribute;
import FileHelper.attribute.AttributeIdentifier;
import FileHelper.commands.CommandRule;
import FileHelper.data.InformationData;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AttributeObject {

    protected ArrayList<Attribute> attributes;

    public AttributeObject(Attribute... attributes) {
        this.attributes = new ArrayList<Attribute>(Arrays.asList(attributes));
    }

    public AttributeIdentifier hasGivenAttribute(ValueType valueType) {
        for (Attribute attribute : attributes)
            for (InformationData ida : valueType.getIdentifier()) {
                String s = ida.getInformation();
                if (attribute.getLookup().equalsIgnoreCase(s))
                    return new AttributeIdentifier(true, attribute);
            }
        return new AttributeIdentifier(false, null);
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    @Override
    public String toString() {
        AttributeIdentifier attributeIdentifier = this.hasGivenAttribute(ValueType.Name);
        if (attributeIdentifier.doesExist()) {
            StringBuilder out = new StringBuilder();


            attributes.forEach(e -> out.append(e.getLookup()).append(" ").append(e.getData().replace("\n", "").replace("\t", "")));

            return out.toString();
        } else
            return attributeIdentifier.doesExist() ? attributeIdentifier.toString() : "Generic Weapon with " + this.attributes.size() + " attributes";
    }

    public boolean compareTo(Attribute attribute, CommandRule.operationTypes operationType, String value) {
        String kindOf = "";
        String regexForInteger = "([0-9])+.*";
        String regexForString = "([a-z]|[A-Z])+";
        String regexForSet = "(([a-z]|[A-Z])+(,|))+";

        if (attribute.getData().matches(regexForInteger)) {
            kindOf = "Integer";
        } else if (attribute.getData().matches(regexForString)) {
            kindOf = "String";
        } else if (attribute.getData().matches(regexForSet)) {
            kindOf = "Set";
        }

        switch (kindOf) {
            case "Integer":
                String subset = attribute.getData().replaceAll("[^0-9.]", "");
                int subsetValue = Integer.parseInt(subset);
                return subsetValue > Integer.parseInt(value);
            case "String":
                if(!(operationType == CommandRule.operationTypes.Equal || operationType == CommandRule.operationTypes.Unequal)) {
                    return false;
                }
                if(operationType == CommandRule.operationTypes.Equal) {
                    return value.equalsIgnoreCase(attribute.getData());
                } else {
                    return !value.equalsIgnoreCase(attribute.getData());
                }

            case "Set":
                if (operationType != CommandRule.operationTypes.Equal)
                    return false;
                for(String s : attribute.getData().split(",")) {
                    if(s.equalsIgnoreCase(value)) {
                        attribute.setData(value);
                        return true;
                    }
                }
                break;
            default:
                System.err.println("Some unexpected error happened");
                break;
        }


        return false;
    }
}
