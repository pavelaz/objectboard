package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.BussinesUnitsReportVO;
import com.psg.objectboard.model.service.BussinesUnitsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/BussinesUnitsReport")
public class BussinesUnitsResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public Response sendDataReportBussinesUnits() {

        BussinesUnitsService bussinesUnitsService = new BussinesUnitsService();
        BussinesUnitsReportVO bussinesUnitsReport =  bussinesUnitsService.getAllBussinesUnitsReport();

        return Response.status(200).entity(bussinesUnitsReport).build();
    }
}
