package test.draw2d.text;
import org.jsoup.nodes.Element;


public class CanvasData {
	public Element element;
	public String html;
	public CanvasData(Element element, String html) {
		super();
		this.element = element;
		this.html = html;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
