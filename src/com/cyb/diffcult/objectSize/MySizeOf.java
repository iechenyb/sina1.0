package com.cyb.diffcult.objectSize;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;

public class MySizeOf {

    static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        inst = instP;
    }

    public static long sizeOf(Object o) {
        if(inst == null) {
            throw new IllegalStateException("Can not access instrumentation environment.\n" +
                    "Please check if jar file containing SizeOfAgent class is \n" +
                    "specified in the java's \"-javaagent\" command line argument.");
        }
        return inst.getObjectSize(o);
    }

    
    public static long fullSizeOf(Object obj) {
        Map<Object, Object> visited = new IdentityHashMap<Object, Object>();
        Stack<Object> stack = new Stack<Object>();
        long result = internalSizeOf(obj, stack, visited);
        while (!stack.isEmpty()) {
            result += internalSizeOf(stack.pop(), stack, visited);
        }
        visited.clear();
        return result;
    }
    
    private static boolean skipObject(Object obj, Map<Object, Object> visited) {
        if (obj instanceof String) {
            if (obj == ((String) obj).intern()) {
                return true;
            }
        }
        return (obj == null) || visited.containsKey(obj);
    }

    private static long internalSizeOf(Object obj, Stack<Object> stack, Map<Object, Object> visited) {
        if (skipObject(obj, visited)) {
            return 0;
        }
        visited.put(obj, null);
        long result = 0;
        result += sizeOf(obj);
        Class <?>clazz = obj.getClass();
        if (clazz.isArray()) {
            if(clazz.getName().length() != 2) {
                int length =  Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    stack.add(Array.get(obj, i));
                }
            }
            return result;
        }
        return getNodeSize(clazz , result , obj , stack);
    }

    private static long getNodeSize(Class <?>clazz , long result , Object obj , Stack<Object> stack) {
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (field.getType().isPrimitive()) {
                        continue;
                    }else {
                        field.setAccessible(true);
                        try {
                            Object objectToAdd = field.get(obj);
                            if (objectToAdd != null) {
                                stack.add(objectToAdd);
                            }
                        } catch (IllegalAccessException ex) {
                            assert false;
                        }
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return result;
    }
}
