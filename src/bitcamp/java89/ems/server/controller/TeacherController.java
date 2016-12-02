package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;


@Component
public class TeacherController {
  @ Autowired TeacherDao teacherDao;
  
  @RequestMapping(value="teacher/add")
  public void add(
      @RequestParam("name") String name,
      @RequestParam("lectureName") String lectureName,
      @RequestParam("jobCareer") String jobCareer,
      @RequestParam("lectureCareer") String lectureCareer,
      @RequestParam("book") String book,
      @RequestParam("school") String school,
      @RequestParam("appraisal") String appraisal,
      @RequestParam("website") String website,
      @RequestParam("prize") String prize,
      PrintStream out)  throws Exception {
      if (teacherDao.existName(name)) {
        out.println("입력하신 성함의 강사님의 정보가 이미 존재합니다.");
        return;
      }
  
      Teacher teacher = new Teacher();
      teacher.setName(name);
      teacher.setLectureName(lectureName);
      teacher.setJobCareer(jobCareer);
      teacher.setLectureCareer(lectureCareer);
      teacher.setBook(book);
      teacher.setSchool(school);
      teacher.setAppraisal(appraisal);
      teacher.setWebsite(website);
      teacher.setPrize(prize);
  
  
      teacherDao.insert(teacher);
      out.println("등록하였습니다.");
  }
  
  
  
  @RequestMapping(value="teacher/delete")
  public void delete(@RequestParam("name") String name, PrintStream out) throws Exception {
      if (!teacherDao.existName(name)) {
        out.println("입력하신 성함의 강사님 정보를 찾지 못했습니다.");
        return;
      }
      
      
      teacherDao.delete(name);
      out.println("해당 데이터를 삭제했습니다.");
  }
  
  
  
  @RequestMapping(value="teacher/list")
  public void list(PrintStream out) throws Exception {
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
  
  
  
  @RequestMapping(value="teacher/update")
  public void update(
      @RequestParam("name") String name,
      @RequestParam("lectureName") String lectureName,
      @RequestParam("jobCareer") String jobCareer,
      @RequestParam("lectureCareer") String lectureCareer,
      @RequestParam("book") String book,
      @RequestParam("school") String school,
      @RequestParam("appraisal") String appraisal,
      @RequestParam("website") String website,
      @RequestParam("prize") String prize, 
      PrintStream out) throws Exception {
      if (!teacherDao.existName(name)) {
        out.println("입력하신 성함의 강사님 정보을 찾지 못했습니다.");
        return;
      }

      Teacher teacher = new Teacher();
      teacher.setName(name);
      teacher.setLectureName(lectureName);
      teacher.setJobCareer(jobCareer);
      teacher.setLectureCareer(lectureCareer);
      teacher.setBook(book);
      teacher.setSchool(school);
      teacher.setAppraisal(appraisal);
      teacher.setWebsite(website);
      teacher.setPrize(prize);

      teacherDao.update(teacher);
      out.println("변경 하였습니다.");
  }
  
  
  
  @RequestMapping(value="teacher/view")
  public void view(@RequestParam("name") String name, PrintStream out) throws Exception {
      for (Teacher teacher : teacherDao.getListByName(name)) {
        if (teacher.getName().equals(name)) {
          out.printf("이름: %s\n", teacher.getName());
          out.printf("담당강의: %s\n", teacher.getLectureName());
          out.printf("회사경력: %s\n", teacher.getJobCareer());
          out.printf("강의경력: %s\n", teacher.getLectureCareer());
          out.printf("저서: %s\n", teacher.getBook());
          out.printf("학력: %s\n", teacher.getSchool());
          out.printf("강의평가: %s\n", teacher.getAppraisal());
          out.printf("웹사이트: %s\n", teacher.getWebsite());
          out.printf("수상내역: %s\n", teacher.getPrize());
        }
      }
  }

}
