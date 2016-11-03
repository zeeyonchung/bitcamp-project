package bitcamp.java89.ems.v8_5;

public class LinkedList {
  int length;
  Box head = new Box();
  Box tail;

  public LinkedList() {
    tail = head;
  }


  public void add(Object value) {
    //상자를 하나 추가해서 파라미터로 받은 Instructor형 데이터들을 이 상자의 value에 저장한다.
    tail.value = value;
    tail.next = new Box();
    tail = tail.next;
    length++;
  }

  public Object get(int index) {
    //index를 입력받아 해당하는 상자를 찾아서 value를 리턴한다. 리턴값의 타입은.. 다 다르겠지.
    Box currentBox = head;

    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }
    return currentBox.value;
  }



  public void set(int index, Object value) {
    Box currentBox = new Box();
    currentBox = head;

    // if (index >= length || index < 0) {
    //   System.out.println("유효하지 않은 인덱스 값입니다.");
    //   return;
    // }

    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }//입력한 인덱스의 박스까지 찾아갔음.

    currentBox.value = value;
  }





  public Object remove(int index) {

    Box oldValue = new Box();
    if (index == 0) {
      oldValue = head;
      head = head.next;
    } else {
      Box currentBox = new Box();
      currentBox = head;
      for (int i = 0; i < index-1; i++) {
        currentBox = currentBox.next;
      }//입력받은 인덱스 직전의 박스까지 찾아가기
      oldValue = currentBox.next;
      currentBox.next = currentBox.next.next;
      //그리고.. 그 박스의 next는 그 담담 박스를 가리킴...
    }
    length--;
    return oldValue.value;

  }


  public int size() {
    return length;
  }


  private class Box {
    Object value;
    Box next;

    public Box() {

    }//생성자
  }


}
