package com.example.techpraktika.repository;

import com.example.techpraktika.entity.ConstructionSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSiteRepo extends JpaRepository<ConstructionSite, Long> {
}
