package com.software.ttsl.Utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

public class MoneyValueFilter extends DigitsKeyListener {
    private int digits ;
    private int startDigits ;


    private AmountListener amountListener;
    private Context context;
    private  EditText editText;
    public MoneyValueFilter(EditText editText) {
        super(false, true);
        this.editText = editText;
        digits =2;
        startDigits =7;

    }

   public MoneyValueFilter(EditText editText,int digits,int startDigits)
   {
       super(false, true);
       this.startDigits =startDigits;
       this.editText = editText;
       this.digits =digits;
   }


    public void setDigits(int d) {
        digits = d;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        CharSequence out = super.filter(source, start, end, dest, dstart, dend);

        // if changed, replace the source
        String temp = editText.getText() + source.toString();

        if (out != null) {
            source = out;
            start = 0;
            end = out.length();
        }

        int len = end - start;

        // if deleting, source is empty
        // and deleting can't break anything
        if (len == 0) {
            return source;
        }

        int dlen = dest.length();

        if (temp.contains(".") || temp.indexOf(".") == startDigits) {
            temp = temp.substring(temp.indexOf(".") + 1, temp.length());

            if (temp.length() > digits) {
                return "";
            } else {
                return new SpannableStringBuilder(source, start, end);
            }
        } else if (temp.length() > startDigits) {
            if (source.toString().trim().equals("."))
                return new SpannableStringBuilder(source, start, end);
            else
                return "";
        }

        return new SpannableStringBuilder(source, start, end);
    }

    public interface  AmountListener{
        public void setAmount(String amount);
    }
}
