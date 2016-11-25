package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController extends AbstractCommand {
  public void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      TeacherDao teacherDao = TeacherDao.getInstance();
      if (!teacherDao.existName(paramMap.get("name"))) {
        out.println("입력하신 성함의 강사님 정보를 찾지 못했습니다.");
        return;
      }
      
      
      teacherDao.delete(paramMap.get("name"));
      out.println("해당 데이터를 삭제했습니다.");
  }
  
  @Override
  public String getCommandString() {
    return "teacher/delete";
  }
}
