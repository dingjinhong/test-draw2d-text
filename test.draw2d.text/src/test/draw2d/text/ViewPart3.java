package test.draw2d.text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.BlockFlow;
import org.eclipse.draw2d.text.FlowAdapter;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class ViewPart3 extends ViewPart implements ISelectionListener{
	Composite com;
	FigureCanvas fcanvas;
	public ViewPart3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		com=new Composite(parent,SWT.NONE);
		GridLayout gl=new GridLayout();
		gl.marginWidth=0;
		gl.marginHeight=0;
		com.setLayout(gl);
		fcanvas=new FigureCanvas(com);
		//fcanvas.setSize(300, 200);
		fcanvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		fcanvas.getViewport().setContentsTracksHeight(true);
		fcanvas.getViewport().setContentsTracksWidth(true);
		fcanvas.setBackground(ColorConstants.white);
		this.getSite().getPage().addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		boolean flag=false;
		IStructuredSelection selection1=(IStructuredSelection)selection;
		Object obj=selection1.getFirstElement();
		if(obj instanceof CanvasData){
		CanvasData cd=(CanvasData) obj;
		Element parent=cd.getElement();
		if(parent.nodeName().equals("body")){
			FlowPage page=new FlowPage();
			fcanvas.setContents(page);
			List<Node> children=parent.childNodes();
			for(Node child:children){
				if(child instanceof TextNode){
					BlockFlow bf=new BlockFlow();
//					TextNode textnode=(TextNode) child;
//					TextFlow text=new TextFlow(textnode.text());
//					bf.add(text);
					FlowAdapter fa = new FlowAdapter();
					fa.setLayoutManager(new ToolbarLayout(true));
					RoundedRectangle rect1 = new RoundedRectangle();
					rect1.setForegroundColor(ColorConstants.white);
					rect1.setPreferredSize(5, 10);
					fa.add(rect1);
					bf.add(fa);
					page.add(bf);
				}else if(child instanceof Element){
					Element echild=(Element) child;
					System.out.println(echild.text());
				if(echild.nodeName().toLowerCase().equals("p")){
					BlockFlow bf=new BlockFlow();
					List<Node> echildren=echild.childNodes();
					if(echildren.size()!=0){
						for(Node e:echildren){
							if(e instanceof TextNode){
								TextNode textnode=(TextNode) e;
								boolean hasunderlined=false;
								hasunderlined=isUnderLined(echild,hasunderlined);
								if(hasunderlined){
									Color color=getNodeBackgroundColor(echild);
									SB4TextFlow text=null;
									if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
										text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED);
									}else{
										text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED|TEXTFLOW.BGCOLOR);
									}
									Font font=getNodetFont(echild);
									text.setFont(font);
									text.setForegroundColor(getNodeColor(echild));
									text.setBackgroundColor(getNodeBackgroundColor(echild));
									text.setText(textnode.text());
									//text.setText(t[0]);
									bf.add(text);
									
								}else{
									Color color=getNodeBackgroundColor(echild);
									SB4TextFlow text=null;
									if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
										text=new SB4TextFlow(fcanvas,0);
									}else{
										text=new SB4TextFlow(fcanvas,TEXTFLOW.BGCOLOR);
									}
							Font font=getNodetFont(echild);
							text.setFont(font);
							text.setForegroundColor(getNodeColor(echild));
							text.setBackgroundColor(getNodeBackgroundColor(echild));
							text.setText(textnode.text());
							//text.setText(t[0]);
							bf.add(text);
						}
							}else if(e instanceof Element){
								Element ee=(Element) e;
								if(ee.nodeName().equals("br")){

									TextFlow text=new TextFlow();
									text.setText("\n");
									bf.add(text);
								}else{
									drawText(ee,bf,echild);


								}
								
						}
						}
					}
					Attributes attrs=child.attributes();
					String style=attrs.get("style");
					if(!style.equals("")&&style!=null){
						if(style.indexOf("text-align")!=-1){
							String align=style.split(":")[1].trim();
							align=align.substring(0, align.length()-1);
							//System.out.println(align);
							if(align.equals("center")){
								bf.setHorizontalAligment(PositionConstants.CENTER);
							}else if(align.equals("right")){
								bf.setHorizontalAligment(PositionConstants.RIGHT);
							}
						}else{
							
						}
					}else{
						
					}
					page.add(bf);
				}else if(echild.nodeName().toLowerCase().equals("ul")){
					List<Node> ulchildren=child.childNodes();
					
					if(ulchildren.size()!=0){
						for(Node e:ulchildren){
							if(e instanceof TextNode){
								TextNode textnode=(TextNode)e;
								BlockFlow bf=new BlockFlow();
								boolean hasunderlined=false;
								hasunderlined=isUnderLined(echild,hasunderlined);
								if(hasunderlined){
									Color color=getNodeBackgroundColor(echild);
									SB4TextFlow text=null;
									if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
										text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED);
									}else{
										text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED|TEXTFLOW.BGCOLOR);
									}
									Font font=getNodetFont(echild);
									text.setFont(font);
									text.setForegroundColor(getNodeColor(echild));
									text.setBackgroundColor(getNodeBackgroundColor(echild));
									text.setText(textnode.text());
									bf.add(text);
									
								}else{
									Color color=getNodeBackgroundColor(echild);
									SB4TextFlow text=null;
									if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
										text=new SB4TextFlow(fcanvas,0);
									}else{
										text=new SB4TextFlow(fcanvas,TEXTFLOW.BGCOLOR);
									}
							Font font=getNodetFont(echild);
							text.setFont(font);
							text.setForegroundColor(getNodeColor(echild));
							text.setBackgroundColor(getNodeBackgroundColor(echild));
							text.setText(textnode.text());
							bf.add(text);
								}
							}else if(e instanceof Element){
							Element ee=(Element)e;
							//String s=child.getText();
							BlockFlow bf=new BlockFlow();
							FlowAdapter fa = new FlowAdapter();
							//int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
							int fsize=getFirstFontSize(ee,"13");
							//System.out.println(fsize+"fsize");
							GC gc=new GC(fcanvas);
							gc.setFont(new Font(null,new FontData("Arial",fsize,SWT.NORMAL)));
							FontMetrics fm = gc.getFontMetrics();
							int leading=fm.getLeading();
							//System.out.println(leading+"fsize");
							RoundedRectangle rect1 = new RoundedRectangle();
							rect1.setForegroundColor(ColorConstants.button);
							rect1.setPreferredSize(5, (fsize-4-leading)/2+4);
							//rect1.setForegroundColor(ColorConstants.red);
							fa.add(rect1);
							fa.setLayoutManager(new ToolbarLayout(true));
							Ellipse arc = new Ellipse();
							arc.setForegroundColor(ColorConstants.black);
							arc.setBackgroundColor(ColorConstants.black);
							arc.setPreferredSize(4, 4);
							fa.add(arc);
							
							RoundedRectangle rect = new RoundedRectangle();
							rect.setForegroundColor(ColorConstants.button);
							rect.setPreferredSize(5, (fsize-4-leading)/2+4);
							fa.add(rect);
							bf.add(fa);
						//	BlockFlow bf=new BlockFlow();
							if(!flag){
								Attributes attrs=e.attributes();
								String style=attrs.get("style");
								if(!style.equals("")&&style!=null){
									if(style.indexOf("text-align")!=-1){
										String align=style.split(":")[1].trim();
										align=align.substring(0, align.length()-1);
									//	System.out.println(align);
										if(align.equals("center")){
											bf.setHorizontalAligment(PositionConstants.CENTER);
										}else if(align.equals("right")){
											bf.setHorizontalAligment(PositionConstants.RIGHT);
										}
									}else{
									
									}
								}else{
								
								}
							}else{
								
							}
							drawText(ee,bf,echild);
							page.add(bf);
						}
						}
					
					}
				}
			}
				
			}
			
		}
		}else if(obj instanceof String){
			FlowPage page=new FlowPage();
			TextFlow text=new TextFlow();
			text.setText((String) obj);
			page.add(text);
			fcanvas.setContents(page);
		}
	}
	private int getLineWidth(Element element){
		if(!element.parent().nodeName().equals("body")){
			
		}else{
			
		}
		return 0;
	}
	private boolean isUnderLined(Element element,boolean hasunderlined){
		if(!element.parent().nodeName().equals("body")){
			hasunderlined=isUnderLined(element.parent(),hasunderlined);
			if(element.nodeName().equals("a")||element.nodeName().equals("u")){
				hasunderlined=true;
			}
			
		}else{
			if(element.nodeName().equals("a")||element.nodeName().equals("u")){
				hasunderlined=true;
			}
		}
		return hasunderlined;
	}
	private void drawText(Element child,BlockFlow bf,Element parent){
		List<Node> children=child.childNodes();
		if(children.size()!=0){
		
			
			for(Node e:children){
			//	if(!e.getText().equals("")){
					if(e instanceof TextNode){
						TextNode textnode=(TextNode)e;
						boolean hasunderlined=false;
						hasunderlined=isUnderLined(child,hasunderlined);
						if(hasunderlined){
							Color color=getNodeBackgroundColor(child);
							SB4TextFlow text=null;
							if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
								text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED);
							}else{
								text=new SB4TextFlow(fcanvas,TEXTFLOW.UNDERLINED|TEXTFLOW.BGCOLOR);
							}
							Font font=getNodetFont(child);
							text.setFont(font);
							text.setForegroundColor(getNodeColor(child));
							text.setBackgroundColor(getNodeBackgroundColor(child));
							text.setText(textnode.text());
							bf.add(text);
							
						}else{
							Color color=getNodeBackgroundColor(child);
							System.out.println(color.getBlue());
							System.out.println(color.getRed());
							System.out.println(color.getGreen());
							SB4TextFlow text=null;
							if(color.getBlue()==255&&color.getGreen()==255&&color.getRed()==255){
								text=new SB4TextFlow(fcanvas,0);
							}else{
								text=new SB4TextFlow(fcanvas,TEXTFLOW.BGCOLOR);
							}
					Font font=getNodetFont(child);
					text.setFont(font);
					text.setForegroundColor(getNodeColor(child));
					text.setBackgroundColor(getNodeBackgroundColor(child));
					System.out.println(text.getBackgroundColor());
					text.setText(textnode.text());
					bf.add(text);
				//	drawText(e,bf,child);
						}
						
					}else if(e instanceof Element){	
						Element ee=(Element) e;
					if(ee.nodeName().equals("br")){
						TextFlow text=new TextFlow();
						text.setText("\n");
						bf.add(text);
					}else{
						drawText(ee,bf,child);
						
					}
					}
				}
		}
	}
	private Color getNodeBackgroundColor(Element element){
		if(!element.parent().nodeName().equals("body")){
			Color color=getNodeBackgroundColor(element.parent());
			Color c=null;
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			if(style==null||style.equals("")){
				c=color;
			}else{
				if(style.indexOf("background-color")!=-1){
					String[] str=style.split(":");
					String rgb=str[1].trim();
					Pattern pattern = Pattern.compile("\\((.*?)\\)"); 
				    Matcher matcher = pattern.matcher(rgb);
				    if (matcher.find()){
				        rgb=matcher.group(1);
				     //   System.out.println(rgb);
				        String[] str1=rgb.split(",");
				        if(str1.length>=3){
				        	String r=str1[0].trim();
				        	String g=str1[1].trim();
				        	String b=str1[2].trim();
				        	int red=Integer.parseInt(r);
				        	int green=Integer.parseInt(g);
				        	int blue=Integer.parseInt(b);
				        	//System.out.println(red);
				        	//System.out.println(green);
				        	//System.out.println(blue);
				        	c=new Color(null,new RGB(red,green,blue));
				        }else{
				        	c=color;
				        }
				    }else{
				    	c=color;
				    }
				}else{
					c=color;
				}
				
			}
			return c;
			
		}else{
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			Color color=null;
			if(style==null||style.equals("")){
				color=new Color(null,new RGB(255,255,255));
				System.out.println(color+"color");
			}else{
				if(style.indexOf("background-color")!=-1){
					String[] str=style.split(":");
					String rgb=str[1].trim();
					Pattern pattern = Pattern.compile("((.*?))"); 
				    Matcher matcher = pattern.matcher(rgb);
				    if (matcher.find()){
				        rgb=matcher.group(1);
				        String[] str1=rgb.split(",");
				        if(str1.length>=3){
				        	String r=str1[0].trim();
				        	String g=str1[1].trim();
				        	String b=str1[2].trim();
				        	int red=Integer.parseInt(r);
				        	int green=Integer.parseInt(g);
				        	int blue=Integer.parseInt(b);
				        	color=new Color(null,new RGB(red,green,blue));
				        }else{
				        	color=new Color(null,new RGB(255,255,255));
				        }
				    }else{
				    	color=new Color(null,new RGB(255,255,255));
				    }
				}else{
					color=new Color(null,new RGB(255,255,255));
				}
			}
				
			
			return color;
		}
	
	}
	private Color getNodeColor(Element element){
		if(!element.parent().nodeName().equals("body")){
			Color color=getNodeColor(element.parent());
			Color c=null;
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			if(style==null||style.equals("")){
				c=color;
			}else{
				if(style.indexOf("color")!=-1&&style.indexOf("background-color")<0){
					String[] str=style.split(":");
					String rgb=str[1].trim();
					Pattern pattern = Pattern.compile("\\((.*?)\\)"); 
				    Matcher matcher = pattern.matcher(rgb);
				    if (matcher.find()){
				        rgb=matcher.group(1);
				     //   System.out.println(rgb);
				        String[] str1=rgb.split(",");
				        if(str1.length>=3){
				        	String r=str1[0].trim();
				        	String g=str1[1].trim();
				        	String b=str1[2].trim();
				        	int red=Integer.parseInt(r);
				        	int green=Integer.parseInt(g);
				        	int blue=Integer.parseInt(b);
				        	//System.out.println(red);
				        	//System.out.println(green);
				        	//System.out.println(blue);
				        	c=new Color(null,new RGB(red,green,blue));
				        }else{
				        	c=color;
				        }
				    }else{
				    	c=color;
				    }
				}else{
					c=color;
				}
				
			}
			return c;
			
		}else{
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			Color color=null;
			if(style==null||style.equals("")){
				color=new Color(null,new RGB(0,0,0));
			}else{
				if(style.indexOf("color")!=-1&&style.indexOf("background-color")<0){
					String[] str=style.split(":");
					String rgb=str[1].trim();
					Pattern pattern = Pattern.compile("((.*?))"); 
				    Matcher matcher = pattern.matcher(rgb);
				    if (matcher.find()){
				        rgb=matcher.group(1);
				        String[] str1=rgb.split(",");
				        if(str1.length>=3){
				        	String r=str1[0].trim();
				        	String g=str1[1].trim();
				        	String b=str1[2].trim();
				        	int red=Integer.parseInt(r);
				        	int green=Integer.parseInt(g);
				        	int blue=Integer.parseInt(b);
				        	color=new Color(null,new RGB(red,green,blue));
				        }else{
				        	color=new Color(null,new RGB(0,0,0));
				        }
				    }else{
				    	color=new Color(null,new RGB(0,0,0));
				    }
				}else{
					color=new Color(null,new RGB(0,0,0));
				}
			}
				
			
			return color;
		}
		
	}
	private  Font getNodetFont(Element element){
		
		if(!element.parent().nodeName().equals("body")){
			Font font=getNodetFont(element.parent());
			int fontstyle=0;
			if(element.nodeName().toLowerCase().equals("em")){
				if(font.getFontData()[0].getStyle()==SWT.BOLD){
					fontstyle=SWT.BOLD|SWT.ITALIC;
				}else if(font.getFontData()[0].getStyle()==(SWT.BOLD|SWT.ITALIC)){
					fontstyle=(SWT.BOLD|SWT.ITALIC);
				}else{
					fontstyle=(SWT.ITALIC);
				}
				
			}else if(element.nodeName().toLowerCase().equals("strong")){
				if(font.getFontData()[0].getStyle()==SWT.ITALIC){
					fontstyle=(SWT.BOLD|SWT.ITALIC);
				}else if(font.getFontData()[0].getStyle()==(SWT.BOLD|SWT.ITALIC)){
					fontstyle=(SWT.BOLD|SWT.ITALIC);
				}else{
					fontstyle=SWT.BOLD;
				}
				
			}else{
				fontstyle=font.getFontData()[0].getStyle();
			}
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			String fontname="";
			String fontsize="";
			if(style==null||style.equals("")){
				fontsize=font.getFontData()[0].getHeight()+"";
				fontname=font.getFontData()[0].getName();
			}else{
				if(style.indexOf("font-family")!=-1){
					String[] str=style.split(":");
					fontname=str[1].substring(0,str[1].length()-1);
					fontname=fontname.trim();
					fontname=fontname.split(",")[0];
					//System.out.println(fontname+"fontname");
					fontsize=font.getFontData()[0].getHeight()+"";
				}else if(style.indexOf("font-size")!=-1){
					String[] str=style.split(":");
					fontsize=str[1].substring(0,str[1].length()-1);
					fontsize=fontsize.trim();
					String size="";
					for(int i=0;i<fontsize.length();i++){
						if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
							size+=fontsize.charAt(i);
						}
					}
					int height=(int)Math.round(Integer.parseInt(size)*72.0/Display.getCurrent().getDPI().y);
					fontsize=height+"";
					fontname=font.getFontData()[0].getName();
				}else{
					fontsize=font.getFontData()[0].getHeight()+"";
					fontname=font.getFontData()[0].getName();
				}
			}
			return new Font(null,new FontData(fontname, Integer.parseInt(fontsize), fontstyle));
		}else{
			Attributes attrs=element.attributes();
			String style=attrs.get("style");
			String fontname="";
			String fontsize="";
			
			if(style==null||style.equals("")){
				int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
				//System.out.println(height);
			//	System.out.println(Display.getCurrent().getDPI().x);
			//	System.out.println(Display.getCurrent().getDPI().y);
				fontsize=height+"";
				fontname="Arial";
			}else{
				if(style.indexOf("font-family")!=-1){
					String[] str=style.split(":");
					fontname=str[1].substring(0,str[1].length()-1);
					fontname=fontname.trim();
					fontname=fontname.split(",")[0];
					int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
					fontsize=height+"";
				}else if(style.indexOf("font-size")!=-1){
					String[] str=style.split(":");
					fontsize=str[1].substring(0,str[1].length()-1);
					fontsize=fontsize.trim();
					String size="";
					for(int i=0;i<fontsize.length();i++){
						if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
							size+=fontsize.charAt(i);
						}
					}
					int height=(int)Math.round(Integer.parseInt(size)*72.0/Display.getCurrent().getDPI().y);
					fontsize=height+"";
					fontname="Arial";
				}else{
					int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
					fontsize=height+"";
					fontname="Arial";
				}	
			}
			int fontstyle=0;
			if(element.nodeName().toLowerCase().equals("em")){
				fontstyle=SWT.ITALIC;
			}else if(element.nodeName().toLowerCase().equals("strong")){
				fontstyle=SWT.BOLD;
			}else{
				fontstyle=SWT.NORMAL;
			}
			Font font=new Font(null,new FontData(fontname, Integer.parseInt(fontsize), fontstyle));
			return font;
		}
		
	}
	private static int getFirstFontSize(Element element,String parentsize){
		Attributes attrs=element.attributes();
		String style=attrs.get("style");
		String fontsize="";
		int fsize=0;
		if(style==null||style.equals("")){
			fontsize=parentsize;
		}else{
			if(style.indexOf("font-size")!=-1){
				String[] str=style.split(":");
				fontsize=str[1].substring(0,str[1].length()-1);
				fontsize=fontsize.trim();
				String size="";
				for(int i=0;i<fontsize.length();i++){
					if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
						size+=fontsize.charAt(i);
					}
				}
				fontsize=size;
			}else{
				fontsize=parentsize;
			}
		}
		fsize=Integer.parseInt(fontsize);
		if(element.children()!=null&&element.children().size()!=0){
			fsize=getFirstFontSize(element.child(0),fontsize);
		}
		return fsize;
	} 
	private static String hexString="0123456789ABCDEF";
	public static String toStringHex(String bytes){
//		if(s.length()!=0){
//		if("0x".equals(s.substring(0,2))){
//			s =s.substring(2);
//		}
//		byte[] baKeyword = new byte[s.length()/2];
//	   	for(int i = 0; i < baKeyword.length; i++){
//	      		try{
//	       		baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
//	      	}
//	      	catch(Exception e){
//	      		e.printStackTrace();
//	      	}
//	   }
//		try{
//	    		s = new String(baKeyword, "utf-8");//UTF-16le:Not
//		} 
//	   	catch (Exception e1){
//	    		e1.printStackTrace();
//	   	}
//		}
//	   	return s;
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
		//将每2位16进制整数组装成一个字节
		for(int i=0;i<bytes.length();i+=2)
		baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
		return new String(baos.toByteArray());
	}

}
