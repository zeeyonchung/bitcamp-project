
package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Instructor;

public class InstructorDao {
  static InstructorDao obj;
  private ArrayList<Instructor> list;
  private String filename = "instructor-v1.7.data";
  private boolean changed;

  
  private InstructorDao() {
    this.load();
  }

  
  public boolean isChanged() {
    return changed;
  }
  
  
  public static InstructorDao getInstance() {
    if (obj == null) {
      obj = new InstructorDao();
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

      list = (ArrayList<Instructor>)in.readObject();

    } catch (EOFException e) {
    } catch (Exception e) {
      System.out.println("강사 데이터 로딩 중 오류 발생!");
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

    out0.close();
    out.close();
  }


  public void insert(Instructor instr) {
    list.add(instr);
    changed = true;
  }


  public boolean existName(String name) {
    for (Instructor instr : list) {
      if (instr.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }


  public ArrayList<Instructor> getList() {
    return this.list;
  }
  
  
  
  public ArrayList<Instructor> getListByName(String name) {
    ArrayList<Instructor> results = new ArrayList<>();
    
    for (Instructor instr : list) {
      if (instr.getName().equals(name)) {
        results.add(instr);
      }
    }
    
    return results;
  }
  


  public void delete(String name) { //이름을 넘겨 받음.
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)) {
        list.remove(i);
        changed = true;
        return;
      }
    }
  }




  public void update(Instructor instr) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(instr.getName())) {
        list.set(i, instr);
        changed = true;
        return;
      }
    }
  }
  
}
