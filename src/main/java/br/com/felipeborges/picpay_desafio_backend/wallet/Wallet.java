package br.com.felipeborges.picpay_desafio_backend.wallet;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Wallet (
    @Id Long id,
    String fullName,
    Long cpf,
    String email,
    String password,
    WalletType type,
    BigDecimal balance) {
}
