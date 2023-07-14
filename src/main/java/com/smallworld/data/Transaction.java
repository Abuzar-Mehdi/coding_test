package com.smallworld.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    // Represent your transaction data here.

    private Long mtn;
    private Double amount;
    private String senderFullName;
    private Long senderAge;
    private String beneficiaryFullName;
    private Long beneficiaryAge;
    private Long issueID;
    private Boolean issueSolved;
    private String issueMessage;

}
