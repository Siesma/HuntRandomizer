package FileHelper.commands;

import application.Runnable;

import java.util.ArrayList;

public class CommandHunter extends AbstractCommand {

  @Override
  public void updateInformationData() {
    ArrayList list = new ArrayList();
    setInformationLists(list);
  }

  @Override
  public void setInformationLists(ArrayList list) {
    super.setInformationLists(list);
  }
  @Override
  public void fire(Runnable run, String arguments, boolean suppress) {
    run.printHunter();
  }

  @Override
  public String getHelp(String... type) {
    return null;
  }
}
