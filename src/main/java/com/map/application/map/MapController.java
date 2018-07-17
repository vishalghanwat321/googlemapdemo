package com.map.application.map;


/*
 *
 *  @project application
 *
 *  @author vishal on 13/07/18  8:45 PM
 *
 */

import com.google.gson.Gson;
import com.map.application.location.domain.LocationDetail;
import com.map.application.location.dto.LocationDetailDTO;
import com.map.application.location.service.LocationService;
import com.map.application.path.ApiRequestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@DependsOn("locationService")
@RequestMapping(ApiRequestPath.MAP)
public class MapController {

    @Autowired
    private LocationService locationService;

    public static final String[][] details = {{"Home", "This is my Home, where I live", "18.599179", "73.898638"},
            {"College", "College where I have completed my engineering ", "18.644855", "73.758187"},
            {"University", "This is Pune University", "18.552640", "73.826518"},
            {"Work", "My Office, MasterCard!!!", "18.550339", "73.890432"},
            {"marketplace", "Market Place, Big Bazar", "18.570732", "73.878294"}};

    @GetMapping
    public String showMapPage(ModelMap model) {

        for (Object[] data : details) {
            this.locationService.setUpLocation(LocationDetailDTO.builder()
                    .name(data[0].toString())
                    .description(data[1].toString())
                    .latitude(Double.valueOf(data[2].toString()))
                    .longitude(Double.valueOf(data[3].toString()))
                    .build());
        }

        Object[][] locations = new Object[details.length][4];

        int i=0;

        for (LocationDetail locationDetail : this.locationService.query()) {
                locations[i][0] = locationDetail.getName();
                locations[i][1] = locationDetail.getDescription();
                locations[i][2] = locationDetail.getLatitude();
                locations[i][3] = locationDetail.getLongitude();
                i++;
            }

        model.addAttribute("locations", new Gson().toJson(locations));

        return "map";
    }

}
