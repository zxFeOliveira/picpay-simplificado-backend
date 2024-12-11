package br.com.felipeborges.picpay_desafio_backend.transaction;

import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction,Long> {
}
