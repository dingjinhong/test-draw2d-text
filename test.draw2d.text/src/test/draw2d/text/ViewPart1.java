package test.draw2d.text;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jsoup.nodes.Element;

public class ViewPart1 extends ViewPart implements ISelectionProvider {
	public String html=null;
	public String mode=null;
	public Element element=null;
	private ISelection selection;
	ArrayList MyListeners=new ArrayList();
	public ViewPart1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite com=new Composite(parent,SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginWidth=0;
		gl.marginHeight=0;
		com.setLayout(gl);
		//final CKEditor ckeditor=new CKEditor(com);
		//final Browser browser =ckeditor.getBrowser();
		final Browser browser=new Browser(com,SWT.NONE);
		final String path=getPluginPath();
		GridData gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=GridData.FILL;
		gd.heightHint=500;
		browser.setLayoutData(gd);
		browser.setUrl(path+"/ckeditor/samples/plugins/djhtest/djhtest.html");
//		ckeditor.addListener(CKEditor.CHANGE, new IListener() {
//			public Object function(Object[] arguments) {
//				String h = (String) arguments[0];
//		        element=HTML5ElementUtil.getElement(h);
//		    	String s=HTML5DrawTextUtil.becomeSB4HTML5(h);
//		    	CanvasData cd=new CanvasData(element, s);
//		    	IStructuredSelection selection1=new StructuredSelection(cd);
//		    	setSelection(selection1);
//				return null;
//			}
//		});
		browser.addProgressListener(new ProgressListener() {
            public void changed(ProgressEvent event)
            {
            }
            public void completed(ProgressEvent event)
            {
            	html=(String) browser.evaluate("return getdata()");
            	mode=(String) browser.evaluate("return gettype()");
            	if(mode!=null){
            	if(mode.equals("source")){
            		IStructuredSelection selection1=new StructuredSelection(html);
            		setSelection(selection1);
            	}else{
            		element=HTML5ElementUtil.getElement(html);
            		String s=HTML5DrawTextUtil.becomeSB4HTML5(html);
            		CanvasData cd=new CanvasData(element, s);
            		IStructuredSelection selection1=new StructuredSelection(cd);
            		setSelection(selection1);
            	}
            	}else{
            		element=HTML5ElementUtil.getElement(html);
            		String s=HTML5DrawTextUtil.becomeSB4HTML5(html);
            		CanvasData cd=new CanvasData(element, s);
            		IStructuredSelection selection1=new StructuredSelection(cd);
            		setSelection(selection1);
//            		FileOutputStream fw1 = null;
//					OutputStreamWriter ow=null;
//					BufferedWriter bw1 = null;
//            		try {
//            			fw1=new FileOutputStream(path+"/html/xixi1.html");
//						ow=new OutputStreamWriter(fw1,"UTF-8");
//						bw1=new BufferedWriter(ow);
//						bw1.write(s);
//						bw1.flush();
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
            	}
            	System.out.println(html);
            	System.out.println(mode);
            
            }
          });
		new CustomFunction(browser, "theJavaFunction");
		Button b=new Button(com,SWT.NONE);
		b.setText("click");
		b.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				html=(String) browser.evaluate("return getdata()");
				mode=(String) browser.evaluate("return gettype()");
				if(mode!=null){
				if(mode.equals("source")){
					IStructuredSelection selection1=new StructuredSelection(html);
            		setSelection(selection1);
            	}else{
            		element=HTML5ElementUtil.getElement(html);
            		String s=HTML5DrawTextUtil.becomeSB4HTML5(html);
            		CanvasData cd=new CanvasData(element, s);
            		IStructuredSelection selection1=new StructuredSelection(cd);
            		setSelection(selection1);
            		
//            		FileOutputStream fw1 = null;
//					OutputStreamWriter ow=null;
//					BufferedWriter bw1 = null;
//            		try {
//            			fw1=new FileOutputStream(path+"/html/xixi1.html");
//						ow=new OutputStreamWriter(fw1,"UTF-8");
//						bw1=new BufferedWriter(ow);
//						bw1.write(s);
//						bw1.flush();
//					} catch (FileNotFoundException ee) {
//						// TODO Auto-generated catch block
//						ee.printStackTrace();
//					} catch (UnsupportedEncodingException ee) {
//						// TODO Auto-generated catch block
//						ee.printStackTrace();
//					} catch (IOException ee) {
//						// TODO Auto-generated catch block
//						ee.printStackTrace();
//					}
            	}
				}else{
					element=HTML5ElementUtil.getElement(html);
					String s=HTML5DrawTextUtil.becomeSB4HTML5(html);
            		CanvasData cd=new CanvasData(element, s);
            		IStructuredSelection selection1=new StructuredSelection(cd);
            		setSelection(selection1);
				}
            	System.out.println(html);
     
            	//System.out.println(mode);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		b.pack();
		this.getSite().setSelectionProvider(this);
		//System.out.println(html);
//		String url = path+"ckeditor/samples/plugins/djhtest/djhtest.html";
//		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
//		driver.setJavascriptEnabled(true);
//		driver.get("file:///"+path+"ckeditor/samples/plugins/djhtest/djhtest.html");
//        System.out.println(driver.getPageSource());
//        driver.quit();
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
	             //System.out.println(path);
	             //System.out.println(path.substring(path.indexOf("/")+1,path.length()));
	          //   path=path.substring(path.indexOf("/")+1,path.length());
	             path=new File(path).getAbsolutePath();
	             System.out.println(path);
	       } catch (IOException e) {
	             path="";
	             e.printStackTrace();
	       }
	       return path;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		if(!MyListeners.contains(listener)){
			MyListeners.add(listener);
		}
	}

	@Override
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		MyListeners.remove(listener);
		
	}

	@Override
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub
		this.selection=selection;
		SelectionChangedEvent event2=new SelectionChangedEvent(this,selection);
		for(Iterator i=MyListeners.iterator();i.hasNext();){
			ISelectionChangedListener object=(ISelectionChangedListener) i.next();
			object.selectionChanged(event2);
		}
	}
	 class CustomFunction extends BrowserFunction{

		public CustomFunction(Browser browser, String name) {
			super(browser, name);
			
			// TODO Auto-generated constructor stub
		}
		public Object function (Object[] arguments) {
            String h = (String) arguments[0];
            element=HTML5ElementUtil.getElement(h);
    		String s=HTML5DrawTextUtil.becomeSB4HTML5(h);
    		CanvasData cd=new CanvasData(element, s);
    		IStructuredSelection selection1=new StructuredSelection(cd);
    		setSelection(selection1);
            return null;
        }
		 
	 }

}
