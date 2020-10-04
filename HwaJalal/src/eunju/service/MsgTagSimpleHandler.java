package eunju.service;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MsgTagSimpleHandler extends SimpleTagSupport {
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		out.println("2015156019 송은주");
	}
}
