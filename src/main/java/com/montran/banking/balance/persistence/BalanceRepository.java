package com.montran.banking.balance.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.balance.domain.entity.Balance;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long> {

}
