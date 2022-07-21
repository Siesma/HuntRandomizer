package FileHelper.commands;

import FileHelper.attribute.Attribute;
import FileHelper.data.RuleType;
import utils.AttributeObject;

public class Rule {
    private String attribute, operand, applicableType;
    private CommandRule.operationTypes operation;

//    public Rule(String attribute, CommandRule.operationTypes operation, String operand) {
//        this.attribute = attribute;
//        this.operand = operand;
//        this.operation = operation;
//
//    }

    public Rule(String attribute, CommandRule.operationTypes operation, String operand, String applicableType) {
        this.attribute = attribute;
        this.operand = operand;
        this.operation = operation;
        this.applicableType = applicableType;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getApplicableType () {
        return applicableType;
    }


    public Rule setAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public CommandRule.operationTypes getOperation() {
        return operation;
    }

    public Rule setOperation(CommandRule.operationTypes operation) {
        this.operation = operation;
        return this;
    }

    public String getOperand() {
        return operand;
    }

    public Rule setOperand(String operand) {
        this.operand = operand;
        return this;
    }


    public Attribute getAttribute(AttributeObject reference) {
        for(Attribute atb : reference.getAttributes()) {
            if(attribute.equalsIgnoreCase(atb.getLookup())) {
                return atb;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return this.attribute + " " + this.operation.getData()[0] + " " + this.operand;
    }

}
