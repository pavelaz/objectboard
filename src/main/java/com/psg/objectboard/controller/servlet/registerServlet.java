package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company_number=null;
        if(request.getParameter("p_company_number")!=null){
            company_number=request.getParameter("p_company_number");
        }
        String company_name=null;
        if(request.getParameter("p_company_name")!=null){
            company_name=request.getParameter("p_company_name");
        }
        String master_superCode=null;
        if(request.getParameter("p_supercode")!=null){
            master_superCode=request.getParameter("p_supercode");
        }
        String user_email=null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String user_name=null;
        if(request.getParameter("p_name")!=null){
            user_name=request.getParameter("p_name");
        }
        String user_password=null;
        if(request.getParameter("p_password")!=null){
            user_password=request.getParameter("p_password");
        }
        String repeat_password=null;
        if(request.getParameter("p_repeat_password")!=null){
            repeat_password=request.getParameter("p_repeat_password");
        }
        String user_question=null;
        if(request.getParameter("p_question")!=null){
            user_question=request.getParameter("p_question");
        }
        String user_answer=null;
        if(request.getParameter("p_answer")!=null){
            user_answer=request.getParameter("p_answer");
        }
        String user_sexo=null;
        if(request.getParameter("p_sexo")!=null){
            user_sexo=request.getParameter("p_sexo");
        }

        BussinessUnitDAO buda = new BussinessUnitDAO();
        String condicion = "bu_bis_code = " + company_number;
        ArrayList<BussinessUnitVO> buvo = buda.getListBussinessUnit(condicion);
        OtherFunctions of = new OtherFunctions();

        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_superCode", master_superCode);
        request.setAttribute("rq_userEmail", user_email);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_userPassword", user_password);
        request.setAttribute("rq_repeatPassword", repeat_password);
        request.setAttribute("rq_userQuestion", user_question);
        request.setAttribute("rq_userAnswer", user_answer);
        request.setAttribute("rq_userSexo", user_sexo);
        request.setAttribute("rq_userSuperC", of.toHex(buvo.get(0).getBuSuperCode()));
        request.setAttribute("rq_userAdminC", of.toHex(buvo.get(0).getBuAdminCode()));
        request.setAttribute("rq_userUser1C", of.toHex(buvo.get(0).getBuUser1Code()));
        request.setAttribute("rq_userUser2C", of.toHex(buvo.get(0).getBuUser2Code()));

        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
