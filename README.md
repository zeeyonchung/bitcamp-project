v3.1
각 DAO의 생성자에서 커넥션을 미리 만든다. SQL 실행 시 이 커넥션을 사용한다.

- Connection 객체를 외부(EduAppServer)에서 생성하여 ApplicationContext에게 넘긴다.
- ApplicationContext 변경 : 생성자 추가. 외부에서 만든 객체를 포함시킨다.
- DAO 변경 : Connection 객체를 주입받을 수 있게 셋터 메서드 추가.
