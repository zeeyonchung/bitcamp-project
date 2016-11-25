package bitcamp.java89.ems.server;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ReflectionUtil {
  
  public static void getCommandClasses(File dir, ArrayList<Class> classList) {
    if (!dir.isDirectory()) {
      return;
    }
    
    
//    class MyFileFilter implements FileFilter {
//      @Override
//      public boolean accept(File file) {
//        return true;
//      }
//    }
//
//    
//    MyFileFilter filter = new MyFileFilter();
    
    
//    FileFilter filter = new FileFilter() {
//      @Override
//      public boolean accept(File file) {
//        return true;
//      }
//    };
    
    
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory())
          return true;
        if (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"))
          return true;
        return false;
      }
    }); 
    //컴파일 하면 이 익명클래스는 만들어짐. 디스플레이를 호출할 때마다 생성되는 게 아님.
    
    
    for (File file : files) {
      if (file.isDirectory()) {
        getCommandClasses(file, classList);
      } else {
        //파일 명에서 "\\"를 "/"로 바꿔서 OS간의 차이가 없도록 한다. & .class 확장자 제거.
        String path = file.getAbsolutePath().replaceAll("\\\\", "/").replaceAll(".class", "");
        int pos = path.indexOf("/bin/"); //"패키지명 + 클래스명"만 추출
        
        //"패키지명 + 클래스명"에 해당하는 클래스를 로딩한다. 리턴 값은 클래스 정보.
        try {
          Class c = Class.forName(path.substring(pos + 5).replaceAll("/", "."));
          
          //로딩된 클래스가 AbstractCommand를 상속 받았는지 검사.
          Class superClass = c.getSuperclass();

          if (superClass == AbstractCommand.class) {
            //System.out.println(c.getName());
            classList.add(c);
          }
          
        } catch (Exception e) {}
      }
    }
  }
  
}
