package com.xpf.android.mock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.xpf.android.mock.constants.SpKey;
import com.xpf.android.mock.entity.BaseEntity;
import com.xpf.android.mock.entity.Xy0001Entity;
import com.xpf.android.mock.mock.MockActivity;
import com.xpf.android.mock.net.ApiSubscriber;
import com.xpf.android.mock.net.RetrofitHelper;
import com.xpf.android.mock.net.Xy0001Service;
import com.xpf.android.mock.utils.LogUtils;
import com.xpf.android.mock.utils.RxThreadUtils;
import com.xpf.android.mock.utils.SpUtil;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by x-sir on 2019/06/19 :)
 * Function:MainActivity
 * {@link # https://github.com/xinpengfei520/RxJavaRetrofit2Demo}
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 666;
    private static final String XY0001 = "http://api.x-sir.com/xy0001";
    private ToggleButton tbEnable;
    private TextView tvMock;
    private TextView tvResult;
    private Button btnSendRequest;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        tbEnable = findViewById(R.id.tbEnable);
        tvResult = findViewById(R.id.tvResult);
        tvMock = findViewById(R.id.tvMock);
        btnSendRequest = findViewById(R.id.btnSendRequest);
    }

    private void initListener() {
        tbEnable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SpUtil.getInstance().save(SpKey.IS_MOCK, isChecked);
        });
        tvMock.setOnClickListener(v -> {
            MockActivity.actionStart(this, REQUEST_CODE);
        });
        btnSendRequest.setOnClickListener(v -> {
            sendRequest();
        });
    }

    private void sendRequest() {
        RetrofitHelper
                .getInstance()
                .createService(Xy0001Service.class)
                .xy0001(XY0001)
                .compose(RxThreadUtils.flowableToMain())
                .subscribe(new ApiSubscriber<BaseEntity<Xy0001Entity>>() {
                    @Override
                    public void onNext(BaseEntity<Xy0001Entity> baseEntity) {
                        parseResult(baseEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.eLogging(TAG, "onError:" + t.getMessage());
                    }
                });
    }

    private void parseResult(BaseEntity<Xy0001Entity> baseEntity) {
        Xy0001Entity xy0001Entity = baseEntity.data;
        String returnCode = baseEntity.getReturnCode();
        String resultCode = baseEntity.getResultCode();
        if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
            tvResult.setText(xy0001Entity.toString());
        } else {
            String errCodeDes = baseEntity.getErrCodeDes();
            tvResult.setText(errCodeDes);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            boolean isMocker = data.getBooleanExtra("isMocker", false);
            tbEnable.setChecked(isMocker);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}
