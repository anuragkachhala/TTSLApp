package com.software.ttsl.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MoneyTextWatcher implements TextWatcher {

    private EditText editText;

    public MoneyTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (editText.getText().toString().matches("^0") || editText.getText().toString().equals(".")) {
            editText.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
