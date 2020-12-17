package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AnnexTypeVO;

import java.util.ArrayList;

public class AnnexTypeDAO {
    public ArrayList<AnnexTypeVO> getAnnexType(){
        ArrayList<AnnexTypeVO> arrcom = new ArrayList<AnnexTypeVO>();

        AnnexTypeVO annexTypeVO0 = new AnnexTypeVO(0, "Not annex");
        AnnexTypeVO annexTypeVO1 = new AnnexTypeVO(1, "Annex document");
        AnnexTypeVO annexTypeVO2 = new AnnexTypeVO(2, "Annex image");

        arrcom.add(annexTypeVO0);
        arrcom.add(annexTypeVO1);
        arrcom.add(annexTypeVO2);

        System.out.println("creacion array exitosa: ");
        return arrcom;
    }
}
