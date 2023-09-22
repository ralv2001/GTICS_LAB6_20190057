package com.example.clase5gtics.repository;

import com.example.clase5gtics.dto.EmpleadosPorPaisDto;
import com.example.clase5gtics.dto.EmpleadosPorRegionDto;
import com.example.clase5gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(nativeQuery = true, value = "select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
            "FROM region r\n" +
            "         inner join territories t on (r.regionid = t.regionid)\n" +
            "         inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
            "         inner join employees e on (e.employeeid = et.employeeid)\n" +
            "group by r.regionid\n" +
            "order by cantidadempleados DESC")
    List<EmpleadosPorRegionDto> obtenerEmpleadosPorRegion();

    @Query(nativeQuery = true, value = "select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
            "FROM region r\n" +
            "         inner join territories t on (r.regionid = t.regionid)\n" +
            "         inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
            "         inner join employees e on (e.employeeid = et.employeeid)\n" +
            "group by r.regionid\n" +
            "having cantidadempleados >= ?1\n" +
            "order by cantidadempleados DESC")
    List<EmpleadosPorRegionDto> obtenerEmpleadosPorRegion(int cantidadMin);

    @Query(nativeQuery = true,value = "SELECT country as pais, count(*) as cantidad FROM employees GROUP BY country")
    List<EmpleadosPorPaisDto> obtenerEmpleadosPorPais();

    @Query(nativeQuery = true,value = "SELECT country as pais, count(*) as cantidad\n" +
            "FROM employees\n" +
            "WHERE country = ?1\n" +
            "GROUP BY country")
    List<EmpleadosPorPaisDto> obtenerEmpleadosPorPais(String pais);
}
