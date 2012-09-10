package com.dkord;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Peter Gyschuk
 */
@WebServlet(name="DeliveryServlet", urlPatterns={"/*"})
public class DeliveryServlet extends AbstractApplicationServlet {
    
    @EJB
    EJBAccessLocal ejbAccess;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return DeliveryApp.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return new DeliveryApp(ejbAccess);
    }

   
}
