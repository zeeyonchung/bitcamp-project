package bitcamp.java89.ems;
//배열이 아니라 LinkedList기법을 이용한다..!

import java.util.Scanner;

public class InstructorController {
  private Scanner keyScan;
  LinkedList<Instructor> list = new LinkedList<Instructor>();


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


  private void doAdd() {
    while (true) {
      try {
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
      } catch (Exception e) {
          System.out.println("실행 중 오류가 발생했습니다.");
      } //난 에러 날 일이 없어.... 다 String 타입인데..

      System.out.print("계속 입력하시겠습니까?(y/n) ");
      if (!this.keyScan.nextLine().equals("y")) {
        break;
      }
    }//while

  }


  private void doList() {
    for (int i = 0; i < list.size(); i++) { //사이즈의 수만큼 반복한다
      Instructor instr = list.get(i); //instr의 값 (LinkedList의 currentBox.value)을 출력한다..
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
    try {
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
      return;
    } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
    } catch (Exception e) {
        System.out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
    }
  }


  private void doDelete() {
    //인덱스를 줘서 상자를 삭제한다(next 조작) remove

    try {
      System.out.println("정보를 삭제할 강사님의 인덱스를 입력하세요.");
      int index = Integer.parseInt(keyScan.nextLine());

      Instructor deletedInfo = list.remove(index);
      System.out.printf("%s님의 정보를 삭제했습니다.\n", deletedInfo.name);
    } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
    } catch (Exception e) {
        System.out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
    }

  }



  private void doUpdate() {

    try {
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
      } else {
        System.out.println("저장이 취소되었습니다.");
      }
    } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
    } catch (Exception e) {
        System.out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
    }
  }

}
