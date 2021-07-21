package FileHelper.attribute;

public class AttributeIdentifier {
    private boolean exists;
    private Attribute attribute;

    public AttributeIdentifier(boolean exists, Attribute attribute) {
        this.exists = exists;
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public AttributeIdentifier setAttribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public boolean doesExist() {
        return exists;
    }

    public AttributeIdentifier setExists(boolean exists) {
        this.exists = exists;
        return this;
    }

    @Override
    public String toString () {
        return getAttribute().toString();
    }

}
