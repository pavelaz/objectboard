package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CountryDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CityVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CountryVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.StateVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CityServlet", urlPatterns = "/city")
public class CityServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String) objSesion.getAttribute("companyName");
        String user_name = (String) objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        String acciones = null;
        if (request.getParameter("p_acciones") != null) {
            acciones = request.getParameter("p_acciones");
        }

        if (acciones.equals("create")) {
            CountryDAO cod = new CountryDAO();
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);
            ArrayList<CountryVO> paises;
            paises = cod.getListCountries("");

            StateDAO sod = new StateDAO();
            sod.setDataUser(data_user);
            sod.setDataPassword(data_pasword);
            ArrayList<StateVO> states = new ArrayList<StateVO>();
            Integer[][] sta_cod = new Integer[paises.size()][100];
            String[][] sta_nom = new String[paises.size()][100];
            Integer[] sta_largos = new Integer[paises.size()];
            for (int i = 0; i < paises.size(); i++) {
                String condicion = "country_co_country_code=" + paises.get(i).getCoCountryCode();
                states = sod.getListStates(condicion);
                if (states.size() != 0) {
                    for (int s = 0; s < states.size(); s++) {
                        sta_cod[i][s] = Math.toIntExact(states.get(s).getStStateCode());
                        sta_nom[i][s] = states.get(s).getStName();
                    }
                    sta_largos[i] = states.size();
                } else {
                    sta_cod[i][0] = Math.toIntExact(0);
                    sta_nom[i][0] = "[Select a State]";
                    sta_largos[i] = 1;
                }

            }

            request.setAttribute("rq_paises", paises);
            request.setAttribute("rq_largoPaises", paises.size());
            request.setAttribute("rq_largos_states", sta_largos);
            request.setAttribute("rq_staCod", sta_cod);
            request.setAttribute("rq_staNom", sta_nom);
        }else{
            String none = null;
            Integer country_number = 0;
            if (request.getParameter("p_country") != null) {
                none = request.getParameter("p_country");
                country_number = Integer.valueOf(none);
            }
            Integer state_number = 0;
            if (request.getParameter("p_state") != null) {
                none = request.getParameter("p_state");
                state_number = Integer.valueOf(none);
            }
            Integer city_number = 0;
            if (request.getParameter("p_city") != null) {
                none = request.getParameter("p_city");
                city_number = Integer.valueOf(none);
            }
            CityDAO ccd = new CityDAO();
            ccd.setDataUser(data_user);
            ccd.setDataPassword(data_pasword);
            CityVO cvo = ccd.serchCityDAO(city_number,state_number,country_number);
            StateDAO std = new StateDAO();
            std.setDataUser(data_user);
            std.setDataPassword(data_pasword);
            StateVO svo = std.serchStateDAO(state_number,country_number);
            CountryDAO cod = new CountryDAO();
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);
            CountryVO covo = cod.serchCountryDAO(country_number);

            request.setAttribute("rq_countryNumber", country_number);
            request.setAttribute("rq_stateNumber", state_number);
            request.setAttribute("rq_cityNumber", city_number);
            request.setAttribute("rq_cityName", cvo.getCiName());
            request.setAttribute("rq_stateName", svo.getStName());
            request.setAttribute("rq_countryName", covo.getCoName());
        }
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/city.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}