package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.IncomeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IncomeLab {

        private static IncomeLab sIncomeLab;
        private Context mContext;
        private SQLiteDatabase mDatabase;

        public void deleteIncome(Crime c){
            mDatabase.delete(IncomeDbSchema.IncomeTable.NAME, IncomeDbSchema.IncomeTable.Cols.UUID + " = ?",
                    new String[] { c.getId().toString() });
        }

        public void addIncome(Income c) {
            ContentValues values = getContentValues(c);
            mDatabase.insert(IncomeDbSchema.IncomeTable.NAME, null, values);
        }

        public static IncomeLab get(Context context) {
            if (sIncomeLab == null) {
                sIncomeLab = new IncomeLab(context);
            }

            return sIncomeLab;
        }

        private IncomeLab(Context context) {
            mContext = context.getApplicationContext();
            mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
        }

        public List<Income> getIncome() {
            List<Income> income = new ArrayList<>();
            CrimeCursorWrapper cursor = queryIncome(null, null);

            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    //income.add(cursor.getIncome); //Need to make IncomeCursorWrapper to work
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
            return income;
        }

        public Crime getIncome(UUID id) {
            CrimeCursorWrapper cursor = queryIncome (
                    IncomeDbSchema.IncomeTable.Cols.UUID + " = ?",
                    new String[] { id.toString() }
            );

            try {
                if (cursor.getCount() == 0) {
                    return null;
                }
                cursor.moveToFirst();
                return cursor.getCrime();
            } finally {
                cursor.close();
            }
        }

        public void updateIncome(Income income) {
            String uuidString = income.getId().toString();
            ContentValues values = getContentValues(income);

            mDatabase.update(IncomeDbSchema.IncomeTable.NAME, values,
                    IncomeDbSchema.IncomeTable.Cols.UUID + " = ?",
                    new String[] {uuidString});
        }

        private CrimeCursorWrapper queryIncome(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(
                    IncomeDbSchema.IncomeTable.NAME,
                    null, //columns - null selects all columns
                    whereClause,
                    whereArgs,
                    null, //groupBy
                    null,   //having
                    null    //orderBy
            );
            return new CrimeCursorWrapper(cursor);
        }

        private static ContentValues getContentValues(Income income) {
            ContentValues values = new ContentValues();
            values.put(IncomeDbSchema.IncomeTable.Cols.UUID, income.getId().toString());
            values.put(IncomeDbSchema.IncomeTable.Cols.TITLE, income.getIncomeName());

            return values;
        }
    }


