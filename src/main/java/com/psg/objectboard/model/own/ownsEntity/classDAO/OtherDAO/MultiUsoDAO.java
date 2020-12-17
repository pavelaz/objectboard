package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

public class MultiUsoDAO {
    private static String answer = null;
    /*
        Seccion de bloques utilizados en la pagina de countries.jsp

    public String poneTablaPrevia(Boolean existe_buscar, String buscar, Integer valor){
        answer = "<table width='70%' border='0' align='center'><tbody>\n" +
                 "<tr>\n" +
                "<td width='90%' align='left' >\n";
        if(existe_buscar)
            answer = answer + "<input type='text' name='p_buscar' id='p_buscar' maxlength='15' size='20' value='" + buscar + "' class='round' pattern='[A-Z]{1}'>&nbsp;\n";
        else{
            answer = answer + "<input type='text' name='p_buscar' id='p_buscar' maxlength='15' size='20' placeholder='User to search' class='round' pattern='[A-Z]{1}'>&nbsp;\n";
        }
        answer = answer + "<a href='#' onClick='buscar()'><i class='fa fa-search fa-fw' alt='Buscar Usuario'></i></a>\n" +
                          "</td>\n" +
                          "<td width='10%' align='right' style='color: black'>\n";

        answer = answer + "<a href='#' onClick='listar()'><i class='fa fa-print fa-fw' alt='Listar Usuario'></i></a>&nbsp;\n";
        if(valor==0)
            answer = answer + "<a href='#' onClick=borrar_usuario()><i class='fa fa-trash-o fa-fw' alt='Eliminar Usuario'></i></a>\n";
        answer = answer + "<a href='#' onClick=nuevo_usuario()><i class='fa fa-file-o fa-fw' alt='Nuevo Usuario'></i></a>\n" +
                          "</td>\n" +
                          "</tr>\n" +
                          "</tbody></table>";
        return answer;
    }

    public String pone_encabezado(){
        answer = "<table width='70%' border='1' align='center'><tbody>\n" +
                 "<tr>\n" +
                 "<td width='5%' align='center'>\n" +
                 "<input type='checkbox' onclick='marcar(this);' />\n" +
                 "</td>\n" +
                 "<td width='85%' align='center' style='color: black'><strong>COUNTRIES NAME</strong></td>\n" +
                 "<td width='10%' align='center' style='color: black'><strong>&nbsp;OPTIONS&nbsp;</strong></td>\n" +
                 "</tr>";
        return answer;
    }

    public String pone_tabla_cero(){
        answer = "<table width='70%' border='1' align='center'><tbody>\n" +
                 "<tr>\n" +
                 "<td width='100%' align='left' style='color: blue'>\n" +
                 "<strong>There are no users created or for the search criteria</strong>\n" +
                 "</td>\n" +
                 "</tr>\n" +
                 "</tbody></table>";
        return answer;
    }

    public String pone_tabla_pie(Integer listas,Integer lotes,Integer inicio, Integer adelante, Integer atras,Long fin){
        answer = "<table width='70%' border='0' align='center'><tbody>\n" +
                 "<tr>\n" +
                 "<td width='50%' align='center' style='color: blue'>\n" +
                 "<strong>&nbsp;</strong>\n" +
                 "</td>\n" +
                 "<td width='50%' align='right' style='color: black'>\n";
        if(listas!=1){
            answer = answer + "<a href='#' onClick=desplaza('" + inicio + "')>\n" +
                              "<i class='fa fa-step-backward fa-fw' alt='Listar Usuario'></i></a>&nbsp;\n" +
                              "<a href='#' onClick=desplaza('" + atras + "')>\n" +
                              "<i class='fa fa-arrow-left fa-fw' alt='Listar Usuario'></i></a>&nbsp;\n";
        }
        for(int i=1;i<=lotes;i++){
            if(i!=listas)
                answer = answer + "<a href='#' onClick=desplaza('" + i + "')>" + i + "</a>&nbsp;\n";
            else
                answer = answer + "<a href='#' onClick=desplaza('" + i + "') style='color: black'>" + i + "</a>&nbsp;\n";
        }
        if(listas!=lotes){
            answer = answer + "<a href='#' onClick=desplaza('" + adelante + "')><i class='fa fa-arrow-right fa-fw'></i></a>&nbsp;\n" +
                              "<a href='#' onClick=desplaza('" + fin + "')><i class='fa fa-step-forward fa-fw'></i></a>\n";
        }
        answer = answer + "</td>\n" +
                          "</tr>\n" +
                          "</tbody></table>\n";
        return answer;
    }*/
}
