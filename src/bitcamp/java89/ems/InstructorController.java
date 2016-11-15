//v1.3
//파일 불러오기, 저장하기

package bitcamp.java89.ems;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.lang.Exception;
import java.io.File;

public class InstructorController {
  private Scanner keyScan;
  ArrayList<Instructor> list = new ArrayList<Instructor>();
  static String filePath = "./Test2.data";
  static boolean change = false;


  public InstructorController(Scanner keyScan) {
    this.keyScan = keyScan;
  }


  public void service() {
    loop:
    while (true) {
      System.out.println("add:등록 / list:전체조회 / view:강사조회 / delete:삭제 / update:수정 / main:메인");
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();

      try {
        switch (command) {
        case "add" : this.doAdd(); break;
        case "list" : this.doList(); break;
        case "view" : this.doView(); break;
        case "delete" : this.doDelete(); break;
        case "update" : this.doUpdate(); break;
        case "main" :
          break loop;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
        }//switch
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
          System.out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
      }
    }//while
  }


  public boolean isChanged() {
    return change;
  }


  public void open() {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (Exception e) {
      System.out.println("파일이 정상적으로 로딩되지 않음");
    }

    FileInputStream in0 = null;
    DataInputStream in2 = null;

    try {
      in0 = new FileInputStream(filePath);
      in2 = new DataInputStream(in0);

     while (in0.available() > 0) {
        Instructor instr = new Instructor();
        instr.name = in2.readUTF();
        instr.lectureName = in2.readUTF();
        instr.jobCareer = in2.readUTF();
        instr.lectureCareer = in2.readUTF();
        instr.book = in2.readUTF();
        instr.school = in2.readUTF();
        instr.appraisal = in2.readUTF();
        instr.webSite = in2.readUTF();
        instr.prize = in2.readUTF();
        list.add(instr);
      }
    } catch (EOFException e) {
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("데이터 비정상");
    } finally {
      try {
        in0.close();
        in2.close();
      } catch (Exception e) {}
    }
  }




  public void save() {
    try {
      FileOutputStream out = new FileOutputStream(filePath);
      DataOutputStream out2 = new DataOutputStream(out);


      for (Instructor instr : list) {
          out2.writeUTF(instr.name);
          out2.writeUTF(instr.lectureName);
          out2.writeUTF(instr.jobCareer);
          out2.writeUTF(instr.lectureCareer);
          out2.writeUTF(instr.book);
          out2.writeUTF(instr.school);
          out2.writeUTF(instr.appraisal);
          out2.writeUTF(instr.webSite);
          out2.writeUTF(instr.prize);
      }

      System.out.println("저장하였습니다.");

      out.close();
      out2.close();
    } catch (Exception e) {

    }
  }


  private void doAdd() {
    while (true) {
      Instructor instr = new Instructor();

      System.out.print("이름(예:엄진영)? ");
      instr.name = this.keyScan.nextLine();

      System.out.print("담당강의(예:JAVA 입문)? ");
      instr.lectureName = this.keyScan.nextLine();

      System.out.print("회사경력(예:롯데 전산실 2005-2006)? ");
      instr.jobCareer = this.keyScan.nextLine();

      System.out.print("강의경력(예:비트캠프 자바 강의 2년)? ");
      instr.lectureCareer = this.keyScan.nextLine();

      System.out.print("저서(예:자바 시작)? ");
      instr.book = this.keyScan.nextLine();

      System.out.print("학력(예:서울대 컴퓨터공학과)? ");
      instr.school = this.keyScan.nextLine();

      System.out.print("강의평가(예:*****)? ");
      instr.appraisal = this.keyScan.nextLine();

      System.out.print("웹사이트(예:eom.com)? ");
      instr.webSite = this.keyScan.nextLine();

      System.out.print("수상내역(예:## 공모전 입상)? ");
      instr.prize = this.keyScan.nextLine();

      list.add(instr);

      System.out.print("계속 입력하시겠습니까?(y/n) ");
      if (!this.keyScan.nextLine().equals("y")) {
        change = true;
        break;
      }
    }//while

  }


  private void doList() {
    for (Instructor instr : list) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s\n",
        instr.name,
        instr.lectureName,
        instr.jobCareer,
        instr.lectureCareer,
        instr.book,
        instr.school,
        instr.appraisal,
        instr.webSite,
        instr.prize);
    }
  }


  private void doView() {
      System.out.println("조회할 강사님의 인덱스를 입력하세요.");

      int index = Integer.parseInt(keyScan.nextLine());

      Instructor instr = list.get(index);

      System.out.printf("이름: %s\n", instr.name);
      System.out.printf("담당강의: %s\n", instr.lectureName);
      System.out.printf("회사경력: %s\n", instr.jobCareer);
      System.out.printf("강의경력: %s\n", instr.lectureCareer);
      System.out.printf("저서: %s\n", instr.book);
      System.out.printf("학력: %s\n", instr.school);
      System.out.printf("강의평가: %s\n", instr.appraisal);
      System.out.printf("웹사이트: %s\n", instr.webSite);
      System.out.printf("수상내역: %s\n", instr.prize);
  }


  private void doDelete() {
      System.out.println("정보를 삭제할 강사님의 인덱스를 입력하세요.");
      int index = Integer.parseInt(keyScan.nextLine());

      Instructor deletedInfo = list.remove(index);
      System.out.printf("%s님의 정보를 삭제했습니다.\n", deletedInfo.name);
      change = true;
  }



  private void doUpdate() {

    System.out.println("정보를 수정할 강사님의 인덱스를 입력하세요.");
    int index = Integer.parseInt(keyScan.nextLine());

    Instructor oldValue = list.get(index);
  
    Instructor instr = new Instructor();

    instr.name = oldValue.name;

    System.out.print("담당강의(예:JAVA 입문)? ");
    instr.lectureName = this.keyScan.nextLine();

    System.out.print("회사경력(예:롯데 전산실 2005-2006)? ");
    instr.jobCareer = this.keyScan.nextLine();

    System.out.print("강의경력(예:비트캠프 자바 강의 2년)? ");
    instr.lectureCareer = this.keyScan.nextLine();

    System.out.print("저서(예:자바 시작)? ");
    instr.book = this.keyScan.nextLine();

    System.out.print("학력(예:서울대 컴퓨터공학과)? ");
    instr.school = this.keyScan.nextLine();

    System.out.print("강의평가(예:*****)? ");
    instr.appraisal = this.keyScan.nextLine();

    System.out.print("웹사이트(예:eom.com)? ");
    instr.webSite = this.keyScan.nextLine();

    System.out.print("수상내역(예:## 공모전 입상)? ");
    instr.prize = this.keyScan.nextLine();

    System.out.print("저장하시겠습니까?(y/n) ");
    if (this.keyScan.nextLine().equals("y")) {
      Instructor newValue = instr;
      list.set(index, newValue);
    //  = list.set(index, newValue);
      System.out.println("저장되었습니다.");
      change = true;
    } else {
      System.out.println("저장이 취소되었습니다.");
    }
  }

}
