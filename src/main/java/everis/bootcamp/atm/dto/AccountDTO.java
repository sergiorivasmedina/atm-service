package everis.bootcamp.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String idBankAccount;
    private Double availableBalance;
    private String accountType;
    private String currency;
    private int numberOfAtmTransactions;
    private double commission;

    public AccountDTO(String idBankAccount) {
        this.idBankAccount = idBankAccount;
        this.availableBalance = null;
        this.accountType = this.currency = null;
    }
}