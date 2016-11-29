package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.context.ApplicationContext;

public class RequestThread extends Thread {
  
  Socket socket;
  private Scanner in;
  private PrintStream out;
  private ApplicationContext appContext;
  
  public RequestThread(Socket socket, ApplicationContext appContext) {
    this.socket = socket;
    this.appContext = appContext;
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
        HashMap<String, String> paramMap = new HashMap<>();
        
        if (command.length == 2) {
          String[] params = command[1].split("&");
  
          for (String value : params) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
          }
        }
        
        
        Command commandHandler = (Command)appContext.getBean(command[0]);// contact/view
        
        
        if (commandHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue; 
        }
        
        
        commandHandler.service(paramMap, out);
        
      }//while
    } catch (Exception e) {
      
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }

  }
  
  
  
  private boolean doQuit() {
    System.out.println("클라이언트 연결 종료!");
    return true;
  }

  
}
