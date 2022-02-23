package launch1app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        System.out.println("This is the app.");
        callComp();
    }
    
    private void callComp() {
        // late binding to "component1.Task" which could come from launch1-comp but also from another project
        try {
            Class<?> c = Class.forName("component1.Task");
            Object o = c.newInstance();
            Method m = c.getMethod("getName");
            String response = (String) m.invoke(o);
            System.out.println("Task.name: " + response);
        } catch (InvocationTargetException e) {
            System.err.println(e.getCause().getClass().getName() + ": " + e.getCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
