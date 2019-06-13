//package com.candidjava.servlet.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletSignout")
public class ServletSignout extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * This method will allow client to sign out and delete the client's cookie
     *
     * @param request   use to get the client request information
     * @param response  use to send the service to the client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie ck=new Cookie("auth","un");
        ck.setMaxAge(0);
        response.addCookie(ck);
        response.sendRedirect("Signout.html");
    }

}