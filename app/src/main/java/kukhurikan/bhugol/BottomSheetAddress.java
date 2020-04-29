package kukhurikan.bhugol;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetAddress extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    Button b1,b2,b3,b4;
    TextView t1,t2,t3,t4;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_add,container, false);

        b1 = v.findViewById(R.id.bt_add1);
        b2 = v.findViewById(R.id.bt_add2);
        b3 = v.findViewById(R.id.bt_add3);
        b4 = v.findViewById(R.id.bt_add4);
        t1 = v.findViewById(R.id.txt_add1);
        t2 = v.findViewById(R.id.txt_add2);
        t3 = v.findViewById(R.id.txt_add3);
        t4 = v.findViewById(R.id.txt_add4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked(0,9);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked(1,9);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked(2,9);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked(3,9);
                dismiss();
            }
        });

        return v;
    }


    public interface BottomSheetListener{
        void onButtonClicked(int x, int y);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener = (BottomSheetListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

    //Changing TextView
    public void setText(String text, int x) {
        t1 = v.findViewById(R.id.txt_add1);
        t2 = v.findViewById(R.id.txt_add2);
        t3 = v.findViewById(R.id.txt_add3);
        t4 = v.findViewById(R.id.txt_add4);
        if (x==0) { t1.setText(text);}
        if (x==1) { t2.setText(text);}
        if (x==2) { t3.setText(text);}
        if (x==3) { t4.setText(text);}

    }
}
