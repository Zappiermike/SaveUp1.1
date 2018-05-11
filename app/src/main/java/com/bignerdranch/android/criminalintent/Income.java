package com.bignerdranch.android.criminalintent;

import java.util.UUID;

public class Income {
    private UUID mId;
    private String mIncomeName;
    private String mIncomeAmount;

    public Income(){
        mId = UUID.randomUUID();

    }

    public Income(UUID id){
        mId = id;
    }

    public UUID getId(){
        return mId;
    }
    public String getIncomeName(){
        return mIncomeName;
    }
    public String getIncomeAmount(){
        return mIncomeAmount;
    }
    public void setIncomeName(String title){
        mIncomeName= title;
    }
    public void setIncomeAmount(String amount){
        mIncomeAmount=amount;
    }
}
