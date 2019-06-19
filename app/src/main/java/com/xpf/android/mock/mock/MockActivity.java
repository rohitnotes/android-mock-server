package com.xpf.android.mock.mock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpf.android.mock.R;
import com.xpf.android.mock.utils.LogUtils;
import com.xpf.android.mock.utils.SpUtil;
import com.xpf.android.mock.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:
 */
public class MockActivity extends AppCompatActivity {

    private static final String TAG = "MockActivity";
    private static final String[] API = {"xy0001"};
    private TextView tvBack;
    private TextView tvReset;
    private TextView tvSave;
    private RecyclerView recyclerView;
    private List<MockBean> mList;
    private MockAdapter mMockAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);
        tvBack = findViewById(R.id.tvBack);
        tvReset = findViewById(R.id.tvReset);
        tvSave = findViewById(R.id.tvSave);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        initData();
        initListener();
    }

    private void initListener() {
        tvBack.setOnClickListener(v -> {
            ToastUtils.showShort(this, "设置取消~");
            finish();
        });
        tvReset.setOnClickListener(v -> {
            for (MockBean mockBean : mList) {
                mockBean.state = Boolean.TRUE;
            }

            if (mMockAdapter != null) {
                mMockAdapter.notifyDataSetChanged();
            }
        });
        tvSave.setOnClickListener(v -> saveMockState());
    }

    private void saveMockState() {
        HashMap<String, Object> map = new HashMap<>();
        for (MockBean mockBean : mList) {
            String key = "Mock_" + mockBean.api;
            boolean value = mockBean.state;
            map.put(key, value);
        }

        SpUtil.getInstance().save(map);

        Intent intent = new Intent();
        intent.putExtra("isMocker", true);
        setResult(RESULT_OK, intent);
        ToastUtils.showShort(this, "设置成功~");
        finish();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (String aMApi : API) {
            MockBean mockBean = new MockBean();
            mockBean.api = aMApi;
            mockBean.state = SpUtil.getInstance().getBoolean("Mock_" + mockBean.api, true);
            mList.add(mockBean);
        }

        mMockAdapter = new MockAdapter(mList);
        recyclerView.setAdapter(mMockAdapter);
    }

    class MockBean {
        public String api;
        public Boolean state;
    }

    class MockAdapter extends BaseQuickAdapter<MockBean, BaseViewHolder> {

        public MockAdapter(@Nullable List<MockBean> data) {
            super(R.layout.item_mock, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MockBean item) {
            helper.setText(R.id.tvApiName, item.api);
            ToggleButton toggleButton = helper.getView(R.id.tbState);
            toggleButton.setChecked(item.state);
            helper.setOnCheckedChangeListener(R.id.tbState, (buttonView, isChecked) -> {
                item.state = isChecked ? Boolean.TRUE : Boolean.FALSE;
            });
        }
    }

    public static void actionStart(Context context, int requestCode) {
        if (context != null) {
            Intent intent = new Intent(context, MockActivity.class);
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            LogUtils.eLogging(TAG, "context is null!");
        }
    }
}
