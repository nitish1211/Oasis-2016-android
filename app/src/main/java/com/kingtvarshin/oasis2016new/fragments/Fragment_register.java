package com.kingtvarshin.oasis2016new.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kingtvarshin.oasis2016new.R;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_register extends Fragment {

//    ImageButton ib1;
    Button selectevent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

//        ib1 = (ImageButton)rootView.findViewById(R.id.imageButton) ;
//
//        final Dialog dialog;
//        final String[] items = {"Choreo",
//                "Street Dance",
//                "Tandav",
//                "Desert Duel",
//                "Razzmatazz"};
//        final ArrayList itemsSelected = new ArrayList();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Select events you want to take part in: ");
//        builder.setMultiChoiceItems(items, null,
//                new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedItemId,
//                                        boolean isSelected) {
//                        if (isSelected) {
//                            itemsSelected.add(selectedItemId);
//                        } else if (itemsSelected.contains(selectedItemId)) {
//                            itemsSelected.remove(Integer.valueOf(selectedItemId));
//                        }
//                    }
//                })
//                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        //Your logic when OK button is clicked
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                });
//        dialog = builder.create();
//
//        ib1.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.show();
//                    }
//                }
//        );

        selectevent = (Button)rootView.findViewById(R.id.button);


        selectevent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, new Fragment_eventselect());
                        ft.commit();
                    }
                }
        );

        return rootView;
    }

}