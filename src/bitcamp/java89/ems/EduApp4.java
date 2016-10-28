package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp4 {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다.");

    Instructor[] instrs = new Instructor[100];
    int length = 0;

    Scanner keyScan = new Scanner(System.in);


    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add" :
        while (length < instrs.length) {
          Instructor instr = new Instructor();

          System.out.print("이름(예:엄진영)? ");
          instr.name = keyScan.nextLine();

          System.out.print("담당강의(예:JAVA 입문)? ");
          instr.lectureName = keyScan.nextLine();

          System.out.print("회사경력(예:롯데 전산실 2005-2006)? ");
          instr.jobCareer = keyScan.nextLine();

          System.out.print("강의경력(예:비트캠프 자바 강의 2년)? ");
          instr.lectureCareer = keyScan.nextLine();

          System.out.print("저서(예:자바 시작)? ");
          instr.book = keyScan.nextLine();

          System.out.print("학력(예:서울대 컴퓨터공학과)? ");
          instr.school = keyScan.nextLine();

          System.out.print("강의평가(예:*****)? ");
          instr.appraisal = keyScan.nextLine();

          System.out.print("웹사이트(예:eom.com)? ");
          instr.webSite = keyScan.nextLine();

          System.out.print("수상내역(예:## 공모전 입상)? ");
          instr.prize = keyScan.nextLine();

          instrs[length++] = instr;

          System.out.print("계속 입력하시겠습니까?(y/n)");
          if (!keyScan.nextLine().equals("y")) {
            break;
          }
        }
        break;
      case "list" :
        printInstructorList(instrs, length);
        break;
      case "view" :
        System.out.println("조회할 강사님의 성함을 입력하세요.");
        String instructorName = keyScan.nextLine().toLowerCase();
        for (int i = 0; i < length; i++) {
          if ( instrs[i].name.toLowerCase().equals(instructorName) ) {
            System.out.printf("이름: %s\n", instrs[i].name);
            System.out.printf("담당강의: %s\n", instrs[i].lectureName);
            System.out.printf("회사경력: %s\n", instrs[i].jobCareer);
            System.out.printf("강의경력: %s\n", instrs[i].lectureCareer);
            System.out.printf("저서: %s\n", instrs[i].book);
            System.out.printf("학력: %s\n", instrs[i].school);
            System.out.printf("강의평가: %s\n", instrs[i].appraisal);
            System.out.printf("웹사이트: %s\n", instrs[i].webSite);
            System.out.printf("수상내역: %s\n", instrs[i].prize);
            break;//가장 가까운 반복문 for 나감
          }
        }
        break; //스위치문 빠져나감
      case "quit" :
        System.out.println("Good bye!");
        break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }

  }



  static void printInstructorList(Instructor[] instrs, int length) {
    //Instructor instr = null;
    for (int i = 0; i < length; i ++) {
      Instructor instr = instrs[i];
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

}
