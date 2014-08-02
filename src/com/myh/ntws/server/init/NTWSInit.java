package com.myh.ntws.server.init;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class NTWSInit
{
	
	public static HashMap<String, Method> init(String path)
	{
		HashMap<String, Method> methodHandler = new HashMap<String, Method>();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File(path));
			Element root = document.getRootElement();
			Stack<Element> stack = new Stack<Element>();
			stack.push(root);
			while (!stack.empty()) {
				Element element = stack.pop();
				if ("implementation.java".equals(element.getName())) {
					String attribute = element.attributeValue("class");
					register(attribute,methodHandler);
				}
				List<Element> listElement = element.elements();
				for (Element e : listElement) {
					stack.push(e);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return methodHandler;
	}

	private static void register(String clazzPath,HashMap<String, Method> methodHandler) throws IllegalAccessException, InvocationTargetException, InstantiationException
	{
		try {
			Class<?> clazz = Class.forName(clazzPath);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				methodHandler.put(clazzPath + methodName, method);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		HashMap<String, Method> hashMap = init("src/com/myh/ntws/server/init/init.xml");
		for(Iterator<String> iter = hashMap.keySet().iterator();iter.hasNext();)
		{
			String key = iter.next();
			System.out.println(key +" : " + hashMap.get(key));
		}
	}
}
