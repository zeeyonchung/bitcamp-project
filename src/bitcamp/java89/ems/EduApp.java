

package bitcamp.java89.ems;

import java.util.Scanner;
import java.lang.Exception;
import java.io.EOFException;


public class EduApp {
  static Scanner keyScan = new Scanner(System.in);
  static InstructorController instructorController;


  public static void main(String[] args) {
    instructorController = new InstructorController(keyScan);
  
   
    instructorController.open();


    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다.");

    loop:
    while (true) {
      System.out.println("menu:메뉴 / quit:나가기 / save:파일저장");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "menu" : doMenu(); break;
      case "go 1" : instructorController.service(); break;
      case "save" : instructorController.save();
                    instructorController.change = false;
                    break;
      case "quit" :
        if (instructorController.isChanged() == true) {
          System.out.print("학생 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n) ");
          if (keyScan.nextLine().equals("y")) {
            System.out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
            System.out.println("Good bye!");
            break loop;
          } else {break;}
        } else {
          System.out.println("Good bye!"); //나가기
          break loop;
        }
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  static void doMenu() {
    System.out.println("[메뉴]");
    System.out.println("[1. 강사관리]");
    System.out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
  }

}
