package everis.bootcamp.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import everis.bootcamp.atm.dto.AccountDTO;
import everis.bootcamp.atm.services.AtmService;
import reactor.core.publisher.Mono;

@RestController
public class AtmController {
    @Autowired
    private AtmService atmService;

    //deposit
    @GetMapping(value = "/atm/deposit/{bankAccountId}/{amount}")
    public Mono<AccountDTO> depositATM(@PathVariable(name = "bankAccountId") String bankAccountId, @PathVariable(name = "amount") Double amount) {
        return atmService.depositATM(bankAccountId, amount);
    }

    //withdraw
}