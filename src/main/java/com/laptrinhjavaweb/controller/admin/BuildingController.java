package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService ;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch",buildingDTO);
        List<String> buildingTypes = new ArrayList<>();
        for (String item:buildingDTO.getBuildingTypes()) {
            buildingTypes.add(item);

        }
        mav.addObject("buildings",buildingService.searchBuilding(buildingDTO));
        mav.addObject("staffMaps",buildingService.getStaff(buildingDTO.getId()));
        mav.addObject("buildingTypes",buildingService.buildingTypes() );
        mav.addObject("district", buildingService.districtName());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("addBuilding") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("addBuilding",buildingDTO);
        mav.addObject("buildingTypes",buildingService.buildingTypes() );
        mav.addObject("district", buildingService.districtName());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable(value = "id") Long id ) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO =  buildingService.findById(id);
        mav.addObject("buildingTypes",buildingService.buildingTypes() );
        mav.addObject("addBuilding",buildingDTO);
        mav.addObject("district", buildingService.districtName());
        return mav;
    }
}
