package test.draw2d.text;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.text.BlockFlow;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestUnderLined {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(1<<1);
		System.out.println(1<<0);
		System.out.println(1<<1|1<<0);
		Display d=Display.getDefault();
		final Shell s=new Shell(d);
		s.setLayout(new FillLayout());
		final FigureCanvas lws=new FigureCanvas(s);
		lws.getViewport().setContentsTracksHeight(true);
		lws.getViewport().setContentsTracksWidth(true);
		final FlowPage page=new FlowPage();
		BlockFlow bf=new BlockFlow();
		GC gc=new GC(s);
		gc.setFont(new Font(null,new FontData("Arial",36,SWT.NORMAL)));
		FontMetrics fm = gc.getFontMetrics();
		Point p=gc.textExtent("_");
		System.out.println(p.x+"x");
		System.out.println(p.y+"y");
		System.out.println(fm.getHeight());
		SB4TextFlow ultf=new SB4TextFlow(s,TEXTFLOW.UNDERLINED,p.y);
		ultf.setFont(new Font(null,new FontData("Arial",36,SWT.NORMAL)));
		ultf.setText("djhg dsd dsd ds ds d ds dsd sdsds ds dsds dsd sd sd sds ");
		bf.add(ultf);
		page.add(bf);
		lws.setContents(page);
		s.open();
		while (!s.isDisposed())
			while (!d.readAndDispatch())
				d.sleep();
	}

}
