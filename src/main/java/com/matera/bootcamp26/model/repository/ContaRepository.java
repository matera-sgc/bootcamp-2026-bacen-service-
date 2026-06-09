package com.matera.bootcamp26.model.repository;

import com.matera.bootcamp26.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumConta(Integer destinoConta);
}
