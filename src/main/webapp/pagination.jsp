<%@ page import="model.ModelManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    int rowscout = ModelManager.getPagecount();

    if (session.getAttribute("offset") == null) {
        session.setAttribute("offset", 0);
    }

    if (request.getParameter("action").equals("< Previous")) {
        if ((Integer)session.getAttribute("offset") - rowscout >= 0) {
            session.setAttribute("offset", (Integer)session.getAttribute("offset") - rowscout);
        } else {
            session.setAttribute("offset", 0);
        }
    } else if (request.getParameter("action").equals("Next >")) {
        session.setAttribute("offset", (Integer)session.getAttribute("offset") + rowscout);
    }

    System.out.println("offset = " + session.getAttribute("offset"));
    request.getRequestDispatcher("index.jsp").forward(request, response);

%>
