package com.briup.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Xml {
	public static String xmlpath(String string1, String string2) throws DocumentException {
		String path = null;
		SAXReader sax = new SAXReader();
		Document document = sax.read(new File("src/main/java/config.xml"));
		// 获取根元素
		Element root = document.getRootElement();
		// 获取所有的子元素Love
		List<Element> elements = root.elements(string1);
		// 输入name元素的内容
		for (Element element : elements) {
			String elementTextTrim = element.elementTextTrim(string2);
			path = elementTextTrim;
		}
		return path;
	}

}
