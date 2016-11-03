package bitcamp.java89.ems;
//배열이 아니라 LinkedList기법을 이용한다..!

import java.util.Scanner;

public class InstructorController {
  private int length;
  private Scanner keyScan;
  Box head;
  Box tail;

  public InstructorController(Scanner keyScan) {
    head = new Box();
    tail = head;
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


      tail.value = instr;
      tail.next = new Box();
      tail = tail.next;
      length++;

      System.out.print("계속 입력하시겠습니까?(y/n) ");
      if (!this.keyScan.nextLine().equals("y")) {
        break;
      }
    }
  }


  private void doList() {
    Box currentBox = new Box();
    currentBox = head;
    while (currentBox != tail) {
      Instructor instr = (Instructor)currentBox.value;
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
      currentBox = currentBox.next;
    }
  }


  private void doView() {
    System.out.println("조회할 강사님의 인덱스를 입력하세요.");

    int index = Integer.parseInt(keyScan.nextLine());
    Box currentBox = new Box();
    currentBox = head;

    if (index >= length || index < 0) {
      System.out.println("유효하지 않은 인덱스 값입니다.");
      return;
    }

    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }

    Instructor instr = (Instructor)currentBox.value;

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
  }


  private void doDelete() {
    System.out.println("정보를 삭제할 강사님의 인덱스를 입력하세요.");

    int index = Integer.parseInt(keyScan.nextLine());
    Box currentBox = new Box();
    currentBox = head;

    if (index >= length || index < 0) {
      System.out.println("유효하지 않은 인덱스 값입니다.");
      return;
    }

    if (index == 0) {
      head = head.next;
    } else {
      for (int i = 0; i < index-1; i++) {
        currentBox = currentBox.next;
      }//입력받은 인덱스 직전의 박스까지 찾아가기
      currentBox.next = currentBox.next.next;
      //그리고.. 그 박스의 next는 그 담담 박스를 가리킴...
    }
    length--;
    System.out.println("삭제를 완료했습니다.");

    // System.out.print("삭제하시겠습니까?(y/n) ");
    // if (this.keyScan.nextLine().equals("y")) {
    //   currentBox.value = instr;
    //   System.out.println("삭제되었습니다.");
    // } else {
    //   System.out.println("삭제가 취소되었습니다.");
    // }

  }


  private void doUpdate() {
    System.out.println("정보를 수정할 강사님의 인덱스를 입력하세요.");

    int index = Integer.parseInt(keyScan.nextLine());
    Box currentBox = new Box();
    currentBox = head;

    if (index >= length || index < 0) {
      System.out.println("유효하지 않은 인덱스 값입니다.");
      return;
    }

    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }//입력한 인덱스의 박스까지 찾아갔음.

    Instructor oldInfo = new Instructor();
    Instructor instr = new Instructor();
    oldInfo = (Instructor)currentBox.value;

    instr.name = oldInfo.name;

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
      currentBox.value = instr;
      System.out.println("저장되었습니다.");
    } else {
      System.out.println("저장이 취소되었습니다.");
    }
  }


}
