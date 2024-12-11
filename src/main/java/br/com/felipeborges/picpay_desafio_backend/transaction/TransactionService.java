package br.com.felipeborges.picpay_desafio_backend.transaction;

import br.com.felipeborges.picpay_desafio_backend.wallet.Wallet;
import br.com.felipeborges.picpay_desafio_backend.wallet.WalletRepository;
import br.com.felipeborges.picpay_desafio_backend.wallet.WalletType;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService (TransactionRepository transactionRepository, WalletRepository walletRepository){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public Transaction create(Transaction transaction) {
        // 1 - validar
        validate(transaction);

        // 2 - criar a transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - debitar da carteira
        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

        // 4 chamar serviços externos

        return newTransaction;
    }

    /*
    * - the payer has a common wallet
    * -the payer has enough balance
    * - the payer is not the payee
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(payer -> payer.type() == WalletType.COMUM.getValue() &&
                        payer.balance().compareTo(transaction.value()) >= 0 &&
                        !payer.id().equals(transaction.payee()) ? transaction : null));
        //1:09:07
    }
}
