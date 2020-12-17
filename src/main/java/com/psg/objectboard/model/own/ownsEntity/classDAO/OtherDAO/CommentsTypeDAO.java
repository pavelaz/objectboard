package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.CommentsTypeVO;

import java.util.ArrayList;

public class CommentsTypeDAO {
    public ArrayList<CommentsTypeVO> getCommentsType(){
        ArrayList<CommentsTypeVO> arrcom = new ArrayList<CommentsTypeVO>();

        CommentsTypeVO commentsTypeVO0 = new CommentsTypeVO(0, "Select Type");
        CommentsTypeVO commentsTypeVO1 = new CommentsTypeVO(1, "Legend");
        CommentsTypeVO commentsTypeVO2 = new CommentsTypeVO(2, "Exception");
        CommentsTypeVO commentsTypeVO3 = new CommentsTypeVO(3, "Notes");
        CommentsTypeVO commentsTypeVO4 = new CommentsTypeVO(4, "Terms");

        arrcom.add(commentsTypeVO0);
        arrcom.add(commentsTypeVO1);
        arrcom.add(commentsTypeVO2);
        arrcom.add(commentsTypeVO3);
        arrcom.add(commentsTypeVO4);

        System.out.println("creacion array exitosa: ");
        return arrcom;
    }
}
