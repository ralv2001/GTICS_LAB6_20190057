package com.example.clase5gtics.repository;

import com.example.clase5gtics.entity.Device;
import com.example.clase5gtics.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
}


