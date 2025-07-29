package sn.dev.examen.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.dev.examen.dto.ClasseDto;
import sn.dev.examen.dto.SectorDto;
import sn.dev.examen.service.IClasseService;
import sn.dev.examen.service.ISectorService;

import java.util.List;

@Controller
public class ClasseController {
    private IClasseService classeService;
    private ISectorService sectorService;

    public ClasseController(IClasseService classeService, ISectorService sectorService) {
        this.classeService = classeService;
        this.sectorService = sectorService;
    }
    // Add methods to handle requests related to Classe
    @GetMapping("/classes")
    public String getAllClasses(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        Page<ClasseDto> paginatedClasses = classeService.getAllClassesPaginated(page, size);
        model.addAttribute("classes", paginatedClasses.getContent());
        List<SectorDto> sectors = sectorService.getAllSectors();
        model.addAttribute("sectors", sectors);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginatedClasses.getTotalPages());
        return "classes";
    }
    @PostMapping("/classes")
    public String createClasseForm(@ModelAttribute("newClasse") ClasseDto classeDto) {
        classeService.createClasse(classeDto);
        return "redirect:/classes";
    }
    @GetMapping("/classes/search")
    @ResponseBody
    public List<ClasseDto> searchClasses(@RequestParam("query") String query) {
        return classeService.searchClasses(query);
    }
    @PostMapping("/classes/update")
    public String updateClasse(@ModelAttribute ClasseDto dto) {
        SectorDto sector = sectorService.getSectorById(dto.getSectorId());
        dto.setSector(sector);
        classeService.updateClasse(dto);
        return "redirect:/classes";
    }
    @PostMapping("/classes/delete")
    public String deleteClasse(@RequestParam("id") int id) {
        classeService.deleteClasse(id);
        return "redirect:/classes";
    }
}
