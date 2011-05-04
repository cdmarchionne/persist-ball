package ar.edu.unq.tpi.persistencia.utils;

import ar.edu.unq.tpi.persistencia.exception.UserException;


@SuppressWarnings("rawtypes")
public class ReflectionUtils {


    public static Object invokeMethod(final Object model, final String actionName) {
        try {
            return model.getClass().getMethod(actionName, new Class[] {}).invoke(model, new Object[] {});
        } catch (Exception e) {
            throw new UserException(e);
        }
    }
    
    public static void invokeMethod(final Object model, final String actionName, final Class[] c, final Object... args) {
        try {
            model.getClass().getMethod(actionName, c).invoke(model, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	public static void invokeMethod(Object object, String method, Object[] args) {
        Class[] clazz = new Class[args.length] ;
        int i = 0;
        for (Object object2 : args) {
            clazz[i] = object2.getClass();
            i++;
        }
        invokeMethod(object, method, clazz, args);
    }


}
