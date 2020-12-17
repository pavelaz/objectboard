package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.TypifiedReportVO;
import com.psg.objectboard.model.service.TypifiedService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/typifiedsReport")
public class TypifiedResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportTypified(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        TypifiedService typifiedService = new TypifiedService();
        TypifiedReportVO typifiedReport =  typifiedService.getAllTypifiedReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(typifiedReport).build();
    }
}
