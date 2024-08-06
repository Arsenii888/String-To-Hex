package com.u063.nativeboot;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.u063.nativeboot.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'nativeboot' library on application startup.
    static {
        System.loadLibrary("nativeboot");
    }
    String FileName="dat";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("hi",Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/"+FileName);
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        //read(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/"+FileName);
    }
    public void read(View w) {
        EditText etx=(EditText) findViewById(R.id.editTextText);
        File f=new File(etx.getText().toString());
        if(!f.isDirectory()){
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    List<String> strings = Files.readAllLines(Paths.get(f.getPath()));
                    if(!strings.isEmpty()) {
                        for (String str : strings) {
                            for (int i = 0; i < str.length(); i++) {
                                TextView tx = new TextView(MainActivity.this);
                                tx.setText("0x"+getInt(str.charAt(i)));
                                tx.setTextSize(20);
                                LinearLayout ll = findViewById(R.id.hex);
                                ll.addView(tx);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * A native method that is implemented by the 'nativeboot' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String getInt(char data);
}