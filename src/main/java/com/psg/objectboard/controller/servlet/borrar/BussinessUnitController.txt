package com.psg.objectboard.controller;

import com.psg.objectboard.model.datatransferobject.BussinessUnitFullDto;
import com.psg.objectboard.model.service.BussinessUnitService;

import java.util.List;

public class BussinessUnitController {
    private BussinessUnitService bussinessUnitService;

    public BussinessUnitController(){this.bussinessUnitService = new BussinessUnitService();}

    public BussinessUnitFullDto ListBussinessUnit(){

        List<BussinessUnitFullDto> listBussinessUnit = bussinessUnitService.getListBussinessUnit();
        System.out.println("Leido Controlador BussinessUnit");

        return ListBussinessUnit();
    }
}
