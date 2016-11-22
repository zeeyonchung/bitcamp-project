

package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;


public class EduAppServer {
  private Scanner in;
  private PrintStream out;


  public static void main(String[] args) throws Exception {
   EduAppServer eduServer = new EduAppServer();
   eduServer.service();
  }
  
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중....");
    
    while (true) {
      new RequestThread(ss.accept()).start();
    }
  }

}
