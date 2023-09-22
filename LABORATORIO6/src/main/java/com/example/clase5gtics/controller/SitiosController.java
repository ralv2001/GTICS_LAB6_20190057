package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Site;
import com.example.clase5gtics.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/sitios")
public class SitiosController {

    final SiteRepositroy siteRepositroy;
    final LocationRepository locationRepository;

    public SitiosController(SiteRepositroy siteRepositroy, LocationRepository locationRepository) {
        this.siteRepositroy = siteRepositroy;
        this.locationRepository = locationRepository;
    }

    @GetMapping(value = {"", "/listar"})
    public String listaSitios(Model model) {
        model.addAttribute("listaSitios", siteRepositroy.findAll());
        return "sitios/list";
    }

    @GetMapping("/new")
    public String nuevoSitioFrm(Model model) {
        model.addAttribute("listaLocation", locationRepository.findAll());
        return "sitios/newFrm";
    }

    @PostMapping("/save")
    public String guardarSitio(Site site) {
        siteRepositroy.save(site);
        return "redirect:/sitios";
    }

    @GetMapping("/edit")
    //ESTE ID DEL @RequestParam ES EL QUE SE PONE EN LA URL PARA EDITAR:
    // <td><a class="btn btn-primary" th:href="@{'/sitios/edit' + '?id=' + ${sitio.shipperId}}">Editar</a>
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Site> optSitio = siteRepositroy.findById(id);

        if (optSitio.isPresent()) {
            Site site = optSitio.get();
            model.addAttribute("site", site);
            model.addAttribute("listaLocation", locationRepository.findAll());
            return "sitios/editFrm";
        } else {
            return "redirect:/sitios";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id) {

        Optional<Site> optSitio = siteRepositroy.findById(id);

        if (optSitio.isPresent()) {
            siteRepositroy.deleteById(id);
        }
        return "redirect:/sitios";

    }
}
