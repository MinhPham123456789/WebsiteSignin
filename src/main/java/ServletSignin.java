//package com.candidjava.servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletSignin")
public class ServletSignin extends HttpServlet {

    /**
     * This method receive the client's username and password to validate
     * and return cookie for the user to access private websites
     *
     * @param request   use to get the client request information
     * @param response  use to send the service to the client
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un= request.getParameter("uname");
        String pw= request.getParameter("pass");

        PrintWriter out=response.getWriter();
        Cookie ck=new Cookie("auth", un);
        ck.setMaxAge(600);
        if(un.equals("a")&pw.equals("a"))
        {
            response.addCookie(ck);
            response.sendRedirect("home.jsp");
            return;
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("ReSignin.html");
            rd.include(request, response);
        }
    }

}