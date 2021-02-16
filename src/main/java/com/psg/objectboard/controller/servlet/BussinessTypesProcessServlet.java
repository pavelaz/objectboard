package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessTypeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ProjectDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessTypeVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BussinessTypesProcessServlet", value = "/bussinesstypesprocess")
public class BussinessTypesProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        BussinessTypeDAO prodao= new BussinessTypeDAO();
        BussinessTypeVO pvo = new BussinessTypeVO();
        ArrayList<Integer> cual_1 = new ArrayList<Integer>();

        if (acciones.equals("save") || acciones.equals("create") ){
            String none = null;
            if(request.getParameter("p_id")!=null) {
                none = request.getParameter("p_id");
                pvo.setBtCodeType(Long.parseLong(none));
            }
            if(request.getParameter("p_name")!=null) {
                none = request.getParameter("p_name");
                pvo.setBtDescription(none);
            }
            if(request.getParameter("p_note")!=null) {
                none = request.getParameter("p_note");
                pvo.setBtNote(none);
            }
        }
        if (acciones.equals("delete")){
            Integer num_filas = 0;
            String none= null;
            if(request.getParameter("p_cuantos")!=null) {
                none = request.getParameter("p_cuantos");
                num_filas = Integer.parseInt(none);
            }
            for(int f=1 ; f < (num_filas + 1);f++){
                if(request.getParameter("p_selec_"+f)!=null) {
                    none = request.getParameter("p_cual_2"+f);
                    if (cual_1.isEmpty()){
                        cual_1.add(0,Integer.parseInt(none));
                    }else{
                        cual_1.add(Integer.parseInt(none));
                    }
                }
            }
        }

        if (!acciones.equals("consult")) {
            OtherConexion ocn = new OtherConexion();
            Connection con = null;
            con=ocn.conectarse(data_user,data_pasword);
            try {
                // iniciar transacion
                ocn.init_trans(con);
                if (acciones.equals("create")) {
                    prodao.insertBussinessTypeDAO(pvo, con);
                } else {
                    if (acciones.equals("delete")) {
                        pvo.setResult(true);
                        for (int x = 0; x < cual_1.size(); x++) {
                            if (pvo.getResult()) {
                                pvo.setResult(prodao.deleteBussinessTypeDAO(cual_1.get(x), con));
                            }
                        }
                    } else {
                        prodao.updateBussinessTypeDAO(pvo, con);
                    }
                }
                // valida status de la transacion
                ocn.valida_trans(con, pvo.getResult());
                //
                ocn.cierra_coneccion(con);
            } catch (Exception es) {
                System.out.println(es.getMessage());
            }

            request.setAttribute("rq_result", pvo.getResult());
            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);
            request.setAttribute("rq_pantalla", pantalla);
            request.setAttribute("rq_acciones", acciones);
            request.setAttribute("rq_companyNumber", company_number);

            BussinessUnitDAO bud = new BussinessUnitDAO();
            request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

            request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}