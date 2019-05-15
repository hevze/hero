package com.hero.business.model.index;

import com.hero.business.model.my.BaseUser;

public class HeroUserDynamic extends BaseUser {

    private Integer parentId;

    private String title;

    private String brief;

    private String imgs;

    private Integer dynamicType;

    private Integer categoryId;

    private Integer comments;

    private Integer forwards;

    private Integer likes;

    private String linkUrl;

    private Integer enableFlag;

    private String content;

    private Boolean attention;

    private HeroUserDynamic forwardInfo;


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getDynamicType() {
        return dynamicType;
    }

    public void setDynamicType(Integer dynamicType) {
        this.dynamicType = dynamicType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getForwards() {
        return forwards;
    }

    public void setForwards(Integer forwards) {
        this.forwards = forwards;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HeroUserDynamic getForwardInfo() {
        return forwardInfo;
    }

    public void setForwardInfo(HeroUserDynamic forwardInfo) {
        this.forwardInfo = forwardInfo;
    }

    public Boolean getAttention() {
        return attention;
    }

    public void setAttention(Boolean attention) {
        this.attention = attention;
    }
}