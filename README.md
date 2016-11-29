v2.5
애노테이션을 이용하여 객체의 이름을 JVM에 전달하기
- Component.java 애노테이션을 정의한다.
- ApplicationContext가 관리할 클래스에 Component 애노케이션을 적용한다.
  - DAO에 적용
  - Controller에 적용 (Controller의 명령 문자열을 애노테이션에 등록한다.)