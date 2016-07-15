package ufam.edu.com.br.apollo.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import ufam.edu.com.br.apollo.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FileChooserExampleActivity";

    private static final int REQUEST_CODE = 6384; // onActivityResult request

    private Button btnChooseFile;
    private Button btnShareFile;
    private TextView tvFileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChooseFile = (Button) findViewById(R.id.choose_file);
        btnShareFile = (Button) findViewById(R.id.share_file);

        tvFileName = (TextView) findViewById(R.id.file_name);

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                showChooser();
            }
        });

    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, getString(R.string.chooser_title));
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i("INFO", "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI

                            final String path = FileUtils.getPath(this, uri);
                            if (!FileUtils.getExtension(path).equals(".mp3")){
                                Toast.makeText(MainActivity.this,
                                    "You must choose a mp3 file, bitch.", Toast.LENGTH_LONG).show();
                            }else {
                                btnShareFile.setVisibility(View.VISIBLE);
                                tvFileName.setText(FileUtils.getFileName(path));
                            }
                        } catch (Exception e) {
                            Log.e("ERROR", "File select error");
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
