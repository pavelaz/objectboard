package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.BussinessTypeController;
import com.psg.objectboard.model.datatransferobject.BussinessTypeBasicDto;
import com.psg.objectboard.model.datatransferobject.BussinessTypeDto;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessTypeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessTypeVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (name="BussinessTypeServlet", urlPatterns = "/bussinesstype")
public class BussinessTypeServlet extends HttpServlet {

    public BussinessTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        final HttpSession objSesion = request.getSession();

        String company_name = (String) objSesion.getAttribute("companyName");
        String company_number = (String) objSesion.getAttribute("companyNumber");
        String user_name = (String) objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        BussinessTypeDAO controller = null;

        if (request.getMethod().equals("GET")) {
            /* Inicio ****************************     Solo para ejecutar Servlet directamente *****************************/
            if (company_number != null) {
                controller = new BussinessTypeDAO();
                //BussinessTypeVO bussinessTypeDto = controller.getBussinessTypeList();
                //request.setAttribute("rq_BussinessTypeDto", bussinessTypeDto);
                request.setAttribute("rq_userName", user_name);
            }
            /* Fin ****************************     Solo para ejecutar Servlet directamente ****************************
             * Pendiente resolver el resultado de la consulta en cero, cuando no hay usuario reguistrado que hacemos?
             * */

            System.out.println("MasterUserServlet metodo 'GET', muestra informacion de formulario");
        }
        else if (request.getMethod().equals("POST")) {// EL METODO POST SE UTILIZA PARA ENVIOS DE FORMULARIOS SIN ARCHIVOS Y PARA EL ESTADO DE (MODIFICAR).for ()
            if (request.getParameter("arrayTable") != null &&
                    request.getParameter("lengthTable") != null){

                controller = new BussinessTypeDAO();

                //BussinessTypeVO bussinessTypeDto = controller.tableDataConversionToList(request.getParameter("arrayTable"), Integer.parseInt(request.getParameter("lengthTable")));

               // BussinessTypeBasicDto bussinessTypeBasicDto = controller.classificationOfDataTable(bussinessTypeDto);

               // controller.updateBussinessTypeList(bussinessTypeBasicDto);

                //controller.insertBussinessTypeList(bussinessTypeBasicDto);


            }
            if (request.getParameter("arrayDeleteTable") != null &&
                    request.getParameter("lengthTable") != null){

                controller = new BussinessTypeDAO();

                //BussinessTypeVO bussinessTypeDto = controller.tableDataConversionToList(request.getParameter("arrayDeleteTable"), Integer.parseInt(request.getParameter("lengthTable")));

                //controller.deleteBussinessTypeList(bussinessTypeDto);
            }
        }
        else if (request.getMethod().equals("DELETE")) {// EL METODO POST SE UTILIZA PARA ENVIOS DE FORMULARIOS SIN ARCHIVOS Y PARA EL ESTADO DE (MODIFICAR).for ()

        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));
        request.getRequestDispatcher("WEB-INF/pages/jsp/master/bussinessType.jsp").forward(request, response);
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
