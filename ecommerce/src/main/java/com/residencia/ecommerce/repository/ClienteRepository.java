package com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente,Integer>{

    List<Cliente> findByEmail(String email);

}
