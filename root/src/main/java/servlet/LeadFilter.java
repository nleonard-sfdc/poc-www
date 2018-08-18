package servlet;/*
 * Copyright, 1999-2017, salesforce.com All Rights Reserved Company Confidential
 */


import org.apache.commons.io.IOUtils;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author nleonard
 * @since 2018-08
 */
public class LeadFilter extends HttpServlet {

    public static final String FORWARDING_LEADCAPTURE = "https://sheltered-plateau-61056.herokuapp.com/leadcapture/ForwardingLeadCaptureServlet";

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Form form = Form.form();

        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String param = params.nextElement();
            form.add(param, req.getParameter(param));
        }

        Response leadResponse = Request.Post(FORWARDING_LEADCAPTURE)
            .addHeader("True-Client-IP", req.getRemoteAddr())
            .bodyForm(form.build()).execute();

        String confPath = req.getRequestURI();
        IOUtils.copy(req.getServletContext().getResourceAsStream(confPath), resp.getOutputStream());

    }
}
