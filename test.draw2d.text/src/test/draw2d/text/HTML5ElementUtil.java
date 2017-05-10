package test.draw2d.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.runtime.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HTML5ElementUtil {
	public static Element getElement(String html){
		Document doc = Jsoup.parse(html);
		Element body=doc.select("body").get(0);
		return body;
	}
	public static HTML5Element getHTMLElement(String html){
			Document doc = Jsoup.parse(html);
			Element body=doc.select("body").get(0);
			HTML5Element element=new HTML5Element();
			element.setName(body.nodeName());
			StringBuffer sb=new StringBuffer();
			getText(sb,body);
			element.setText(sb.toString());
			element.setAttrs(body.attributes());
			System.out.println(body.text());
			if(body.children()!=null&&body.children().size()!=0){
			Elements children=body.children();
				ArrayList<HTML5Element> al=new ArrayList<HTML5Element>();
				for(Element e:children){
					HTML5Element echild=new HTML5Element();
					echild.setName(e.nodeName());
					StringBuffer sb1=new StringBuffer();
					getText(sb1,e);
					echild.setText(sb1.toString());
					echild.setParent(element);
					echild.setAttrs(e.attributes());
					al.add(echild);
					if(e.children()!=null&&e.children().size()!=0){
						getChildren(e,echild);
					}
				}
				element.setChildren(al);
			}
			return element;
		}
	private static void getChildren(Element element,HTML5Element htmle){
		Elements children=element.children();
		ArrayList<HTML5Element> al=new ArrayList<HTML5Element>();
		for(Element e:children){
			HTML5Element echild=new HTML5Element();
			echild.setName(e.nodeName());
			//System.out.println(e.nodeName()+"node");
			if(e.nodeName().equals("br")){
			//	System.out.println(parentHtml);
				echild.setText(" ");
			}else{
				StringBuffer sb=new StringBuffer();
				getText(sb,e);
				System.out.println(sb.toString());
				echild.setText(sb.toString());
			}
			//System.out.println(e.text()+"text");
			echild.setAttrs(e.attributes());
//			if(e.nodeName().equals("span")){
//				Attributes attr=e.attributes();
//				System.out.println(attr.get("style")+"style");
//				System.out.println(attr.get("font-size")+"font-size");
//			}
			echild.setParent(htmle);
			al.add(echild);
			if(e.children()!=null&&e.children().size()!=0){
				getChildren(e,echild);
			}
		}
		htmle.setChildren(al);
	
	}
	private static void getText(StringBuffer sb,Element element){
		
		List<Node> nodes=element.childNodes();
		if(nodes!=null&&nodes.size()!=0){
			for(Node node:nodes){
				if(node instanceof TextNode){
					TextNode textnode=(TextNode) node;
					String text=textnode.text();
					if(!text.isEmpty()){
						sb.append(text);
					}
				}else if(node instanceof Element){
					Element e=(Element) node;
					if(e.nodeName().equals("br")){
						sb.append("\n");
					}else{
						getText(sb,e);
					}
				}
			}
		}
		
	}
	//	Element script=doc.select("h1").get(0);
	//	System.out.println(script.text());
//		ScriptEngineManager mgr = new ScriptEngineManager();
//		ScriptEngine engine = mgr.getEngineByName("javascript");
//		String jsfile=path+"ckeditor/samples/plugins/djhtest/djhtest.html";
//		try {
//			FileReader reader=new FileReader(jsfile);
//			engine.eval(reader);
//			if(engine instanceof Invocable){
//				Invocable invoke=(Invocable) engine;
//				try {
//					Object o=invoke.invokeFunction("getdata");
//					System.out.println(o);
//				} catch (NoSuchMethodException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(h1s.size());
//		for(Element h1:h1s){
//			System.out.println(h1.text());
//		}

	
}
