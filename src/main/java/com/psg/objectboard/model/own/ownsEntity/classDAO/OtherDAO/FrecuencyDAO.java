package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.FrecuencyVO;

import java.util.ArrayList;

public class FrecuencyDAO {
    public ArrayList<FrecuencyVO> getFrecuency(){
        ArrayList<FrecuencyVO> arrcom = new ArrayList<FrecuencyVO>();

        FrecuencyVO frecuencyVO0 = new FrecuencyVO(0, "Select Frecuency",0);
        FrecuencyVO frecuencyVO1 = new FrecuencyVO(1, "daily",1);
        FrecuencyVO frecuencyVO2 = new FrecuencyVO(2, "weekly",7);
        FrecuencyVO frecuencyVO3 = new FrecuencyVO(3, "biweekly",14);
        FrecuencyVO frecuencyVO4 = new FrecuencyVO(4, "monthly",30);
        FrecuencyVO frecuencyVO5 = new FrecuencyVO(5, "yearly",365);

        arrcom.add(frecuencyVO0);
        arrcom.add(frecuencyVO1);
        arrcom.add(frecuencyVO2);
        arrcom.add(frecuencyVO3);
        arrcom.add(frecuencyVO4);
        arrcom.add(frecuencyVO5);

        System.out.println("creacion array exitosa: ");
        return arrcom;
    }
}
