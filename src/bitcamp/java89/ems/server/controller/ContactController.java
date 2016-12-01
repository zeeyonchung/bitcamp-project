package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component
public class ContactController {
  //의존 객체 Dao를 저장하기 위한 변수
  // => 직접 클래스 이름을 명시하기보다 인터페이스 변수를 선언하는 것이
  //    향후 확장성에 좋다. 왜? 이 변수에 다양한 객체를 넣을 수 있기 때문이다.
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  
  @RequestMapping(value="contact/add")
  public void add(
      @RequestParam("name") String name,
      @RequestParam("position") String position,
      @RequestParam("tel") String tel,
      @RequestParam("email") String email,
      PrintStream out) throws Exception {

    if (contactDao.existEmail(email)) {
      out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
      return;
    }

    Contact contact = new Contact();
    contact.setName(name);
    contact.setPosition(position);
    contact.setTel(tel);
    contact.setEmail(email);


    contactDao.insert(contact);
    out.println("등록하였습니다.");
  }
  
  
  
  @RequestMapping(value="contact/delete")
  public void delete(@RequestParam("email") String email, PrintStream out) throws Exception {
      if (!contactDao.existEmail(email)) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      contactDao.delete(email);
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
  
  
  
  @RequestMapping(value="contact/list")
  public void list(PrintStream out) throws Exception {
      ArrayList<Contact> list = contactDao.getList();
      for (Contact contact : list) {
        out.printf("%s,%s,%s,%s\n",
            contact.getName(),
            contact.getPosition(),
            contact.getTel(),
            contact.getEmail());
    }
  }
  
  
  
  @RequestMapping(value="contact/update")
  public void update(
      @RequestParam("name") String name,
      @RequestParam("position") String position,
      @RequestParam("tel") String tel,
      @RequestParam("email") String email, 
      PrintStream out) throws Exception {

    if (!contactDao.existEmail(email)) {
      out.println("이메일을 찾지 못했습니다.");
      return;
    }

    Contact contact = new Contact();
    contact.setEmail(email);
    contact.setName(name);
    contact.setPosition(position);
    contact.setTel(tel);


    contactDao.update(contact);
    out.println("변경 하였습니다.");
  }
  
  
  
  @RequestMapping(value="contact/view")
  public void view(@RequestParam("name") String name, PrintStream out) throws Exception {
    ArrayList<Contact> list = contactDao.getListByName(name);
    for (Contact contact : list) {
      out.println("----------------");
      out.printf("이름: %s\n", contact.getName());
      out.printf("직위: %s\n", contact.getPosition());
      out.printf("전화: %s\n", contact.getTel());
      out.printf("이메일: %s\n", contact.getEmail());
    }
  }

}
