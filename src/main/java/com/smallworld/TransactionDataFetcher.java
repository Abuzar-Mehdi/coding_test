package com.smallworld;

import com.smallworld.data.Transaction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionDataFetcher {

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().mapToDouble(Transaction::getAmount).sum();

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().
                filter(t -> t.getSenderFullName().equalsIgnoreCase(senderFullName))
                        .mapToDouble(Transaction::getAmount).sum();
                //        throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().
                mapToDouble(Transaction::getAmount)
                .max().getAsDouble();

        //throw new UnsupportedOperationException();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        List<String> sender = transactionList.stream().map(Transaction::getSenderFullName).distinct().toList();
        List<String> beneficiary = transactionList.stream().map(Transaction::getBeneficiaryFullName).distinct().toList();

        List<String> merge = new ArrayList<>();
        merge.addAll(sender);
        merge.addAll(beneficiary);

        return merge.stream().distinct().count();

        //throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        List<Transaction> sender = transactionList.stream().filter(t -> t.getSenderFullName().equalsIgnoreCase(clientFullName)).toList();
        List<Transaction> beneficiary = transactionList.stream().filter(t -> t.getBeneficiaryFullName().equalsIgnoreCase(clientFullName)).toList();

        List<Transaction> merge = new ArrayList<>();
        merge.addAll(sender);
        merge.addAll(beneficiary);

        long count = merge.stream().filter(t -> !t.getIssueSolved()).count();


        if (count > 0){
            return true;
        }else{
            return false;
        }

        //throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().collect(Collectors.toMap(Transaction::getBeneficiaryFullName,t -> t));


       // throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().
                filter(t -> !t.getIssueSolved()).
                map(t -> Math.toIntExact(t.getIssueID())).collect(Collectors.toSet());

        //throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().
                filter(t -> t.getIssueSolved())
                .map(Transaction::getIssueMessage).collect(Collectors.toList());

        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

        return transactionList.stream().
                sorted( Comparator.comparing(Transaction::getAmount).reversed())
                .limit(3)
                .toList();


       // throw new UnsupportedOperationException();
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {

        List<Transaction> transactionList=ReadJsonFile.getDataFromFile();

       Map<String, Double> result =transactionList.stream().
                collect(Collectors.groupingBy(Transaction::getSenderFullName,Collectors.summingDouble(Transaction::getAmount)));

       return Optional.ofNullable(result.entrySet().stream()
               .max(Map.Entry.comparingByValue())
               .get().getKey());

        //throw new UnsupportedOperationException();
    }

}
