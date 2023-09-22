package com.example.clase5gtics.repository;

import com.example.clase5gtics.entity.Location;
import com.example.clase5gtics.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepositroy  extends JpaRepository<Site, Integer>{
}
