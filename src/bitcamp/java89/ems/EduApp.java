package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);


  public static void main(String[] args) {
    //InstructorController.keyScan = keyScan;
    InstructorController instructorController = new InstructorController(keyScan);

    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다.");

    loop:
    while (true) {
      System.out.println("add:등록 / list:전체조회 / view:강사조회 / delete:삭제 / update:수정 / quit:나가기");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add" : instructorController.doAdd(); break;
      case "list" : instructorController.doList(); break;
      case "view" : instructorController.doView(); break;
      case "delete" : instructorController.doDelete(); break;
      case "update" : instructorController.doUpdate(); break;
      case "quit" :
        System.out.println("Good bye!");
        break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

}
