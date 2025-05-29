package com.example.techpraktika.repository;

import com.example.techpraktika.entity.ConstructionSite;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionSiteRepo extends JpaRepository<ConstructionSite, Long> {

    @EntityGraph(attributePaths = {"defects", "siteStages", "brigades"})
    @Override
    List<ConstructionSite> findAll();
}
