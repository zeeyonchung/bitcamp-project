package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Contact;

public class ContactDao {
  static ContactDao obj;
  private String filename = "contact-v17.data";
  private ArrayList<Contact> list;
  private boolean changed;

  private ContactDao() {
    this.load(); 
  }

  public boolean isChanged() {
    return changed;
  }
  
  
  public static ContactDao getInstance() {
    if (obj == null) {
      obj = new ContactDao();
    }
    return obj;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Contact>)in.readObject();

    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
      
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }


  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);

    changed = false;

    out.close();
    out0.close();
  }


  public ArrayList<Contact> getList() {
    return this.list;
  }

  public ArrayList<Contact> getListByName(String name) {
    ArrayList<Contact> results = new ArrayList<>();

    for (Contact contact : list) {
      if (contact.getName().equals(name)) {
        results.add(contact);
      }
    }
    return results;
  }


  synchronized public void insert(Contact contact) {
    list.add(contact);
    changed = true;
  }
  

  synchronized public void update (Contact contact) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getEmail().equals(contact.getEmail())) {
        list.set(i, contact);
        changed = true;
        return;
      }
    }
  }


  synchronized public void delete(String email) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getEmail().equals(email)) {
        list.remove(i);
        changed = true;
        return;
      }
    }
  }


  public boolean existEmail(String email) {
    for (Contact contact : list) {
      if (contact.getEmail().toLowerCase().equals(email.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

}

