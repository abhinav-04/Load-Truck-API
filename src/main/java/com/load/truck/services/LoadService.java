package com.load.truck.services;

import com.load.truck.models.Load;
import com.load.truck.repositories.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public Load addLoad(Load load) {
        return loadRepository.save(load);
    }
    public List<Load> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }
    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId).orElse(null);
    }

    public Load updateLoad(UUID loadId, Load updatedLoad) {
        if (loadRepository.existsById(loadId)) {
            updatedLoad.setId(loadId);
            return loadRepository.save(updatedLoad);
        }
        return null;
    }

    public void deleteLoad(UUID loadId) {
        loadRepository.deleteById(loadId);
    }

}
