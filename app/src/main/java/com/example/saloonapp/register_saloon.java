package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class register_saloon extends AppCompatActivity {

    EditText saloon_id,saloon_password,saloon_name,saloon_number,saloon_address,saloon_city;
    EditText Text,Text2,Text3,Text4,Text5,Text6,Text7,Text8,Text9,Text10;
    Button button,image_button;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox,checkBox7,checkBox8,checkBox9;
    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference Folder,Folder1;
    private Bitmap link1;
    private ProgressBar progressBar;
    private String s1="a",link;

    FirebaseAuth fAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("saloon");
    private ArrayList<String> check;

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            mImageUri = data.getData();
            Folder1=Folder.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            Folder1.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 600);

                          /*  try {
                                link1 = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), mImageUri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                               link= Folder1.getPath().toString();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_saloon);
        setTitle("Saloon Registration");

        Folder= FirebaseStorage.getInstance().getReference().child("photo");

        saloon_id=findViewById(R.id.saloon_id);
        progressBar=findViewById(R.id.progressBar);
        saloon_password=findViewById(R.id.saloon_password);
        saloon_name=findViewById(R.id.saloon_name);
        saloon_number=findViewById(R.id.phone_number);
        saloon_address=findViewById(R.id.saloon_address);
        saloon_city=findViewById(R.id.saloon_city);
        Text=findViewById(R.id.editTextNumber);
        Text2=findViewById(R.id.editTextNumber2);
        Text3=findViewById(R.id.editTextNumber3);
        Text4=findViewById(R.id.editTextNumber4);
        Text5=findViewById(R.id.editTextNumber5);
        Text6=findViewById(R.id.editTextNumber6);
        Text7=findViewById(R.id.editTextNumber7);
        Text8=findViewById(R.id.editTextNumber8);
        Text9=findViewById(R.id.editTextNumber9);
        Text10=findViewById(R.id.editTextNumber10);
        button=findViewById(R.id.button);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);
        checkBox7=findViewById(R.id.checkBox7);
        checkBox8=findViewById(R.id.checkBox8);
        checkBox9=findViewById(R.id.checkBox9);
        checkBox=findViewById(R.id.checkBox);
        image_button=findViewById(R.id.image_button);

        fAuth=FirebaseAuth.getInstance();
        check=new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = saloon_id.getText().toString().trim();
                final String password = saloon_password.getText().toString().trim();
                    if(TextUtils.isEmpty(email))
                    {
                        saloon_id.setError("id is required");
                    }
                    else if(TextUtils.isEmpty(password))
                    {
                        saloon_password.setError("password is required");
                    }
                    else if(TextUtils.isEmpty(saloon_city.getText().toString().trim()))
                    {
                        saloon_city.setError("city is required");
                    }
                    else if(TextUtils.isEmpty(saloon_number.getText().toString().trim()))
                    {
                        saloon_number.setError("mobile number is required");
                    }
                    else if(TextUtils.isEmpty(saloon_name.getText().toString().trim()))
                    {
                        saloon_name.setError("name is required");
                    }
                    else if(TextUtils.isEmpty(saloon_address.getText().toString().trim()))
                    {
                        saloon_address.setError("address is required");
                    }
                    else if(checkBox1.isChecked() && TextUtils.isEmpty(Text2.getText().toString().trim()))
                    {
                         Text2.setError("price is required");
                    }
                    else if(checkBox2.isChecked() && TextUtils.isEmpty(Text3.getText().toString().trim()))
                    {
                            Text3.setError("price is required");
                    }
                    else if(checkBox3.isChecked() && TextUtils.isEmpty(Text4.getText().toString().trim()))
                    {
                            Text4.setError("price is required");
                    }
                    else if(checkBox4.isChecked() && TextUtils.isEmpty(Text5.getText().toString().trim()))
                    {
                            Text5.setError("price is required");
                    }
                    else if(checkBox5.isChecked() && TextUtils.isEmpty(Text6.getText().toString().trim()))
                    {
                            Text6.setError("price is required");
                    }
                    else if(checkBox6.isChecked() && TextUtils.isEmpty(Text7.getText().toString().trim()))
                    {
                            Text7.setError("price is required");
                    }
                    else if(checkBox7.isChecked() && TextUtils.isEmpty(Text8.getText().toString().trim()))
                    {
                        Text8.setError("price is required");
                    }
                    else if(checkBox8.isChecked() && TextUtils.isEmpty(Text9.getText().toString().trim()))
                    {
                        Text9.setError("price is required");
                    }
                    else if(checkBox9.isChecked() && TextUtils.isEmpty(Text10.getText().toString().trim()))
                    {
                        Text10.setError("price is required");
                    }
                    else if(checkBox.isChecked() && TextUtils.isEmpty(Text.getText().toString().trim()))
                    {
                        Text.setError("price is required");
                    }
                    else if(TextUtils.isEmpty(link))
                    {
                        Toast.makeText(register_saloon.this, "upload logo!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        fAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                            Map<String, Object> saloon = new HashMap<>();
                                            saloon.put("id", email);
                                            saloon.put("password", password);
                                            saloon.put("name", saloon_name.getText().toString().trim());
                                            saloon.put("number", saloon_number.getText().toString().trim());
                                            saloon.put("address", saloon_address.getText().toString().trim());
                                            saloon.put("city",saloon_city.getText().toString().trim().toLowerCase());
                                            saloon.put("verified",false);
                                            saloon.put("link",link);
                                            saloon.put("total_client","0");
                                            saloon.put("total_vote","0");
                                            saloon.put("rating","1");
                                            Map<String, String> m1=new HashMap<>();
                                            Map<String, String> g1=new HashMap<>();

                                            if(checkBox1.isChecked())
                                            {
                                                m1.put("Hair Cutting",Text2.getText().toString());
                                               // Toast.makeText(register_saloon.this, "s=="+Text2.getText().toString(), Toast.LENGTH_SHORT).show();
                                            }

                                            String sm,sw;
                                            sm="a";
                                            if(checkBox2.isChecked()) { m1.put("Hair Colour",Text3.getText().toString());sm="b";}
                                            if(checkBox3.isChecked()) { m1.put("Hair Styling",Text4.getText().toString());sm="b";}
                                            if(checkBox4.isChecked()) { m1.put("Facial and Skin Care",Text5.getText().toString());sm="b";}
                                            if(checkBox5.isChecked()) { m1.put("Massages",Text6.getText().toString());sm="b";}

                                            sw="a";
                                            if(checkBox.isChecked())  { g1.put("Eye brow",Text.getText().toString());sw="b";}
                                            if(checkBox6.isChecked()) { g1.put("Nail Service",Text7.getText().toString());sw="b";}
                                            if(checkBox7.isChecked()) { g1.put("Facial",Text8.getText().toString());sw="b";}
                                            if(checkBox8.isChecked()) { g1.put("Custom Makeup",Text9.getText().toString());sw="b";}
                                            if(checkBox9.isChecked()) { g1.put("Hair Cutting",Text10.getText().toString());sw="b";}
                                            saloon.put("male",m1);
                                            saloon.put("female",g1);
                                            if(sm.equals("a"))
                                            {
                                                saloon.put("m","no");
                                            }
                                            else
                                            {
                                                saloon.put("m","yes");
                                            }

                                            if(sw.equals("a"))
                                            {
                                                saloon.put("w","no");
                                            }
                                            else
                                            {
                                                saloon.put("w","yes");
                                            }

                                            cf.add(saloon);
                                            SharedPreferences sh = getSharedPreferences("sp1", MODE_PRIVATE);
                                            SharedPreferences.Editor myEdit = sh.edit();
                                            myEdit.remove("saloon_ref");
                                            myEdit.remove("email");
                                            myEdit.remove("password");
                                            myEdit.remove("name");
                                            myEdit.remove("link");
                                            myEdit.commit();
                                            startActivity(new Intent(getApplicationContext(),login_saloon.class));
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Registration failed!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }


            }
        });
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });





    }
}