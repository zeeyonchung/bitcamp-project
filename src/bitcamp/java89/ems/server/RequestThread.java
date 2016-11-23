package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class RequestThread extends Thread {
  
  Socket socket;
  private Scanner in;
  private PrintStream out;
  private HashMap<String, Command> commandMap;
  
  public RequestThread(Socket socket, HashMap<String, Command> commandMap) {
    this.socket = socket;
    this.commandMap = commandMap;
  }
  
  
  
  @Override
  public void run() {
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      out.println("관리 시스템에 오신 걸 환영합니다.");
      

      while (true) {
        out.println("명령> ");
        out.println();
        
        String[] command = in.nextLine().split("\\?");
        //클라이언트가 보낸 명령문을 분석하여 명령어와 파라미터 값을 분리.
        //command[0]은 명령어, command[1]은 name=1&tel=3 ...
        HashMap<String, String> paramMap = new HashMap<>();
        
        if (command.length == 2) {
          String[] params = command[1].split("&");
  
          for (String value : params) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
            //HashMap paramMap에  name : 1 / tel : 3 / ... 저장
          }
        }
        
        
        Command commandHandler = commandMap.get(command[0]);// contact/view
        
        
        if (commandHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue; //다음 줄로 가지 않고 반복문 조건 검사로 건너 뛴다. 위 while로..
        }
        
        //클라이언트가 보낸 명령을 처리할 객체가 있다면, 작업을 실행한다.
        commandHandler.service(paramMap, out); // = (new ContactListController()).service();
        
      }//while
    } catch (Exception e) {
      
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }

  }
  
  
  
  private boolean doQuit() {
    doSave();
    System.out.println("클라이언트 연결 종료!");
    return true;
  }

  
  private void doSave() {
    
    try {
      //contactController.save();
    } catch (Exception e) {
      System.out.println("연락처 정보 저장 중에 오류가 발생했습니다.");
    }
    
    try {
      //instructorController.save();
    } catch (Exception e) {
      System.out.println("강사 정보 저장 중에 오류가 발생했습니다.");
    }
  }
  
}
