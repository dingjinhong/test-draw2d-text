package test.draw2d.text;

import javax.xml.soap.Text;

import org.eclipse.draw2d.Graphics;

import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class SB4TextFlow extends TextFlow {
	Composite com;
	int style=0;
	int linewidth=0;
	public SB4TextFlow(Composite c,int s){
		com=c;
		style=s;
	}
	public SB4TextFlow(Composite c,int s,int width){
		com=c;
		style=s;
		linewidth=width;
	}
	protected void paintText(Graphics g, String draw, int x, int y,
			int bidiLevel) {
			GC gc=new GC(com);
		if (bidiLevel == -1) {
			System.out.println(x);
			System.out.println(y);
			System.out.println(draw);
			gc.setFont(g.getFont());
			FontMetrics fm = gc.getFontMetrics();
			Point extent = gc.textExtent(draw);
			if(style==TEXTFLOW.UNDERLINED){
				System.out.println("underlined");
				g.drawText(draw, x, y);
				g.setForegroundColor(this.getForegroundColor());
				g.setLineWidth(linewidth);
				g.drawLine(x-1, y+extent.y-fm.getDescent()+1, x+extent.x-1, y+extent.y-fm.getDescent()+1);
			}else if(style==TEXTFLOW.BGCOLOR){
				System.out.println(this.getBounds().height+"bgcolor");
				System.out.println(this.getSize().height);
				g.setForegroundColor(this.getBackgroundColor());
				g.setBackgroundColor(this.getBackgroundColor());
				g.fillRectangle(x,y,extent.x,fm.getHeight());
				g.setForegroundColor(this.getForegroundColor());
				
				g.drawText(draw, x, y);
			}else if(style==(TEXTFLOW.BGCOLOR|TEXTFLOW.UNDERLINED)){
				System.out.println("underlined and bgcolor");
				g.setForegroundColor(this.getBackgroundColor());
				g.setBackgroundColor(this.getBackgroundColor());
				g.fillRectangle(x,y,extent.x,fm.getHeight());
				g.setForegroundColor(this.getForegroundColor());
				g.drawText(draw, x, y);
				g.drawLine(x-1, y+extent.y-fm.getDescent()+1, x+extent.x-1, y+extent.y-fm.getDescent()+1);
			}else{
				g.drawText(draw, x, y);
			}
//			String underlined="";
//			int i=0;
//			for(i=0;i<draw.length();i++){
//				underlined=underlined+"_";
//			//	System.out.println(i);
//			}
			//System.out.println(i);
		//	g.drawText(underlined,x,y);
		} else {
			TextLayout tl= new TextLayout(Display.getDefault());
			tl.setOrientation(SWT.LEFT_TO_RIGHT);
			if (isMirrored())
				tl.setOrientation(SWT.RIGHT_TO_LEFT);
			tl.setFont(g.getFont());
			tl.setText(draw);
			//g.drawTextLayout(tl, x, y);
			gc.setFont(g.getFont());
			FontMetrics fm = gc.getFontMetrics();
			Point extent = gc.textExtent(draw);
			if(style==TEXTFLOW.UNDERLINED){
				System.out.println("underlined");
				g.drawTextLayout(tl, x, y);
				g.setForegroundColor(this.getForegroundColor());
				g.drawLine(x-1, y+extent.y-fm.getDescent()+1, x+extent.x-1, y+extent.y-fm.getDescent()+1);
			}else if(style==TEXTFLOW.BGCOLOR){
				System.out.println(this.getBounds().height+"bgcolor");
				g.setForegroundColor(this.getBackgroundColor());
				g.setBackgroundColor(this.getBackgroundColor());
				g.fillRectangle(x,y,extent.x,fm.getHeight());
				g.setForegroundColor(this.getForegroundColor());
				
				g.drawTextLayout(tl, x, y);
			}else if(style==(TEXTFLOW.BGCOLOR|TEXTFLOW.UNDERLINED)){
				System.out.println("underlined and bgcolor");
				g.setForegroundColor(this.getBackgroundColor());
				g.setBackgroundColor(this.getBackgroundColor());
				g.fillRectangle(x,y,extent.x,fm.getHeight());
				g.setForegroundColor(this.getForegroundColor());
				g.drawTextLayout(tl, x, y);
				g.drawLine(x-1, y+extent.y-fm.getDescent()+1, x+extent.x-1, y+extent.y-fm.getDescent()+1);
			}else{
				g.drawTextLayout(tl, x, y);
			}
//			gc.setFont(g.getFont());
//			FontMetrics fm = gc.getFontMetrics();
//			Point extent = gc.textExtent(draw);
////			String underlined="";
////			for(int i=0;i<draw.length();i++){
////				underlined=underlined+"_";
////			}
////			g.drawText(underlined,x,y);
//			g.drawLine(x-1, y+extent.y+1-fm.getDescent(), x+extent.x-1, y+extent.y+1-fm.getDescent());
		}
	}
}
