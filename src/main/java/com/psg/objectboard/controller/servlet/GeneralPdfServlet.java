package com.psg.objectboard.controller.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.own.ownsEntity.classViewDAO.BussinessUnitCoStCiDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewDAO.DischargeViewDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewDAO.ProfilesDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.BussinessUnitCoStCiVO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.DischargeViewVO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.ProfilesVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherFunctions;

@WebServlet(name = "GeneralPdfServlet", urlPatterns = "/generalpdf")
public class GeneralPdfServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        //String company_number = (String) objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String pdf_list = null;
        if(request.getParameter("p_acciones")!=null){
            pdf_list=request.getParameter("p_acciones");
        }
        OtherFunctions of = new OtherFunctions();

        // Aqui se imprimen las licencias de cada empresa individualmente
        if (pdf_list.equals("licence")) {
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            String discharge = null;
            if (request.getParameter("p_discharge") != null) {
                discharge = request.getParameter("p_discharge");
            }
            String cia_number = null;
            if (request.getParameter("p_cia_number") != null) {
                cia_number = request.getParameter("p_cia_number");
            }

            // se define el tipo de papel y si es vertical u horizontal.  en este caso es horizontal, por defecto vertical
            Document doc = new Document(PageSize.LETTER);
            // agrgando margenes.  por defecto es de media pulgada en los cuatro lados
            doc.addAuthor("Paul Velazquez");
            doc.setMargins(15, 20, 15, 15);

            try {
                //String ruta = System.getProperty("user.home");
                //PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter.getInstance(doc, out);
                PdfWriter pdfw = PdfWriter.getInstance(doc, out);

                BussinessUnitVO buvo = null;
                BussinessUnitDAO bud = new BussinessUnitDAO();
                buvo = bud.serchBussinessUnitDAO(cia_number);

                String none1 = bud.searchLogoName(cia_number,data_user,data_pasword,1);
                FilesController fc = new FilesController();

                fc.writerFileInFolder(buvo.getBuLogoImageByte(),of.searchLink("4")+ buvo.getBuLogoName());

                String fichero = of.searchLink("4")+ buvo.getBuLogoName();
                //String fichero = of.buscaLogoYDirCliente(cia_number,data_user,data_pasword);
                //BussinessUnitDAO bud = new BussinessUnitDAO();
                //request.setAttribute("rq_format", bud.searchLogoName(cia_number,data_user,data_pasword,1));

                Image header = Image.getInstance(fichero);
                header.scaleToFit(50, 60);
                header.setAlignment(Chunk.ALIGN_LEFT);

                doc.open();

                fc.deleteFile(of.searchLink("4")+ buvo.getBuLogoName());

                try {
                    DischargeViewDAO cod = new DischargeViewDAO();
                    cod.setDataUser(data_user);
                    cod.setDataPassword(data_pasword);
                    String condicion = "di_license_num = " + discharge;
                    ArrayList<DischargeViewVO> dischar = cod.getListDischargeView(condicion);
                    DateFunctions df = new DateFunctions();
                    String none = df.parseFecha_2(dischar.get(0).getDiStartDate());

                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.add(company_name + "\n\n");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
                    parrafo.add("LICENCIA DE USO No. " + dischar.get(0).getDiLicenseNum() + "\n\n\n");

                    doc.add(header);
                    doc.add(parrafo);

                    parrafo = new Paragraph();
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo.add("     Licencia de uso para ( " + dischar.get(0).getDiNumberUser() + " ) usuarios, del modulo ");
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo.add(dischar.get(0).getPrName());
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo.add(", a nombre del cliente: ");
                    parrafo.setFont(FontFactory.getFont("Time", 16, Font.BOLDITALIC, BaseColor.BLACK));
                    parrafo.add(dischar.get(0).getBuName());
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo.add(", por el periodo de tiempo comprendido entre el ");
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo.add(none);
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo.add(" y el ");
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.BOLD, BaseColor.BLACK));
                    none = df.parseFecha_2(dischar.get(0).getDiEndDate());
                    parrafo.add(none + ".  \n\n");
                    parrafo.setFont(FontFactory.getFont("Time", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo.add("     El serial de uso de esta licencia es el siguiente:           ");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.RED));
                    //parrafo.setAlignment(Paragraph.ALIGN_RIGHT);
                    parrafo.add(dischar.get(0).getDiLicenseCode() + "\n\n");

                    /*BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCodeType(Barcode.EAN13);
                    codeEAN.setCode("9781935182610");
                    //Preparo las variables para utiliza
                    Image img = codeEAN.createImageWithBarcode( pdfw.getDirectContent(), BaseColor.GREEN, BaseColor.RED);
                    parrafo.add(img);
                    parrafo.add("\n\n");*/

                    Barcode128 code128 = new Barcode128();
                    code128.setCodeType(Barcode.CODE128);
                    code128.setCode(dischar.get(0).getDiLicenseCode());
                    //Preparo las variables para utiliza
                    Image img1 = code128.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.RED);
                    parrafo.add(img1);
                    parrafo.add("                             ");

                    // parrafo = new Paragraph();
                    // Primero debemos setear el valor que posea nuestro código QR dentro de una variable del tipo String.
                    String textCodigoQR = discharge + " " + dischar.get(0).getBuName() +
                            " " + dischar.get(0).getDiLicenseCode() +
                            " " + dischar.get(0).getDiLicenseNum() +
                            " " + dischar.get(0).getDiStartDate() +
                            " " + dischar.get(0).getDiEndDate();
                    // Luego una instancia de la clase BarcodeQRCode correspondiente al CÓDIGO QR, pasandole al constructor nuestro valor que previamente fue asignado a la variable.
                    BarcodeQRCode codigoBarrasQR = new BarcodeQRCode(textCodigoQR, 100, 100, null);
                    // Nota:  El constructor de la clase BarcodeQRCode, posee cuatro parámetros, valor, alto, ancho y la manera de como se arma el código QR, por defecto esta ultima le asignamos null.
                    // obtenemos la imagen del código QR y la agregamos al documento,
                    parrafo.add(codigoBarrasQR.getImage());

                    doc.add(parrafo);

                } catch (Exception ex) {
                    System.out.println("Error en creacion de reporte: " + ex);
                }
                doc.close();
                System.out.println("Reporte Creado");
            } catch (Exception e) {
                System.out.println("Error en creacion de reporte final: " + e);
            }

            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);

            request.getRequestDispatcher("/WEB-INF/pages/jsp/master/discharges.jsp").forward(request, response);
        }
        // Aqui se imprimen los datos de un usuario de la pantalla profile
        if (pdf_list.equals("user")) {
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            String company_number = (String) objSesion.getAttribute("companyNumber");
            String user_email = (String) objSesion.getAttribute("userEmail");

            // se define el tipo de papel y si es vertical u horizontal.  en este caso es horizontal, por defecto vertical
            Document doc = new Document(PageSize.LETTER);
            // agrgando margenes.  por defecto es de media pulgada en los cuatro lados
            doc.addAuthor("Paul Velazquez");
            doc.setMargins(20, 20, 20, 20);

            try {
                //String ruta = System.getProperty("user.home");
                //PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter.getInstance(doc, out);
                PdfWriter pdfw = PdfWriter.getInstance(doc, out);

                /*String fichero = of.buscaLogoYDirCliente(company_number,data_user,data_pasword);
                Image header = Image.getInstance(fichero);
                header.scaleToFit(50, 60);
                header.setAlignment(Chunk.ALIGN_LEFT);

                doc.open();*/

                try {
                    MasterUserDAO cod = new MasterUserDAO();
                    cod.setDataUser(data_user);
                    cod.setDataPassword(data_pasword);
                    String condicion = "mu_email = '" + user_email +"' AND bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number);
                    ArrayList<MasterUserVO> usuario = cod.getListMasterUser(condicion);
                    DateFunctions df = new DateFunctions();
                    //String none = df.parseFecha_2(dischar.get(0).getDiStartDate());

                    /*FilesController filesController = new FilesController();

                    String photo = filesController.updateFile(request, (1024 * 1024 * 1)); // 1024 * 1024 * 1,= 1 MB
                    master_user_dto.setRoutePhoto(photo);

                    filesController.get
                    ServletOutputStream outputStream = null;
                    outputStream = filesController.blobToDifferentFormats(response,usuario.get(0).getMuPhotoByte(),"image/png");

                    Image header1 = Image.getInstance(ruta +
                            "/IdeaProjects/objectboard/" +
                            "/src/main/webapp/complements/img/favicon.png");
                    header1.scaleToFit(50, 60);
                    header1.setAlignment(Chunk.ALIGN_LEFT);*/

                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.DARK_GRAY));
                    parrafo.add(company_name + "\n\n");
                    parrafo.add("User Profile \n\n");

                    //doc.add(header);
                    doc.add(parrafo);

                    Paragraph parrafo1 = new Paragraph();
                    parrafo1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("Email : ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(usuario.get(0).getMuEmail());
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nFull Name: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(usuario.get(0).getMuName());
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nGender : ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    if (usuario.get(0).getMuGender().equals("M"))
                        parrafo1.add("Male ");
                    else
                        parrafo1.add("Female ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nRecovery cuestion: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(usuario.get(0).getMuQuestion());
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nAnswer Question : ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(usuario.get(0).getMuAnswer() );
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nRegister date: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(df.parseFecha_2(usuario.get(0).getMuStartDate()));
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nRegister date : ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(df.parseFecha_2(usuario.get(0).getMuDate()));
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nStatus: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    if (usuario.get(0).getMuStatus().equals("T"))
                        parrafo1.add("Active");
                    else
                        parrafo1.add("Inactive");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nRenovation Password day: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    parrafo1.add(usuario.get(0).getMuEffectiveDays() + " day.");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
                    parrafo1.add("\nConfirm Email: ");
                    parrafo1.setFont(FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
                    if (usuario.get(0).getMuEmailConfirm().equals("T"))
                        parrafo1.add("Confirm");
                    else
                        parrafo1.add("Not Confirm");
                    doc.add(parrafo1);
                } catch (Exception ex) {
                    System.out.println("Error en creacion de reporte: " + ex);
                }
                doc.close();
                System.out.println("Reporte Creado");
            } catch (Exception e) {
                System.out.println("Error en creacion de reporte final: " + e);
            }

            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);

            request.getRequestDispatcher("/WEB-INF/pages/jsp/master/discharges.jsp").forward(request, response);
        }
        // Esta es la lista de usuarios sin parametros
        if (pdf_list.equals("users")) {
            String company_number = (String) objSesion.getAttribute("companyNumber");
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            // se define el tipo de papel y si es vertical u horizontal.  en este caso es horizontal, por defecto vertical
            Document doc = new Document(PageSize.LEGAL.rotate());
            // agrgando margenes.  por defecto es de media pulgada en los cuatro lados
            doc.addAuthor("Paul Velazquez");
            doc.setMargins(15, 20, 15, 15);

            try {
                //String ruta = System.getProperty("user.home");
                //PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter.getInstance(doc, out);
                PdfWriter pdfw = PdfWriter.getInstance(doc, out);

                /*String fichero = of.buscaLogoYDirCliente(company_number,data_user,data_pasword);
                Image header = Image.getInstance(fichero);
                header.scaleToFit(40, 50);
                header.setAlignment(Chunk.ALIGN_LEFT);

                doc.open();*/

                try {
                    ProfilesDAO pod= new ProfilesDAO();
                    ArrayList<ProfilesVO> profiles = null;
                    pod.setDataUser(data_user);
                    pod.setDataPassword(data_pasword);

                    profiles= pod.getListProfiles();

                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.add(company_name + "\n\n");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
                    parrafo.add("Users List \n\n\n");

                    //doc.add(header);
                    doc.add(parrafo);

                    DateFunctions df = new DateFunctions();
                    String fecha = null;

                    PdfPTable tabla = new PdfPTable(14);
                    // ancho de la tabla respetando los margenes
                    tabla.setWidthPercentage(100);

                    tabla.addCell("EMAIL");
                    tabla.addCell("U.NAME");
                    tabla.addCell("USER NAME");
                    tabla.addCell("START DATE");
                    tabla.addCell("STATUS");
                    tabla.addCell("GENDER");
                    tabla.addCell("SEC. TIME");
                    tabla.addCell("CF.EMAIL");
                    tabla.addCell("VALID PASS.");
                    tabla.addCell("EXPIRES");
                    tabla.addCell("DATE EXPIRE");
                    tabla.addCell("COUNTRY");
                    tabla.addCell("STATE");
                    tabla.addCell("CITY");

                    for (int x=0; x <= profiles.size() -1; x++){
                        tabla.addCell(profiles.get(x).getMuEmail());
                        tabla.addCell(profiles.get(x).getBu_name());
                        tabla.addCell(profiles.get(x).getMuName());
                        fecha = df.parseFecha_2(profiles.get(x).getMuStartDate());
                        tabla.addCell(fecha);
                        if (profiles.get(x).getMuStatus().equals("T"))
                           tabla.addCell("Enabled");
                        else
                            tabla.addCell("Disabled");
                        if(profiles.get(x).getMuGender().equals("M"))
                            tabla.addCell("Male");
                        else
                            tabla.addCell("Female");
                        fecha = profiles.get(x).getMuSectionTime() / 60 + " Minutes";
                        tabla.addCell(fecha);
                        if (profiles.get(x).getMuEmailConfirm().equals("T"))
                            tabla.addCell("Yes");
                        else
                            tabla.addCell("No");
                        fecha = profiles.get(x).getMuEffectiveDays() + " Days";
                        tabla.addCell(fecha);
                        if (profiles.get(x).getMuExpires().equals("T"))
                            tabla.addCell("Yes");
                        else
                            tabla.addCell("No");
                        fecha = df.parseFecha_2(profiles.get(x).getMuDateExpires());
                        tabla.addCell(fecha);
                        tabla.addCell(profiles.get(x).getContry_name());
                        tabla.addCell(profiles.get(x).getState_name());
                        tabla.addCell(profiles.get(x).getCity_name());
                    }

                    doc.add(tabla);

                } catch (Exception ex) {
                    System.out.println("Error en creacion de reporte: " + ex);
                }
                doc.close();
                System.out.println("Reporte Creado");
            } catch (Exception e) {
                System.out.println("Error en creacion de reporte final: " + e);
            }

            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);

            request.getRequestDispatcher("/WEB-INF/pages/jsp/master/userProfileConsult.jsp").forward(request, response);
        }
        // crea la lista de Licencias General
        if (pdf_list.equals("licences")) {
            String company_number = (String) objSesion.getAttribute("companyNumber");
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            // se define el tipo de papel y si es vertical u horizontal.  en este caso es horizontal, por defecto vertical
            Document doc = new Document(PageSize.LETTER.rotate());
            // agrgando margenes.  por defecto es de media pulgada en los cuatro lados
            doc.addAuthor("Paul Velazquez");
            doc.setMargins(15, 20, 15, 15);

            try {
                //String ruta = System.getProperty("user.home");
                //PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter.getInstance(doc, out);
                PdfWriter pdfw = PdfWriter.getInstance(doc, out);

                /*String fichero = of.buscaLogoYDirCliente(company_number,data_user,data_pasword);
                Image header = Image.getInstance(fichero);
                header.scaleToFit(40, 50);
                header.setAlignment(Chunk.ALIGN_LEFT);

                doc.open();*/

                try {
                    DischargeViewDAO pod= new DischargeViewDAO();
                    ArrayList<DischargeViewVO> discharges = null;
                    pod.setDataUser(data_user);
                    pod.setDataPassword(data_pasword);

                    discharges= pod.getListDischargeView("");

                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.add(company_name + "\n\n");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
                    parrafo.add("Dischages List \n\n\n");

                    //doc.add(header);
                    doc.add(parrafo);
                    parrafo.setFont(FontFactory.getFont("Time", 10, Font.NORMAL, BaseColor.BLACK));

                    DateFunctions df = new DateFunctions();

                    PdfPTable tabla = new PdfPTable(10);
                    // ancho de la tabla respetando los margenes
                    tabla.setWidthPercentage(100);

                    tabla.addCell("No.LIC.");
                    tabla.addCell("S.DATE");
                    tabla.addCell("E.DATE");
                    tabla.addCell("USERS");
                    tabla.addCell("PERMANENT");
                    tabla.addCell("SALE");
                    tabla.addCell("L.CODE");
                    tabla.addCell("STATUS");
                    tabla.addCell("PROJECT");
                    tabla.addCell("UNIT");

                    for (int x=0; x <= discharges.size() -1; x++){
                        tabla.addCell(String.valueOf(discharges.get(x).getDiLicenseNum()));
                        tabla.addCell(df.parseFecha_2(discharges.get(x).getDiStartDate()));
                        tabla.addCell(df.parseFecha_2(discharges.get(x).getDiEndDate()));
                        tabla.addCell(String.valueOf(discharges.get(x).getDiNumberUser()));
                        if (discharges.get(x).getDiPermanent().equals("T"))
                            tabla.addCell("Yes");
                        else
                            tabla.addCell("No");
                        tabla.addCell(String.valueOf(discharges.get(x).getDiSalesCode()));
                        tabla.addCell(discharges.get(x).getDiLicenseCode());
                        if ( df.ComparaFechas(df.fechaFull(9),discharges.get(x).getDiEndDate()) == 1) {
                            tabla.addCell("Invalid");
                        }else
                            tabla.addCell("Valid");
                        tabla.addCell(discharges.get(x).getPrName());
                        tabla.addCell(discharges.get(x).getBuName());
                    }

                    doc.add(tabla);

                } catch (Exception ex) {
                    System.out.println("Error en creacion de reporte: " + ex);
                }
                doc.close();
                System.out.println("Reporte Creado");
            } catch (Exception e) {
                System.out.println("Error en creacion de reporte final: " + e);
            }

            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);

            request.getRequestDispatcher("/WEB-INF/pages/jsp/master/discharges.jsp").forward(request, response);
        }
        // Esta es la lista de unidades de negocio sin parametros
        if (pdf_list.equals("unidades")) {
            String company_number = (String) objSesion.getAttribute("companyNumber");
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

            // se define el tipo de papel y si es vertical u horizontal.  en este caso es horizontal, por defecto vertical
            Document doc = new Document(PageSize.LEGAL.rotate());
            // agregando margenes.  por defecto es de media pulgada en los cuatro lados
            doc.addAuthor("Paul Velazquez");
            doc.setMargins(15, 20, 15, 15);

            try {
                //String ruta = System.getProperty("user.home");
                //PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta + "/Escritorio/reporte.pdf"));
                //PdfWriter.getInstance(doc, out);
                PdfWriter pdfw = PdfWriter.getInstance(doc, out);

                /*String fichero = of.buscaLogoYDirCliente(company_number,data_user,data_pasword);
                Image header = Image.getInstance(fichero);
                header.scaleToFit(40, 50);
                header.setAlignment(Chunk.ALIGN_LEFT);

                doc.open();*/

                try {
                    BussinessUnitCoStCiDAO pod= new BussinessUnitCoStCiDAO();
                    ArrayList<BussinessUnitCoStCiVO> unidad = null;
                    pod.setDataUser(data_user);
                    pod.setDataPassword(data_pasword);

                    unidad= pod.getListBussinessUnitCoStCi();

                    Paragraph parrafo = new Paragraph();
                    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo.add(company_name + "\n\n");
                    parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
                    parrafo.add("Bussiness Units List \n\n\n");

                    //doc.add(header);
                    doc.add(parrafo);

                    DateFunctions df = new DateFunctions();
                    String fecha = null;

                    PdfPTable tabla = new PdfPTable(11);
                    // ancho de la tabla respetando los margenes
                    tabla.setWidthPercentage(100);

                    tabla.addCell("U.NAME");
                    tabla.addCell("NO.FED.");
                    tabla.addCell("No.PROV.");
                    tabla.addCell("STATUS");
                    tabla.addCell("PHONE");
                    tabla.addCell("ZIP CODE");
                    tabla.addCell("ADDRESS");
                    tabla.addCell("COUNTRY");
                    tabla.addCell("STATE");
                    tabla.addCell("CITY");
                    tabla.addCell("TYPE");

                    for (int x=0; x <= unidad.size() -1; x++){
                        tabla.addCell(unidad.get(x).getBuName());
                        tabla.addCell(unidad.get(x).getBuFederalNumber());
                        tabla.addCell(unidad.get(x).getBuProvincialNumber());
                        if (unidad.get(x).getBuStatus().equals("T"))
                            tabla.addCell("Enabled");
                        else
                            tabla.addCell("Disabled");
                        tabla.addCell(unidad.get(x).getBuPhone());
                        tabla.addCell(unidad.get(x).getBuZipcode());
                        tabla.addCell(unidad.get(x).getBuAddress());
                        tabla.addCell(unidad.get(x).getCountryName());
                        tabla.addCell(unidad.get(x).getStateName());
                        tabla.addCell(unidad.get(x).getCityName());
                        tabla.addCell(unidad.get(x).getTypeName());
                    }

                    doc.add(tabla);

                } catch (Exception ex) {
                    System.out.println("Error en creacion de reporte: " + ex);
                }
                doc.close();
                System.out.println("Reporte Creado");
            } catch (Exception e) {
                System.out.println("Error en creacion de reporte final: " + e);
            }

            request.setAttribute("rq_companyName", company_name);
            request.setAttribute("rq_userName", user_name);

            request.getRequestDispatcher("/WEB-INF/pages/jsp/master/bussinessUnits.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
