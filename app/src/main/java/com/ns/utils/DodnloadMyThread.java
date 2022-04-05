package com.ns.utils;

import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.IOException;

import static com.ns.utils.DownloadPdfMethod.downloadFile;

public class DodnloadMyThread  implements Runnable {

    String mUrl;
    String mFileName;
    int mListCount;
    Handler mHandler;

    public DodnloadMyThread(Handler mHandler_, String url_, String fileName_, int listCount_) {
        this.mUrl = url_;
        this.mFileName = fileName_;
        this.mListCount = listCount_;
        this.mHandler= mHandler_;
    }

    @Override
    public void run() {


        String extStorageDirectory = Environment.getExternalStorageDirectory().toString() + "/" + UiConstants.FOLDER_NAME;
        File folder = new File(extStorageDirectory);
        folder.mkdir();

        File pdfFile = new File(folder, mFileName);

        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(500);
            downloadFile(mHandler ,mUrl, pdfFile, mListCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}