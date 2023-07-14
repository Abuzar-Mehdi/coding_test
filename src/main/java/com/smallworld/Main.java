package com.smallworld;

import com.smallworld.data.Transaction;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ReadJsonFile.getDataFromFile();

        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        System.out.println("transactionDataFetcher "+ transactionDataFetcher.getTransactionsByBeneficiaryName());
    }
}
