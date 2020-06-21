package com.diploma.tablet_manager.mapper;

public interface Mapper<D, E> {

    D toDto(E e);
}
