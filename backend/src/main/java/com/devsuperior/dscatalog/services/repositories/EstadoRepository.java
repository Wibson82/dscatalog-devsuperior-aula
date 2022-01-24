package com.devsuperior.dscatalog.services.repositories;

import com.devsuperior.dscatalog.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
