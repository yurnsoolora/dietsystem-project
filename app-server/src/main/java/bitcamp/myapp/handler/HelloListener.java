package bitcamp.myapp.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    try {
      String name = prompt.inputString("이름은? ");
      prompt.printf("%s 님의 다이어트를 응원해요!\n", name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
