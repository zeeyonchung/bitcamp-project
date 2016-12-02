

package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.java89.ems.server.context.RequestHandlerMapping;


public class EduAppServer {
  ApplicationContext appContext;
  RequestHandlerMapping handlerMapping;
  
  
  public EduAppServer() {
    appContext = new ClassPathXmlApplicationContext(
        new String[] {"bitcamp/java89/ems/server/application-context.xml"});
    
    String[] names = appContext.getBeanDefinitionNames();
    ArrayList<Object> objList = new ArrayList<>();
    for(String name : names) {
      System.out.println(name);
      objList.add(appContext.getBean(name));
    }
    
    handlerMapping = new RequestHandlerMapping(objList);
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
