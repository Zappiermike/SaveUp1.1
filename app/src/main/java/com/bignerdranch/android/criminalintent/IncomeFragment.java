package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class IncomeFragment extends Fragment{
    private static final String ARG_INCOME_ID="income_id";

    private Income mIncome;
    private EditText mIncomeTitle;
    private EditText mIncomeAmount;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIncome= new Income();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragment_income, container, false);

        mImageView = (ImageView) v.findViewById(R.id.imageView02);
        Picasso.with(getContext())
                .load("https://cdn4.iconfinder.com/data/icons/economy-color-v-2/512/money_currency_bill_bills_stack_euro-256.png")
                .into(mImageView);


        mIncomeTitle = (EditText) v.findViewById(R.id.EditIncomeName);
        mIncomeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIncome.setIncomeName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIncomeAmount = (EditText) v.findViewById(R.id.editText);
        mIncomeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIncome.setIncomeAmount(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }
}

