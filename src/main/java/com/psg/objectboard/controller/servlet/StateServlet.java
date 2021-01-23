package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.StateVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "StateServlet", urlPatterns = "/state")
public class StateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        String acciones = null;
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }

        StateVO sovo = new StateVO();
        sovo.setStName("sin nombre");
        String none1 = "0";
        String none2 = "0";

        CountryDAO cod = new CountryDAO();
        ArrayList<CountryVO> paises;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);
        paises = cod.getListCountries("");
        CountryVO covo = new CountryVO();

        if (acciones.equals("save")){
            if(request.getParameter("p_pais")!=null){
                none1=request.getParameter("p_pais");
            }
            if(request.getParameter("p_state")!=null){
                none2=request.getParameter("p_state");
            }
            StateDAO sod = new StateDAO();
            sod.setDataUser(data_user);
            sod.setDataPassword(data_pasword);
            sovo = sod.serchStateDAO(Integer.parseInt(none2),Integer.parseInt(none1));
            covo = cod.serchCountryDAO(Integer.parseInt(none1));
        }

        request.setAttribute("rq_largo", paises.size());
        request.setAttribute("rq_paises", paises);
        request.setAttribute("rq_countryName", covo.getCoName());
        request.setAttribute("rq_countryNumber", Integer.parseInt(none1));
        request.setAttribute("rq_stateNumber", Integer.parseInt(none2));
        request.setAttribute("rq_stateName", sovo.getStName());
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/state.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
