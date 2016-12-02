

package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import bitcamp.java89.ems.server.context.ApplicationContext;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;


public class EduAppServer {
  ApplicationContext appContext;
  RequestHandlerMapping handlerMapping;
  
  
  public EduAppServer() {
    //ApplicationContext가 만들지 못하는 객체를 여기에서 미리 생성하여
    //ApplicationContext에게 전달한다.
    HashMap<String, Object> builtInObjMap = new HashMap<>();
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", "java89", "1111");
      builtInObjMap.put("dbcon", con);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    appContext = new ApplicationContext(new String[] {
        "bitcamp.java89.ems.server.controller", "bitcamp.java89.ems.server.dao"}, builtInObjMap);
    
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
