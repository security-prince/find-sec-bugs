package testcode.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.owasp.esapi.ESAPI.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.owasp.esapi.ESAPI;

public class XssServlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");

        resp.getWriter().write(input1);

        resp.getWriter().write(ESAPI.encoder().encodeForHTML(input1));
        resp.getWriter().write(StringEscapeUtils.escapeHtml(input1));
    }
}
