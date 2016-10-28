package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다.");

    //사용자로부터 값 입력받을 때 사용할 입력 도구 준비
    Scanner keyScan = new Scanner(System.in);

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
}
