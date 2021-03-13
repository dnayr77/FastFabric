package edu.umich.fastfabricui1;

import android.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;



public class ResultFragment extends DialogFragment {

    private Result result;
    private OnLearnListener listener;

    public ResultFragment() {
        // empty constructor, helps avoid errors, apparently a perk of dialog fragments.
    }

    public static ResultFragment newInstance(int position) {
        ResultFragment frag = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view1 = inflater.inflate(R.layout.fragment_result, null);

        AlertDialog dialog  =  new AlertDialog.Builder(getActivity())
                .setView(view1)
                .create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((AlertDialog) dialog).getWindow().setLayout(800, 600);
                Button learnButton = view1.findViewById(R.id.learn_more);
                ImageButton likeButton = view1.findViewById(R.id.like);
                ImageButton dislikeButton = view1.findViewById(R.id.dislike);
                learnButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onLearnClick(getArguments().getInt("position"));
                    }
                });
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onLikeClick(getArguments().getInt("position"));
                    }
                });
                dislikeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onDislikeClick(getArguments().getInt("position"));
                    }
                });
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnLearnListener) context;
    }

    public interface OnLearnListener{
        void onLearnClick(int position);
        void onLikeClick(int position);
        void onDislikeClick(int position);
    }

    public void doNegativeClick() {
        getDialog().cancel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}






