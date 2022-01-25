package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ClientDTO;
import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.services.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(@PathVariable Long id) {
        Optional<Client> obj = repository.findById(id);
        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException(
                "Entidade não encontrada! Id: " + id + ", Tipo: " + Client.class.getName()));
        return new ClientDTO(entity);
    }

    @Transactional()
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional()
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (javax.persistence.EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(
                    "Cliente não encontrado para o id " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(
                    "Não é possível excluir um cliente que possui produtos");
        }
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
