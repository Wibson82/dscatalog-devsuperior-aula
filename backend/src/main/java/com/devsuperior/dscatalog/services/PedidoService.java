package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.entities.Pedido;
import com.devsuperior.dscatalog.repositories.PedidoRepository;
import com.devsuperior.dscatalog.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;
    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Pedido find(@PathVariable Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetcNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
