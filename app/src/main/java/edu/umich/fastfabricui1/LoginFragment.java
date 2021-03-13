package edu.umich.fastfabricui1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class LoginFragment extends DialogFragment {


    public LoginFragment() {
        // empty constructor, helps avoid errors, apparently a perk of dialog fragments.
    }

    public static LoginFragment newInstance() {
        LoginFragment frag = new LoginFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view1 = inflater.inflate(R.layout.fragment_login, null);

        AlertDialog dialog  =  new AlertDialog.Builder(getActivity())
                .setTitle("Create Account/Login")
                .setView(view1)
                .setPositiveButton("Submit", null)
                .setNegativeButton("Cancel", null)
                .create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button buttonPos = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNeg = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

                buttonPos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doPositiveClick(view1);
                    }
                });

                buttonNeg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doNegativeClick();
                    }
                });
            }
        });



        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public void doPositiveClick(View view) {
        getDialog().cancel();
    }


    public void doNegativeClick() {
        getDialog().cancel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}






