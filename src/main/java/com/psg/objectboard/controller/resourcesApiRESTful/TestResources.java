package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/test")
public class TestResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendDataReportTypified() {

        String data_user = "boarduser",
                data_password = "1#Object5Board*%";
            BussinessUnitDAO sud = new BussinessUnitDAO();
            sud.setDataUser(data_user);
            sud.setDataPassword(data_password);
            ArrayList<BussinessUnitVO> unit;
            unit = sud.getListBussinessUnit("");

        return Response.status(200).entity(unit).build();
    }
}
