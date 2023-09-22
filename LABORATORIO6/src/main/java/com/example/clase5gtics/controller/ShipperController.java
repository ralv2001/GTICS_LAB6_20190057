package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Shipper;
import com.example.clase5gtics.repository.ShipperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    final ShipperRepository shipperRepository;

    public ShipperController(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Shipper shipper, RedirectAttributes attr) {
        String estado = shipper.getShipperId() == null ? "creado" : "actualizado";
        attr.addFlashAttribute("msg", "Transportista " + estado + " correctamente");

        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrm";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id, RedirectAttributes attributes) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            String shipperName = shipper.getCompanyname();
            shipperRepository.deleteById(id);
            attributes.addFlashAttribute("msg", "Transportista " + shipperName + " borrado exitosamente");
        }
        return "redirect:/shipper/list";

    }

    @PostMapping("/BuscarTransportistas")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        List<Shipper> listaTransportistas = shipperRepository.buscarTransPorCompName(searchField);
        model.addAttribute("shipperList", listaTransportistas);

        return "shipper/list";
    }


}

