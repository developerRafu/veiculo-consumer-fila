package com.example.demo.service;

import com.example.demo.models.entities.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService implements BaseService<Cliente> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente save(Cliente obj) {
        return repository.save(obj);
    }

    @Override
    public Cliente update(Cliente obj) {
        return repository.save(obj);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
