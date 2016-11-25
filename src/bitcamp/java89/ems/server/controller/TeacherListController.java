package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherListController extends AbstractCommand {
  public void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      TeacherDao teacherDao = TeacherDao.getInstance();
      for (Teacher teacher : teacherDao.getList()) {
        out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
            teacher.getName(),
            teacher.getLectureName(),
            teacher.getJobCareer(),
            teacher.getLectureCareer(),
            teacher.getBook(),
            teacher.getSchool(),
            teacher.getAppraisal(),
            teacher.getWebsite(),
            teacher.getPrize());
      } 
  }

  @Override
  public String getCommandString() {
    return "teacher/list";
  }
}
