package FileHelper.data;

public class InformationData {
    private String information, data;
    public InformationData (String information, String data) {
        this.information = information;
        this.data = data;
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
}

