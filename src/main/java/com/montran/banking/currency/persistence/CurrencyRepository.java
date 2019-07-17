package com.montran.banking.currency.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.currency.domain.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
