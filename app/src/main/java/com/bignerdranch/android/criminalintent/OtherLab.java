package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.OtherDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OtherLab {
    private static OtherLab sOtherLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public void deleteOther(Other c){
        mDatabase.delete(OtherDbSchema.OtherTable.NAME, OtherDbSchema.OtherTable.Cols.UUID + " = ?", new String[] { c.getId().toString() });
    }

    public void addOther(Other c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(OtherDbSchema.OtherTable.NAME, null, values);
    }

    public static OtherLab get(Context context) {
        if (sOtherLab == null) {
            sOtherLab = new OtherLab(context);
        }

        return sOtherLab;
    }

    private OtherLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getOther(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes (
                OtherDbSchema.OtherTable.Cols.UUID + " = ?",
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

    public void updateCrime(Other other) {
        String uuidString = other.getId().toString();
        ContentValues values = getContentValues(other);

        mDatabase.update(OtherDbSchema.OtherTable.NAME, values,
                OtherDbSchema.OtherTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                OtherDbSchema.OtherTable.NAME,
                null, //colums - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null,   //having
                null    //orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Other other) {
        ContentValues values = new ContentValues();
        values.put(OtherDbSchema.OtherTable.Cols.UUID, other.getId().toString());
        values.put(OtherDbSchema.OtherTable.Cols.TITLE, other.getTitle());
        values.put(OtherDbSchema.OtherTable.Cols.DATE, other.getDate().getTime());
        return values;
    }
}
