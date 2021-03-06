package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AssignmentReportVO;
import com.psg.objectboard.model.service.AssignmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/assignmentsReport")
public class AssignmentResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportAssignment(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        AssignmentService assignmentService = new AssignmentService();
        AssignmentReportVO assignmentsReport =  assignmentService.getAllAssignmentReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(assignmentsReport).build();
    }
}
