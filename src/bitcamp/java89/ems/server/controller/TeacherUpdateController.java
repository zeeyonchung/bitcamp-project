package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component(value="teacher/update")
public class TeacherUpdateController extends AbstractCommand {
  TeacherDao teacherDao;
  
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  
  public void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      if (!teacherDao.existName(paramMap.get("name"))) {
        out.println("입력하신 성함의 강사님 정보을 찾지 못했습니다.");
        return;
      }

      Teacher teacher = new Teacher();
      teacher.setName(paramMap.get("name"));
      teacher.setLectureName(paramMap.get("lectureName"));
      teacher.setJobCareer(paramMap.get("jobCareer"));
      teacher.setLectureCareer(paramMap.get("lectureCareer"));
      teacher.setBook(paramMap.get("book"));
      teacher.setSchool(paramMap.get("school"));
      teacher.setAppraisal(paramMap.get("appraisal"));
      teacher.setWebsite(paramMap.get("website"));
      teacher.setPrize(paramMap.get("prize"));

      teacherDao.update(teacher);
      out.println("변경 하였습니다.");
  }

}
