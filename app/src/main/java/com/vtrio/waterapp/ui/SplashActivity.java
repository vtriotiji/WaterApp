package com.vtrio.waterapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.vtrio.waterapp.R;

import com.vtrio.waterapp.databinding.ActivitySplashBinding;
import com.vtrio.waterapp.services.FetchAddressIntentService;
import com.vtrio.waterapp.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, TabLayout.OnTabSelectedListener {


    protected Location mLastKnownLocation;
    private AddressResultReceiver mResultReceiver;
    private FusedLocationProviderClient mFusedLocationClient;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    private Fragment newUserFragment;
    private Fragment existingUserFragment;

    ActivitySplashBinding activitySplashBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        activitySplashBinding.setTitle(getString(R.string.app_name));
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkForPermissions();
        activitySplashBinding.progressBar.changeAnimationDirection(DotProgressBar.RIGHT_DIRECTION);
//        activitySplashBinding.userTabLayout.

    }

    private void setupTabLayout() {
        newUserFragment = new NewUserFragment();
        existingUserFragment = new ExistingUserFragment();
        setCurrentTabFragment(0);
        activitySplashBinding.userTabLayout.addOnTabSelectedListener(this);
    }


    protected void startIntentService() {
        mResultReceiver = new AddressResultReceiver(new Handler());
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastKnownLocation);
        startService(intent);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void checkForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isPermissionGranted();
        } else {
            fetchAddressFromService();
        }
    }

    private void isPermissionGranted() {

        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("ACCESS COARSE LOCATION");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("ACCESS FINE LOCATION");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_NETWORK_STATE))
            permissionsNeeded.add("ACCESS_NETWORK_STATE");
        if (!addPermission(permissionsList, Manifest.permission.INTERNET))
            permissionsNeeded.add("INTERNET");
        if (!addPermission(permissionsList, Manifest.permission.READ_SMS))
            permissionsNeeded.add("READ_SMS");
        if (!addPermission(permissionsList, Manifest.permission.SEND_SMS))
            permissionsNeeded.add("SEND_SMS");
        if (!addPermission(permissionsList, Manifest.permission.RECEIVE_SMS))
            permissionsNeeded.add("RECEIVE_SMS");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsList.size() > 0) {
                if (permissionsNeeded.size() > 0) {
                    requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                            REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                }
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                fetchAddressFromService();
            }
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.GET_PERMISSIONS);
                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.GET_PERMISSIONS);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.GET_PERMISSIONS);
                perms.put(Manifest.permission.READ_SMS, PackageManager.GET_PERMISSIONS);
                perms.put(Manifest.permission.RECEIVE_SMS, PackageManager.GET_PERMISSIONS);
                perms.put(Manifest.permission.SEND_SMS, PackageManager.GET_PERMISSIONS);
                for (int i = 0; i < permissions.length; i++) {
                    perms.put(permissions[i], grantResults[i]);
                }
                if ((perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                        (perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                        (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                        (perms.get(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) &&
                        (perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) &&
                        (perms.get(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)) {
                    fetchAddressFromService();
                } else if ((perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                } else if ((perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                } else if ((perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                } else if ((perms.get(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                } else if ((perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                } else if ((perms.get(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(SplashActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void fetchAddressFromService() {
        Log.d("WaterApp", "fetchAddressFromService 1 ");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("WaterApp", "fetchAddressFromService : " + "check self permission granted");
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            mLastKnownLocation = location;

                            // In some rare cases the location returned can be null
                            if (mLastKnownLocation == null) {
                                displayResultMessage(Constants.FAILURE_RESULT, getString(R.string.Address_Not_Retreivable));
                                return;
                            }

                            if (!Geocoder.isPresent()) {
                                displayResultMessage(Constants.FAILURE_RESULT, getString(R.string.Address_Not_Retreivable));
                                Toast.makeText(SplashActivity.this,
                                        R.string.location_service_not_available,
                                        Toast.LENGTH_LONG).show();
                                return;
                            }

                            // Start service and update UI to reflect new location
                            startIntentService();
//                            updateUI();
                        }
                    });
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setCurrentTabFragment(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(existingUserFragment);
                break;
            case 1:
                replaceFragment(newUserFragment);
                break;
            default:
                replaceFragment(existingUserFragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.simpleFrameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    class AddressResultReceiver extends ResultReceiver {
        String mAddressOutput;

        AddressResultReceiver(Handler handler) {
            super(handler);
        }


        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                displayResultMessage(Constants.FAILURE_RESULT, getString(R.string.Address_Not_Retreivable));
                return;

            }

            // Display the address string
            // or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            if (mAddressOutput == null) {
                mAddressOutput = "";
            }
            Log.d("mAddressOutput : ", mAddressOutput);
//            displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
//                showToast(getString(R.string.address_found));
                displayResultMessage(Constants.SUCCESS_RESULT, mAddressOutput);
            } else if (resultCode == Constants.FAILURE_RESULT) {
                displayResultMessage(Constants.FAILURE_RESULT, getString(R.string.Address_Not_Retreivable));
            }

        }
    }

    private void displayResultMessage(int result, String resultMessage) {

        switch (result) {
            case Constants.FAILURE_RESULT:
                hideProgressBar();
                activitySplashBinding.setMessage(resultMessage);
                break;
            case Constants.SUCCESS_RESULT:
                hideProgressBar();
                activitySplashBinding.tabLinearLayout.setVisibility(View.VISIBLE);
                setupTabLayout();
                activitySplashBinding.setMessage("You Current Address : " + resultMessage);
                break;
        }

    }

    private void hideProgressBar() {

        activitySplashBinding.progressBar.setVisibility(View.GONE);

    }

}
