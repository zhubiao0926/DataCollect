package com.briup.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {

	static SAXReader sax;
	static Document document;
	static Element root;
	static {
		try {
			sax = new SAXReader();
			document = sax.read(new File("src/main/java/config.xml"));
			root = document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	// public static Object getInstanceObject(String sonName,String... fieldName) {
	// // 获取所有的子元素
	// List<Element> elements = root.elements(sonName);
	// //子节点个数
	// int nodeCount = root.element(sonName).nodeCount();
	// System.out.println(nodeCount);
	// // 获取权限类名
	// String gatherClassPath = root.element(sonName).attributeValue("class");
	// // 要返回的对象
	// Object newInstance = null;
	// // 最终属性值
	// String elementTextTrim = null;
	//
	// try {
	// Class<?> gatherClass = Class.forName(gatherClassPath);
	// newInstance = gatherClass.newInstance();
	// // 获取什么元素
	// for(int i=0;i<nodeCount;i++) {
	// for (Element element : elements) {
	// elementTextTrim = element.elementTextTrim(fieldName[i]);
	// Field field = gatherClass.getField(fieldName[i]);
	// field.set(newInstance, elementTextTrim);
	// }
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return newInstance;
	// }
	public static Object getInstanceObject(String sonName) {
		// 获取权限类名
		String gatherClassPath = root.element(sonName).attributeValue("class");
		// 要返回的对象
		Object newInstance = null;
		// 要赋值的属性值
		String elementTextTrim = null;
		// 要赋值的属性名
		String name =null;

		try {
			Class<?> gatherClass = Class.forName(gatherClassPath);
			newInstance = gatherClass.newInstance();
			// 遍历节点
			for (Iterator it = root.element(sonName).elementIterator(); it.hasNext();) {
				// 获取节点
				Element element = (Element) it.next();
				// 获取节点名
				name = element.getName();
				// 去掉空格，获取节点值
				elementTextTrim = element.getTextTrim();
				Field field = gatherClass.getField(name);
				field.set(newInstance, elementTextTrim);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newInstance;
	}
}
