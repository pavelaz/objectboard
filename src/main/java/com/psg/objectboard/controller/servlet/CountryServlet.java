package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CountryServlet", urlPatterns = "/country")
public class CountryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        String pantalla = null;
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        String acciones = null;
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }

        CountryVO covo = new CountryVO();
        covo.setCoName("sin nombre");
        String none = "0";
        if (acciones.equals("save")){
            if(request.getParameter("p_pais")!=null){
                none=request.getParameter("p_pais");
            }
            CountryDAO cod = new CountryDAO();
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);
            covo = cod.serchCountryDAO(Integer.parseInt(none));
        }

        request.setAttribute("rq_countryName", covo.getCoName());
        request.setAttribute("rq_countryNumber", Integer.parseInt(none));
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_pantalla", pantalla);
        request.setAttribute("rq_acciones", acciones);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/country.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
