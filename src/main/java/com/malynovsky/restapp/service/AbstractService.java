package com.malynovsky.restapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<ENTITY> implements Service<ENTITY> {
    
    protected abstract JpaRepository<ENTITY, Integer> getRepo();

    public List<ENTITY> getAll() {
        return getRepo().findAll();
    }

    public Page<ENTITY> getPage(int pageNumber, int count) {
        Pageable pageInfo = PageRequest.of(pageNumber, count);
        return getRepo().findAll(pageInfo);
    }

    public ENTITY getById(int id) {
        return getRepo().getOne(id);
    }

    public ENTITY save(ENTITY entity) {
        return getRepo().save(entity);
    }

    @Override
    public List<ENTITY> save(List<ENTITY> entity) {
        return getRepo().saveAll(entity);
    }
}
