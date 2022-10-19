package com.sena.leonardo.algamoneyapi.domain.repositories.register;

import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.repositories.filter.RegisterFilter;
import com.sena.leonardo.algamoneyapi.domain.repositories.projection.RegisterSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterRepositoryQuery {

    public Page<Register> filter(RegisterFilter filter, Pageable pageable);

    public Page<RegisterSummary> registerSummary(RegisterFilter filter, Pageable pageable);
}
