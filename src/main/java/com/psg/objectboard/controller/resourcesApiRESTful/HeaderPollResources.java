package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.HeaderPollReportVO;
import com.psg.objectboard.model.service.HeadersPollsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/headerspollsReport")
public class HeaderPollResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportHeaderPoll(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        HeadersPollsService headersPollsService = new HeadersPollsService();
        HeaderPollReportVO headersPollsReport =  headersPollsService.getAllHeadersPollsReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(headersPollsReport).build();
    }
}
