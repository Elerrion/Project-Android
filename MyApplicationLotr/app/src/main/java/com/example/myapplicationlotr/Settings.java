package com.example.myapplicationlotr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Settings() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_settings, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button exit = (Button) view.findViewById(R.id.Exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirm Exit")
                        .setMessage("Are you sure you want to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().moveTaskToBack(true);
                                getActivity().finish();
                                System.exit(0);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }

        });
        Button sensor = (Button) view.findViewById(R.id.NSensor);
        sensor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.sensor_spinner, null);
                mBuilder.setTitle("Sensor Type");
                Spinner mSpiner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sensor_type));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpiner.setAdapter(adapter);

                EditText mLower = mView.findViewById(R.id.Lower);
                EditText mUpper = mView.findViewById(R.id.Upper);

                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float lower = Float.parseFloat(mLower.getText().toString());
                        float upper = Float.parseFloat(mUpper.getText().toString());
                        if (!mSpiner.getSelectedItem().toString().equalsIgnoreCase("Select Sensor")) {
                            if ((mSpiner.getSelectedItem().toString().equalsIgnoreCase("Smoke Sensor") && lower >= 0 && upper <= 0.25 && lower <= upper) ||
                                    (mSpiner.getSelectedItem().toString().equalsIgnoreCase("Gas Sensor") && lower >= 0 && upper <= 11 && lower <= upper) ||
                                    (mSpiner.getSelectedItem().toString().equalsIgnoreCase("Temp Sensor") && lower >= -5 && upper <= 80 && lower <= upper) ||
                                    (mSpiner.getSelectedItem().toString().equalsIgnoreCase("Ultraviolet Sensor") && lower >= 0 && upper <= 11 && lower <= upper)) {
                                Toast.makeText(getActivity(), mSpiner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Out of Bounds", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });
    }
}