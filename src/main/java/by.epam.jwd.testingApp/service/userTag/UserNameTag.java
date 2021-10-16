package by.epam.jwd.testingApp.service.userTag;

import by.epam.jwd.testingApp.controller.mapping.AttributeNames;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class UserNameTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Object role = getJspContext().findAttribute(AttributeNames.USER_ROLE);

        if(role!=null) {
            Object name = getJspContext().findAttribute(AttributeNames.NICK_NAME);
            if(name!=null){
                out.write(name.toString());
            }
        }
    }

}
