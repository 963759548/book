package com.yll520wcf.chapter9.section3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yll520wcf.chapter9.R;


/**
 * 简单的Fragment
 */
public class SimpleFragment extends Fragment {
    private static final String ARG_SELECTION_NUM = "arg_selection_num";
    //在res/values/string.xml文件中 定义一些固定文本
    private static final int[] TEXTS = {R.string.wjk_text, R.string.wy_text, R.string.yyqx_text};

    TextView textView;

    public SimpleFragment() {
    }
    /**方便用来传递数据*/
    public static SimpleFragment newInstance(int selectionNum) {
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTION_NUM, selectionNum);
        simpleFragment.setArguments(args);
        return simpleFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textView= (TextView) view.findViewById(R.id.main_tv_text);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(TEXTS[getArguments().getInt(ARG_SELECTION_NUM)]);
    }
}
