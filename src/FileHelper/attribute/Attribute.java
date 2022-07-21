package FileHelper.attribute;

public class Attribute {
  private String lookup, data;

  public Attribute(String lookup, String data) {
    this.lookup = lookup;
    this.data = data;
  }

  public String getLookup() {
    return lookup;
  }

  public Attribute setLookup(String lookup) {
    this.lookup = lookup;
    return this;
  }

  public String getData() {
    return data;
  }

  public Attribute setData(String data) {
    this.data = data;
    return this;
  }

  public String reducedData() {


    return "";
  }

  @Override
  public String toString() {
    String cleanedData = this.data;
    while (cleanedData.endsWith(" ") || cleanedData.endsWith("\t") || cleanedData.endsWith("\n"))
      cleanedData = cleanedData.substring(0, cleanedData.length() - 1);

    while (cleanedData.startsWith(" ") || cleanedData.startsWith("\t") || cleanedData.startsWith("\n"))
      cleanedData = cleanedData.substring(1);


    return this.lookup + "; " + "\"" + cleanedData + "\"";
  }


}
