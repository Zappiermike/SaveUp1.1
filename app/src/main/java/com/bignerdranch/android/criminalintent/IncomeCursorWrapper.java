package com.bignerdranch.android.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.database.IncomeDbSchema;

import java.util.UUID;

public class IncomeCursorWrapper extends CursorWrapper {

    public IncomeCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Income getIncome(){
        String uuid= getString(getColumnIndex(IncomeDbSchema.IncomeTable.Cols.UUID));
        String title = getString(getColumnIndex(IncomeDbSchema.IncomeTable.Cols.TITLE));
        String amount= getString(getColumnIndex(IncomeDbSchema.IncomeTable.Cols.AMOUNT));

        Income income = new Income(UUID.fromString(uuid));
        income.setIncomeName(title);
        income.setIncomeAmount(amount);

        return income;
    }
}
