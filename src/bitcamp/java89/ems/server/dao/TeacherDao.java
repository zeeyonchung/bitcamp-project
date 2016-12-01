package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Teacher;

public interface TeacherDao {
  ArrayList<Teacher> getList() throws Exception;
  ArrayList<Teacher> getListByName(String name) throws Exception;
  void insert(Teacher teacher) throws Exception;
  boolean existName(String name) throws Exception;
  void delete(String name) throws Exception;
  void update(Teacher teacher) throws Exception;
}
