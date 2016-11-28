

package bitcamp.java89.ems.server;

import java.io.File;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.dao.TeacherDao;


public class EduAppServer {
  HashMap<String, Command> commandMap = new HashMap<>();
  
  public EduAppServer() {
    //Controller가 사용할 DAO 객체 준비
    ContactDao contactDao = new ContactDao();
    contactDao.setFilename("contact-v1.9.data");
    try {
      contactDao.load();
    } catch (Exception e) {
      System.out.println("연락처 로딩 중 오류 발생!");
    }
    
    TeacherDao teacherDao = new TeacherDao();
    teacherDao.setFilename("teacher-v1.9.data");
    try {
      teacherDao.load();
    } catch (Exception e) {
      System.out.println("강사 정보 로딩 중 오류 발생!");
    }
  
    
    ArrayList<Class> classList = new ArrayList();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    
    for (Class c : classList) {
      //System.out.println(c.getName());
      try {
        AbstractCommand command = (AbstractCommand)c.newInstance();
        
        //commandMap에 저장하기 전에 각 Controller에 대해 DAO를 주입한다.
        try {
          Method m = command.getClass().getMethod("setContactDao", ContactDao.class);
          //System.out.printf("%s:%s\n", command.getClass().getName(), m.getName());
          m.invoke(command, contactDao);
        } catch (Exception e) {}
       
        
        try {
          Method m = command.getClass().getMethod("setTeacherDao", TeacherDao.class);
          //System.out.printf("%s:%s\n", command.getClass().getName(), m.getName());
          m.invoke(command, teacherDao);
        } catch (Exception e) {}
        
        
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
