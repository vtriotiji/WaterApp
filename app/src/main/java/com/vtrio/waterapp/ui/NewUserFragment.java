package com.vtrio.waterapp.ui;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.databinding.FragmentNewUserBinding;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;


public class NewUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Activity activityContext;


    public NewUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewUserFragment newInstance(String param1, String param2) {
        NewUserFragment fragment = new NewUserFragment();
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
        FragmentNewUserBinding fragmentNewUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_user, container, false);
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentNewUserBinding.userNameLabelTextView);
        fragmentNewUserBinding.userNameLabelTextView.setTextSize(activityContext.getResources().getDimension(R.dimen.dim_48_pt));
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentNewUserBinding.emailIDLabelTextView);
        fragmentNewUserBinding.emailIDLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentNewUserBinding.mobileLabelTextView);
        fragmentNewUserBinding.mobileLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        HelveticaNeueFontHelper.applyHelveticaneueLtFont(activityContext, fragmentNewUserBinding.otpLabelTextView);
        fragmentNewUserBinding.otpLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        fragmentNewUserBinding.cancelButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(activityContext));
        fragmentNewUserBinding.cancelButton.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        fragmentNewUserBinding.registerButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(activityContext));
        fragmentNewUserBinding.registerButton.setTextSize(getResources().getDimension(R.dimen.dim_48_pt));
        fragmentNewUserBinding.setUserNameLabel(activityContext.getString(R.string.User_Name));
        fragmentNewUserBinding.setEmailLabel(activityContext.getString(R.string.Email_id));
        fragmentNewUserBinding.setMobileLabel(activityContext.getString(R.string.mobile));
        fragmentNewUserBinding.setOtpLabel(activityContext.getString(R.string.OTP));
        fragmentNewUserBinding.setCancelButtonLabel(activityContext.getString(R.string.cancel));
        fragmentNewUserBinding.setRegisterButtonLabel(activityContext.getString(R.string.register));

        return fragmentNewUserBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
