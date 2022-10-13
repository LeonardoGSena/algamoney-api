package com.sena.leonardo.algamoneyapi.domain.repositories;

import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.repositories.register.RegisterRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>, RegisterRepositoryQuery {

}
