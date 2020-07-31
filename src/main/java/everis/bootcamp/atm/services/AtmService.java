package everis.bootcamp.atm.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import everis.bootcamp.atm.dto.AccountDTO;
import everis.bootcamp.atm.dto.TransactionAccountDTO;
import reactor.core.publisher.Mono;

@Service
public class AtmService {
    
    private final String accountsUri = "localhost:8082";

    public Mono<AccountDTO> depositATM(String accountId, Double amount) {
        //make TransactionType object for deposit
        TransactionAccountDTO transactionAccountDto = new TransactionAccountDTO(amount, "5f09e93466bb6e3bd0a30c78");
        //create a new account transaction
        Mono<TransactionAccountDTO> newTransaction = WebClient.create(accountsUri + "/transaction/new")
                                    .post()
                                    .body(Mono.just(transactionAccountDto), TransactionAccountDTO.class)
                                    .retrieve()
                                    .bodyToMono(TransactionAccountDTO.class);
        //make a deposit
        return newTransaction.flatMap(transaction -> {
            return WebClient.create(accountsUri + "/account/atm/deposit/"+accountId+"/"+amount+"/"+transaction.getIdAccountTransaction()+"")
                    .put()
                    .retrieve()
                    .bodyToMono(AccountDTO.class);
        });
    }

    public Mono<AccountDTO> withdrawATM(String accountId, Double amount) {
        //make TransactionType object for withdraw
        TransactionAccountDTO transactionAccountDto = new TransactionAccountDTO(amount, "5f09e93466bb6e3bd0a30c78");
        //create a new account transaction
        Mono<TransactionAccountDTO> newTransaction = WebClient.create(accountsUri + "/transaction/new")
                                    .post()
                                    .body(Mono.just(transactionAccountDto), TransactionAccountDTO.class)
                                    .retrieve()
                                    .bodyToMono(TransactionAccountDTO.class);
        //make a withdraw
        return newTransaction.flatMap(transaction -> {
            return WebClient.create(accountsUri + "/account/atm/withdraw/"+accountId+"/"+amount+"/"+transaction.getIdAccountTransaction()+"")
                    .put()
                    .retrieve()
                    .bodyToMono(AccountDTO.class);
        });
    }

    public Mono<AccountDTO> depositToOtherBank(String accountId, Double amount) {
        //make TransactionType object for deposit
        TransactionAccountDTO transactionAccountDto = new TransactionAccountDTO(amount, "5f09e93466bb6e3bd0a30c78");
        //create a new account transaction
        Mono<TransactionAccountDTO> newTransaction = WebClient.create(accountsUri + "/transaction/new")
                                    .post()
                                    .body(Mono.just(transactionAccountDto), TransactionAccountDTO.class)
                                    .retrieve()
                                    .bodyToMono(TransactionAccountDTO.class);

        //make a deposit to other bank
        return newTransaction.flatMap(transaction -> {
            return WebClient.create(accountsUri + "/account/atm/otherBank/deposit/"+accountId+"/"+amount+"/"+transaction.getIdAccountTransaction()+"")
                    .put()
                    .retrieve()
                    .bodyToMono(AccountDTO.class)
                    .switchIfEmpty(Mono.just(new AccountDTO("No se encontró cuenta bancaria")));
        });
    }
}