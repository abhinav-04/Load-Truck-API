package com.load.truck.controllers;

import com.load.truck.models.Load;
import com.load.truck.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping("/load")
    public ResponseEntity<Load> addLoad(@RequestBody Load load) {
        Load responseLoad = loadService.addLoad(load);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseLoad);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getLoadsByShipperId(@RequestParam String shipperId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loadService.getLoadsByShipperId(shipperId));
    }

    @GetMapping("/load/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId) {
        return ResponseEntity.status(HttpStatus.OK).body(loadService.getLoadById(loadId));
    }

    @PutMapping("/load/{loadId}")
    public ResponseEntity<Object> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoad) {
        Load load = loadService.updateLoad(loadId, updatedLoad);
        if (load != null) {
            return ResponseEntity.status(HttpStatus.OK).body(load);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Load not found with id: " + loadId);
        }
    }

    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Load deleted successfully");
    }
}
