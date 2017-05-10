package test.draw2d.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ViewPart4 extends ViewPart implements ISelectionListener{
	Browser browser;
	public ViewPart4() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		String path=getPluginPath();
		Composite com=new Composite(parent,SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginWidth=0;
		gl.marginHeight=0;
		com.setLayout(gl);
		browser=new Browser(com,SWT.NONE);
		GridData gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=GridData.FILL;
		gd.grabExcessVerticalSpace=true;
		gd.verticalAlignment=GridData.FILL;
		browser.setLayoutData(gd);
		browser.setUrl(path+"/html/testCanvasDrawText.html");
		Display.getCurrent().getActiveShell().addListener (SWT.Resize, new Listener () {
		    public void handleEvent (Event e)
		    {
		    	browser.refresh();
		    }
		});
		this.getSite().getPage().addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private String getPluginPath(){
		 String path;
		// System.out.println(Platform.getBundle("SB3editor.html5tool").getVersion().toString());
	       try {
	             path= Platform.asLocalURL(Platform.getBundle("test.draw2d.text").getEntry("")).getPath();
	             path=new File(path).getAbsolutePath();
	           //  path=path.substring(path.indexOf("/")+1,path.length());
	       } catch (IOException e) {
	             path="";
	             e.printStackTrace();
	       }
	       return path;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		String path=getPluginPath();
		IStructuredSelection selection1=(IStructuredSelection)selection;
		Object obj=selection1.getFirstElement();
		if(obj instanceof CanvasData){
			CanvasData cd=(CanvasData) obj;
			String s=cd.getHtml();
			System.out.println(s+"zhongwen");
			s=s.replaceAll("&nbsp;"," ");
			s=s.replaceAll(" \n","");
			System.out.println(s+"zhongwen");
    		FileOutputStream fw1 = null;
			OutputStreamWriter ow=null;
			BufferedWriter bw1 = null;
    		try {
    			fw1=new FileOutputStream(path+"/html/xml.js");
				ow=new OutputStreamWriter(fw1,"UTF-8");
				bw1=new BufferedWriter(ow);
				bw1.write("xml='<haha>"+s+"</haha>'");
				bw1.flush();
				fw1.close();
				ow.close();
				bw1.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		browser.refresh();
		}
	}


}
