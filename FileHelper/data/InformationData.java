package FileHelper.data;

import FileHelper.commands.Rule;

public class InformationData {
    private String information, data;
    private InformationData other;
    public InformationData (String information, String data) {
        this.information = information;
        this.data = data;
    }

    public InformationData (String information, InformationData other) {
        this.information = information;
        this.data = other.toString();
        this.other = other;
    }

    public String getInformation() {
        return information;
    }

    public InformationData setInformation(String information) {
        this.information = information;
        return this;
    }

    public String getData() {
        return data;
    }

    public InformationData setData(String data) {
        this.data = data;
        return this;
    }

    public InformationData getOther() {
        return other;
    }
}

