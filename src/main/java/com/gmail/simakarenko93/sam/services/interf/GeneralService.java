package com.gmail.simakarenko93.sam.services.interf;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService<E> {
    void addObj(String email,E obj);
    //void delete(List<Long> idList);
    void update(String email, E obj);
    List<E> getList(String email, Pageable pageable);
    Long count(String email);
}
