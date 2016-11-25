

package bitcamp.java89.ems.server;

import java.io.File;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;


public class EduAppServer {
  HashMap<String, Command> commandMap = new HashMap<>();
  //클라이언트 요청을 처리할 Command 객체들을 보관한다.
  
  public EduAppServer() {
    ArrayList<Class> classList = new ArrayList();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    
    for (Class c : classList) {
      System.out.println(c.getName());
      try {
        AbstractCommand command = (AbstractCommand)c.newInstance();
        commandMap.put(command.getCommandString(), command);
        
      } catch (Exception e) {}
    }
  }
  //contact/add?name=1&position=2&tel=3&email=4
  //teacher/add?name=1&lectureName=2&jobCareer=3&lectureCareer=5&book=4&school=66&appraisal=5&website=7&prize=3
  
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중....");
    
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
  }

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
}
