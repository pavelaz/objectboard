package com.psg.objectboard.controller.resourcesApiRESTful;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.CertificationReportVO;
import com.psg.objectboard.model.service.CertificationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/certificationsReport")
public class CertificationResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll/{dataUser}/{dataPassword}/{companyNumber}")
    public Response sendDataReportTypified(  @PathParam("dataUser") String dataUser,
                            @PathParam("dataPassword") String dataPassword,
                            @PathParam("companyNumber") String companyNumber) {

        CertificationService certificationService = new CertificationService();
        CertificationReportVO certificationReport =  certificationService.getAllCertificationReport(dataUser,dataPassword, companyNumber);

        return Response.status(200).entity(certificationReport).build();
    }
}
