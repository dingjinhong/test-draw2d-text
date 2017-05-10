package test.draw2d.text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
import org.jsoup.select.Elements;

public class ViewPart2 extends ViewPart implements ISelectionListener{

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
//	Composite com;
//	FigureCanvas fcanvas;
//	public ViewPart2() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void createPartControl(Composite parent) {
//		// TODO Auto-generated method stub
//		com=new Composite(parent,SWT.NONE);
//		GridLayout gl=new GridLayout();
//		gl.marginWidth=30;
//		gl.marginHeight=10;
//		com.setLayout(gl);
//		fcanvas=new FigureCanvas(com);
//		fcanvas.setLayoutData(new GridData(GridData.FILL_BOTH));
//		fcanvas.getViewport().setContentsTracksHeight(true);
//		fcanvas.getViewport().setContentsTracksWidth(true);
//		this.getSite().getPage().addSelectionListener(this);
//	}
//
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//		// TODO Auto-generated method stub
//		boolean flag=false;
//		IStructuredSelection selection1=(IStructuredSelection)selection;
//		Object obj=selection1.getFirstElement();
//		if(obj instanceof HTML5Element){
//		HTML5Element parent=(HTML5Element) obj;
//		if(parent.getName().equals("body")){
//			FlowPage page=new FlowPage();
//			fcanvas.setContents(page);
//			ArrayList<HTML5Element> children=parent.getChildren();
//			for(HTML5Element child:children){
//				if(child.getName().toLowerCase().equals("p")){
//					BlockFlow bf=new BlockFlow();
//					String s=child.getText();
//					if(child.getChildren().size()!=0){
//						for(HTML5Element e:child.getChildren()){
//							if(!e.getText().equals("")){
//								if(e.getName().equals("br")){
//									String[] t=s.split("\n",2);
//									if(t.length>1){
//										s=t[1];
//									}
//									//System.out.println(t[0]+"t0");
//									boolean hasunderlined=false;
//									hasunderlined=isUnderLined(child,hasunderlined);
//									if(hasunderlined){
//										SB4TextFlow text=new SB4TextFlow();
//										Font font=getNodetFont(child);
//										text.setFont(font);
//										text.setForegroundColor(getNodeColor(child));
//										String realys=toStringHex(t[0]);
//										text.setText(realys+"\n");
//										//text.setText(t[0]+"\n");
//										bf.add(text);
//									}else{
//									TextFlow text=new TextFlow();
//									Font font=getNodetFont(child);
//									text.setFont(font);
//									text.setForegroundColor(getNodeColor(child));
//									String realys=toStringHex(t[0]);
//									text.setText(realys+"\n");
//									//text.setText(t[0]+"\n");
//									bf.add(text);
//									}
//								}else{
//									String[] t=s.split(e.getText(),2);
//									if(t.length>1){
//										s=t[1];
//									}
//									boolean hasunderlined=false;
//									hasunderlined=isUnderLined(child,hasunderlined);
//									if(hasunderlined){
//										SB4TextFlow text=new SB4TextFlow();
//										Font font=getNodetFont(child);
//										text.setFont(font);
//										text.setForegroundColor(getNodeColor(child));
//										String realys=toStringHex(t[0]);
//										text.setText(realys);
//										//text.setText(t[0]);
//										bf.add(text);
//										drawText(e,bf,child);
//									}else{
//								TextFlow text=new TextFlow();
////								Attributes attrs=child.getAttrs();
////								String fontname=attrs.get("font-family");
////								if(fontname==null||fontname.equals("")){
////									fontname="Helvetica";
////								}
////								String fontsize=attrs.get("font-size");
////								if(fontsize==null||fontsize.equals("")){
////									fontsize="9";
////								}
////								Font font=new Font(null,fontname,Integer.parseInt(fontsize),SWT.NORMAL);
//								Font font=getNodetFont(child);
//								text.setFont(font);
//								text.setForegroundColor(getNodeColor(child));
//								String realys=toStringHex(t[0]);
//								text.setText(realys);
//								//text.setText(t[0]);
//								bf.add(text);
//							
//								drawText(e,bf,child);
//									}
//								}
//								
//							}
//						}
//						boolean hasunderlined=false;
//						hasunderlined=isUnderLined(child,hasunderlined);
//						if(hasunderlined){
//							SB4TextFlow text=new SB4TextFlow();
//							Font font=getNodetFont(child);
//							text.setFont(font);
//							text.setForegroundColor(getNodeColor(child));
//							String realys=toStringHex(s);
//							text.setText(realys);
//							//text.setText(s);
//							bf.add(text);
//
//						}else{
//						TextFlow text=new TextFlow();
//						Font font=getNodetFont(child);
//						text.setFont(font);
//						text.setForegroundColor(getNodeColor(child));
//						String realys=toStringHex(s);
//						text.setText(realys);
//						//text.setText(s);
//						bf.add(text);
//						}
//					}else{
//						boolean hasunderlined=false;
//						hasunderlined=isUnderLined(child,hasunderlined);
//						if(hasunderlined){
//							SB4TextFlow text=new SB4TextFlow();
//							Font font=getNodetFont(child);
//							text.setFont(font);
//							text.setForegroundColor(getNodeColor(child));
//							String realys=toStringHex(s);
//							text.setText(realys);
//							//text.setText(s);
//							bf.add(text);
//
//						}else{
//						TextFlow text=new TextFlow();
//						Font font=getNodetFont(child);
//						text.setFont(font);
//						text.setForegroundColor(getNodeColor(child));
//						String realys=toStringHex(s);
//						text.setText(realys);
//						//text.setText(s);
//						bf.add(text);
//						}
//						
//					}
//					Attributes attrs=child.getAttrs();
//					String style=attrs.get("style");
//					if(!style.equals("")&&style!=null){
//						if(style.indexOf("text-align")!=-1){
//							String align=style.split(":")[1].trim();
//							align=align.substring(0, align.length()-1);
//							//System.out.println(align);
//							if(align.equals("center")){
//								bf.setHorizontalAligment(PositionConstants.CENTER);
//							}else if(align.equals("right")){
//								bf.setHorizontalAligment(PositionConstants.RIGHT);
//							}
//						}else{
//							
//						}
//					}else{
//						
//					}
//					page.add(bf);
//				}else if(child.getName().toLowerCase().equals("ul")){
//					if(child.getChildren().size()!=0){
//						for(HTML5Element e:child.getChildren()){
//							//String s=child.getText();
//							BlockFlow bf=new BlockFlow();
//							FlowAdapter fa = new FlowAdapter();
//							//int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
//							int fsize=getFirstFontSize(e,"13");
//							System.out.println(fsize+"fsize");
//							GC gc=new GC(fcanvas);
//							gc.setFont(new Font(null,new FontData("Arial",fsize,SWT.NORMAL)));
//							FontMetrics fm = gc.getFontMetrics();
//							int leading=fm.getLeading();
//							System.out.println(leading+"fsize");
//							RoundedRectangle rect1 = new RoundedRectangle();
//							rect1.setForegroundColor(ColorConstants.button);
//							rect1.setPreferredSize(5, (fsize-4-leading)/2+4);
//							//rect1.setForegroundColor(ColorConstants.red);
//							fa.add(rect1);
//							fa.setLayoutManager(new ToolbarLayout(true));
//							Ellipse arc = new Ellipse();
//							arc.setForegroundColor(ColorConstants.black);
//							arc.setBackgroundColor(ColorConstants.black);
//							arc.setPreferredSize(4, 4);
//							fa.add(arc);
//							
//							RoundedRectangle rect = new RoundedRectangle();
//							rect.setForegroundColor(ColorConstants.button);
//							rect.setPreferredSize(5, (fsize-4-leading)/2+4);
//							fa.add(rect);
//							bf.add(fa);
//						//	BlockFlow bf=new BlockFlow();
//							if(!flag){
//								Attributes attrs=e.getAttrs();
//								String style=attrs.get("style");
//								if(!style.equals("")&&style!=null){
//									if(style.indexOf("text-align")!=-1){
//										String align=style.split(":")[1].trim();
//										align=align.substring(0, align.length()-1);
//									//	System.out.println(align);
//										if(align.equals("center")){
//											bf.setHorizontalAligment(PositionConstants.CENTER);
//										}else if(align.equals("right")){
//											bf.setHorizontalAligment(PositionConstants.RIGHT);
//										}
//									}else{
//									
//									}
//								}else{
//								
//								}
//							}else{
//								
//							}
////							if(!e.getText().equals("")){
////								String[] t=s.split(e.getText(),2);
////								if(t.length>1){
////									s=t[1];
////								}
////								TextFlow text=new TextFlow();
//////								Attributes attrs=child.getAttrs();
//////								String fontname=attrs.get("font-family");
//////								if(fontname==null||fontname.equals("")){
//////									fontname="Helvetica";
//////								}
//////								String fontsize=attrs.get("font-size");
//////								if(fontsize==null||fontsize.equals("")){
//////									fontsize="9";
//////								}
//////								Font font=new Font(null,fontname,Integer.parseInt(fontsize),SWT.NORMAL);
////								Font font=getNodetFont(child);
////								text.setFont(font);
////								text.setForegroundColor(getNodeColor(child));
////								text.setText(t[0]);
////								bf.add(text);
//								drawText(e,bf,child);
//								
////							}
//							//System.out.println(bf.getPreferredSize().width+"width");
//							//System.out.println(bf.getPreferredSize().height+"height");
//							page.add(bf);
//						}
////						TextFlow text=new TextFlow();
////						Font font=getNodetFont(child);
////						text.setFont(font);
////						text.setText(s);
////						bf.add(text);
////					}else{
////						TextFlow text=new TextFlow();
////						Font font=getNodetFont(child);
////						text.setFont(font);
////						text.setText(s);
////						bf.add(text);
////					}
//					
//					}
//				}
//				
//			}
//			
//		}
//		}else if(obj instanceof String){
//			FlowPage page=new FlowPage();
//			TextFlow text=new TextFlow();
//			text.setText((String) obj);
//			page.add(text);
//			fcanvas.setContents(page);
//		}
//	}
//	private boolean isUnderLined(HTML5Element element,boolean hasunderlined){
//		if(element.getParent()!=null){
//			hasunderlined=isUnderLined(element.getParent(),hasunderlined);
//			if(element.getName().equals("a")||element.getName().equals("u")){
//				hasunderlined=true;
//			}
//			
//		}else{
//			if(element.getName().equals("a")||element.getName().equals("u")){
//				hasunderlined=true;
//			}
//		}
//		return hasunderlined;
//	}
//	private void drawText(HTML5Element child,BlockFlow bf,HTML5Element parent){
//		String s=child.getText();
//		if(child.getChildren().size()!=0){
//			
//			for(HTML5Element e:child.getChildren()){
//				if(!e.getText().equals("")){
//					
//					if(e.getName().equals("br")){
//						String[] t=s.split("\n",2);
//						if(t.length>1){
//							s=t[1];
//						}
//						boolean hasunderlined=false;
//						hasunderlined=isUnderLined(child,hasunderlined);
//						if(hasunderlined){
//							SB4TextFlow text=new SB4TextFlow();
//							Font font=getNodetFont(child);
//							text.setFont(font);
//							text.setForegroundColor(getNodeColor(child));
//							String realys=toStringHex(t[0]);
//							text.setText(realys+"\n");
//							//text.setText(t+"\n");
//							bf.add(text);
//
//						}else{
//						TextFlow text=new TextFlow();
//						Font font=getNodetFont(child);
//						text.setFont(font);
//						text.setForegroundColor(getNodeColor(child));
//						String realys=toStringHex(t[0]);
//						text.setText(realys+"\n");
//						//text.setText(t[0]+"\n");
//						bf.add(text);
//						}
//					}else{
//						String[] t=s.split(e.getText(),2);
//						if(t.length>1){
//							s=t[1];
//						}
//						boolean hasunderlined=false;
//						hasunderlined=isUnderLined(child,hasunderlined);
//						if(hasunderlined){
//							SB4TextFlow text=new SB4TextFlow();
//							Font font=getNodetFont(child);
//							text.setFont(font);
//							text.setForegroundColor(getNodeColor(child));
//							String realys=toStringHex(t[0]);
//							text.setText(realys);
//							bf.add(text);
//							drawText(e,bf,child);
//						}else{
//					TextFlow text=new TextFlow();
////					Attributes attrs=child.getAttrs();
////					String fontname=attrs.get("font-family");
////					if(fontname==null||fontname.equals("")){
////						fontname="Helvetica";
////					}
////					String fontsize=attrs.get("font-size");
////					if(fontsize==null||fontsize.equals("")){
////						fontsize="9";
////					}
////					Font font=new Font(null,fontname,Integer.parseInt(fontsize),SWT.NORMAL);
//					Font font=getNodetFont(child);
//					text.setFont(font);
//					text.setForegroundColor(getNodeColor(child));
//					String realys=toStringHex(t[0]);
//					text.setText(realys);
//					bf.add(text);
//				
//					drawText(e,bf,child);
//						}
//					}
//			//		TextFlow text=new TextFlow();
////					if(child.getName().toLowerCase().equals("strong")){
////						Attributes attrs=parent.getAttrs();
////						String fontname=attrs.get("font-family");
////						if(fontname==null||fontname.equals("")){
////							fontname="Helvetica";
////						}
////						String fontsize=attrs.get("font-size");
////						if(fontsize==null||fontsize.equals("")){
////							fontsize="9";
////						}
////						Attributes childattrs=child.getAttrs();
////						String childfontname=childattrs.get("font-family");
////						if(childfontname==null||childfontname.equals("")){
////							childfontname=fontname;
////						}
////						String childfontsize=childattrs.get("font-size");
////						if(childfontsize==null||childfontsize.equals("")){
////							childfontsize=fontsize;
////						}
////						Font font=new Font(null,childfontname,Integer.parseInt(childfontsize),SWT.BOLD);
////						text.setFont(font);
////					}
////					if(child.getName().toLowerCase().equals("em")){
////						Attributes attrs=parent.getAttrs();
////						String fontname=attrs.get("font-family");
////						if(fontname==null||fontname.equals("")){
////							fontname="Helvetica";
////						}
////						String fontsize=attrs.get("font-size");
////						if(fontsize==null||fontsize.equals("")){
////							fontsize="9";
////						}
////						
////						Attributes childattrs=child.getAttrs();
////						String childfontname=childattrs.get("font-family");
////						if(childfontname==null||childfontname.equals("")){
////							childfontname=fontname;
////						}
////						String childfontsize=childattrs.get("font-size");
////						if(childfontsize==null||childfontsize.equals("")){
////							childfontsize=fontsize;
////						}
////						Font font=new Font(null,childfontname,Integer.parseInt(childfontsize),SWT.ITALIC);
////						text.setFont(font);
////					}
//				//	Font font=getNodetFont(child);
//				//	text.setFont(font);
//				//	text.setForegroundColor(getNodeColor(child));
//				//	text.setText(t[0]);
//				//	bf.add(text);
//				//	drawText(e,bf,child);
//				}
//			}
//			boolean hasunderlined=false;
//			hasunderlined=isUnderLined(child,hasunderlined);
//			if(hasunderlined){
//				SB4TextFlow text=new SB4TextFlow();
//				Font font=getNodetFont(child);
//				text.setFont(font);
//				text.setForegroundColor(getNodeColor(child));
//				s=toStringHex(s);
//				text.setText(s);
//				bf.add(text);
//			}else{
//			TextFlow text=new TextFlow();
//			Font font=getNodetFont(child);
//			text.setForegroundColor(getNodeColor(child));
//			text.setFont(font);
//			s=toStringHex(s);
//			text.setText(s);
//			bf.add(text);
//			}
//		}else{
//			boolean hasunderlined=false;
//			hasunderlined=isUnderLined(child,hasunderlined);
//			if(hasunderlined){
//				SB4TextFlow text=new SB4TextFlow();
//				Font font=getNodetFont(child);
//				text.setFont(font);
//				text.setForegroundColor(getNodeColor(child));
//				s=toStringHex(s);
//				text.setText(s);
//				bf.add(text);
//			}else{
//			TextFlow text=new TextFlow();
////			if(child.getName().toLowerCase().equals("strong")){
////				Attributes attrs=parent.getAttrs();
////				String fontname=attrs.get("font-family");
////				System.out.println(fontname+"fontname");
////				if(fontname==null||fontname.equals("")){
////					fontname="Helvetica";
////				}
////				System.out.println(fontname+"fontname");
////				String fontsize=attrs.get("font-size");
////				System.out.println(fontsize+"fontsize");
////				if(fontsize==null||fontsize.equals("")){
////					fontsize="9";
////				}
////				System.out.println(fontsize+"fontsize");
////				Attributes childattrs=child.getAttrs();
////				String childfontname=childattrs.get("font-family");
////				if(childfontname==null||childfontname.equals("")){
////					childfontname=fontname;
////				}
////				String childfontsize=childattrs.get("font-size");
////				if(childfontsize==null||childfontsize.equals("")){
////					childfontsize=fontsize;
////				}
////				Font font=new Font(null,childfontname,Integer.parseInt(childfontsize),SWT.BOLD);
////				text.setFont(font);
////			}
////			if(child.getName().toLowerCase().equals("em")){
////				Attributes attrs=parent.getAttrs();
////				String fontname=attrs.get("font-family");
////				if(fontname==null||fontname.equals("")){
////					fontname="Helvetica";
////				}
////				String fontsize=attrs.get("font-size");
////				if(fontsize==null||fontsize.equals("")){
////					fontsize="9";
////				}
////				Attributes childattrs=child.getAttrs();
////				String childfontname=childattrs.get("font-family");
////				if(childfontname==null||childfontname.equals("")){
////					childfontname=fontname;
////				}
////				String childfontsize=childattrs.get("font-size");
////				if(childfontsize==null||childfontsize.equals("")){
////					childfontsize=fontsize;
////				}
////				Font font=new Font(null,childfontname,Integer.parseInt(childfontsize),SWT.ITALIC);
//				Font font=getNodetFont(child);
//				text.setFont(font);
//				text.setForegroundColor(getNodeColor(child));
////			}
//				s=toStringHex(s);
//			text.setText(s);
//			bf.add(text);
//			}
//		}
//	}
//	private Color getNodeColor(HTML5Element element){
//		if(element.getParent()!=null){
//			Color color=getNodeColor(element.getParent());
//			Color c=null;
//			Attributes attrs=element.getAttrs();
//			String style=attrs.get("style");
//			if(style==null||style.equals("")){
//				c=color;
//			}else{
//				if(style.indexOf("color")!=-1){
//					String[] str=style.split(":");
//					String rgb=str[1].trim();
//					Pattern pattern = Pattern.compile("\\((.*?)\\)"); 
//				    Matcher matcher = pattern.matcher(rgb);
//				    if (matcher.find()){
//				        rgb=matcher.group(1);
//				     //   System.out.println(rgb);
//				        String[] str1=rgb.split(",");
//				        if(str1.length>=3){
//				        	String r=str1[0].trim();
//				        	String g=str1[1].trim();
//				        	String b=str1[2].trim();
//				        	int red=Integer.parseInt(r);
//				        	int green=Integer.parseInt(g);
//				        	int blue=Integer.parseInt(b);
//				        	//System.out.println(red);
//				        	//System.out.println(green);
//				        	//System.out.println(blue);
//				        	c=new Color(null,new RGB(red,green,blue));
//				        }else{
//				        	c=color;
//				        }
//				    }else{
//				    	c=color;
//				    }
//				}else{
//					c=color;
//				}
//				
//			}
//			return c;
//			
//		}else{
//			Attributes attrs=element.getAttrs();
//			String style=attrs.get("style");
//			Color color=null;
//			if(style==null||style.equals("")){
//				color=new Color(null,new RGB(0,0,0));
//			}else{
//				if(style.indexOf("color")!=-1){
//					String[] str=style.split(":");
//					String rgb=str[1].trim();
//					Pattern pattern = Pattern.compile("((.*?))"); //中文括号 
//				    Matcher matcher = pattern.matcher(rgb);
//				    if (matcher.find()){
//				        rgb=matcher.group(1);
//				        String[] str1=rgb.split(",");
//				        if(str1.length>=3){
//				        	String r=str1[0].trim();
//				        	String g=str1[1].trim();
//				        	String b=str1[2].trim();
//				        	int red=Integer.parseInt(r);
//				        	int green=Integer.parseInt(g);
//				        	int blue=Integer.parseInt(b);
//				        	color=new Color(null,new RGB(red,green,blue));
//				        }else{
//				        	color=new Color(null,new RGB(0,0,0));
//				        }
//				    }else{
//				    	color=new Color(null,new RGB(0,0,0));
//				    }
//				}else{
//					color=new Color(null,new RGB(0,0,0));
//				}
//			}
//				
//			
//			return color;
//		}
//		
//	}
//	private  Font getNodetFont(HTML5Element element){
//		
//		if(element.getParent()!=null){
//			Font font=getNodetFont(element.getParent());
//			int fontstyle=0;
//			if(element.getName().toLowerCase().equals("em")){
//				if(font.getFontData()[0].getStyle()==SWT.BOLD){
//					fontstyle=SWT.BOLD|SWT.ITALIC;
//				}else if(font.getFontData()[0].getStyle()==(SWT.BOLD|SWT.ITALIC)){
//					fontstyle=(SWT.BOLD|SWT.ITALIC);
//				}else{
//					fontstyle=(SWT.ITALIC);
//				}
//				
//			}else if(element.getName().toLowerCase().equals("strong")){
//				if(font.getFontData()[0].getStyle()==SWT.ITALIC){
//					fontstyle=(SWT.BOLD|SWT.ITALIC);
//				}else if(font.getFontData()[0].getStyle()==(SWT.BOLD|SWT.ITALIC)){
//					fontstyle=(SWT.BOLD|SWT.ITALIC);
//				}else{
//					fontstyle=SWT.BOLD;
//				}
//				
//			}else{
//				fontstyle=font.getFontData()[0].getStyle();
//			}
//			Attributes attrs=element.getAttrs();
//			String style=attrs.get("style");
//			String fontname="";
//			String fontsize="";
//			if(style==null||style.equals("")){
//				fontsize=font.getFontData()[0].getHeight()+"";
//				fontname=font.getFontData()[0].getName();
//			}else{
//				if(style.indexOf("font-family")!=-1){
//					String[] str=style.split(":");
//					fontname=str[1].substring(0,str[1].length()-1);
//					fontname=fontname.trim();
//					fontname=fontname.split(",")[0];
//					//System.out.println(fontname+"fontname");
//					fontsize=font.getFontData()[0].getHeight()+"";
//				}else if(style.indexOf("font-size")!=-1){
//					String[] str=style.split(":");
//					fontsize=str[1].substring(0,str[1].length()-1);
//					fontsize=fontsize.trim();
//					String size="";
//					for(int i=0;i<fontsize.length();i++){
//						if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
//							size+=fontsize.charAt(i);
//						}
//					}
//					int height=(int)Math.round(Integer.parseInt(size)*72.0/Display.getCurrent().getDPI().y);
//					fontsize=height+"";
//					fontname=font.getFontData()[0].getName();
//				}else{
//					fontsize=font.getFontData()[0].getHeight()+"";
//					fontname=font.getFontData()[0].getName();
//				}
//			}
//			return new Font(null,new FontData(fontname, Integer.parseInt(fontsize), fontstyle));
//		}else{
//			Attributes attrs=element.getAttrs();
//			String style=attrs.get("style");
//			String fontname="";
//			String fontsize="";
//			
//			if(style==null||style.equals("")){
//				int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
//				//System.out.println(height);
//			//	System.out.println(Display.getCurrent().getDPI().x);
//			//	System.out.println(Display.getCurrent().getDPI().y);
//				fontsize=height+"";
//				fontname="Arial";
//			}else{
//				if(style.indexOf("font-family")!=-1){
//					String[] str=style.split(":");
//					fontname=str[1].substring(0,str[1].length()-1);
//					fontname=fontname.trim();
//					fontname=fontname.split(",")[0];
//					int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
//					fontsize=height+"";
//				}else if(style.indexOf("font-size")!=-1){
//					String[] str=style.split(":");
//					fontsize=str[1].substring(0,str[1].length()-1);
//					fontsize=fontsize.trim();
//					String size="";
//					for(int i=0;i<fontsize.length();i++){
//						if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
//							size+=fontsize.charAt(i);
//						}
//					}
//					int height=(int)Math.round(Integer.parseInt(size)*72.0/Display.getCurrent().getDPI().y);
//					fontsize=height+"";
//					fontname="Arial";
//				}else{
//					int height=(int) Math.round(13*72.0/Display.getCurrent().getDPI().y);
//					fontsize=height+"";
//					fontname="Arial";
//				}	
//			}
//			int fontstyle=0;
//			if(element.getName().toLowerCase().equals("em")){
//				fontstyle=SWT.ITALIC;
//			}else if(element.getName().toLowerCase().equals("strong")){
//				fontstyle=SWT.BOLD;
//			}else{
//				fontstyle=SWT.NORMAL;
//			}
//			Font font=new Font(null,new FontData(fontname, Integer.parseInt(fontsize), fontstyle));
//			return font;
//		}
//		
//	}
//	private static int getFirstFontSize(HTML5Element element,String parentsize){
//		Attributes attrs=element.getAttrs();
//		String style=attrs.get("style");
//		String fontsize="";
//		int fsize=0;
//		if(style==null||style.equals("")){
//			fontsize=parentsize;
//		}else{
//			if(style.indexOf("font-size")!=-1){
//				String[] str=style.split(":");
//				fontsize=str[1].substring(0,str[1].length()-1);
//				fontsize=fontsize.trim();
//				String size="";
//				for(int i=0;i<fontsize.length();i++){
//					if(fontsize.charAt(i)>=48 && fontsize.charAt(i)<=57){
//						size+=fontsize.charAt(i);
//					}
//				}
//				fontsize=size;
//			}else{
//				fontsize=parentsize;
//			}
//		}
//		fsize=Integer.parseInt(fontsize);
//		if(element.getChildren()!=null&&element.getChildren().size()!=0){
//			fsize=getFirstFontSize(element.getChildren().get(0),fontsize);
//		}
//		return fsize;
//	} 
//	private static String hexString="0123456789ABCDEF";
//	public static String toStringHex(String bytes){
////		if(s.length()!=0){
////		if("0x".equals(s.substring(0,2))){
////			s =s.substring(2);
////		}
////		byte[] baKeyword = new byte[s.length()/2];
////	   	for(int i = 0; i < baKeyword.length; i++){
////	      		try{
////	       		baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
////	      	}
////	      	catch(Exception e){
////	      		e.printStackTrace();
////	      	}
////	   }
////		try{
////	    		s = new String(baKeyword, "utf-8");//UTF-16le:Not
////		} 
////	   	catch (Exception e1){
////	    		e1.printStackTrace();
////	   	}
////		}
////	   	return s;
//		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
//		//将每2位16进制整数组装成一个字节
//		for(int i=0;i<bytes.length();i+=2)
//		baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
//		return new String(baos.toByteArray());
//	}
//	

}
