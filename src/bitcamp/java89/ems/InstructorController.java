package bitcamp.java89.ems;

import java.util.Scanner;

public class InstructorController {
  private Instructor[] instrs = new Instructor[100];
  private int length = 0;
  private Scanner keyScan;


  public InstructorController(Scanner keyScan) {
    this.keyScan = keyScan;
  }


  public void service() {
    loop:
    while (true) {
      System.out.println("add:등록 / list:전체조회 / view:강사조회 / delete:삭제 / update:수정 / main:메인");
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();

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
      }
    }
  }


  private void doList() {
    for (int i = 0; i < this.length; i ++) {
      Instructor instr = this.instrs[i];
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


  private void doAdd() {
    while (length < this.instrs.length) {
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

      instrs[length++] = instr;

      System.out.print("계속 입력하시겠습니까?(y/n)");
      if (!this.keyScan.nextLine().equals("y")) {
        break;
      }
    }
  }


  private void doView() {
    System.out.println("조회할 강사님의 성함을 입력하세요.");
    String instructorName = keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if ( this.instrs[i].name.toLowerCase().equals(instructorName) ) {
        System.out.printf("이름: %s\n", this.instrs[i].name);
        System.out.printf("담당강의: %s\n", this.instrs[i].lectureName);
        System.out.printf("회사경력: %s\n", this.instrs[i].jobCareer);
        System.out.printf("강의경력: %s\n", this.instrs[i].lectureCareer);
        System.out.printf("저서: %s\n", this.instrs[i].book);
        System.out.printf("학력: %s\n", this.instrs[i].school);
        System.out.printf("강의평가: %s\n", this.instrs[i].appraisal);
        System.out.printf("웹사이트: %s\n", this.instrs[i].webSite);
        System.out.printf("수상내역: %s\n", this.instrs[i].prize);
        return;
      }
    }
    System.out.printf("%s 강사님의 정보는 없습니다.\n", instructorName);
  }


  private void doDelete() {
    System.out.println("정보를 삭제할 강사님의 성함을 입력하세요.");
    String instructorName = keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if ( this.instrs[i].name.toLowerCase().equals(instructorName) ) {
        for (int x = i + 1; x < this.length; x++, i++) {
          this.instrs[i] = instrs[x];
        }
        this.instrs[--length] = null;

        System.out.printf("%s 강사님의 정보를 삭제하였습니다.\n", instructorName);
        return;
      }
    }
    System.out.printf("%s 강사님의 정보는 없습니다.\n", instructorName);
  }


  private void doUpdate() {
    System.out.println("정보를 수정할 강사님의 성함을 입력하세요.");
    String instructorName = keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if ( this.instrs[i].name.toLowerCase().equals(instructorName) ) {

        Instructor instr = new Instructor();

        //System.out.print("이름(예:엄진영)? ");
        instr.name = this.instrs[i].name;

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


        System.out.print("저장하시겠습니까?(y/n)");
        if (this.keyScan.nextLine().equals("y")) {
          this.instrs[i] = instr;
          System.out.println("저장되었습니다.");
        } else {
          System.out.println("저장이 취소되었습니다.");
        }
      }
      return;
    }
    System.out.printf("%s 강사님의 정보는 없습니다.\n", instructorName);
  }


}
