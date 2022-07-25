import FileHelper.ImportHelper;
import FileHelper.ValueType;
import FileHelper.attribute.Attribute;
import FileHelper.attribute.AttributeIdentifier;
import utils.AttributeObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OtherHelper {

  public static void main(String[] args) throws IOException {

    ArrayList<AttributeObject> obj = (new ImportHelper()).get(null, "DataFile");
    StringBuilder stringBuilder = new StringBuilder();
    for (AttributeObject ato : obj) {
      AttributeIdentifier useType = null;
      if ((useType = ato.hasGivenAttribute(ValueType.Use_Type)) != null) {
//        System.out.print("@" + useType.getAttribute().getData().replace(" ", "").split(",")[0]);
        stringBuilder.append("@" + useType.getAttribute().getData().replace(" ", "").split(",")[0]);
      }
      for (Attribute attribute : ato.getAttributes()) {
//        System.out.println("\t" + attribute.toString().replace(";", ":").replace("\"", ""));
        stringBuilder.append("\t" + attribute.toString().replace(";", ":").replace("\"", "") + "\n");
        if (attribute.getLookup().equalsIgnoreCase("Slot_Size")) {
          stringBuilder.append("\tCapacity: 0" + "\n");
//          System.out.println("\tCapacity: 0");
          stringBuilder.append("\tReserve: 0" + "\n");
//          System.out.println("\tReserve: 0");
        }
      }
    }
    File f = new File("MyNewDataFile");
    f.createNewFile();
    FileWriter writer = new FileWriter("MyNewDataFile");
    writer.write(stringBuilder.toString());
    System.out.println(stringBuilder.toString());
  }
}