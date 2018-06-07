package com.vtrio.waterapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.databinding.FragmentExistingUserBinding;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;


public class ExistingUserFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Activity activityContext;


    public ExistingUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExistingUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExistingUserFragment newInstance(String param1, String param2) {
        ExistingUserFragment fragment = new ExistingUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        activityContext = (SplashActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentExistingUserBinding fragmentExistingUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_existing_user, container, false);
        fragmentExistingUserBinding.setSignInButtonLabel(activityContext.getResources().getString(R.string.Sign_In));
        fragmentExistingUserBinding.setCancelButtonLabel(activityContext.getResources().getString(R.string.cancel));
        fragmentExistingUserBinding.setOtpLabel(activityContext.getString(R.string.OTP));
        fragmentExistingUserBinding.setMobileLabel(activityContext.getString(R.string.mobile));
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentExistingUserBinding.otpLabelTextView);
        fragmentExistingUserBinding.otpLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentExistingUserBinding.mobileLabelTextView);
        fragmentExistingUserBinding.mobileLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        fragmentExistingUserBinding.cancelButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(activityContext));
        fragmentExistingUserBinding.cancelButton.setTextSize(activityContext.getResources().getDimension(R.dimen.dim_48_pt));
        fragmentExistingUserBinding.signInButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(activityContext));
        fragmentExistingUserBinding.signInButton.setTextSize(activityContext.getResources().getDimension(R.dimen.dim_48_pt));
        fragmentExistingUserBinding.signInButton.setOnClickListener(this);
        fragmentExistingUserBinding.cancelButton.setOnClickListener(this);
        return fragmentExistingUserBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInButton:
                Log.d("WaterApp", "onClick of existing user");
                SplashActivity activity = (SplashActivity) getActivity();
                Intent productIntent = new Intent(activity, ProductActivity.class);
                activity.startActivity(productIntent);
                break;
        }
    }
}
