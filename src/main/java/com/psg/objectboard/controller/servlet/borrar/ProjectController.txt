package com.psg.objectboard.controller.servlet.borrar;

import com.psg.objectboard.model.datatransferobject.ProjectGetDto;
import com.psg.objectboard.model.datatransferobject.ProjectInsertUpdateDto;
import com.psg.objectboard.model.service.ProjectService;

import java.util.HashSet;

public class ProjectController {

    private ProjectService projectService;

    public ProjectController(){
        this.projectService = new ProjectService();
    }

    public ProjectGetDto getProjectList (){

        ProjectGetDto projectGetDto = projectService.getListProject();
        System.out.println("Leido Controlador Project GetList");

        return projectGetDto;
    }

    public void insertProjectList (ProjectInsertUpdateDto projectInsertUpdateDto){

        projectService.insertProject(projectInsertUpdateDto);

        System.out.println("Leido Controlador Project Insert");

    }

    public void updateProjectList (ProjectInsertUpdateDto projectInsertUpdateDto){

        projectService.updateBussinnessType(projectInsertUpdateDto);

        System.out.println("Leido Controlador Project UpdateList");

    }

    public void deleteProjectList (ProjectGetDto projectGetDto){

        projectService.deleteProject(projectGetDto);

        System.out.println("Leido Controlador Project delete");

    }

    public ProjectGetDto tableDataConversionToList(String stringTable, int lengthTable) {
        ProjectGetDto projectDto = new ProjectGetDto();
        ProjectGetDto arrayTablaDto = new ProjectGetDto();
        String [] arrayTabla = stringTable.split(",");
        int i=0, y=1;
        projectDto.setProjectDtoSet(new HashSet<>());
        for(String s: arrayTabla) {
                i++;
                System.out.println("Objeto ("+ y +") = Index:("+ i +") " + s.trim());
                if (i == lengthTable) {i = 0;y++;}
                switch (i) {//Segun crezca el numero de las columnas en la tabla se debe agregar el (case:n.) con el atributtoDTO.
                    case 1:
                        arrayTablaDto.setActions(s.intern());
                        break;
                    case 2:
                        arrayTablaDto.setPrIdProject(Long.parseLong(s.intern()));
                        break;
                    case 3:
                        arrayTablaDto.setPrName(s.intern());
                        break;
                    default:
                        arrayTablaDto.setPrNote(s.intern());

                        final ProjectGetDto valorizationDto = new ProjectGetDto();
                        valorizationDto.setPrIdProject(arrayTablaDto.getPrIdProject());
                        valorizationDto.setActions(arrayTablaDto.getActions());
                        valorizationDto.setPrName(arrayTablaDto.getPrName());
                        valorizationDto.setPrNote(arrayTablaDto.getPrNote());

                        projectDto.getProjectDtoSet().add(valorizationDto);
                }
        }
        return projectDto;
    }

    public ProjectInsertUpdateDto classificationOfDataTable (ProjectGetDto projectGetDto) {
        ProjectInsertUpdateDto projectInsertUpdateDto = new ProjectInsertUpdateDto();

        projectInsertUpdateDto.setProjectInsertDtoSet(new HashSet<>());
        projectInsertUpdateDto.setProjectUpdateDtoSet(new HashSet<>());
        for (ProjectGetDto projectDto:projectGetDto.getProjectDtoSet()) {
            final ProjectInsertUpdateDto valorizationDto = new ProjectInsertUpdateDto();
            switch (projectDto.getActions()){
                case ("up"):
                    valorizationDto.setPrIdProject(projectDto.getPrIdProject());
                    valorizationDto.setPrName(projectDto.getPrName());
                    valorizationDto.setPrNote(projectDto.getPrNote());

                    projectInsertUpdateDto.getProjectUpdateDtoSet().add(valorizationDto);
                break;
                case ("+"):
                    valorizationDto.setPrIdProject(projectDto.getPrIdProject());
                    valorizationDto.setPrName(projectDto.getPrName());
                    valorizationDto.setPrNote(projectDto.getPrNote());

                    projectInsertUpdateDto.getProjectInsertDtoSet().add(valorizationDto);
                    //projectDto.getProjectDtoSet().iterator().next();
                break;
            }
        }
        for (ProjectInsertUpdateDto toPrintDto: projectInsertUpdateDto.getProjectUpdateDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Update ("+ i +") -> getBtCodeType: " +
                    toPrintDto.getPrIdProject() + " getBtDescription: " +
                    toPrintDto.getPrName() + " getBtNote: " + toPrintDto.getPrNote());
            i++;
        }
        for (ProjectInsertUpdateDto toPrintDto: projectInsertUpdateDto.getProjectInsertDtoSet()) {
            int i = 0;
            System.out.println("Objeto to Insert ("+ i +") -> getBtCodeType: " +
                    toPrintDto.getPrIdProject()+" getBtDescription: " +
                    toPrintDto.getPrName()+" getBtNote: " + toPrintDto.getPrNote());
            i++;
        }
        return projectInsertUpdateDto;
    }

}