package sn.dev.examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sn.dev.examen.service.IClasseService;
import sn.dev.examen.service.ISectorService;


public class HomeController {
    private IClasseService classeService;
    private ISectorService sectorService;

    public HomeController(IClasseService classeService, ISectorService sectorService) {
        this.classeService = classeService;
        this.sectorService = sectorService;
    }

    //@GetMapping("/")
    public String index(Model model) {

        model.addAttribute("totalSectors", 12);
        model.addAttribute("totalClasses", 48);
        model.addAttribute("lastSectors", sectorService.getAllSectors());
        model.addAttribute("lastClasses", classeService.getAllClasses());

        return "index";
    }
}
