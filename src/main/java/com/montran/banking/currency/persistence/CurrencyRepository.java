package com.montran.banking.currency.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.montran.banking.currency.domain.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

	public Optional<Currency> findByName(String name);
}
