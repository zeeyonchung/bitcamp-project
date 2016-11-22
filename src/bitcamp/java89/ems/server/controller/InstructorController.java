
package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Contact;
import bitcamp.java89.ems.server.vo.Instructor;

public class InstructorController {
  private ArrayList<Instructor> list;
  private String filename = "instructor-v1.6.data";
  private boolean changed;
  private Scanner in;
  private PrintStream out;


  public InstructorController(Scanner in, PrintStream out) {
    list = new ArrayList<Instructor>();
    this.in = in;
    this.out = out;
    this.load();
  }


  public boolean service() {
      while (true) {
        out.println("강사관리> ");
        out.println();

        String[] commands = in.nextLine().split("\\?");

        try {
          switch (commands[0]) {
          case "add" : this.doAdd(commands[1]); break;
          case "list" : this.doList(); break;
          case "view" : this.doView(commands[1]); break;
          case "delete" : this.doDelete(commands[1]); break;
          case "update" : this.doUpdate(commands[1]); break;
          case "main" : return true;
          case "quit" : return false;
          default:
            out.println("지원하지 않는 명령어입니다.");
          }//switch
        } catch (IndexOutOfBoundsException e) {
          out.println("인덱스가 유효하지 않습니다.");
        } catch (Exception e) {
          out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
        }
      }//while
  }


  public boolean isChanged() {
    return changed;
  }


  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Instructor>)in.readObject();

    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("강사 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }




  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);


    out.writeObject(list);

    changed = false;

    out0.close();
    out.close();
  }


  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    } 

    // add?name=1&lectureName=1&jobCareer=1&lectureCareer=4&book=2&school=3&appraisal=3&webSite=5&prize=2
    Instructor instr = new Instructor();
    instr.setName(paramMap.get("name"));
    instr.setLectureName(paramMap.get("lectureName"));
    instr.setJobCareer(paramMap.get("jobCareer"));
    instr.setLectureCareer(paramMap.get("lectureCareer"));
    instr.setBook(paramMap.get("book"));
    instr.setSchool(paramMap.get("school"));
    instr.setAppraisal(paramMap.get("appraisal"));
    instr.setWebSite(paramMap.get("webSite"));
    instr.setPrize(paramMap.get("prize"));


    if (existName(instr.getName())) {
      out.println("입력하신 성함의 강사님의 정보가 이미 존재합니다.");
      return;
    }

    list.add(instr);
    changed = true;
    out.println("등록하였습니다.");

  }


  private boolean existName(String name) {
    for (Instructor instr : list) {
      if (instr.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }


  private void doList() {
    for (Instructor instr : list) {
      out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
          instr.getName(),
          instr.getLectureName(),
          instr.getJobCareer(),
          instr.getLectureCareer(),
          instr.getBook(),
          instr.getSchool(),
          instr.getAppraisal(),
          instr.getWebSite(),
          instr.getPrize());
    }
  }


  private void doView(String param) {
    String[] name = param.split("=");
    for (Instructor instr : list) {
      if (instr.getName().equals(name[1])) {
        out.printf("이름: %s\n", instr.getName());
        out.printf("담당강의: %s\n", instr.getLectureName());
        out.printf("회사경력: %s\n", instr.getJobCareer());
        out.printf("강의경력: %s\n", instr.getLectureCareer());
        out.printf("저서: %s\n", instr.getBook());
        out.printf("학력: %s\n", instr.getSchool());
        out.printf("강의평가: %s\n", instr.getAppraisal());
        out.printf("웹사이트: %s\n", instr.getWebSite());
        out.printf("수상내역: %s\n", instr.getPrize());
      }
    }
  }


  private void doDelete(String param) {
    String[] name = param.split("=");

    ArrayList<Instructor> deleteList = new ArrayList<>();

    for (Instructor instr : list) {
      if (instr.getName().equals(name[1])) {
        deleteList.add(instr);
      }
    }

    for (Instructor instr : deleteList) {
      list.remove(instr);
    }

    changed = true;
    out.println("삭제하였습니다.");
  }




  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    for (Instructor  instr: list) {
      if (instr.getName().equals(paramMap.get("name"))) {
        instr.setName(paramMap.get("name"));
        instr.setLectureName(paramMap.get("lectureName"));
        instr.setJobCareer(paramMap.get("jobCareer"));
        instr.setLectureCareer(paramMap.get("lectureCareer"));
        instr.setBook(paramMap.get("book"));
        instr.setSchool(paramMap.get("school"));
        instr.setAppraisal(paramMap.get("appraisal"));
        instr.setWebSite(paramMap.get("webSite"));
        instr.setPrize(paramMap.get("prize"));
        out.println("변경 하였습니다.");
        changed = true;
        return;
      } 
    }
    out.println("이메일을 찾지 못했습니다.");
  }
}
