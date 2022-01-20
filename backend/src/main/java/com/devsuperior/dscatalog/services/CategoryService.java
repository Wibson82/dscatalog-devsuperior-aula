package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.resources.exceptions.DataIntegrityException;
import com.devsuperior.dscatalog.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category buscar(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Category find(@PathVariable Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetcNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Category update(Category obj) {
        Category newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(obj);
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setNome(obj.getNome());
    }

    public void delete(Long id) {
        //find(id);
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw  new DataIntegrityException(
                    "Não é possível excluir uma categoria que possui produtos");
        }
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return  list.stream().map(x -> new CategoryDTO(x))
                .collect(Collectors.toList());
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getNome());
    }
}
