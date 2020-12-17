package com.psg.objectboard.controller;

import com.psg.objectboard.model.datatransferobject.MasterUserDto;
import com.psg.objectboard.model.datatransferobject.PhotoDto;
import com.psg.objectboard.model.service.MasterUserProfileService;

public class MasterUserProfileController {

    private MasterUserProfileService masterUserService;

    public MasterUserProfileController(){
        this.masterUserService = new MasterUserProfileService();
    }

    public PhotoDto getShowPhoto (long identifier, String email){

        PhotoDto photoDto = masterUserService.getShowPhoto(identifier, email);
        System.out.println("Leido Controlador MasterUser Photo");

        return photoDto;
    }

    public MasterUserDto DetailModuleProfileUser(long identifier, String email){

        MasterUserDto masterUserDto = masterUserService.getMasterUser(identifier, email);
        System.out.println("Leido Controlador MasterUser");

        return masterUserDto;
    }

    public void updateMasterUser (MasterUserDto masterUserDto){

        masterUserService.updateMasterUser(masterUserDto);
        System.out.println("Recibie controlador jsp_objeto y envia a Servicio");
    }
}
