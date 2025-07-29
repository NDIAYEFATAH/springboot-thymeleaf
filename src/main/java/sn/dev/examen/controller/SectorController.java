package sn.dev.examen.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sn.dev.examen.dto.SectorDto;
import sn.dev.examen.service.ISectorService;

import java.util.List;

@Controller
public class SectorController {
    private ISectorService sectorService;

    public SectorController(ISectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public String getAllSectors(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        Page<SectorDto> paginatedClasses = sectorService.getAllClassesPaginated(page, size);
//        List<SectorDto> sectors = sectorService.getAllSectors();
//        sectors.forEach(s -> System.out.println("ID: " + s.getId() + ", Name: " + s.getName()));
//        model.addAttribute("sectors", sectors);
        model.addAttribute("sectors", paginatedClasses.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginatedClasses.getTotalPages());
        return "sectors";
    }
    @PostMapping("/sectors")
    public String createSector(@ModelAttribute("newSector") SectorDto sectorDto) {
        sectorService.createSector(sectorDto);
        return "redirect:/sectors";
    }
    @PostMapping("/sectors/update")
    public String updateSector(@ModelAttribute SectorDto sectorDto) {
        sectorService.updateSector(sectorDto);
        return "redirect:/sectors";
    }
    @PostMapping("/sectors/delete")
    public String deleteSector(@RequestParam Integer id) {
        sectorService.deleteSector(id);
        return "redirect:/sectors";
    }


}
