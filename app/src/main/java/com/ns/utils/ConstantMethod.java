package com.ns.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.activity.PurchaseActivity;
import com.ns.database.SharedPref;
import com.ns.fragment.MyProgressDialogFragment;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ConstantMethod {

    public static void RunLayoutAnimationRecyclerview(RecyclerView mRecyclerView) {
        final Context context = mRecyclerView.getContext();

        int animRes = R.anim.layout_animation_from_right;

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, animRes);

        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }


    public static void ShowProgressBar(FragmentActivity activity) {

        MyProgressDialogFragment progressDialogFragment = (MyProgressDialogFragment) activity.getSupportFragmentManager().findFragmentByTag(MyProgressDialogFragment.class.getName());

        if (progressDialogFragment == null) {
            progressDialogFragment = new MyProgressDialogFragment();
        }

        if (!progressDialogFragment.isVisible()) {
            progressDialogFragment.show(activity.getSupportFragmentManager(), MyProgressDialogFragment.class.getName());
        }

    }

    public static void HideProgressBar(FragmentActivity activity) {
        MyProgressDialogFragment progress = (MyProgressDialogFragment) activity.getSupportFragmentManager().findFragmentByTag(MyProgressDialogFragment.class.getName());

        if (progress != null && (progress.isVisible()) && (progress.isAdded())) {
            progress.dismiss();
        }
    }


    public static void ShowToastMessage(Context context, String msg){
        Toast toast= Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }



    public static void DialogShow(Context context, String message){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.my_alert_dialog);

        ((TextView) dialog.findViewById(R.id.myalert_title)).setText(message);
        Button dialogButton = (Button) dialog.findViewById(R.id.myalert_cancle);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public static void DialogShowFragmentBackPress(Activity activity, String message){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.my_alert_dialog);

        ((TextView) dialog.findViewById(R.id.myalert_title)).setText(message);
        Button dialogButton = (Button) dialog.findViewById(R.id.myalert_cancle);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.onBackPressed();

            }
        });

        dialog.show();
    }


    public interface DialogAction{
        void onAction(boolean status);
    }
    public static void DialogShowDoubleAction(DialogAction dialogAction, Context context, String message, String oneBtn, String twoBtn){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.my_alert_dialog_two_button);

        ((TextView) dialog.findViewById(R.id.myalert_title)).setText(message);
        Button dialogBtn_Yes = (Button) dialog.findViewById(R.id.myalert_twobtn_yes);
        dialogBtn_Yes.setText(oneBtn);
        Button dialogBtn_No = (Button) dialog.findViewById(R.id.myalert_twobtn_no);
        dialogBtn_No.setText(twoBtn);


        // if button is clicked, yes the custom dialog
        dialogBtn_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAction.onAction(true);
                dialog.dismiss();
            }
        });

        dialogBtn_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAction.onAction(false);
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public interface DialogSingleAction{
        void onAction(boolean status);
    }
    /**
     *  Single button click and callback action
     * @param dialogAction
     * @param context
     * @param message
     */
    public static void DialogShowSingleAction(DialogSingleAction dialogAction, Context context, String message){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.my_alert_dialog);

        ((TextView) dialog.findViewById(R.id.myalert_title)).setText(message);
        Button dialogBtn_Yes = (Button) dialog.findViewById(R.id.myalert_cancle);


        // if button is clicked, yes the custom dialog
        dialogBtn_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAction.onAction(true);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public static void CustomerSupportPhoneCall(Context mContext, String phoneNumber) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mContext.startActivity(callIntent);
    }


    public static ArrayList<Bitmap> pdfToBitmap(File pdfFile, Context context) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        try {
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));

            Bitmap bitmap;
            final int pageCount = renderer.getPageCount();
            for (int i = 0; i < pageCount; i++) {
                PdfRenderer.Page page = renderer.openPage(i);

                int width = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
                int height = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getHeight();
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                bitmaps.add(bitmap);

                // close the page
                page.close();

            }

            // close the renderer
            renderer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bitmaps;
    }


    public static List<String> AllDayList() {

        List<String> dayList = new ArrayList<>();
        dayList.add("Saturday");
        dayList.add("Friday");
        dayList.add("Thursday");
        dayList.add("Wednesday");
        dayList.add("Tuesday");
        dayList.add("Monday");
        dayList.add("Sunday");

        return dayList;
    }

    public static List<String> DayHeadingList() {

        List<String> dayList = new ArrayList<>();
        dayList.add("");
        dayList.add("Restrict Non- Vegetarian for these days");
        dayList.add("Restrict Continental for these days");

        return dayList;
    }





    public static String  getFolderPath() {
        String path = Environment.getExternalStorageDirectory().toString() + "/" + UiConstants.FOLDER_NAME;
        System.out.println("Path  : " +path );
        File FPath = new File(path);
        if (!FPath.exists()) {
            if (!FPath.mkdir()) {
                System.out.println("***Problem creating Image folder " +path );
            }
        }

        return path;
    }

    public static boolean checks(String ticket_name){

        String path  =getFolderPath();
        boolean check = false;
        File directory = new File(path);


        File[] files = directory.listFiles();
        if (files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals(ticket_name)) {
                    check = true;
                }
                Log.d("Files", "FileName:" + files[i].getName());
            }
        }

        return check;
    }

}
