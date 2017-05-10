package test.draw2d.text;

import java.util.ArrayList;

import org.jsoup.nodes.Attributes;



public class HTML5Element {
	private static String hexString="0123456789ABCDEF";
	String name="";
	String text="";
	ArrayList<HTML5Element> children=new ArrayList<HTML5Element>();
	HTML5Element parent=null;
	Attributes attrs=null; 
	public HTML5Element(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = toHexString(text);
	}
	public ArrayList<HTML5Element> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<HTML5Element> children) {
		this.children = children;
	}
	public Attributes getAttrs() {
		return attrs;
	}
	public void setAttrs(Attributes attrs) {
		this.attrs = attrs;
	}
	public HTML5Element getParent() {
		return parent;
	}
	public void setParent(HTML5Element parent) {
		this.parent = parent;
	}
	public static String toHexString(String str){  
		//根据默认编码获取字节数组
		byte[] bytes=str.getBytes();
		StringBuilder sb=new StringBuilder(bytes.length*2);
		//将字节数组中每个字节拆解成2位16进制整数
		for(int i=0;i<bytes.length;i++)
		{
		sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
		sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
		}
		return sb.toString();
//		String str="";   
//		for (int i=0;i<s.length();i++){   
//			int ch = (int)s.charAt(i);   
//			String s4 = Integer.toHexString(ch);   
//			str = str + s4; 
//		}   
//		return str;   
	} 
}
