package com.example.welcome.registerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.welcome.registerapp.database.installation;
import com.example.welcome.registerapp.database.requirements;
import com.example.welcome.registerapp.database.service;
import com.example.welcome.registerapp.installation.installationListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class pdf_activity extends AppCompatActivity {

    private static final String TAG = "PdfCreatorActivity";
    private EditText mContentEditText;
    private Button mCreateButton,button_req,button_ser;
    private File pdfFile;
    AlertDialog.Builder builder;
    AlertDialog progressDialog;
    DatabaseReference installation_db,installation_db2,installation_db3;
    ArrayList<installation> arrayList_installation = new ArrayList<>();
    ArrayList<requirements> arrayList_requirements = new ArrayList<>();
    ArrayList<service> arrayList_services = new ArrayList<>();
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_activity);

        progressDialog = getDialogProgressBar().create();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        installation_db = FirebaseDatabase.getInstance().getReference("installation");
        progressDialog.show();
        installation_db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                Toast.makeText(pdf_activity.this, "ondatachange", Toast.LENGTH_SHORT).show();
                arrayList_installation.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    installation install = postSnapshot.getValue(installation.class);
                    arrayList_installation.add(install);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(pdf_activity.this, "db error", Toast.LENGTH_SHORT).show();
            }
        });

        installation_db2 = FirebaseDatabase.getInstance().getReference("requirements");
        installation_db2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                arrayList_requirements.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    requirements install = postSnapshot.getValue(requirements.class);
                    arrayList_requirements.add(install);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(pdf_activity.this, "db error", Toast.LENGTH_SHORT).show();
            }
        });

        installation_db3 = FirebaseDatabase.getInstance().getReference("services");
        installation_db3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                arrayList_services.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    service install = postSnapshot.getValue(service.class);
                    arrayList_services.add(install);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(pdf_activity.this, "db error", Toast.LENGTH_SHORT).show();

            }
        });

        progressDialog.dismiss();



        mCreateButton = (Button) findViewById(R.id.button_create);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "installation";

                try {
                    createPdfWrapper(name);
                } catch (FileNotFoundException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

        button_req = findViewById(R.id.button_create2);
        button_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "requirements";

                try {
                    createPdfWrapper(name);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }
        });

        button_ser = findViewById(R.id.button_create3);
        button_ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "services";

                try {
                    createPdfWrapper(name);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    private void createPdfWrapper(String name) throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            if(name.equals("installation"))
            createPdf_installations();
            else if (name.equals("requirements"))
                createPdf_requirements();
            else
                createPdf_services();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper("installation");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf_installations() throws FileNotFoundException, DocumentException {



        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String uniqueString = UUID.randomUUID().toString();
        pdfFile = new File(docsFolder.getAbsolutePath(),uniqueString+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        
        PdfPTable table = new PdfPTable(13);
        Font fontH1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
        Font fontP1 = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL);
        table.addCell(new PdfPCell(new Phrase("s.No",fontH1)));
        table.addCell(new PdfPCell(new Phrase("vehicle type",fontH1)));
        table.addCell(new PdfPCell(new Phrase("vehicle no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("device name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("device imei no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim imei no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("location",fontH1)));
        table.addCell(new PdfPCell(new Phrase("service time",fontH1)));
        table.addCell(new PdfPCell(new Phrase("service engineer name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("site incharge name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("authorised person",fontH1)));

        for (int i=0;i<arrayList_installation.size();i++)
        {
            installation install = arrayList_installation.get(i);
            table.addCell(new PdfPCell(new Phrase(""+i,fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getVehicle_type(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getVehicle_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getDevice_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getDevice_imei_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_imei_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getLocation(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getService_time(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getService_engineer_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSite_incharge_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getAuthorised_person(),fontP1)));
        }
        document.add(table);
        document.close();
        previewPdf();

    }
    private void createPdf_requirements() throws FileNotFoundException, DocumentException {




        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String uniqueString = UUID.randomUUID().toString();
        pdfFile = new File(docsFolder.getAbsolutePath(),uniqueString+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

        //String installationId, String noofDevice, String typeofDevice, String region, String deviceName, String siteName, String email, String mobileNo, String address, String pincode) {


        PdfPTable table = new PdfPTable(10);
        Font fontH1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
        Font fontP1 = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL);
        table.addCell(new PdfPCell(new Phrase("s.No",fontH1)));
        table.addCell(new PdfPCell(new Phrase("noofDevice",fontH1)));
        table.addCell(new PdfPCell(new Phrase("typeofDevice",fontH1)));
        table.addCell(new PdfPCell(new Phrase("region",fontH1)));
        table.addCell(new PdfPCell(new Phrase("deviceName",fontH1)));
        table.addCell(new PdfPCell(new Phrase("siteName",fontH1)));
        table.addCell(new PdfPCell(new Phrase("email",fontH1)));
        table.addCell(new PdfPCell(new Phrase("mobileNo",fontH1)));
        table.addCell(new PdfPCell(new Phrase("address",fontH1)));
        table.addCell(new PdfPCell(new Phrase("pincode",fontH1)));


        for (int i=0;i<arrayList_requirements.size();i++)
        {
            requirements install = arrayList_requirements.get(i);
            Toast.makeText(this, "working"+arrayList_requirements.size(), Toast.LENGTH_SHORT).show();
            table.addCell(new PdfPCell(new Phrase(""+i,fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getNoofdevice(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getTypeofdevice(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getRegion(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getDevicename(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSitename(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getEmail(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getMobileno(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getAddress(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getPincode(),fontP1)));

        }

        document.add(table);
        document.close();
        previewPdf();

    }

    private void createPdf_services() throws FileNotFoundException, DocumentException {





        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String uniqueString = UUID.randomUUID().toString();
        pdfFile = new File(docsFolder.getAbsolutePath(),uniqueString+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

        //String installationId, String service_date, String vehicle_no, String device_name, String device_imei_no, String sim_name, String sim_imei_no, String sim_no, String asset_code, String location, String service_time, String service_engineer_name, String site_incharge_name, String authorised_person) {

        PdfPTable table = new PdfPTable(14);
        Font fontH1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
        Font fontP1 = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL);
        table.addCell(new PdfPCell(new Phrase("s.No",fontH1)));
        table.addCell(new PdfPCell(new Phrase("service_date",fontH1)));
        table.addCell(new PdfPCell(new Phrase("vehicle no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("device name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("device imei no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim imei no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("sim no",fontH1)));
        table.addCell(new PdfPCell(new Phrase("asset code",fontH1)));
        table.addCell(new PdfPCell(new Phrase("location",fontH1)));
        table.addCell(new PdfPCell(new Phrase("service time",fontH1)));
        table.addCell(new PdfPCell(new Phrase("service engineer name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("site incharge name",fontH1)));
        table.addCell(new PdfPCell(new Phrase("authorised person",fontH1)));

        for (int i=0;i<arrayList_services.size();i++)
        {
            service install = arrayList_services.get(i);
            Toast.makeText(this, "working"+arrayList_installation.size(), Toast.LENGTH_SHORT).show();
            table.addCell(new PdfPCell(new Phrase(""+i,fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getService_date(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getVehicle_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getDevice_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getDevice_imei_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_imei_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSim_no(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getAsset_code(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getLocation(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getService_time(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getService_engineer_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getSite_incharge_name(),fontP1)));
            table.addCell(new PdfPCell(new Phrase(install.getAuthorised_person(),fontP1)));
            Toast.makeText(this, ""+arrayList_services.size(), Toast.LENGTH_SHORT).show();
        }

        document.add(table);
        document.close();
        previewPdf();

    }


    private void previewPdf() {
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);


        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");
            startActivity(Intent.createChooser(intent, "Open folder"));
        }

         else {
            Toast.makeText(this, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }




    }

    public AlertDialog.Builder getDialogProgressBar() {

        if (builder == null) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle("Loading...");

            final ProgressBar progressBar = new ProgressBar(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(lp);
            builder.setView(progressBar);
        }
        return builder;
    }

}




