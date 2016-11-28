v2.3
의존 객체 주입 (Dependency Injection) 적용
  - DAO 클래스에 적용된 Singleton 패턴을 제거한다.
  - AbstractDao.java 변경 / ContactDao.java 변경 / StudentDao.java 변경
  - Controller 클래스에 DAO를 주입할 수 있도록 인스턴스 변수와 셋터를 추가한다.
리플렉션 API를 사용하여 의존 객체 주입을 자동화할 수 있다.