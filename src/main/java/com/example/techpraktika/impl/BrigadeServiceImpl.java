package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.repository.BrigadeRepo;
import com.example.techpraktika.repository.ConstructionSiteRepo;
import com.example.techpraktika.service.BrigadeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrigadeServiceImpl implements BrigadeService {

    private final BrigadeRepo brigadeRepository;
    private final ConstructionSiteRepo constructionSiteRepository;

    public BrigadeServiceImpl(BrigadeRepo brigadeRepository, ConstructionSiteRepo constructionSiteRepository) {
        this.brigadeRepository = brigadeRepository;
        this.constructionSiteRepository = constructionSiteRepository;
    }

    @Override
    public List<Brigade> findAll() {
        return brigadeRepository.findAll();
    }

    @Override
    public Optional<Brigade> findById(Long id) {
        return brigadeRepository.findById(id);
    }

    @Override
    public List<Brigade> findByNameContaining(String search) {
        return brigadeRepository.findByNameContainingIgnoreCase(search);
    }

    @Override
    public void save(Brigade brigade) {
        brigadeRepository.save(brigade);
    }

    @Override
    public void update(Brigade brigade) {
        brigadeRepository.save(brigade);
    }

    @Override
    public void deleteById(Long id) {
        brigadeRepository.deleteById(id);
    }

    @Override
    public void addConstructionSite(Long brigadeId, Long siteId) {
        Brigade brigade = brigadeRepository.findById(brigadeId)
                .orElseThrow(() -> new RuntimeException("Brigade not found"));
        ConstructionSite site = constructionSiteRepository.findById(siteId)
                .orElseThrow(() -> new RuntimeException("Construction site not found"));

        if (!brigade.getConstructionSites().contains(site)) {
            brigade.getConstructionSites().add(site);
        }
        if (!site.getBrigades().contains(brigade)) {
            site.getBrigades().add(brigade);
        }


        brigadeRepository.save(brigade);

    }

    @Override
    public void removeConstructionSite(Long brigadeId, Long siteId) {
        Brigade brigade = brigadeRepository.findById(brigadeId)
                .orElseThrow(() -> new RuntimeException("Brigade not found"));
        ConstructionSite site = constructionSiteRepository.findById(siteId)
                .orElseThrow(() -> new RuntimeException("Construction site not found"));

        brigade.getConstructionSites().remove(site);
        site.getBrigades().remove(brigade);

        brigadeRepository.save(brigade);
    }
}
