package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;

public class ContactDeleteController extends AbstractCommand {
  
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }

  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      if (!contactDao.existEmail(paramMap.get("email"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      contactDao.delete(paramMap.get("email"));
      out.println("해당 데이터 삭제 완료하였습니다.");
  }

  @Override
  public String getCommandString() {
    return "contact/delete";
  }

}
