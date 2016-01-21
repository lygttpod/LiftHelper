package com.allen.lifthelper.bean;

/**
 * Created by Allen on 2016/1/21.
 */
public class ListDataBean {
    private int icon;
    private String title;

    public ListDataBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ListDataBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
