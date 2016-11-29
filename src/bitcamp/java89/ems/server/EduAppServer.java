

package bitcamp.java89.ems.server;

import java.net.ServerSocket;

import bitcamp.java89.ems.server.context.ApplicationContext;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;


public class EduAppServer {
  ApplicationContext appContext;
  RequestHandlerMapping handlerMapping;
  
  
  public EduAppServer() {
    appContext = new ApplicationContext(new String[] {
        "bitcamp.java89.ems.server.controller", "bitcamp.java89.ems.server.dao"});
    
    handlerMapping = new RequestHandlerMapping(appContext.getAllBeans());
  }
  
  //contact/add?name=1&position=2&tel=3&email=4
  //teacher/add?name=1&lectureName=2&jobCareer=3&lectureCareer=5&book=4&school=66&appraisal=5&website=7&prize=3
  
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중....");
    
    while (true) {
      new RequestThread(ss.accept(), handlerMapping).start();
    }
  }

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
}
