package com.xpf.android.mock.mock;

import com.xpf.android.mock.MyApplication;
import com.xpf.android.mock.constants.SpKey;
import com.xpf.android.mock.utils.AssetUtils;
import com.xpf.android.mock.utils.SpUtil;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:mock 数据源服务类，只要在这个类中写了某个接口的方法，就会自动返回这里的数据
 */
public class MockService {

    @MOCK("/xy0001")
    public MockResult xy0001(Request request) {
        return getXy0001Result(request);
    }

    private MockResult getXy0001Result(Request request) {
        boolean state = SpUtil.getInstance().getBoolean(SpKey.MOCK_XY0001, true);
        String fileName = state ? "mock/XY0001.json" : "mock/FAILED.json";
        return MockResult.create(request, getJsonByFileName(fileName));
    }

    private String getJsonByFileName(String fileName) {
        return AssetUtils.getJson(MyApplication.getContext(), fileName);
    }

    @MOCK("/getUserInfo")
    public MockResult action(Request request) {
        // 如果是 GET 请求，可以取出 ？后面拼接的参数，然后拼接到返回结果中
        Map<String, String> query = MockRequest.getQuery(request);
        String name = query.get("name");
        return MockResult.create(request, "{\n" +
                "    \"status\": true,\n" +
                "    \"msg\": \"SUCCESS\",\n" +
                "    \"data\": {\n" +
                "        \"bond\": \"100000\",\n" +
                "        \"bio\": [\n" +
                "            {\n" +
                "                \"id\": \"1\",\n" +
                "                \"name\": \"hell0\",\n" +
                "                \"rule_url\": \"http://www.x-sir.com\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}");
    }
}
