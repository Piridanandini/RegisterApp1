package com.example.welcome.registerapp;

/**
 * Created by welcome on 9/16/2019.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class FirstSerFragment extends Fragment {

    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;

    public FirstSerFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_ser, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        view.findViewById(R.id.btnChoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            999);
                }
                else {
                    startGallery();
                }
            }
        });
        return view;
    }

            private void startGallery() {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                cameraIntent.setType("image/*");
                if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, 1000);
                }
            }


            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

                if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
                    Uri uri = data.getData();

                    try {
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        imageView.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                super.onActivityResult(requestCode, resultCode, data);
            }



    }

