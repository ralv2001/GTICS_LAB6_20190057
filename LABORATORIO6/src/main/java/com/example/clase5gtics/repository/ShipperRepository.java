package com.example.clase5gtics.repository;

import com.example.clase5gtics.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyname(String nombre);

    @Query(value = "select * from shippers where Companyname = ?1",
            nativeQuery = true)
    List<Shipper> buscarTransPorCompName(String nombre);


}

