package com.smallworld;

import com.smallworld.data.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadJsonFile {

    public static List<Transaction> getDataFromFile(){

        JSONParser jsonParser = new JSONParser();
        List<Transaction> transactionList;

        try{

            FileReader fileReader = new FileReader("transactions.json");

            Object obj = jsonParser.parse(fileReader);

            JSONArray jsonArray = (JSONArray) obj;

            transactionList = jsonArray.stream().map(transaction -> getTransactionObject((JSONObject) transaction)).toList();


        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return transactionList;
    }

    public static Transaction  getTransactionObject(JSONObject jsonObject){

       // org.json.JSONObject jsonObject = new org.json.JSONObject(json.toJSONString());

        Transaction transaction = new Transaction();
        transaction.setMtn((Long) jsonObject.get("mtn"));
        transaction.setAmount((Double) jsonObject.get("amount"));
        transaction.setSenderFullName((String) jsonObject.get("senderFullName"));
        transaction.setSenderAge((Long) jsonObject.get("senderAge"));
        transaction.setBeneficiaryFullName((String) jsonObject.get("beneficiaryFullName"));
        transaction.setBeneficiaryAge((Long) jsonObject.get("beneficiaryAge"));
        transaction.setIssueID((Long) jsonObject.get("issueId"));
        transaction.setIssueSolved((Boolean) jsonObject.get("issueSolved"));
        transaction.setIssueMessage((String) jsonObject.get("issueMessage"));

        return transaction;
    }

}
