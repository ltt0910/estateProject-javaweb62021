package com.laptrinhjavaweb.enums;

public enum TransactionEnum {
    Dan_Di_Xem("Dẫn đi xem"),
    Cham_Soc_Khach_Hang("Chăm Sóc Khách Hàng");


    private final String transactionType;
    TransactionEnum(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getTransactionType() {
        return transactionType;
    }

}

