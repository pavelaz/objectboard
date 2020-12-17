package com.psg.objectboard.controller;

import com.psg.objectboard.model.datatransferobject.BussinessTypeBasicDto;
import com.psg.objectboard.model.datatransferobject.BussinessTypeDto;
import com.psg.objectboard.model.entity.BussinessTypeEntity;
import com.psg.objectboard.model.service.BussinessTypeService;
import java.util.HashSet;

public class BussinessTypeController {

    private BussinessTypeService bussinessTypeService;

    public BussinessTypeController(){
        this.bussinessTypeService = new BussinessTypeService();
    }

    public BussinessTypeDto getBussinessTypeList (){

        BussinessTypeDto bussinessTypeDto = bussinessTypeService.getListBussinessType();
        System.out.println("Leido Controlador BussinessType GetList");

        return bussinessTypeDto;
    }

    public void insertBussinessTypeList (BussinessTypeBasicDto bussinessTypeBasicDto){

        bussinessTypeService.insertBussinessType(bussinessTypeBasicDto);

        System.out.println("Leido Controlador BussinessType Insert");

    }

    public void updateBussinessTypeList (BussinessTypeBasicDto bussinessTypeBasicDto){

        bussinessTypeService.updateBussinnessType(bussinessTypeBasicDto);

        System.out.println("Leido Controlador BussinessType UpdateList");

    }

    public void deleteBussinessTypeList (BussinessTypeDto bussinessTypeDto){

        bussinessTypeService.deleteBussinessType(bussinessTypeDto);

        System.out.println("Leido Controlador BussinessType delete");

    }

    public BussinessTypeDto tableDataConversionToList(String stringTable, int lengthTable) {
        BussinessTypeDto bussinessTypeDto = new BussinessTypeDto();
        BussinessTypeDto arrayTablaDto = new BussinessTypeDto();
        String [] arrayTabla = stringTable.split(",");
        int i=0, y=1;
        bussinessTypeDto.setBussinessTypeDtoSet(new HashSet<>());
        for(String s: arrayTabla) {
                i++;
                System.out.println("Objeto ("+ y +") = Index:("+ i +") " + s.trim());
                if (i == lengthTable) {i = 0;y++;}
                switch (i) {//Segun crezca el numero de las columnas en la tabla se debe agregar el (case:n.) con el atributtoDTO.
                    case 1:
                        arrayTablaDto.setActions(s.intern());
                        break;
                    case 2:
                        arrayTablaDto.setBtCodeType(Long.parseLong(s.intern()));
                        break;
                    case 3:
                        arrayTablaDto.setBtDescription(s.intern());
                        break;
                    default:
                        arrayTablaDto.setBtNote(s.intern());

                        final BussinessTypeDto valorizationDto = new BussinessTypeDto();
                        valorizationDto.setBtCodeType(arrayTablaDto.getBtCodeType());
                        valorizationDto.setActions(arrayTablaDto.getActions());
                        valorizationDto.setBtDescription(arrayTablaDto.getBtDescription());
                        valorizationDto.setBtNote(arrayTablaDto.getBtNote());

                        bussinessTypeDto.getBussinessTypeDtoSet().add(valorizationDto);
                }
        }
        return bussinessTypeDto;
    }

    public BussinessTypeBasicDto classificationOfDataTable (BussinessTypeDto bussinesTypeDto) {
        BussinessTypeBasicDto bussinessTypeBasicDto = new BussinessTypeBasicDto();

        bussinessTypeBasicDto.setBussinessTypeInsertDtoSet(new HashSet<>());
        bussinessTypeBasicDto.setBussinessTypeUpdateDtoSet(new HashSet<>());
        for (BussinessTypeDto bussinessTypeDto:bussinesTypeDto.getBussinessTypeDtoSet()) {
            final BussinessTypeBasicDto valorizationDto = new BussinessTypeBasicDto();
            switch (bussinessTypeDto.getActions()){
                case ("up"):
                    valorizationDto.setBtCodeType(bussinessTypeDto.getBtCodeType());
                    valorizationDto.setBtDescription(bussinessTypeDto.getBtDescription());
                    valorizationDto.setBtNote(bussinessTypeDto.getBtNote());

                    bussinessTypeBasicDto.getBussinessTypeUpdateDtoSet().add(valorizationDto);
                break;
                case ("+"):
                    valorizationDto.setBtCodeType(bussinessTypeDto.getBtCodeType());
                    valorizationDto.setBtDescription(bussinessTypeDto.getBtDescription());
                    valorizationDto.setBtNote(bussinessTypeDto.getBtNote());

                    bussinessTypeBasicDto.getBussinessTypeInsertDtoSet().add(valorizationDto);
                    //bussinessTypeDto.getBussinessTypeDtoSet().iterator().next();
                break;
            }
        }
        for (BussinessTypeBasicDto toPrintDto:bussinessTypeBasicDto.getBussinessTypeUpdateDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Update ("+ i +") -> getBtCodeType: " +
                    toPrintDto.getBtCodeType() + " getBtDescription: " +
                    toPrintDto.getBtDescription() + " getBtNote: " + toPrintDto.getBtNote());
            i++;
        }
        for (BussinessTypeBasicDto toPrintDto:bussinessTypeBasicDto.getBussinessTypeInsertDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Insert ("+ i +") -> getBtCodeType: " +
                    toPrintDto.getBtCodeType()+" getBtDescription: " +
                    toPrintDto.getBtDescription()+" getBtNote: " + toPrintDto.getBtNote());
            i++;
        }
        return bussinessTypeBasicDto;
    }

}