package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ns.adapter.BoardingPassAdapter;
import com.ns.database.SharedPref;
import com.ns.model.BoardingPass.BoardingPassBeanXX;
import com.ns.model.BoardingPass.BoardingPassResponse;
import com.ns.model.Download.DownloadPojo;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.PaginationScrollListener;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BoardingPassListFragment extends Fragment implements BoardingPassAdapter.BoardingRowClick, BoardingPassUserDetailsFragment.ClickBackButton {
    private static final String TAG = "BoardingPassListFragmen";

    private int offset = 0;
    private int limit = 10;




    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 100;
    private int currentPage = PAGE_START;




    private ProgressBar mProgressBar;

    private APIInterface mApiInterface;
    private RecyclerView mBoardingPassRecyclerview;
    private BoardingPassAdapter mBoardingpaddAdapter;

    List<BoardingPassBeanXX> mBoardingPassBeanLists = new ArrayList<>();

    private boolean mLastFragmentStatus = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.boarding_pass_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


        if (mLastFragmentStatus) {
            APICall(0);
        } else {
            mBoardingpaddAdapter = new BoardingPassAdapter(mBoardingPassBeanLists);
            mBoardingpaddAdapter.setSelectRowClick(this);
            mBoardingPassRecyclerview.setAdapter(mBoardingpaddAdapter);
        }




    }


    private void APICall(int count) {

        mProgressBar.setVisibility(View.VISIBLE);
        String tocken = SharedPref.getSharedPreferences(getContext()).getTocken();
        mApiInterface.getBoardingPassResponse(tocken, count, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BoardingPassResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BoardingPassResponse response) {
                        mProgressBar.setVisibility(View.GONE);
                        if (response.getResultcode() == 1) {
                            Log.d(TAG, "onNext: " + response);
                            ShowBoardingResult(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + e.getMessage());
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void ShowBoardingResult(BoardingPassResponse response) {



        List<BoardingPassBeanXX> mBoardingPassBeanList = response.getData().getBoarding_pass();



        if (mBoardingPassBeanLists.size() == 0) {
            mBoardingPassBeanLists= mBoardingPassBeanList;
        } else {
            mBoardingPassBeanLists.addAll(mBoardingPassBeanList);
        }

        mBoardingpaddAdapter = new BoardingPassAdapter(mBoardingPassBeanLists);
        mBoardingpaddAdapter.setSelectRowClick(this);
        mBoardingPassRecyclerview.setAdapter(mBoardingpaddAdapter);
    }

    private void initView(View view) {

        mBoardingPassRecyclerview = view.findViewById(R.id.recyclerView_boarding_pass);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBoardingPassRecyclerview.setLayoutManager(layoutManager);
        mProgressBar = view.findViewById(R.id.progressBar);



       /* mBoardingPassRecyclerview.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                APICall(currentPage);
                Log.d(TAG, "loadMoreItems: "+currentPage);
               
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });*/


    }

    @Override
    public void selectRowData(String fromToName, long datetime, String flightNo, int setCount, String seatName, String guestName, ArrayList<DownloadPojo> downloadPojoArrayList) {

        Bundle bundle = new Bundle();
        bundle.putString("fromTo", fromToName);
        bundle.putLong("datetime", datetime);
        bundle.putString("flightno", flightNo);
        bundle.putString("seatname", seatName);
        bundle.putInt("seatcount", setCount);
        bundle.putString("guestName", guestName);
        bundle.putSerializable("downloadInfoList", downloadPojoArrayList);

        /*BoardingPassUserDetailsFragment fragment = new BoardingPassUserDetailsFragment();
        fragment.setSharedIconVisible(this);
        fragment.setBackButton(this);
        fragment.setArguments(bundle);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.boardingPass_CPanel, fragment)
                .addToBackStack(null)
                .commit(), UiConstants.DELAYMILLISECOND);*/
    }

    @Override
    public void onRowData(BoardingPassBeanXX boardingPassBeanXX) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("boardingPassBean", boardingPassBeanXX);

        BoardingPassUserDetailsFragment fragment = new BoardingPassUserDetailsFragment();
        fragment.setBackButton(this);
        fragment.setArguments(bundle);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.boardingPass_CPanel, fragment)
                .addToBackStack(null)
                .commit(), UiConstants.DELAYMILLISECOND);

    }

    @Override
    public void backBtn(boolean check) {
        mBackButton.backBtn(check);
    }

    public CBackButton mBackButton;


    public CBackButton setBackButton(CBackButton click) {
        return mBackButton = click;
    }


    public interface CBackButton {
        void backBtn(boolean check);
    }


}
