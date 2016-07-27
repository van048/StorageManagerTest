package cn.ben.storagemanagertest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

import cn.ben.storagemanagertest.R;
import cn.ben.storagemanagertest.manager.ObbExpansionsManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressWarnings("UnusedAssignment") ObbExpansionsManager obbExpansionsManager = ObbExpansionsManager.createNewInstance(this, new ObbExpansionsManager.ObbListener() {
            @Override
            public void onFilesNotFound() {
                Log.d(TAG, "onFilesNotFound");
            }

            @Override
            public void onMountSuccess() {
                Log.d(TAG, "onMountSuccess");
                printLog();
            }
        });

    }

    private void printLog() {
        ObbExpansionsManager obbExpansionsManager = ObbExpansionsManager.getInstance();
        String patchRoot = obbExpansionsManager.getPatchRoot();
        String mainRoot = obbExpansionsManager.getMainRoot();
        File file = obbExpansionsManager.getFile("a.pdf");
        File mainFile = obbExpansionsManager.getFileFromMain("a.pdf");
        File patchFile = obbExpansionsManager.getFileFromPatch("a.pdf");
        Log.d(TAG, "patchRoot: " + patchRoot);
        Log.d(TAG, "mainRoot: " + mainRoot);
        Log.d(TAG, "file: " + (file == null ? "null" : file.getAbsolutePath()));
        Log.d(TAG, "mainFile: " + (mainFile == null ? "null" : mainFile.getAbsolutePath()));
        Log.d(TAG, "patchFile: " + (patchFile == null ? "null" : patchFile.getAbsolutePath()));

    }
}
