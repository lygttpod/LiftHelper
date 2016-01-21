package com.allen.lifthelper.robot.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.allen.lifthelper.R;
import com.allen.lifthelper.activity.BaseActivity;
import com.allen.lifthelper.common.UrlData;
import com.allen.lifthelper.robot.adapter.RobotMsgAdapter;
import com.allen.lifthelper.robot.bean.GetMsgBean;
import com.allen.lifthelper.robot.bean.MsgBean;
import com.allen.lifthelper.utils.LogUtil;
import com.allen.lifthelper.utils.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/1/21.
 */
public class RobotActivity extends BaseActivity {
    private Button sendMsg;
    private EditText msgContent;
    private RecyclerView msg_rv;
    private RobotMsgAdapter robotMsgAdapter;

    private List<MsgBean> msgBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initview();
    }

    private void initToolbar() {
        getToolbar().setTitle("问答机器人").setDisplayHomeAsUpEnabled(true);
    }

    private void initview() {
        sendMsg = ((Button) findViewById(R.id.button_send));
        msgContent = (EditText) findViewById(R.id.send_msg_content_et);
        msg_rv = (RecyclerView) findViewById(R.id.msg_list_recyclerview);
        setRecyclerView();
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgContent.getText().toString().trim();
                if (msg.equals("")){
                    ToastUtils.showShort(RobotActivity.this, "不能发送空消息哦");
                }else if (msg.length()>=60){
                    ToastUtils.showShort(RobotActivity.this, "对话内容不可超过60个字节");
                    msgContent.setText("");
                } else {
                    MsgBean msgBean = new MsgBean(msg, 0);
                    msgBeans.add(msgBean);
                    msg_rv.scrollToPosition(msgBeans.size());
                    robotMsgAdapter.notifyDataSetChanged();
                    sendRequest(msg);
                    msgContent.setText("");
                }

            }
        });
    }

    private void sendRequest(String msg) {
        OkHttpUtils.get().url(UrlData.robot_url + msg).build().execute(new MyMsgCallBack());

    }

    public class MyMsgCallBack extends Callback<GetMsgBean> {

        @Override
        public GetMsgBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            LogUtil.d("allen",string);
            GetMsgBean getMsgBean = new Gson().fromJson(string, GetMsgBean.class);
            return getMsgBean;
        }

        @Override
        public void onError(Request request, Exception e) {

            MsgBean msgBean = new MsgBean("我好累，一会儿再给你说", 1);
            msgBeans.add(msgBean);
        }

        @Override
        public void onResponse(GetMsgBean response) {
            String msg = response.getResult().getText();
            LogUtil.d("allen",msg);
            MsgBean msgBean = new MsgBean(msg, 1);
            msgBeans.add(msgBean);
        }

        @Override
        public void onAfter() {
            super.onAfter();
            msg_rv.scrollToPosition(msgBeans.size());
            robotMsgAdapter.notifyDataSetChanged();

        }
    }

    private void setRecyclerView() {
        MsgBean msgBean = new MsgBean("快来和我聊天吧！", 1);
        msgBeans.add(msgBean);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        msg_rv.setLayoutManager(layoutManager);

        robotMsgAdapter = new RobotMsgAdapter(msgBeans);

        msg_rv.setAdapter(robotMsgAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_robot;
    }
}
