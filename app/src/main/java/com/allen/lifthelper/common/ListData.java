package com.allen.lifthelper.common;

import com.allen.lifthelper.R;
import com.allen.lifthelper.bean.ListDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/1/21.
 */
public class ListData {
    public static List<ListDataBean> listDataBeans = new ArrayList<>();
    /**
     * 功能列表
     */
    public static String[] Function_List = {"生活常用", "金融服务", "影视娱乐", "旅游出行", "医药健康", "问答知识"};
    /**
     * 生活常用
     */
    public static String[] Common_life = {"天气预报", "邮编查询", "身份证查询",
            "笑话大全", "微信精选", "NBA赛事", "足球联赛", "万年历",
            "手机号码归属地", "IP地址", "律师查询", "新闻"};

    /**
     * 生活常用  icon
     */
    public static int[] Common_life_icon = {R.mipmap.tianqiyubao, R.mipmap.youbianchaxun, R.mipmap.shenfengzhengchaxun,
            R.mipmap.xiaohuadaquan, R.mipmap.weixinjingxuan, R.mipmap.nbasaishi, R.mipmap.zuqiuliansai, R.mipmap.wannianli,
            R.mipmap.shoujihaomaguishudi, R.mipmap.ipdizhi, R.mipmap.lvshichaxun, R.mipmap.xinwen};

    public static List<ListDataBean> getData(String[] title,int[] icon) {
        for (int i = 0; i < title.length; i++) {
            ListDataBean listDataBean = new ListDataBean(icon[i],title[i]);
            listDataBeans.add(listDataBean);
        }
        return listDataBeans;
    }

    /**
     * 金融服务
     */
    public static String[] Financial_services = {"股票数据", "股票查询", "货币汇率",
            "黄金数据", "净值数据", "暂停基金",
            "汇率", "基金财务数据", "重创股基金"};
    /**
     * 影视娱乐
     */
    public static String[] Move_fun = {"电影票房", "星座运势", "影视影讯检索", "周公解梦", "老黄历", "QQ号码测吉凶"};

    /**
     * 旅游出行
     */
    public static String[] Tourism_travel = {"火车时刻表", "长途汽车信息", "全国公交及路径规划查询"};

    /**
     * 医药健康
     */
    public static String[] Medical_health = {"药品大全", "药店大全", "医院信息"};

    /**
     * 问答知识
     */
    public static String[] knowledge = {"问答机器人", "历史上的今天", "有道翻译", "成语词典", "新华字典"};
}
