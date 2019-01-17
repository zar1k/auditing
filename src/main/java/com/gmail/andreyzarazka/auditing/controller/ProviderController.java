package com.gmail.andreyzarazka.auditing.controller;

import com.gmail.andreyzarazka.auditing.model.Provider;
import com.gmail.andreyzarazka.auditing.service.ProviderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ProviderController {
    private final ProviderService service;

    public ProviderController(ProviderService service) {
        this.service = service;
    }

    @GetMapping("/find/{id}")
    public Optional<Provider> getById(@PathVariable long id) {
        return this.service.findById(id);
    }

    @GetMapping("/all")
    public Page<Provider> findAll(Pageable pageable) {
        return this.service.findAll(pageable);
    }

    @PostMapping("/save")
    public Provider save(@RequestBody Provider provider) {
        return this.service.save(provider);
    }

    @PutMapping("/update")
    public Provider update(@RequestBody Provider provider) {
        return this.service.save(provider);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }
}
