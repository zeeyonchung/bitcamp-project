package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactAddController extends AbstractCommand {
  
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      ContactDao contactDao = ContactDao.getInstance();

      if (contactDao.existEmail(paramMap.get("email"))) {
        out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
        return;
      }


      Contact contact = new Contact();
      contact.setName(paramMap.get("name"));
      contact.setPosition(paramMap.get("position"));
      contact.setTel(paramMap.get("tel"));
      contact.setEmail(paramMap.get("email"));


      contactDao.insert(contact);
      out.println("등록하였습니다.");
  }

}
