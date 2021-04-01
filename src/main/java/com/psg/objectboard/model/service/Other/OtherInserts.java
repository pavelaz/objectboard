package com.psg.objectboard.model.service.Other;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;

import java.io.*;
import java.time.*;

public class OtherInserts {
    private String answer="";
    public String linkMedio="";

    private OtherFunctions of = null;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLinkMedio() {
        return linkMedio;
    }

    public void setLinkMedio(String linkMedio) {
        this.linkMedio = linkMedio;
    }

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
                                Integer question,Double rank_min,Double rank_max){
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
                    if (rank_max == 0 && rank_min ==0) {
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                        none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                        none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                    }else{
                        Boolean resp = null;
                        OtherFunctions of = new OtherFunctions();
                        resp = of.evaluaRank(rank_max,rank_min,onlyNumber);
                        if (resp) {
                            // por probar todavia
                            none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                            none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                            none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                            none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                        }else{
                            // por probar todavia
                            none = none + "<input type=\"radio\" id=\"codep_" + question + "_1\" name=\"p_audi_" + question + "\" value=\"T\">&nbsp;&nbsp;\n";
                            none = none + "<label for=\"co\" class= \"col-form-label\" >Correct</label>&nbsp;&nbsp; &nbsp; &nbsp;\n";
                            none = none + "<input type=\"radio\" id=\"codep_" + question + "_2\" name=\"p_audi_" + question + "\" value=\"F\">&nbsp;&nbsp;\n";
                            none = none + "<label for=\"co\" class=\"col-form-label\" >Incorrect</label>\n";
                        }
                    }
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
            //none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
            none = none + "<td>Not data</td>";
        } else {
            none = none + "<td>&nbsp;</td>";
            none = none + "<td>Not Action</td>";
            //none = none + "<td>Not data</td>";
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
            //none = none + "<th data-field=\"I1\" data-sortable=\"true\" data-switchable=\"false\">IMAGE</th>\n";
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
            //none = none + "<th data-field=\"I1\" data-sortable=\"true\" data-switchable=\"false\">IMAGE</th>\n";
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

    // seccion contacs
    public String pone_funcionesContacts(String accion,String project) {
        String none = "";

        if (accion.equals("consult")) {
            none = none + "function nuevo_registro(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contacts';" + "\n";
            none = none + "document.forma.p_acciones.value = \"create\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
        }
        if (accion.equals("create")) {
            none = none + "function nuevo_registro(){" + "\n";
            none = none + "if (varias_validaciones() && " +
                              "valida_duplicados(this) && ";
            if (project.equals("2")){
                none = none + "valida_check_envios() && valida_check_yard() ";
            }else{
                none = none + "valida_check_envios() ";
            }
            none = none + ") {" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactsprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"create\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + " }" + "\n";
            none = none + " }" + "\n";

            none = none + "function valida_check_envios(){" + "\n";
            none = none + "if (!document.getElementById('sms_envios').checked && !document.getElementById('email_envios').checked){" + "\n";
            none = none + "alert(\"You cannot create a contact without at least selecting one of the message delivery boxes.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            if (project.equals("2")) {
                none = none + "function valida_check_yard(){" + "\n";
                none = none + "if (!document.getElementById('from').checked && !document.getElementById('back').checked){" + "\n";
                none = none + "alert(\"You cannot create a contact for this module without at least selecting one of the boxes\n" +
                        "that indicates the types or the type of patio of the property.\");" + "\n";
                none = none + "return false;" + "\n";
                none = none + "}" + "\n";
                none = none + "return true;" + "\n";
                none = none + "}" + "\n";
            }

            none = none + "function cancelar(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contacts';" + "\n";
            none = none + "document.forma.p_acciones.value = \"consult\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";

            none = none + "function varias_validaciones() {" + "\n";
            none = none + "if (!valida_email(document.forma.p_user_email.value,\"User Email\")||" + "\n";
            none = none + "!valida_textos(document.forma.p_name.value,\"User Name\",\"\")||" + "\n";
            none = none + "!valida_largos(document.forma.p_name.value.length,\"User Name\",2)||" + "\n";
            none = none + "!valida_textos(document.forma.p_bname.value,\"Bussiness Name\",\"0123456789.\")||" + "\n";
            none = none + "!valida_textos(document.forma.p_ad1.value,\"Address Line 1\",\",.0123456789\")||" + "\n";
            none = none + "!valida_largos(document.forma.p_ad1.value.length,\"Address Line 1\",2)||" + "\n";
            none = none + "!valida_textos(document.forma.p_ad2.value,\"Address Line 2\",\",.0123456789\")||" + "\n";
            none = none + "!valida_telefono(document.forma.p_cell.value,\"Cell Phone\")||" + "\n";
            none = none + "!valida_telefono(document.forma.p_land.value,\"Landline\")||" + "\n";
            none = none + "!valida_selects(document.forma.p_type.value,\"Type\",\"\")" + "\n";
            none = none + "){" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + " return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_duplicados(source) {" + "\n";
            none = none + "var cta0 = 0;" + "\n";
            none = none + "var cta1 = 0;" + "\n";
            none = none + "var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input los llamamboxesos check" + "\n";
            none = none + "for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles" + "\n";
            none = none + "if(checkboxes[i].id === \"cual_0\") {" + "\n";
            none = none + "if (checkboxes[i].value === document.forma.p_user_email.value) {" + "\n";
                none = none + "cta1 = cta1 + 1;" + "\n";
                none = none + "cta0 = cta0 + 1;" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";

            none = none + "if(cta0 !==0){" + "\n";
            none = none + "alert(\"The contacts that you want to create or modify \\nalready exists previously, so the requested changes will NOT be made.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";
        }
        if (accion.equals("save")) {
            none = none + "function update_registro(){" + "\n";
            none = none + "if (varias_validaciones() && valida_cambios() && ";
            if (project.equals("2")){
                none = none + "valida_check_envios() && valida_check_yard() ";
            }else{
                none = none + "valida_check_envios() ";
            }
            none = none + ") {" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactsprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"save\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";

            none = none + "function varias_validaciones() {" + "\n";
            none = none + "if (!valida_textos(document.forma.p_name.value,\"User Name\",\"\") || " + "\n";
            none = none + "!valida_largos(document.forma.p_name.value.length,\"User Name\",2) || " + "\n";
            none = none + "!valida_textos(document.forma.p_bname.value,\"Bussiness Name\",\"0123456789.\") || " + "\n";
            none = none + "!valida_textos(document.forma.p_ad1.value,\"Address Line 1\",\",.0123456789\") || " + "\n";
            none = none + "!valida_largos(document.forma.p_ad1.value.length,\"Address Line 1\",2) || " + "\n";
            none = none + "!valida_textos(document.forma.p_ad2.value,\"Address Line 2\",\",.0123456789\") || " + "\n";
            none = none + "!valida_telefono(document.forma.p_cell.value,\"Cell Phone\") || " + "\n";
            none = none + "!valida_telefono(document.forma.p_land.value,\"Landline\") || " + "\n";
            none = none + "!valida_selects(document.forma.p_type.value,\"Type\",\"\")" + "\n";
            none = none + "){" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + " return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_cambios(){" + "\n";
            /*if (project.equals("2")) {
                none = none + "if (document.forma.p_email.checked)" + "\n";
                none = none + "var envio_email = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "var envio_email = \"F\";" + "\n";
                none = none + "if (document.forma.p_sms.checked)" + "\n";
                none = none + "var envio_sms = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "var envio_sms = \"F\";" + "\n";


            }*/
            none = none + "if(document.forma.p_name.value === document.forma.p_name_old.value && " + "\n";
            none = none + "document.forma.p_bname.value === document.forma.p_bname_old.value && " + "\n";
            none = none + "document.forma.p_ad1.value === document.forma.p_ad1_old.value && " + "\n";
            none = none + "document.forma.p_ad2.value === document.forma.p_ad2_old.value && " + "\n";
            none = none + "document.forma.p_cell.value === document.forma.p_cell_old.value && " + "\n";
            none = none + "document.forma.p_land.value === document.forma.p_land_old.value && " + "\n";
            none = none + "document.forma.p_type.value === document.forma.p_type_old.value && " + "\n";
            if (project.equals("2")){
                none = none + "document.forma.p_email_envio_act.value === document.forma.p_email_msg_old.value && " + "\n";
                none = none + "document.forma.p_sms_envio_act.value === document.forma.p_sms_msg_old.value && " + "\n";
                none = none + "document.forma.p_from_act.value === document.forma.p_from_old.value && " + "\n";
                none = none + "document.forma.p_back_act.value === document.forma.p_back_old.value" + "\n";
            }else{
                none = none + "document.forma.p_email_envio_act.value === document.forma.p_email_msg_old.value && " + "\n";
                none = none + "document.forma.p_sms_envio_act.value === document.forma.p_sms_msg_old.value" + "\n";
            }
            none = none + "){" + "\n";
            none = none + "alert(\"No changes have been made to the registry, so there is nothing to save.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function cancelar(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contacts';" + "\n";
            none = none + "document.forma.p_acciones.value = \"consult\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_check_envios(){" + "\n";
            //none = none + "alert('Paul_03');" + "\n";
            none = none + "if (document.forma.p_sms_envio_act.value == \"F\" && document.forma.p_email_envio_act.value == \"F\"){" + "\n";
            none = none + "alert(\"You cannot create a contact without at least selecting one of the message delivery boxes.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function cambia_email(){" + "\n";
            none = none + "if (document.forma.p_email_envio_act.value == \"F\" )" + "\n";
            none = none + "document.forma.p_email_envio_act.value = \"T\";" + "\n";
            none = none + "else" + "\n";
            none = none + "document.forma.p_email_envio_act.value = \"F\";" + "\n";
            none = none + "}" + "\n";

            none = none + "function cambia_sms(){" + "\n";
            none = none + "if (document.forma.p_sms_envio_act.value == \"F\" )" + "\n";
            none = none + "document.forma.p_sms_envio_act.value = \"T\";" + "\n";
            none = none + "else" + "\n";
            none = none + "document.forma.p_sms_envio_act.value = \"F\";" + "\n";
            none = none + "}" + "\n";

            if (project.equals("2")) {
                none = none + "function valida_check_yard(){" + "\n";
                none = none + "if (document.forma.p_from_act.value == \"F\" && document.forma.p_back_act.value == \"F\" ){" + "\n";
                none = none + "alert(\"You cannot create a contact for this module without at least selecting one of the boxes\n" +
                        "that indicates the types or the type of patio of the property.\");" + "\n";
                none = none + "return false;" + "\n";
                none = none + "}" + "\n";
                none = none + "return true;" + "\n";
                none = none + "}" + "\n";

                none = none + "function cambia_back(){" + "\n";
                none = none + "if (document.forma.p_back_act.value == \"F\" )" + "\n";
                none = none + "document.forma.p_back_act.value = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "document.forma.p_back_act.value = \"F\";" + "\n";
                none = none + "}" + "\n";

                none = none + "function cambia_from(){" + "\n";
                none = none + "if (document.forma.p_from_act.value == \"F\" )" + "\n";
                none = none + "document.forma.p_from_act.value = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "document.forma.p_from_act.value = \"F\";" + "\n";
                none = none + "}" + "\n";
            }
        }
        if (accion.equals("comun")) {
            if (project.equals("0")) { // en este caso project es solo una bandera
                none = none + "function marcar(source) {" + "\n";
                none = none + "checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input" + "\n";
                none = none + "for(i=0;i<checkboxes.length;i++) //recoremos todos los controles" + "\n";
                none = none + "{" + "\n";
                none = none + "if(checkboxes[i].type === \"checkbox\" && checkboxes[i].id === \"p_select\" ) //solo si es un checkbox entramos" + "\n";
                none = none + "{" + "\n";
                none = none + "checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)" + "\n";
                none = none + "}" + "\n";
                none = none + "}" + "\n";
                none = none + "}" + "\n";
            }

            none = none + "function borrar_registro(){" + "\n";
            none = none + "if(validaItems(this)){" + "\n";
            none = none + "if ( confirm(\"Do You really want to delete the selected contacts?\")) {" + "\n";
            none = none + "document.forma.action = '/objectboard/contactsprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"delete\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";

            none = none + "function validaItems(source) {" + "\n";
            none = none + "var cta=0;" + "\n";
            none = none + "var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input" + "\n";
            none = none + "for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles" + "\n";
            none = none + "if(checkboxes[i].id === \"p_select\") {" + "\n";
            none = none + "if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado" + "\n";
            none = none + "cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "if(cta===0){" + "\n";
            none = none + "alert(\"There are no contacts selected to remove.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida(email){" + "\n";
            none = none + "document.forma.p_email_selec.value = email;" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.p_acciones.value = \"save\";" + "\n";
            none = none + "document.forma.action = \"/objectboard/contacts\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_columnas(){" + "\n";
            none = none + "document.forma.action = \"/objectboard/contacts\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
        }

        return none;
    }

    public String pone_encabezadoContactsButtons(String accion){
        String none = "";

        if (accion.equals("consult")) {
            none = none + "<tr>" + "\n";
            none = none + "<button type= \"button\" id=\"add\" title=\"Create New Assignment\" class= \"btn btn-outline-success\" onClick=nuevo_registro() >" + "\n";
            none = none + "<i class='fa fa-file-o fa-fw'></i> New</button>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "<button type= \"button\" id=\"remove\" title=\"Delete Assignments\" class= \"btn btn-outline-danger\" onClick=borrar_registro() >" + "\n";
            none = none + "<i class='fa fa-trash-o fa-fw'></i> Trash" + "\n";
            none = none + "</button>" + "\n";
            none = none + "</tr>" + "\n";
        }
        if (accion.equals("create")) {
            none = none + "<tr>" + "\n";
            none = none + "<button type= \"button\" id=\"add1\" title=\"Create New Assignment\" class= \"btn btn-outline-success\" onClick=nuevo_registro() >" + "\n";
            none = none + "<i class='fa fa-file-o fa-fw'></i> Create</button>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "<button type= \"button\" id=\"remove1\" title=\"Delete Assignments\" class= \"btn btn-outline-danger\" onClick=borrar_registro() >" + "\n";
            none = none + "<i class='fa fa-trash-o fa-fw'></i> Trash" + "\n";
            none = none + "</button>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "<button type= \"button\" id=\"cancel1\" title=\"Cancel operation\" class= \"btn btn-outline-success\" onClick=cancelar() >" + "\n";
            none = none + "<i class='fa fa-undo fa-fw'></i> Cancel" + "\n";
            none = none + "</button>" + "\n";
            none = none + "</tr>" + "\n";
        }
        if (accion.equals("save")) {
            none = none + "<tr>" + "\n";
            none = none + "<button type= \"button\" id=\"add2\" title=\"Update Assignment\" class= \"btn btn-outline-success\" onClick=update_registro() >" + "\n";
            none = none + "<i class='fa fa-file-o fa-fw'></i> Save" + "\n";
            none = none + "</button>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "<button type= \"button\" id=\"remove2\" title=\"Delete Assignments\" class= \"btn btn-outline-danger\" onClick=borrar_registro() >" + "\n";
            none = none + "<i class='fa fa-trash-o fa-fw'></i> Trash" + "\n";
            none = none + "</button>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "<button type= \"button\" id=\"cancel2\" title=\"Cancel operation\" class= \"btn btn-outline-success\" onClick=cancelar() >" + "\n";
            none = none + "<i class='fa fa-undo fa-fw'></i> Cancel" + "\n";
            none = none + "</button>" + "\n";
            none = none + "</tr>" + "\n";
        }

        return none;
    }

    public String pone_encabezadoContacts(String accion) {
        String none = "";

        if (accion.equals("0")) {
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >" + "\n";
            none = none + "<input type='checkbox' onclick='marcar(this);' />" + "\n";
            none = none + "</th>" + "\n";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>" + "\n";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">EMAIL DIRECTION</th>" + "\n";
            none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">USER NAME</th>" + "\n";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">CELL PHONE</th>" + "\n";
            none = none + "<th data-field=\"l5\" data-sortable=\"true\" data-switchable=\"true\">ALLOW SMS</th>" + "\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">ALLOW EMAIL</th>" + "\n";
        }else{
            none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >" + "\n";
            none = none + "<input type='checkbox' onclick='marcar(this);' />" + "\n";
            none = none + "</th>" + "\n";
            none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>" + "\n";
            none = none + "<th data-field=\"l1\" data-sortable=\"true\" data-switchable=\"false\">EMAIL DIRECTION</th>" + "\n";
            none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">USER NAME</th>" + "\n";
            none = none + "<th data-field=\"l3\" data-sortable=\"true\" data-switchable=\"true\">CELL PHONE</th>" + "\n";
            none = none + "<th data-field=\"l5\" data-sortable=\"true\" data-switchable=\"true\">ALLOW SMS</th>" + "\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">ALLOW EMAIL</th>" + "\n";
            none = none + "<th data-field=\"l6\" data-sortable=\"true\" data-switchable=\"true\">BUSSINES NAME</th>" + "\n";
            none = none + "<th data-field=\"l7\" data-sortable=\"true\" data-switchable=\"false\">TYPE</th>" + "\n";
            none = none + "<th data-field=\"l9\" data-sortable=\"true\" data-switchable=\"true\">LANDLINE</th>" + "\n";
            none = none + "<th data-field=\"l10\" data-sortable=\"true\" data-switchable=\"true\">ADDRESS</th>  " + "\n";
        }

        return none;
    }

    public String pone_pieContacts(String accion){
        String none = "";

        if (accion.equals("0")){
            none = none + "<td>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Action" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
        }else{
            none = none + "<td>" + "\n";
            none = none + "&nbsp;" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Action" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
            none = none + "<td>" + "\n";
            none = none + "Not Data" + "\n";
            none = none + "</td>" + "\n";
        }

        return none;
    }

    //seccion contacts list
    public String pone_encabezadoContactsList() {
        String none = "";

        none = none + "<th data-field=\"state\" data-valign=\"middle\" data-align=\"center\" data-sortable=\"false\" >" + "\n";
        none = none + "<input type='checkbox' onclick='marcar(this);' />" + "\n";
        none = none + "</th>" + "\n";
        none = none + "<th data-field=\"ac\" data-sortable=\"false\" data-valign=\"middle\" data-align=\"center\">ACTION</th>" + "\n";
        none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">LIST NAME</th>" + "\n";
        none = none + "<th data-field=\"l2\" data-sortable=\"true\" data-switchable=\"true\">COUNT DIRECTIONS</th>" + "\n";

        return none;
    }

    public String pone_pieContactsList(){
        String none = "";

        none = none + "<td>" + "\n";
        none = none + "&nbsp;" + "\n";
        none = none + "</td>" + "\n";
        none = none + "<td>" + "\n";
        none = none + "Not Action" + "\n";
        none = none + "</td>" + "\n";
        none = none + "<td>" + "\n";
        none = none + "Not Data" + "\n";
        none = none + "</td>" + "\n";
        none = none + "<td>" + "\n";
        none = none + "Not Data" + "\n";
        none = none + "</td>" + "\n";

        return none;
    }

    public String pone_funcionesContactsList(String accion) {
        String none = "";

        if (accion.equals("consult")) {
            none = none + "function nuevo_registro(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslist';" + "\n";
            none = none + "document.forma.p_acciones.value = \"create\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
        }
        if (accion.equals("create")) {
            none = none + "function nuevo_registro(){" + "\n";
            none = none + "if (varias_validaciones() && valida_duplicados() ) {" + "\n";
            //none = none + "if (varias_validaciones()) {" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslistprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"create\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + " }" + "\n";
            none = none + " }" + "\n";

            none = none + "function cancelar(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslist';" + "\n";
            none = none + "document.forma.p_acciones.value = \"consult\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";

            none = none + "function varias_validaciones() {" + "\n";
            none = none + "if (!valida_textos(document.forma.p_name.value,\"Contacts List Name\",\"\") || " + "\n";
            none = none + "!valida_largos(document.forma.p_name.value.length,\"Contacts List Name\",2)" + "\n";
            none = none + "){" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + " return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_duplicados() {" + "\n";
                //none = none + "alert('Paso 0');" + "\n";
                none = none + "var cta0 = 0;" + "\n";
                none = none + "var cta1 = 0;" + "\n";
                none = none + "var checkboxes=document.getElementsByTagName('input'); // obtenemos todos los controles del tipo Input los llamamboxesos check" + "\n";
                none = none + "for(i=0;i<checkboxes.length;i++) { // recoremos todos los controles" + "\n";
                    none = none + "if(checkboxes[i].id === \"cual_0\") {" + "\n";
                        //none = none + "alert('checkboxes ' + checkboxes[i].value );" + "\n";
                        //none = none + "alert('p_name ' + document.forma.p_name.value );" + "\n";
                        none = none + "if(checkboxes[i].value.trim() === document.forma.p_name.value.trim()) {" + "\n";
                            none = none + "cta1 = cta1 + 1;" + "\n";
                            none = none + "cta0 = cta0 + 1;" + "\n";
                            //none = none + "alert('SI');" + "\n";
                        none = none + "}" + "\n";
                        /*none = none + "if(checkboxes[i].value === document.forma.p_name.value) {" + "\n";
                            none = none + "cta1 = cta1 + 1;" + "\n";
                            none = none + "cta0 = cta0 + 1;" + "\n";
                        none = none + "}" + "\n";*/
                    none = none + "}" + "\n";
                none = none + "}" + "\n";
                //none = none + "alert('Paso');" + "\n";
                none = none + "if(cta0 !==0){" + "\n";
                    none = none + "alert(\"The contacts list that you want to create or modify \\nalready exists previously, so the requested changes will NOT be made.\");" + "\n";
                    none = none + "return false;" + "\n";
                none = none + "}" + "\n";
                none = none + "return true;" + "\n";
            //none = none + "return false;" + "\n";
            none = none + "}" + "\n";
        }
        if (accion.equals("save")) {
            none = none + "function update_registro(){" + "\n";
            none = none + "if (varias_validaciones() && valida_cambios() && valida_duplicados()";
            none = none + ") {" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslistprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"save\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";

            none = none + "function varias_validaciones() {" + "\n";
            none = none + "if (!valida_textos(document.forma.p_name.value,\"Contacts List Name\",\"\") || " + "\n";
            none = none + "!valida_largos(document.forma.p_name.value.length,\"Contacts List Name\",2)" + "\n";
            none = none + "){" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + " return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_cambios(){" + "\n";
            none = none + "if(document.forma.p_name.value.trim() === document.forma.p_name_old.value.trim()){" + "\n";
            none = none + "alert(\"No changes have been made to the registry, so there is nothing to save.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_duplicados() {" + "\n";
            none = none + "var cta0 = 0;" + "\n";
            none = none + "var cta1 = 0;" + "\n";
            none = none + "var checkboxes=document.getElementsByTagName('input'); // obtenemos todos los controles del tipo Input los llamamboxesos check" + "\n";
            none = none + "for(i=0;i<checkboxes.length;i++) { // recoremos todos los controles" + "\n";
            none = none + "if(checkboxes[i].id === \"cual_0\") {" + "\n";
            none = none + "if(checkboxes[i].value.trim() === document.forma.p_name.value.trim()) {" + "\n";
            none = none + "cta1 = cta1 + 1;" + "\n";
            none = none + "cta0 = cta0 + 1;" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "if(cta0 !==0){" + "\n";
            none = none + "alert(\"The contacts list that you want to create or modify \\nalready exists previously, so the requested changes will NOT be made.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function cancelar(){" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslist';" + "\n";
            none = none + "document.forma.p_acciones.value = \"consult\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
            /*none = none + "function valida_check_envios(){" + "\n";
            //none = none + "alert('Paul_03');" + "\n";
            none = none + "if (document.forma.p_sms_envio_act.value == \"F\" && document.forma.p_email_envio_act.value == \"F\"){" + "\n";
            none = none + "alert(\"You cannot create a contact without at least selecting one of the message delivery boxes.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function cambia_email(){" + "\n";
            none = none + "if (document.forma.p_email_envio_act.value == \"F\" )" + "\n";
            none = none + "document.forma.p_email_envio_act.value = \"T\";" + "\n";
            none = none + "else" + "\n";
            none = none + "document.forma.p_email_envio_act.value = \"F\";" + "\n";
            none = none + "}" + "\n";

            none = none + "function cambia_sms(){" + "\n";
            none = none + "if (document.forma.p_sms_envio_act.value == \"F\" )" + "\n";
            none = none + "document.forma.p_sms_envio_act.value = \"T\";" + "\n";
            none = none + "else" + "\n";
            none = none + "document.forma.p_sms_envio_act.value = \"F\";" + "\n";
            none = none + "}" + "\n";

            if (project.equals("2")) {
                none = none + "function valida_check_yard(){" + "\n";
                none = none + "if (document.forma.p_from_act.value == \"F\" && document.forma.p_back_act.value == \"F\" ){" + "\n";
                none = none + "alert(\"You cannot create a contact for this module without at least selecting one of the boxes\n" +
                        "that indicates the types or the type of patio of the property.\");" + "\n";
                none = none + "return false;" + "\n";
                none = none + "}" + "\n";
                none = none + "return true;" + "\n";
                none = none + "}" + "\n";

                none = none + "function cambia_back(){" + "\n";
                none = none + "if (document.forma.p_back_act.value == \"F\" )" + "\n";
                none = none + "document.forma.p_back_act.value = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "document.forma.p_back_act.value = \"F\";" + "\n";
                none = none + "}" + "\n";

                none = none + "function cambia_from(){" + "\n";
                none = none + "if (document.forma.p_from_act.value == \"F\" )" + "\n";
                none = none + "document.forma.p_from_act.value = \"T\";" + "\n";
                none = none + "else" + "\n";
                none = none + "document.forma.p_from_act.value = \"F\";" + "\n";
                none = none + "}" + "\n";
            }*/
        }
        if (accion.equals("comun")) {
            //if (project.equals("0")) { // en este caso project es solo una bandera
                none = none + "function marcar(source) {" + "\n";
                none = none + "checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input" + "\n";
                none = none + "for(i=0;i<checkboxes.length;i++) //recoremos todos los controles" + "\n";
                none = none + "{" + "\n";
                none = none + "if(checkboxes[i].type === \"checkbox\" && checkboxes[i].id === \"p_select\" ) //solo si es un checkbox entramos" + "\n";
                none = none + "{" + "\n";
                none = none + "checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)" + "\n";
                none = none + "}" + "\n";
                none = none + "}" + "\n";
                none = none + "}" + "\n";
            //}

            none = none + "function borrar_registro(){" + "\n";
            none = none + "if(validaItems(this)){" + "\n";
            none = none + "if ( confirm(\"Do You really want to delete the selected contacts list?\")) {" + "\n";
            none = none + "document.forma.action = '/objectboard/contactslistprocess';" + "\n";
            none = none + "document.forma.p_acciones.value = \"delete\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";

            none = none + "function validaItems(source) {" + "\n";
            none = none + "var cta=0;" + "\n";
            none = none + "var checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input" + "\n";
            none = none + "for(i=0;i<checkboxes.length;i++) {//recoremos todos los controles" + "\n";
            none = none + "if(checkboxes[i].id === \"p_select\") {" + "\n";
            none = none + "if (checkboxes[i].checked) {//solo si es un checkbox entramos y validamos si esta chequeado" + "\n";
            none = none + "cta = cta + 1; //llevamos control de cuantos de ellos estan chequeados" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "}" + "\n";
            none = none + "if(cta===0){" + "\n";
            none = none + "alert(\"There are no contacts list selected to remove.\");" + "\n";
            none = none + "return false;" + "\n";
            none = none + "}" + "\n";
            none = none + "return true;" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida(id,name){" + "\n";
            none = none + "document.forma.p_id_selec.value = id;" + "\n";
            none = none + "document.forma.p_name_old.value = replaceAllChart(name,\"-\",\" \");" + "\n";
            none = none + "document.forma.target = \"\";" + "\n";
            none = none + "document.forma.p_acciones.value = \"save\";" + "\n";
            none = none + "document.forma.action = \"/objectboard/contactslist\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";

            none = none + "function valida_columnas(){" + "\n";
            none = none + "document.forma.action = \"/objectboard/contactslist\";" + "\n";
            none = none + "document.forma.submit();" + "\n";
            none = none + "}" + "\n";
        }

        return none;
    }
}
