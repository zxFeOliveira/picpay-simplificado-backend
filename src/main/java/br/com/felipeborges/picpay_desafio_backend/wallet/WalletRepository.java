package br.com.felipeborges.picpay_desafio_backend.wallet;

import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
