package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.ConductReportVO;
import com.psg.objectboard.model.service.ConductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/conductsReport")
public class ConductResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportConsult(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        ConductService conductService = new ConductService();
        ConductReportVO consultReport =  conductService.getAllConductReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(consultReport).build();
    }
}
