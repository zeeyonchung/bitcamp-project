package bitcamp.java89.ems;

public class LinkedList<T> {
  int length;
  Box<T> head = new Box<T>();
  Box<T> tail;

  public LinkedList() {
    tail = head;
  }


  public void add(T value) {
    tail.value = value;
    tail.next = new Box<T>();
    tail = tail.next;
    length++;
  }



  public T get(int index) {
    if (index >= length || index < 0) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }

    Box<T> currentBox = head;

    for (int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }
    return currentBox.value;
  }



  public void set(int index, T value) {

     if (index >= length || index < 0) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }

    Box<T> currentBox = new Box<T>();
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



  public T remove(int index) {
    
     if (index >= length || index < 0) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }

    Box<T> oldValue = new Box<T>();
    if (index == 0) {
      oldValue = head;
      head = head.next;
    } else {
      Box<T> currentBox = new Box<T>();
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


  private class Box<T> {
    T value;
    Box<T> next;

    // public Box<T>() {

    // }//생성자
  }


}
