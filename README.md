v2.4
빈 컨테이너 도입 : DAO, Controller 객체 관리를 자동화 시킨다.
ApplicationContext.java 생성
ReflectionUtil 클래스와 ApplicationContext 클래스의 기능을 합친다.
ContactDao, TeacherDao 클래스 변경
  - 생성자 호출 시 load() 메서드를 호출하도록 바꾼다.