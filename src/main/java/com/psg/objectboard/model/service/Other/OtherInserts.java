package com.psg.objectboard.model.service.Other;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;

import java.io.*;
import java.time.*;

public class OtherInserts {
    private String answer="";
    public String linkMedio="";
    private OtherFunctions of = null;

    // genera cuerpos de email predeterminados
    public String bodyConfirmRegister(MasterUserVO muv,String company) throws IOException{
        String link;
        of = new OtherFunctions();
        of.searchLink();
        link = "http://" + this.linkMedio
                + "/objectboard"
                + "/confirmemail?pu="
                + muv.getMuEmail()+ "&ps="+ muv.getMuConfirmCode()+"&pc="+muv.getBussinessUnitBuBisCode();
        answer = "<h1>Our warmest regards</h1><p><br>"
                + "It is a pleasure to inform you that you have successfully registered on our portal, so we have sent you "
                + "in this email a link to activate your account, after this you can already use the portal with the modules "
                + "which are activated by default. Please keep this email, which could be useful in case you forget all "
                + "or part of the information with which you registered, which we attach below:"
                + "</p>"
                + "<p>Company: "+ company.toUpperCase() + "</p>"
                + "<p>User Name: "+muv.getMuName()+"</p>"
                + "<p>Login Email: "+muv.getMuEmail()+"</p>"
                + "<p>Password: "+muv.getMuPassword()+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>Email Confirmation Link:</p>"
                + "<a href='" + link + "'>Confirm email address</a>";
        return answer;
    }

    public String bodyResetPassword(MasterUserVO muv,String company) throws IOException {
        String link,newPassword;
        LocalDateFunctions locDat = new LocalDateFunctions();
        LocalDate fecha = locDat.obtenerFechaActual();
        of = new OtherFunctions();
        newPassword= of.generateRandomString(8);
        of.searchLink();
        link = "http://" + this.linkMedio // esta parte debe sustituirse al poner el sistema en produccion
                // + "/pages/jsp/process/confirm_reset.jsp?pu="
                + "/objectboard"
                + "/confirmreset?pu="
                + muv.getMuEmail() + "&ps="
                + newPassword + "&pc="
                + muv.getBussinessUnitBuBisCode() + "&pf="
                + fecha;
        answer = "<h1>Our warmest regards</h1><p><br>"
                + "It is with pleasure to inform you that after you click on the link that appears in this email, the new \n"
                + "password for your account will be as follows: "
                + "</p>"
                + "<p>Company: "+ company.toUpperCase() + "</p>"
                + "<p>Valid Date: "+ fecha + "</p>"
                + "<p>Login Email: "+muv.getMuEmail()+"</p>"
                + "<p>New Password: "+newPassword+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>Email reset Link:</p>"
                + "<a href='" + link + "'>Reset Password System --></a>"
                + "<p>&nbsp;</p>"
                + "<p>This email will only be valid within the validity date that appears in the email. After this time,\n"
                + "if the link has not been followed, you must request the reset of your password again.</p>";
        return answer;
    }

    public String bodyInformationRequest(String nombre,String correo,String telefono,String mensaje, String fecha) throws IOException {
        answer = "<h1>Our warmest regards</h1><p><br>"
                + "We have received the following request for information under the following parameters: \n"
                + "</p>"
                + "<p>&nbsp;</p>"
                + "<p>Name: "+ nombre.toUpperCase() + "</p>"
                + "<p>Email: "+ correo + "</p>"
                + "<p>Phone: "+ telefono +"</p>"
                + "<p>Date  Request: "+fecha+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>Message:</p>"
                + "<p>Message: "+mensaje+"</p>"
                + "<p>&nbsp;</p>"
                + "<p>In a short period of time, our staff will contact you by any of the available channels.</p>";
        return answer;
    }

