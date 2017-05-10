package test.draw2d.text;

import org.eclipse.swt.browser.BrowserFunction;

public class AddMarkerFunction extends BrowserFunction {
	private CKEditor ckeditor;

	public AddMarkerFunction(CKEditor ckeditor, String name) {
		super(ckeditor.getBrowser(), name);
		this.ckeditor = ckeditor;
	}

	public Object function(Object[] arguments) {
		IListener listener = ckeditor.getListener(CKEditor.CHANGE);
		if (listener != null) {
			return listener.function(arguments);
		}
		return null;
	}

	public CKEditor getMap() {
		return ckeditor;
	}
}