package com.software.ttsl.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.software.ttsl.R;

public class TaskDialog extends AppCompatDialogFragment {

    private Context context;
    private String[] itemList;
    public OnDialogSelectorListener dialogSelectorListener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        itemList = context.getResources().getStringArray(R.array.list_task_status);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setSingleChoiceItems(itemList, 3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogSelectorListener.onSelectedOption(itemList[i]);

            }
        });
       /* builder.setPositiveButton(R.string.ok_btn_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogSelectorListener.onSelectedOption(itemList[i]);
                dialogInterface.dismiss();




            }
        });*/
        builder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        return builder.create();




    }




    @Override
    public void onAttach(Context context) {

        this.context =context;
        super.onAttach(context);
        try {
            dialogSelectorListener = (OnDialogSelectorListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " must implements OnDialogSelectorListener");
        }
    }

    public interface OnDialogSelectorListener {
        public void onSelectedOption(String selectedIndex);
    }
}