    // Seccion para auditsRevision.jsp
    public String valida_result(Long code,Integer evento,Integer typeRequest,String answer,String answerSolution,
                                String annexType,String onlyText,Double onlyNumber,String onlyTime,String onlyDate,
                                Integer question){
        String none = "";
        if(evento==1) {
            none = none + "<div class='card-footer'>\n";
        }
        none = none + "<hr>\n";
        none = none + "<div class=\"form-group row\">\n";
        if (annexType.equals("0")){
            none = none + "<div class=\"col-md-12\" align=\"right\">\n";
            none = none + "<input type=\"hidden\" id=\"comen\" name=\"p_comen_" + question + "\"  value=\" \">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Result of the audit:</label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;\n";
            if (typeRequest == 1 || typeRequest==2 || typeRequest==3){
                if (answer.equals(answerSolution)){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }
            }
            if (typeRequest == 6){
                if (onlyText.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyText)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest == 7){
                if (onlyNumber==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (Double.parseDouble(answer) == onlyNumber){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==8){
                if (onlyTime.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyTime)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==9){
                if (onlyDate.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyDate)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked disabled>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
        }else{
            none = none + "<div class=\"col-md-2\" align=\"left\">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Audit Comment:</label>\n";
            none = none + "</div>\n";
            none = none + "<div class=\"col-md-4\" align=\"left\">\n";
            none = none + "<input type=\"text\" id=\"comen\" name=\"p_comen_" + question + "\" class=\"form-control\" maxlength=\"145\" placeholder=\"Comment the result\">\n";
            none = none + "</div>\n";
            none = none + "<div class=\"col-md-6\" align=\"right\">\n";
            none = none + "<label for=\"co\" class=\"col-form-label\">Result of the audit:</label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;\n";
            if (typeRequest == 1 || typeRequest==2 || typeRequest==3){
                if (answer.equals(answerSolution)){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }
            }
            if (typeRequest == 4 || typeRequest == 5){
                none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
            }
            if (typeRequest == 6){
                if (onlyText.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyText)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest == 7){
                if (onlyNumber==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (Double.parseDouble(answer) == onlyNumber){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==8){
                if (onlyTime.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyTime)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
            if (typeRequest==9){
                if (onlyDate.trim().length()==0){
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                    none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                    none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                }else{
                    if (answer.equals(onlyDate)){
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\" >&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\" checked>&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }
                }
            }
        }

        none = none + "</div>\n";
        none = none + "</div>\n";
        if(evento==1) {
            none = none + "</div>\n";
        }
        return none;
    }

    public String valida_comentario(Integer format,Long code,String coment){
        String none = "";
        none = none + "<div class=\"form-group row\">\n";
        none = none + "<div class=\"col-sm-2\">\n";
        none = none + "<label for=\"co\" class=\"col-form-label\">Comment :</label>\n";
        none = none + "</div>\n";
        none = none + "<div class=\"col-sm-10\" >\n";
        if (format == 2) {
            none = none + "<textarea id=\"co\" name=\"p_co_" + code + "\" rows=\"1\" maxlength=\"145\"\n" +
                    "            cols=\"80\" class=\"form-control\" placeholder=\"Write here the comment.\" onfocus=\"selecciona_contenido(this)\"\n" +
                    "                   disabled >\n";
            none = none + coment + "</textarea>";
        }else {
            none = none + "<textarea id=\"co\" name=\"p_co_" + code + "\" rows=\"1\" maxlength=\"145\"\n" +
                    "            cols=\"80\" class=\"form-control\" placeholder=\"Write here the comment.\" onfocus=\"selecciona_contenido(this)\" disabled></textarea>";
        }
        none = none + "</div>\n";
        none = none + "</div>\n";
        return none;
    }

    public String valida_tipoArchivo(Integer ctaLinea,String annexType, String directorio_gral, Integer format,
                                     String company,Long id,Long question,Long survey,String userEmail,String sumario){
        String none = "";
        if (ctaLinea == 1){
            if (annexType.equals("1")){
                none = none + "Validate the document:\n";
            }else{
                none = none + "Validate the image:\n";
            }
        }
        String ruta = null;

        if (ctaLinea == 2) {
            if (annexType.equals("1")) {
                if(format == 2) {
                    ruta = "showfile.html?p_unit=" + company + "&p_archivo=2&p_conduct=" + id +
                            "&p_question=" + question + "&p_survey=" + survey;
                    none = none + "<a  href='" + ruta + "' target='_blank' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            } else {
                if(format == 2) {
                    try {
                        MasterUserVO muv = null;
                        MasterUserDAO mud = new MasterUserDAO();
                        muv = mud.serchMasterUserDAO(userEmail,company);
                        of = new OtherFunctions();
                        //  Primer link
                        ruta = of.searchLink("8") + "/cardImage/" + muv.getMuName() + "/Auditor/" + sumario + "/";
                        ruta = ruta + muv.getMuDataUser() + "/" + muv.getMuDataPassword() + "/";
                        ruta = ruta + company + "/" + userEmail + "/";
                        // Segundo link
                        ruta = ruta + "2" + "/" + survey + "/" + question + "/" + id + "/";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    none = none + "<a  href='" + ruta + "' target='_blank' title=\"View Image\"  >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Image\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }
        }
        return none;
    }

    public String validacionGral_tipoArchivo(Integer type_request,String directorio_gral,Integer format,
                                             String company,Long id,Long question,Long survey,String userEmail,String sumario)  {
        String none = "";
        String ruta = null;

        if (type_request == 5) {
            if (format == 2) {
                try {
                    MasterUserVO muv = null;
                    MasterUserDAO mud = new MasterUserDAO();
                    muv = mud.serchMasterUserDAO(userEmail,company);
                    of = new OtherFunctions();
                    //  Primer link
                    ruta = of.searchLink("8") + "/cardImage/" + muv.getMuName() + "/Auditor/" + sumario + "/";
                    ruta = ruta + muv.getMuDataUser() + "/" + muv.getMuDataPassword() + "/";
                    ruta = ruta + company + "/" + userEmail + "/";
                    // Segundo link
                    ruta = ruta + "2" + "/" + survey + "/" + question + "/" + id + "/";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                none = none + "<a  href='" + ruta + "' target='_blank' title=\"View Image\" >\n";
                none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                none = none + "</a>\n";
            } else {
                none = none + "<a href='#!' title=\"View Image\" >\n";
                none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                none = none + "</a>\n";
            }
        }else{
            if (format == 2) {
                ruta = "showfile.html?p_unit=" + company + "&p_archivo=2&p_conduct=" + id +
                        "&p_question=" + question + "&p_survey=" + survey;
                none = none + "<a  href='" + ruta + "' target='_blank' title=\"View Document\" >\n";
                none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                none = none + "</a>\n";
            } else {
                none = none + "<a href='#!' title=\"View Document\" >\n";
                none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                none = none + "</a>\n";
            }
        }

        return none;
    }

    public String valida_mensaFiletype(String annexType){
        String none = "";
        if (!annexType.equals("0")){
            if (annexType.equals("1")){
                none = none + "Validate the document:\n";
            }else{
                none = none + "Validate the image:\n";
            }
        }
        return none;
    }

    public String valida_mensaFile(String annexType,String dir_doc,String nameAnnexFile,String directorio_gral,String dir_img,Integer format){
        String none = "";
        if (!annexType.equals("0")){
            if (annexType.equals("1")){
                if(format == 2) {
                    none = none + "<a href='" + dir_doc + nameAnnexFile + "' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }else{
                if(format == 2) {
                    none = none + "<a href='" + dir_img + nameAnnexFile + "' title=\"View Image\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "image_gral.jpg\" alt=\"Profile-image\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }else{
                    none = none + "<a href='#!' title=\"View Document\" >\n";
                    none = none + "<img src=\"" + directorio_gral + "document_gral.png\" alt=\"Profile-document\" width=\"100\" height=\"60\" border=\"1\">\n";
                    none = none + "</a>\n";
                }
            }
        }
        return none;
    }

    // Seccion para header_polls.jsp
    public String pone_pieHeaderPolls(String column) {
        String none = "";
        if (column.equals("0")) {
            none = none + "<td>&nbsp;</td>";
            none = none + "<td>Not Action</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
        } else {
            none = none + "<td>&nbsp;</td>";
            none = none + "<td>Not Action</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
        }
        return none;
    }

    public String pone_encabezadoHeaderPolls(String column){
        String none = "";
        if (column.equals("0")){
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >\n";
            none = none + "<input type='checkbox' onclick='marcar(this);' />\n";
            none = none + "</th>\n";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>\n";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">DESCRIPTION NAME</th>\n";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">STATUS</th>\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">DATE CREATION</th>\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">No.REQUEST</th>\n";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">AUDITED</th>\n";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">TOTAL POINTS</th>\n";
        }else {
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >";
            none = none + "<input type='checkbox' onclick='marcar(this);' />";
            none = none + "</th>";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">DESCRIPTION NAME</th>";
            none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">REFERENCE</th>";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">STATUS</th>";
            none = none + "<th data-field=\"l5\" data-sortable=\"true\" data-switchable=\"true\">VERSION</th>";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">DATE CREATION</th>";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">No.REQUEST</th>";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">AUDITED</th>";
            none = none + "<th data-field=\"l7\" data-sortable=\"true\" data-switchable=\"false\">ORG LEVEL1</th>";
            none = none + "<th data-field=\"l9\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL2</th>";
            none = none + "<th data-field=\"l10\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL3</th>";
            none = none + "<th data-field=\"l11\" data-sortable=\"true\" data-switchable=\"true\">ORG LEVEL4</th>";
            none = none + "<th data-field=\"l12\" data-sortable=\"true\" data-switchable=\"false\">TYP LEVEL1</th>";
            none = none + "<th data-field=\"l13\" data-sortable=\"true\" data-switchable=\"true\">TYP LEVEL2</th>";
            none = none + "<th data-field=\"l14\" data-sortable=\"true\" data-switchable=\"true\">TYP LEVEL3</th>";
            none = none + "<th data-field=\"l15\" data-sortable=\"true\" data-switchable=\"true\">LAST MODIF.</th>";
            none = none + "<th data-field=\"l16\" data-sortable=\"true\" data-switchable=\"true\">TOTAL POINTS</th>";
        }
        return none;
    }
}
