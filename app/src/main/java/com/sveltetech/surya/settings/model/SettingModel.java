package com.sveltetech.surya.settings.model;

public class SettingModel {

    int title,subtitle,icon;

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(int subtitle) {
        this.subtitle = subtitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public SettingModel(int title, int subtitle, int icon) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
    }
}
