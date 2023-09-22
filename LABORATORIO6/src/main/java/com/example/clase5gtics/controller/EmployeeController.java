package com.example.clase5gtics.controller;

import com.example.clase5gtics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/stats")
    public String estadisticas(Model model) {
        model.addAttribute("listaEmpleadosRegion", employeeRepository.obtenerEmpleadosPorRegion());
        model.addAttribute("listaEmpleadosPais",employeeRepository.obtenerEmpleadosPorPais());
        model.addAttribute("listaEmpleadoPaisCompleta",employeeRepository.obtenerEmpleadosPorPais());

        return "employee/estadistica";
    }

    @PostMapping("/searchEmpMin")
    public String busqueda(@RequestParam("searchField") int busqueda, Model model){
        model.addAttribute("listaEmpleadosRegion",employeeRepository.obtenerEmpleadosPorRegion(busqueda));
        model.addAttribute("listaEmpleadosPais",employeeRepository.obtenerEmpleadosPorPais());
        model.addAttribute("listaEmpleadoPaisCompleta",employeeRepository.obtenerEmpleadosPorPais());

        return "employee/estadistica";
    }

    @PostMapping("/filtroPais")
    public String filtrarPais(@RequestParam("filtro") String filtro, Model model){

        model.addAttribute("listaEmpleadosRegion", employeeRepository.obtenerEmpleadosPorRegion());
        model.addAttribute("listaEmpleadoPaisCompleta",employeeRepository.obtenerEmpleadosPorPais());
        model.addAttribute("filtro",filtro);

        if(filtro.equals("-1")){
            model.addAttribute("listaEmpleadosPais",employeeRepository.obtenerEmpleadosPorPais());
        }else{
            model.addAttribute("listaEmpleadosPais",employeeRepository.obtenerEmpleadosPorPais(filtro));
        }

        return "employee/estadistica";
    }
}
