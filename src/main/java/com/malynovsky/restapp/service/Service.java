package com.malynovsky.restapp.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface Service<ENTITY> {

    List<ENTITY> getAll();

    Page<ENTITY> getPage(int pageNumber, int count);

    ENTITY getById(int id);

    ENTITY save(ENTITY entity);

    List<ENTITY> save(List<ENTITY> entity);
}
