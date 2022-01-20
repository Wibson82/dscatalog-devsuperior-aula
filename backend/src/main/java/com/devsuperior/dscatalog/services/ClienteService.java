package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.entities.Cliente;
import com.devsuperior.dscatalog.dto.ClienteDTO;
import com.devsuperior.dscatalog.repositories.ClienteRepository;
import com.devsuperior.dscatalog.resources.exceptions.DataIntegrityException;
import com.devsuperior.dscatalog.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;
    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Cliente find(@PathVariable Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetcNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        //find(id);
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw  new DataIntegrityException(
                    "Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(),
                objDto.getEmail(), null, null);
    }
}
