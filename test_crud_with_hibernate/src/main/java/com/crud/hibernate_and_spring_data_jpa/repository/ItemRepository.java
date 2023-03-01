package com.crud.hibernate_and_spring_data_jpa.repository;

import com.crud.hibernate_and_spring_data_jpa.entity.Item;
import com.crud.hibernate_and_spring_data_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findItemsByOwner(Person owner);
}
