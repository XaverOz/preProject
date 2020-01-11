package web.controller;

import model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CarController {

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public void getCars(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        CarService carService = new CarService();
        model.addAttribute("locale", allRequestParams.get("locale"));
        model.addAttribute("cars", carService.getCars());
    }

}
