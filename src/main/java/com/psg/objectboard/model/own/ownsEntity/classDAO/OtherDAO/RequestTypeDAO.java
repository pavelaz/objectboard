package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.RequestTypeVO;

import java.util.ArrayList;

public class RequestTypeDAO {
    public ArrayList<RequestTypeVO> getRequestType(){
        ArrayList<RequestTypeVO> arrcom = new ArrayList<RequestTypeVO>();

        RequestTypeVO requestTypeVO0 = new RequestTypeVO(0, "Select type");
        RequestTypeVO requestTypeVO1 = new RequestTypeVO(1, "Boolean (Yes or Not)");
        RequestTypeVO requestTypeVO2 = new RequestTypeVO(2, "Unique selection");
        RequestTypeVO requestTypeVO3 = new RequestTypeVO(3, "Multi selection");
        RequestTypeVO requestTypeVO4 = new RequestTypeVO(4, "Only document");
        RequestTypeVO requestTypeVO5 = new RequestTypeVO(5, "Only image");
        RequestTypeVO requestTypeVO6 = new RequestTypeVO(6, "Input text");
        RequestTypeVO requestTypeVO7 = new RequestTypeVO(7, "Input number");
        RequestTypeVO requestTypeVO8 = new RequestTypeVO(8, "Input time");
        RequestTypeVO requestTypeVO9 = new RequestTypeVO(9, "Input date");

        arrcom.add(requestTypeVO0);
        arrcom.add(requestTypeVO1);
        arrcom.add(requestTypeVO2);
        arrcom.add(requestTypeVO3);
        arrcom.add(requestTypeVO4);
        arrcom.add(requestTypeVO5);
        arrcom.add(requestTypeVO6);
        arrcom.add(requestTypeVO7);
        arrcom.add(requestTypeVO8);
        arrcom.add(requestTypeVO9);

        System.out.println("creacion array exitosa: ");
        return arrcom;
    }
}
