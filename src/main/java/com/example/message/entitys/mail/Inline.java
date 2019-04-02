package com.example.message.entitys.mail;

/**
 * 嵌入图片属性
 */
public class Inline {
    //路径
    private String rscPath ;
    //嵌入content的id
    private String cid;
    public String getRscPath() {
        return rscPath;
    }

    public void setRscPath(String rscPath) {
        this.rscPath = rscPath;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}