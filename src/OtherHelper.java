import FileHelper.ImportHelper;
import FileHelper.ValueType;
import FileHelper.attribute.Attribute;
import FileHelper.commands.CommandHelp;
import utils.AttributeObject;

import java.util.ArrayList;

public class OtherHelper {

    public static void main(String[] args) {
        for(Attribute attribute : (new ImportHelper()).get(null, "DataFile").get(0).getAttributes()) {
            System.out.println(attribute.toString());
        }
    }
}