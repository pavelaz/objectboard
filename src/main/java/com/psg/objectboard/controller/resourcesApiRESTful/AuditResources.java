package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AuditReportVO;
import com.psg.objectboard.model.service.AuditService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auditsReport")
public class AuditResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportAudit(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        AuditService auditService = new AuditService();
        AuditReportVO auditReport =  auditService.getAllAuditReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(auditReport).build();
    }
}
