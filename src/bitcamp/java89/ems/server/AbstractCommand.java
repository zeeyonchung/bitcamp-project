package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public abstract class AbstractCommand implements Command {
  @Override
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      doResponse(paramMap, out);
      
    } catch (Exception e) {
      out.println("작업 중 오류가 발생했습니다.");
    }
  }

  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    System.out.println("작업을 중비 중입니다.");
  }
  
}
