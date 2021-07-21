package FileHelper.commands;

public class Rule {
    private String attribute, operand;
    private CommandRule.operationTypes operation;

    public Rule(String attribute, CommandRule.operationTypes operation, String operand) {
        this.attribute = attribute;
        this.operand = operand;
        this.operation = operation;
    }

    public String getAttribute() {
        return attribute;
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

    @Override
    public String toString () {
        return this.attribute + " " + this.operation.getData()[0] + " " + this.operand;
    }

}
