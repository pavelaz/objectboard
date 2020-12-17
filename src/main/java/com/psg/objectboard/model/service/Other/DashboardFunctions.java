package com.psg.objectboard.model.service.Other;

import com.psg.objectboard.model.own.ownsEntity.classDAO.HeaderConductSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DashboardFunctions {
    public String BuscaValoresPrimerBloque(String password,String user,String cia) {
        String none ="";
        // Primer Objeto
        DecimalFormat def = new DecimalFormat("#.00");
        HeaderConductSurveyVO hcvo = new HeaderConductSurveyVO();
        HeaderConductSurveyDAO hcdo = new HeaderConductSurveyDAO();
        hcdo.setDataPassword(password);
        hcdo.setDataUser(user);
        ArrayList<HeaderConductSurveyVO> conduct;
        String condicion = "headersSurvey_bussinessUnit_bu_bis_code = "+ cia;
        conduct = hcdo.getListHeaderConductSurvey(condicion);
        Integer total_survey = conduct.size();

        Double porc_last24 = Double.parseDouble("0");

        Integer inter1 = 0;
        Double  fecha_hoy = Double.parseDouble("0");
        Double  fecha_eva = Double.parseDouble("0");
        Double  varios_porc = Double.parseDouble("0");

        String fecha,
                varios = "",
                varios0 = "",
                inter2 = "";
        DateFunctions df =  new DateFunctions();
        fecha = df.fechaFull(9);

        String[] parts = fecha.substring(0,10).split("/");
        varios = parts[0] + parts[1] + parts[2];
        inter1 = Integer.parseInt(varios) -1;
        inter2 = String.valueOf(inter1);

        parts = fecha.substring(11,19).split(":");
        varios = parts[0] + parts[1] + parts[2];
        varios0 = inter2 + varios;
        fecha_hoy = Double.parseDouble(varios0);

        Integer ejecuciones_auto = 0;
        Integer ejecuciones_manual = 0;
        Integer ejecuciones_pendientes = 0;
        Integer ejecuciones_auditadas = 0;

        for (int x = 0; x<conduct.size(); x++){
            fecha = conduct.get(x).getConduct_date();
            parts = fecha.substring(0,10).split("/");
            varios = parts[0] + parts[1] + parts[2];
            inter1 = Integer.parseInt(varios);
            inter2 = String.valueOf(inter1);

            parts = fecha.substring(11,19).split(":");
            varios = parts[0] + parts[1] + parts[2];
            varios0 = inter2 + varios;
            fecha_eva = Double.parseDouble(varios0);

            if (fecha_eva >= fecha_hoy){
                porc_last24++;
            }
            if (conduct.get(x).getAuditable().equals("T"))
                ejecuciones_manual++;
            else
                ejecuciones_auto++;
            if (conduct.get(x).getTo_audited().equals("T"))
                ejecuciones_pendientes++;
            else
                ejecuciones_auditadas++;
        }
        porc_last24 = (porc_last24 / total_survey) * 100;
        none = String.valueOf(def.format(porc_last24)) + "," + String.valueOf(total_survey);
        // Segundo objeto
        none = none + "," + String.valueOf(ejecuciones_manual);
        varios = String.valueOf(ejecuciones_manual);
        varios_porc = (Double.parseDouble(varios) / total_survey) * 100;
        none = none + "," + String.valueOf(def.format(varios_porc));
        // objeto  Tres
        none = none + "," + String.valueOf(ejecuciones_auto);
        varios = String.valueOf(ejecuciones_auto);
        varios_porc = (Double.parseDouble(varios) / total_survey) * 100;
        none = none + "," + String.valueOf(def.format(varios_porc));
        // objeto Cuatro
        none = none + "," + String.valueOf(ejecuciones_pendientes);
        varios = String.valueOf(ejecuciones_pendientes);
        varios_porc = (Double.parseDouble(varios) / total_survey) * 100;
        none = none + "," + String.valueOf(def.format(varios_porc));
        // objeto Quinto
        none = none + "," + String.valueOf(ejecuciones_auditadas);
        varios = String.valueOf(ejecuciones_auditadas);
        varios_porc = (Double.parseDouble(varios) / total_survey) * 100;
        none = none + "," + String.valueOf(def.format(varios_porc));
        // objeto seis
        HeadersSurveyVO hvo = new HeadersSurveyVO();
        HeadersSurveyDAO hdo = new HeadersSurveyDAO();
        hdo.setDataPassword(password);
        hdo.setDataUser(user);
        ArrayList<HeadersSurveyVO> survey;
        condicion = "bussinessUnit_bu_bis_code = "+ cia + " AND surveyStatus = 'F'";
        survey = hdo.getListHeadersSurvey(condicion);
        Integer total_polls = survey.size();

        Double porc_last3 = Double.parseDouble("0");

        fecha = df.fechaFull(9);

        parts = fecha.substring(0,10).split("/");
        varios = parts[0] + parts[1] + parts[2];
        inter1 = Integer.parseInt(varios) - 300; // fecha de hoy menos tres meses
        inter2 = String.valueOf(inter1);

        parts = fecha.substring(11,19).split(":");
        varios = parts[0] + parts[1] + parts[2];
        varios0 = inter2 + varios;
        fecha_hoy = Double.parseDouble(varios0);

        for (int x = 0; x<survey.size(); x++) {
            fecha = survey.get(x).getDateCreation();
            parts = fecha.substring(0, 10).split("/");
            varios = parts[0] + parts[1] + parts[2];
            inter1 = Integer.parseInt(varios);
            inter2 = String.valueOf(inter1);

            parts = fecha.substring(11, 19).split(":");
            varios = parts[0] + parts[1] + parts[2];
            varios0 = inter2 + varios;
            fecha_eva = Double.parseDouble(varios0);

            if (fecha_eva >= fecha_hoy) {
                porc_last3++;
            }
        }
        porc_last3 = (porc_last3 / total_polls) * 100;
        //System.out.println(df.format(number));
        none = none + "," + String.valueOf(def.format(porc_last3)) + "," + String.valueOf(total_polls);
        return none;
    }

}
