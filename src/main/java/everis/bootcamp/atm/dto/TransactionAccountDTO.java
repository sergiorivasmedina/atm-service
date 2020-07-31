package everis.bootcamp.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAccountDTO {
    private String idAccountTransaction;
    private Double amount;
    private String transactionType;

    public TransactionAccountDTO(Double amount, String transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }
}