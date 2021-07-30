package utils;

import FileHelper.ValueType;
import FileHelper.attribute.Attribute;
import FileHelper.attribute.AttributeIdentifier;
import FileHelper.commands.CommandRule;
import FileHelper.commands.Rule;
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


            attributes.forEach(e -> out.append(e.getLookup()).append(" \"").append(e.getData().replace("\n", "").replace("\t", "")).append("\", "));

            return out.toString();
        } else
            return attributeIdentifier.doesExist() ? attributeIdentifier.toString() : "Generic Weapon with " + this.attributes.size() + " attributes";
    }

    public boolean compareTo(Rule r) {
        Attribute ruleAttrib = r.getAttribute(this);
        if (ruleAttrib == null)
            return false;
        CommandRule.operationTypes ruleOperation = r.getOperation();
        String ruleOperand = r.getOperand();
        String compareValue = ruleAttrib.getData();

        String ruleApplicableType = r.getApplicableType();
        String[] splittedClassString = this.getClass().toString().split("\\.");

        if (!(splittedClassString[splittedClassString.length - 1].equalsIgnoreCase(ruleApplicableType))  && !ruleApplicableType.equalsIgnoreCase("all")) {
            return true;
        }

        switch (ValueType.valueOf(ruleAttrib.getLookup()).getRuleType()) {
            case String:
                switch (ruleOperation) {
                    case Contains:
                        return compareValue.contains(ruleOperand);
                    case Equal:
                        return compareValue.equalsIgnoreCase(ruleOperand);
                    case Unequal:
                        return !compareValue.equalsIgnoreCase(ruleOperand);
                    default:
                        return false;
                }
            case Set:
                switch (ruleOperation) {
                    case Contains:
                        return compareValue.contains(ruleOperand);
                    default:
                        return false;
                }
            case Integer:
                switch (ruleOperation) {
                    case Equal:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) == Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    case Unequal:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) != Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    case Greater:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) > Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    case Lower:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) < Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    case GreaterNEqual:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) >= Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    case LowerNEqual:
                        return Integer.parseInt(compareValue.replaceAll("[^0-9]", "")) <= Integer.parseInt(ruleOperand.replaceAll("[^0-9]", ""));
                    default:
                        return false;
                }
            case Boolean:
                switch (ruleOperation) {
                    case Equal:
                        return Boolean.parseBoolean(compareValue) == Boolean.parseBoolean(ruleOperand);
                    case Unequal:
                        return Boolean.parseBoolean(compareValue) != Boolean.parseBoolean(ruleOperand);
                    default:
                        return false;
                }
        }
        return false;
    }


}
