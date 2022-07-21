import application.Runnable;

public class Handler {

  public static void main(String[] args) {
    if (args == null || args.length == 0) {
      args = new String[1];
      args[0] = "DataFile";
    }
    System.out.println("Trying to load the file \"" + args[0] + "\"");
    Runnable run = new Runnable();
    run.start(args[0]);
  }

}
