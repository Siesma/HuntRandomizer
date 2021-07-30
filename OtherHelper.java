import application.Runnable;
import utils.AttributeObject;

public class OtherHelper {

    public static void main(String[] args) {


        Runnable runnable = new Runnable("TestingGround");

        AttributeObject attributeObject = runnable.getUnfiltered_attributeObjects().get(1);

        System.out.println(attributeObject.compareTo(runnable.getRules().get(0)));


    }
}