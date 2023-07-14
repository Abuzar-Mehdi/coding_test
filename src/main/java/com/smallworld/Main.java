package com.smallworld;

import com.smallworld.data.Transaction;

public class Main {

    public static void main(String[] args) {

        ReadJsonFile.getDataFromFile();

        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

    }
}
