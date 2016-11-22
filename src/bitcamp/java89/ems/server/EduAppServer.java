

package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java89.ems.server.controller.ContactController;
import bitcamp.java89.ems.server.controller.InstructorController;


public class EduAppServer {
  static Scanner keyScan = new Scanner(System.in);
  static InstructorController instructorController;
  static ContactController contactController;
  static Scanner in;
  static PrintStream out;


  public static void main(String[] args) throws Exception {
    
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중....");
    
    while (true) {
      processRequest(ss.accept());
    }
  }

  private static void processRequest(Socket socket) {
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      instructorController = new InstructorController(in, out);
      contactController = new ContactController(in, out);
      
      
      out.println("관리 시스템에 오신 걸 환영합니다.");
      
      loop:
      while (true) {
        out.println("명령> ");
        out.println();
        
        String command = in.nextLine().toLowerCase();
   
        boolean running = true;
        switch (command) {
        case "menu" : doMenu(); break;
        case "go 1" : running = instructorController.service(); break;
        case "go 2" : running = contactController.service(); break;
        case "save" : doSave(); break;
        case "quit" :
          if (doQuit()) {
            break loop;
          }
          break;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
        
        if (!running) {
          doQuit();
          break;
        }
      }
    } catch (Exception e) {
      
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }

  static void doMenu() {
    out.println("[메뉴]");
    out.println("[1. 강사관리]");
    out.println("[2. 연락처관리]");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
    out.println("[명령]");
    out.println("save   데이터 저장");
    out.println("quit   나가기");
  }
  
  
  static boolean doQuit() {
    boolean changed1 = instructorController.isChanged();
    boolean changed2 = contactController.isChanged();
    if (changed1 || changed2) {
      doSave();
    }
    System.out.println("클라이언트 연결 종료!");
    return true;
  }

  
  static void doSave() {
    try {
      instructorController.save();
    } catch (Exception e) {
      System.out.println("강사 정보 저장 중에 오류가 발생했습니다.");
    }
    
    try {
      contactController.save();
    } catch (Exception e) {
      System.out.println("연락처 정보 저장 중에 오류가 발생했습니다.");
    }
  }

}
