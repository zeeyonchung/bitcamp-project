package bitcamp.java89.ems;

import java.util.Scanner;

public class InstructorController {
  Instructor[] instrs = new Instructor[100];
  int length = 0;
  Scanner keyScan;

  public InstructorController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void doList() {
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


  void doAdd() {
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


  public void doView() {
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
        break;
      }
    }
  }

}
