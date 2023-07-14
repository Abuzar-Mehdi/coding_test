package com.smallworld;

import org.junit.Assert;
import org.junit.internal.matchers.IsCollectionContaining;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Test {

    TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();


    @org.junit.Test
    public void getTotalTransactionAmount() {

        Assert.assertEquals(4371.37, transactionDataFetcher.getTotalTransactionAmount(), 0.0);

    }

    @org.junit.Test
    public void getTotalTransactionAmountSentBy() {

        Assert.assertEquals(828.26,transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"),0.0);
    }

    @org.junit.Test
    public void getMaxTransactionAmount() {

        Assert.assertEquals(985.0,transactionDataFetcher.getMaxTransactionAmount(),0.0);
    }

    @org.junit.Test
    public void countUniqueClients() {

        Assert.assertEquals(14,transactionDataFetcher.countUniqueClients());
    }

    @org.junit.Test
    public void hasOpenComplianceIssues() {

        Assert.assertEquals(true,transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby"));
    }

    @org.junit.Test
    public void getTransactionsByBeneficiaryName() {
    }

    @org.junit.Test
    public void getUnsolvedIssueIds() {

        assertThat(transactionDataFetcher.getUnsolvedIssueIds(),IsCollectionContaining.hasItems(3));
    }

    @org.junit.Test
    public void getAllSolvedIssueMessages() {

        assertThat(transactionDataFetcher.getAllSolvedIssueMessages(), IsCollectionContaining.hasItems("Never gonna let you down"));
    }

    @org.junit.Test
    public void getTop3TransactionsByAmount() {

        assertThat(transactionDataFetcher.getTop3TransactionsByAmount().size(),is(3));
    }

    @org.junit.Test
    public void getTopSender() {

        Optional<String> expected= Optional.of("Grace Burgess");

        Assert.assertEquals(expected,transactionDataFetcher.getTopSender());
    }
}
