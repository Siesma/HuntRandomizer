package FileHelper.commands;

import application.Runnable;

public class CommandClear extends AbstractCommand {
  @Override
  public void fire(Runnable run, String arguments, boolean suppress) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  @Override
  public String getHelp(String... type) {
    return "Flushes the console";
  }

  @Override
  public void updateInformationData() {

  }
}
