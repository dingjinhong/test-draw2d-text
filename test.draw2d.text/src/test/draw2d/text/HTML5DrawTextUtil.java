package test.draw2d.text;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class HTML5DrawTextUtil {
	public static String becomeSB4HTML5(String html){
			Document doc = Jsoup.parse(html);
			List<Node> list=doc.body().childNodes();
			System.out.println(doc.body().html()+"html");
			if(list.size()!=0){
				for(Node node:list){
					if(node instanceof Element){
						Element e=(Element) node;
						if(e.nodeName().equals("strong")){
							
							Tag tag=Tag.valueOf("span");
							Element newe=new Element(tag, e.baseUri());
							newe.attr("style", "font-weight:bold");
							newe.html(e.html());
							e.replaceWith(newe);
							getChildren(newe);
						}else if(e.nodeName().equals("em")){
							Tag tag=Tag.valueOf("span");
							Element newe=new Element(tag, e.baseUri());
							newe.attr("style", "font-style:italic");
							newe.html(e.html());
							e.replaceWith(newe);
							getChildren(newe);
						}else if(e.nodeName().equals("u")){
							Tag tag=Tag.valueOf("span");
							Element newe=new Element(tag, e.baseUri());
							newe.attr("style", "text-decoration:underline");
							newe.html(e.html());
							e.replaceWith(newe);
							getChildren(newe);
						}else if(e.nodeName().equals("p")){
							Attributes attr=e.attributes();
							String style=attr.get("style");
							if(style==null||style.equals("")){
								e.attr("style", "font-family:Arial;font-size:13px;color:#000000");
							}else{
								if(style.indexOf("font-family")!=-1){
									if(style.indexOf("font-size")!=-1){
										e.attr("style",attr.get("style")+"color:#000000");
									}else{
										e.attr("style",attr.get("style")+";font-size:13px;color:#000000");
									}
								}else{
									if(style.indexOf("font-size")!=-1){
										e.attr("style",attr.get("style")+";font-family:Arial;color:#000000");
									}else{
										e.attr("style",attr.get("style")+";font-size:13px;font-family:Arial");
									}
								}
							}
							getChildren(e);
						}else{
							getChildren(e);
						}
					}
				}
			}
			System.out.println(doc.body().html());
		return doc.body().html();
	}
	private static void getChildren(Element element){
		List<Node> list=element.childNodes();
		if(list.size()!=0){
			for(Node node:list){
				if(node instanceof Element){
					Element e=(Element) node;
					if(e.nodeName().equals("strong")){
						Tag tag=Tag.valueOf("span");
						Element newe=new Element(tag, e.baseUri());
						newe.attr("style", "font-weight:bold");
						newe.html(e.html());
						e.replaceWith(newe);
					}else if(e.nodeName().equals("em")){
						Tag tag=Tag.valueOf("span");
						Element newe=new Element(tag, e.baseUri());
						newe.attr("style", "font-style:italic");
						newe.html(e.html());
						e.replaceWith(newe);
						getChildren(newe);
					}else if(e.nodeName().equals("u")){
						Tag tag=Tag.valueOf("span");
						Element newe=new Element(tag, e.baseUri());
						newe.attr("style", "text-decoration:underline");
						newe.html(e.html());
						e.replaceWith(newe);
						getChildren(newe);
					}else if(e.nodeName().equals("p")){
						Attributes attr=e.attributes();
						String style=attr.get("style");
						if(style==null||style.equals("")){
							e.attr("style", "font-family:Arial;font-size:13px;color:#000000");
						}else{
							if(style.indexOf("font-family")!=-1){
								if(style.indexOf("font-size")!=-1){
									e.attr("style",attr.get("style")+"color:#000000");
								}else{
									e.attr("style",attr.get("style")+";font-size:13px;color:#000000");
								}
							}else{
								if(style.indexOf("font-size")!=-1){
									e.attr("style",attr.get("style")+";font-family:Arial;color:#000000");
								}else{
									e.attr("style",attr.get("style")+";font-size:13px;font-family:Arial");
								}
							}
						}
						getChildren(e);
					}else{
						getChildren(e);
					}
				}
			}
		}
	}
}
