package com.biglietti_teatro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biglietti_teatro.model.Cliente;
import com.biglietti_teatro.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
    private ClienteRepository clienteRepository;
	
	// tutti clienti (solo admin gestione clienti)
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }
    // per login
    public Optional<Cliente> getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

}
