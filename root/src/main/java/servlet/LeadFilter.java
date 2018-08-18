package servlet;/*
 * Copyright, 1999-2017, salesforce.com All Rights Reserved Company Confidential
 */


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nleonard
 * @since 2018-08
 */
public class LeadFilter extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String confPath = req.getRequestURI();
        IOUtils.copy(req.getServletContext().getResourceAsStream(confPath), resp.getOutputStream());

    }
}
