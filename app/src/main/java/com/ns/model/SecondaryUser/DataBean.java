package com.ns.model.SecondaryUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBean  {
    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3Qtc2Vjb25kYXJ5Iiwic2Vjb25kYXJ5X3VzZXJfaWQiOjE5LCJpYXQiOjE1NTgzMzU1MDQsImV4cCI6MTU2MDkyNzUwNH0.MkcGKBkwq9AgXm9iFb9h7Nk2P5pnTuJQVHTEVVUMEmM
     * refresh_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QtcmVmcmVzaCIsInNlY29uZGFyeV91c2VyX2lkIjoxOSwiaWF0IjoxNTU4MzM1NTA0LCJleHAiOiIifQ.nuBkFvdcKb8D91YxxERofVf8cxOeY_-qKyQgb98Id_w
     * primary_users : [{"id":6,"name":"Krishan","email":"krishan.k@ninestars.in","phone":"8277027575"}]
     */

    private String token;
    private String refresh_token;
    private ArrayList<PrimaryUsersBean> primary_users;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public ArrayList<PrimaryUsersBean> getPrimary_users() {
        return primary_users;
    }

    public void setPrimary_users(ArrayList<PrimaryUsersBean> primary_users) {
        this.primary_users = primary_users;
    }

}