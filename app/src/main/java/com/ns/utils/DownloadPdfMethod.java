package com.ns.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPdfMethod {

    private static final String TAG = "DownloadPdfMethod";


    public static void downloadFile(Handler mHandler, String url, File outputFile, int mListCount) {
        int count;
        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];


            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));

            long total = 0;

            while ((count = stream.read(buffer)) != -1) {
                total += count;
                Message message = new Message();
                message.arg1 = (int) ((total * 100) / contentLength);
                message.arg2 = mListCount;
                mHandler.sendMessage(message);

                // writing data to file
                fos.write(buffer, 0, count);
            }

            stream.readFully(buffer);
            stream.close();

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "downloadFile: " + e.getMessage());
            return; // swallow a 404
        } catch (IOException e) {
            Log.e(TAG, "downloadFile: " + e.getMessage());
            return; // swallow a 404
        }
    }
    
    
}
