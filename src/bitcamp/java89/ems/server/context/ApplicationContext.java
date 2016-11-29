/*역할: 빈 컨테이너 (bean container) = IoC 컨테이너
 * => bean = object = instance
 * => 객체의 생성과 소멸의 관리한다.
 * => 또한 객체가 사용하는 의존 객체 주입을 수행한다.
 */

package bitcamp.java89.ems.server.context;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;


public class ApplicationContext {
  HashMap<String, Object> objPool = new HashMap<String, Object>();
  
  
  public ApplicationContext(String[] packages) {
    ArrayList<Class<?>> classList = getClassList(packages);
    
    prepareObjects(classList);
    
    injectDependencies();
  }
  
  
  public Object getBean(String name) {
    return objPool.get(name);
  }



  private void injectDependencies() {
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      Method[] methods = obj.getClass().getMethods();
      for (Method m : methods) {
        if (!m.getName().startsWith("set")) {
          continue;
        }
        
        if (m.getParameterCount() != 1) {
          continue;
        }
        
        Class<?> paramType = m.getParameterTypes()[0];
        Object dependency = findDependency(paramType);
        if (dependency != null) {
          try {
            m.invoke(obj, dependency);
          } catch (Exception e) {}
        }
      }
    }
  }



  private Object findDependency(Class<?> paramType) {
    Collection<Object> objects = objPool.values();
    
    for (Object obj : objects) {
      if (paramType.isInstance(obj)) {
        return obj;
      }
    }
    return null;
  }



  private ArrayList<Class<?>> getClassList(String[] packages) {
    ArrayList<Class<?>> classList = new ArrayList<>();
    
    for (String packageName : packages) {
      try {
        
        findClasses(packageNameToFile(packageName), classList);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return classList;
  }



  private void prepareObjects(ArrayList<Class<?>> classList) {
    for (Class<?> clazz : classList) {
      try {
        Object obj = clazz.newInstance();
        
        Component compAnno = clazz.getAnnotation(Component.class);
        
        if (compAnno.value().isEmpty()) {
          objPool.put(clazz.getName(), obj);
          System.out.println(clazz.getName());
        } else {
          objPool.put(compAnno.value(), obj);
          System.out.println(compAnno.value());
        }
        
        String key = null;
        
        objPool.put(key, obj);
      } catch (Exception e) {}
    }
  }
  
  
  
  private File packageNameToFile(String packageName) {
    return new File("./bin/" + packageName.replaceAll("\\.", "/"));
  }
  
  
  
  private void findClasses(File dir, ArrayList<Class<?>> classList) {
    if (!dir.isDirectory()) {
      return;
    }
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
    
    for (File file : files) {
      if (file.isDirectory()) {
        findClasses(file, classList);
      } else {
        try {
          Class<?> c = loadClass(file);
          
          if (!isAbstract(c) && isComponent(c)) {
            classList.add(c);
          }
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  
  private boolean isComponent(Class<?> c) {
    return c.getAnnotation(Component.class) != null;
  }


  private boolean isAbstract(Class<?> clazz) {
    if ((clazz.getModifiers() & Modifier.ABSTRACT) == Modifier.ABSTRACT) {
      return true;
    }
    return false;
  }



  private Class<?> loadClass(File file) throws IOException, ClassNotFoundException {
    String path = file.getCanonicalPath().replaceAll("\\\\", "/").replaceAll(".class", "");
    int pos = path.indexOf("/bin/");
    
    return Class.forName(path.substring(pos + 5).replaceAll("/", "."));
  }
  
}
