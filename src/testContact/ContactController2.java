//v1.5

package testContact;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactController2 {
  private Scanner keyScan;
  private ArrayList<Contact2> list;
  private String filename = "./Contactv1.5.data";
  private boolean changed;


  public ContactController2(Scanner keyScan) {
    list = new ArrayList<Contact2>();
    this.keyScan = keyScan;
    this.load();
  }


  public void service() {
    loop:
      while (true) {
        System.out.println("add:등록 / list:전체조회 / view:강사조회 / delete:삭제 / update:수정 / main:메인");
        System.out.print("연락처관리> ");
        String command = keyScan.nextLine().toLowerCase();

        try {
          switch (command) {
          case "add" : this.doAdd(); break;
          case "list" : this.doList(); break;
          case "view" : this.doView(); break;
          case "delete" : this.doDelete(); break;
          //          case "update" : this.doUpdate(); break;
          case "main" :
            break loop;
          default:
            System.out.println("지원하지 않는 명령어입니다.");
          }//switch
        } catch (IndexOutOfBoundsException e) {
          System.out.println("인덱스가 유효하지 않습니다.");
        } catch (Exception e) {
          System.out.println("인덱스 값이 잘못되었거나 실행 중 오류가 발생했습니다.");
        }
      }//while
  }


  public boolean isChanged() {
    return changed;
  }


  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Contact2>)in.readObject();

    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }




  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);


    out.writeObject(list);

    changed = false;

    out0.close();
    out.close();
  }


  private void doAdd() {
    while (true) {
      Contact2 contact = new Contact2();

      System.out.print("이름? ");
      contact.setName(this.keyScan.nextLine());

      System.out.print("직위? ");
      contact.setPosition(this.keyScan.nextLine());

      System.out.print("전화? ");
      contact.setTel(this.keyScan.nextLine());

      System.out.print("이메일? ");
      contact.setEmail(this.keyScan.nextLine());

      boolean save = true;
      if (existEmail(contact.getEmail())) {
        System.out.print("같은 이메일이 존재합니다. 저장하시겠습니까?(y/n) ");
        if (!keyScan.nextLine().toLowerCase().equals("y")) {
          save = false;
          System.out.println("저장을 취소하였습니다.");
        }
      }


      if (save) {
        list.add(contact);
        changed = true;
      }

      System.out.print("계속 입력하시겠습니까?(y/n) ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }//while

  }


  private boolean existEmail(String email) {
    for (Contact2 contact : list) {
      if (contact.getEmail().toLowerCase().equals(email.toLowerCase())) 
          return true;
      }
    return false;
  }




  private void doList() {
    for (Contact2 contact : list) {
      System.out.printf("%s, %s, %s, %s\n",
          contact.getName(),
          contact.getPosition(),
          contact.getTel(),
          contact.getEmail());
    }
  }


  private void doView() {
    System.out.print("이름? ");
    String name = this.keyScan.nextLine();

    for (Contact2 contact : list) {
      if (contact.getName().equals(name)) {
        System.out.println("--------------------");
        System.out.printf("이름: %s\n", contact.getName());
        System.out.printf("담당강의: %s\n", contact.getPosition());
        System.out.printf("회사경력: %s\n", contact.getTel());
        System.out.printf("강의경력: %s\n", contact.getEmail());
      }
    }


  }



  //  private void doDelete() {
  //    System.out.print("이름? ");
  //    String name = keyScan.nextLine();
  //
  //    ArrayList<Contact> deleteList = new ArrayList<>();
  //    
  //    for (Contact contact : list) {
  //      if (contact.getName().equals(name)) {
  //        deleteList.add(contact);
  //      }
  //    }
  //    
  //    
  //    for (Contact contact : deleteList) {
  //      list.remove(contact);
  //    }
  //    
  //    changed = true;
  //    System.out.println("삭제하였습니다.");
  //  }


//  private void doDelete() {
//    System.out.print("이름? ");
//    String name = keyScan.nextLine();
//
//    for (int i = list.size() - 1; i >= 0; i--) {
//      if (list.get(i).getName().equals(name)) {
//        list.remove(i);
//      }
//    }
//
//    changed = true;
//    System.out.println("삭제하였습니다.");
//  }

  
  private void doDelete() {
    System.out.print("이름? ");
    String name = keyScan.nextLine();
    
    ArrayList<Contact2> deleteList = searchByName(name);
    
    System.out.println("---------------");
    for (int i = 0; i < deleteList.size(); i++) {
      Contact2 contact = deleteList.get(i);
      System.out.printf("%d. %s (%s)\n", (i + 1),  contact.getName(), contact.getEmail());
    }
    
    System.out.println("---------------");
    
    System.out.print("삭제할 연락처의 번호? ");
    int deleteNo = Integer.parseInt(keyScan.nextLine());
    
    if (deleteNo < 1 || deleteNo > deleteList.size()) {
      System.out.println("유효하지 않은 번호입니다.");
      return;
    }
    
    list.remove(deleteList.get(deleteNo - 1));
    changed = true;
    System.out.println("삭제하였습니다.");
  }
  
  
  
  private ArrayList<Contact2> searchByName(String name) {
    ArrayList<Contact2> searchList = new ArrayList<>();
    
    for (Contact2 contact : list) {
      if (contact.getName().toLowerCase().equals(name.toLowerCase())) {
        searchList.add(contact);
      }
    }
    
    return searchList;
  }
  
  



  /*
  private void doUpdate() {

    System.out.println("정보를 수정할 강사님의 인덱스를 입력하세요.");
    int index = Integer.parseInt(keyScan.nextLine());

    Contact oldValue = list.get(index);

    Contact contact = new Contact();

    contact.name = oldValue.name;

    System.out.print("담당강의(예:JAVA 입문)? ");
    contact.lectureName = this.keyScan.nextLine();

    System.out.print("회사경력(예:롯데 전산실 2005-2006)? ");
    contact.jobCareer = this.keyScan.nextLine();

    System.out.print("강의경력(예:비트캠프 자바 강의 2년)? ");
    contact.lectureCareer = this.keyScan.nextLine();

    System.out.print("저서(예:자바 시작)? ");
    contact.book = this.keyScan.nextLine();

    System.out.print("학력(예:서울대 컴퓨터공학과)? ");
    contact.school = this.keyScan.nextLine();

    System.out.print("강의평가(예:*****)? ");
    contact.appraisal = this.keyScan.nextLine();

    System.out.print("웹사이트(예:eom.com)? ");
    contact.webSite = this.keyScan.nextLine();

    System.out.print("수상내역(예:## 공모전 입상)? ");
    contact.prize = this.keyScan.nextLine();

    System.out.print("저장하시겠습니까?(y/n) ");
    if (this.keyScan.nextLine().equals("y")) {
      Contact newValue = contact;
      list.set(index, newValue);
      //  = list.set(index, newValue);
      System.out.println("저장되었습니다.");
      changed = true;
    } else {
      System.out.println("저장이 취소되었습니다.");
    }
  }
   */

}
