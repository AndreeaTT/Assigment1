package com.spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spr.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}