package com.ns.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.adapter.adapter_holder.BoardingPassMyHolder;
import com.ns.model.BoardingPass.BoardingPassBeanXX;
import com.ns.model.BoardingPass.BoardingPassResponse;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BoardingPassAdapter extends RecyclerView.Adapter<BoardingPassMyHolder> {

    private static final String TAG = "BoardingPassAdapter";

    private ArrayList<DownloadPojo> mDownloadList = new ArrayList<>();

    //    int seatCount = 0;
    BoardingRowClick mBoardingRowClick;

    public BoardingRowClick setSelectRowClick(BoardingRowClick boardingRowClick) {
        return mBoardingRowClick = boardingRowClick;
    }

    public interface BoardingRowClick {
        void selectRowData(String fromToName, long datetime, String flightNo, int setCount, String seatName, String geatName, ArrayList<DownloadPojo> downloadPojoArrayList);
        void onRowData(BoardingPassBeanXX boardingPassBeanXX);
    }

    private List<BoardingPassBeanXX> mBoardingPassList;

    public BoardingPassAdapter(List<BoardingPassBeanXX> boarding_pass) {
        this.mBoardingPassList = boarding_pass;
    }

    @Override
    public BoardingPassMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_boarding_pass_layout, parent, false);
        return new BoardingPassMyHolder(view);
    }

    @Override
    public void onBindViewHolder(BoardingPassMyHolder holder, int position) {

        int seatCount =0;
        BoardingPassBeanXX boardingPass = mBoardingPassList.get(position);

        String fromName = boardingPass.getFrom_city_info().getName();
        String toName = boardingPass.getTo_city_info().getName();
        String fromToto = fromName + " to " + toName;
        long rowDateTime = boardingPass.getJourney_datetime();
        String flightNo = boardingPass.getFlight();

        holder.fromCitiName.setText(fromName);
        holder.fromAirpoartName.setText(boardingPass.getFrom_city_info().getAirport());

        holder.toCitiName.setText(toName);
        holder.toAirpoartName.setText(boardingPass.getTo_city_info().getAirport());

        holder.boardingPassDate.setText(ConstantDateFormate.getFormattedCompeltedDate(rowDateTime));
        holder.departurePassDate.setText(ConstantDateFormate.getFormattedhours(rowDateTime) + " hrs");


        if (boardingPass.getTravelling_self() == 1) {
            if (boardingPass.getPrefs().getMain_passenger().getStatus().equals("Confirmed")) {
                seatCount++;
            }
        }

        // Particular Co-Passenger titcket status
        int count = boardingPass.getPrefs().getCo_passengers().size();
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (boardingPass.getPrefs().getCo_passengers().get(i).getStatus().equals("Confirmed")) {
                    seatCount++;
                }
            }
        }


        holder.boardingPassSeats.setText("" + seatCount);
        holder.flightName.setText(flightNo);

        int    seatCounts = seatCount;
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String guestSetName = "";
                String seatCode = "";

                DownloadPojo pojo;
                // Particular Main Passenger titcket status
                if (boardingPass.getTravelling_self() == 1) {
                    if (boardingPass.getPrefs().getMain_passenger().getStatus().equals("Confirmed")) {
                        guestSetName = boardingPass.getPrefs().getMain_passenger().getName();
                        seatCode = boardingPass.getPrefs().getMain_passenger().getSeats_info().getSeat_code();
                        String boarding_pass_url = boardingPass.getPrefs().getMain_passenger().getBoarding_pass_url();

                        pojo = new DownloadPojo(seatCode, guestSetName, boarding_pass_url);
                        mDownloadList.add(pojo);

                        Log.d(TAG, "onClick: ");
                    } else if (boardingPass.getPrefs().getMain_passenger().getStatus().equals("Cancelled")) {
                        Log.d(TAG, "onClick: ");
                    }
                }

                // Particular Co-Passenger titcket status
                int co_passengerInfoList = boardingPass.getPrefs().getCo_passengers().size();

                if (co_passengerInfoList != 0) {
                    for (int i = 0; i < co_passengerInfoList; i++) {
                        if (boardingPass.getPrefs().getCo_passengers().get(i).getStatus().equals("Confirmed")) {

                            if (guestSetName == "") {
                                guestSetName = boardingPass.getPrefs().getCo_passengers().get(i).getName();
                            } else {
                                guestSetName = guestSetName + "," + boardingPass.getPrefs().getCo_passengers().get(i).getName();
                            }

                            if (seatCode == "") {
                                seatCode = boardingPass.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                            } else {
                                seatCode = seatCode + "," + boardingPass.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                            }

                            String boarding_pass_url = boardingPass.getPrefs().getCo_passengers().get(i).getBoarding_pass_url();

                            pojo = new DownloadPojo(boardingPass.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code(), boardingPass.getPrefs().getCo_passengers().get(i).getName(), boarding_pass_url);
                            mDownloadList.add(pojo);

                            Log.d(TAG, "onClick: ");
                        }
                    }
                }

                mBoardingRowClick.selectRowData(fromToto, rowDateTime, flightNo, seatCounts, seatCode, guestSetName, mDownloadList);
                mBoardingRowClick.onRowData(boardingPass);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBoardingPassList.size();
    }


}
