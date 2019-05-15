package com.hero.business.model.my;

public class HeroUser extends BaseUser{

    private String loginAccount;

    private String loginPwd;

    private String nickName;

    private Boolean accountType;


    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }


    public Boolean getAccountType() {
        return accountType;
    }

    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}