package test.draw2d.text;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class CKEditor {
	public static final String CHANGE = "theJavaFunction";
	private Browser browser = null;
	private Map<String, IListener> lis = new HashMap<String, IListener>();
	public IListener getListener(String key) {
		return lis.get(key);
	}

	public void addListener(String key, IListener l) {
		lis.put(key, l);
	}
	public Browser getBrowser() {
		return browser;
	}
	public CKEditor(Composite com){
		browser=new Browser(com,SWT.BORDER);
		final String path=getPluginPath();
		GridData gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=GridData.FILL;
		gd.heightHint=300;
		browser.setLayoutData(gd);
		browser.setUrl(path+"/ckeditor/samples/plugins/djhtest/djhtest.html");
		new AddMarkerFunction(this, CHANGE);
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

}
