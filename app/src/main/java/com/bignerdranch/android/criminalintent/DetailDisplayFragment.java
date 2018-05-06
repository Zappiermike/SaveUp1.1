package com.bignerdranch.android.criminalintent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

public class DetailDisplayFragment extends android.support.v4.app.DialogFragment {

    private ImageView mPhotoView;
    private File mPhotoFile;

    private static final String ARG_PHOTO_FILE = "photoFile";

    public static DetailDisplayFragment newInstance(File photoFile) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_PHOTO_FILE, photoFile);

        DetailDisplayFragment fragment = new DetailDisplayFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mPhotoFile = (File) getArguments().getSerializable(ARG_PHOTO_FILE);
        View v = inflater.inflate(R.layout.detail_display, container, false);
        mPhotoView = (ImageView) v.findViewById(R.id.photo_view_dialog);



        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap =  PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
        return v;
    }
}
