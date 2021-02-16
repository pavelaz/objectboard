package com.psg.objectboard.controller;

import com.psg.objectboard.model.datatransferobject.CommentsGetDto;
import com.psg.objectboard.model.datatransferobject.CommentsInsertUpdateDto;
import com.psg.objectboard.model.service.CommentsService;

import java.util.HashSet;

public class CommentsController {

    private CommentsService commentsService;

    public CommentsController(){
        this.commentsService = new CommentsService();
    }

    public CommentsGetDto getCommentsList (){

        CommentsGetDto commentsGetDto = commentsService.getListComments();
        System.out.println("Leido Controlador Comments GetList");

        return commentsGetDto;
    }

    public void insertCommentsList (CommentsInsertUpdateDto commentsInsertUpdateDto){

        commentsService.insertComments(commentsInsertUpdateDto);

        System.out.println("Leido Controlador Comments Insert");

    }

    public void updateCommentsList (CommentsInsertUpdateDto commentsInsertUpdateDto){

        commentsService.updateBussinnessType(commentsInsertUpdateDto);

        System.out.println("Leido Controlador Comments UpdateList");

    }

    public void deleteCommentsList (CommentsGetDto commentsGetDto){

        commentsService.deleteComments(commentsGetDto);

        System.out.println("Leido Controlador Comments delete");

    }

    public CommentsGetDto tableDataConversionToList(String stringTable, int lengthTable) {
        CommentsGetDto commentsDto = new CommentsGetDto();
        CommentsGetDto arrayTablaDto = new CommentsGetDto();
        String [] arrayTabla = stringTable.split(",");
        int i=0, y=1;
        commentsDto.setCommentsDtoSet(new HashSet<>());
        for(String s: arrayTabla) {
                i++;
                System.out.println("Objeto ("+ y +") = Index:("+ i +") " + s.trim());
                if (i == lengthTable) {i = 0;y++;}
                switch (i) {//Segun crezca el numero de las columnas en la tabla se debe agregar el (case:n.) con el atributtoDTO.
                    case 1:
                        arrayTablaDto.setActions(s.intern());
                        break;
                    case 2:
                        arrayTablaDto.setCommentCode(Long.parseLong(s.intern()));
                        break;
                    case 3:
                        arrayTablaDto.setBussinessUnitBuBisCode(Long.parseLong(s.intern()));
                        break;
                    case 4:
                        arrayTablaDto.setCommentDescription(s.intern());
                        break;
                    default:
                        arrayTablaDto.setCommentType(s.intern());

                        final CommentsGetDto valorizationDto = new CommentsGetDto();
                        valorizationDto.setCommentCode(arrayTablaDto.getCommentCode());
                        valorizationDto.setBussinessUnitBuBisCode(arrayTablaDto.getBussinessUnitBuBisCode());
                        valorizationDto.setActions(arrayTablaDto.getActions());
                        valorizationDto.setCommentDescription(arrayTablaDto.getCommentDescription());
                        valorizationDto.setCommentType(arrayTablaDto.getCommentType());

                        commentsDto.getCommentsDtoSet().add(valorizationDto);
                }
        }
        return commentsDto;
    }

    public CommentsInsertUpdateDto classificationOfDataTable (CommentsGetDto commentsGetDto) {
        CommentsInsertUpdateDto commentsInsertUpdateDto = new CommentsInsertUpdateDto();

        commentsInsertUpdateDto.setCommentsInsertDtoSet(new HashSet<>());
        commentsInsertUpdateDto.setCommentsUpdateDtoSet(new HashSet<>());
        for (CommentsGetDto commentsDto:commentsGetDto.getCommentsDtoSet()) {
            final CommentsInsertUpdateDto valorizationDto = new CommentsInsertUpdateDto();
            switch (commentsDto.getActions()){
                case ("up"):
                    valorizationDto.setCommentCode(commentsDto.getCommentCode());
                    valorizationDto.setBussinessUnitBuBisCode(commentsDto.getBussinessUnitBuBisCode());
                    valorizationDto.setCommentDescription(commentsDto.getCommentDescription());
                    valorizationDto.setCommentType(commentsDto.getCommentType());

                    commentsInsertUpdateDto.getCommentsUpdateDtoSet().add(valorizationDto);
                break;
                case ("+"):
                    valorizationDto.setCommentCode(commentsDto.getCommentCode());
                    valorizationDto.setBussinessUnitBuBisCode(commentsDto.getBussinessUnitBuBisCode());
                    valorizationDto.setCommentDescription(commentsDto.getCommentDescription());
                    valorizationDto.setCommentType(commentsDto.getCommentType());

                    commentsInsertUpdateDto.getCommentsInsertDtoSet().add(valorizationDto);
                break;
            }
        }
        for (CommentsInsertUpdateDto toPrintDto: commentsInsertUpdateDto.getCommentsUpdateDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Update ("+ i +") -> getCommentCode(): " +
                    toPrintDto.getCommentCode() + " getName(): " + " getCommentDescription(): " +
                    toPrintDto.getCommentDescription() +
                    " getCommentDescription(): "+ toPrintDto.getCommentType());
            i++;
        }
        for (CommentsInsertUpdateDto toPrintDto: commentsInsertUpdateDto.getCommentsInsertDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Update ("+ i +") -> getCommentCode(): " +
                    toPrintDto.getCommentCode() + " getName(): " + " getCommentDescription(): " +
                    toPrintDto.getCommentDescription() + " getCommentType(): " + toPrintDto.getCommentType());
            i++;
        }
        return commentsInsertUpdateDto;
    }

}