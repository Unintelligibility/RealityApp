package com.reality.realityapp.bean;

/**
 * Created by 铠联 on 2018/3/10.
 */

public class RankItem {
    private String _id;
    private String source;
    private String count;
    private String report_time;
    private String reliability;
    private String remark;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
