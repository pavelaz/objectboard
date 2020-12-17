package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.sql.Connection;

@WebListener()
public class MyAppServletContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private LogoutServlet logoutServlet;

    private static int totalActiveSessions;

    public static int getTotalActiveSessions() {
        return totalActiveSessions;
    }

    public static void setTotalActiveSessions(int totalActiveSessions) {
        MyAppServletContextListener.totalActiveSessions = totalActiveSessions;
    }

    // Public constructor is required by servlet spec
    public MyAppServletContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        System.out.println("ServletContextListener started");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("ServletContextListener destroyed");

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions++;
        /* Session is created. */
        System.out.println("Total sections open on enter: " + totalActiveSessions);
        System.out.println("ServletContextListener Seccion started");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions--;
        /* Session is destroyed. */
        //request.getRequestDispatcher("WEB-INF/pages/jsp/process/logout.jsp").forward(request, response);
        System.out.println("Total sections open on exit: " + totalActiveSessions);
        System.out.println("ServletContextListener Seccion destroyed");
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse("","");
        //logoutServlet.getServletContext().getRequestDispatcher("WEB-INF/pages/jsp/process/logout.jsp");*/

    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
