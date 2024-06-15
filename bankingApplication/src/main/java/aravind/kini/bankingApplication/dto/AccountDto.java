package aravind.kini.bankingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.AccessType;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
