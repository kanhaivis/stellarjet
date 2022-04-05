package com.ns.model.SecondaryUser;

import java.io.Serializable;
import java.util.List;

public class SecondaryUserLoginResponse  {

    /**
     * resultcode : 1
     * message : Secondary User logged in successfully.
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3Qtc2Vjb25kYXJ5Iiwic2Vjb25kYXJ5X3VzZXJfaWQiOjE5LCJpYXQiOjE1NTgzMzU1MDQsImV4cCI6MTU2MDkyNzUwNH0.MkcGKBkwq9AgXm9iFb9h7Nk2P5pnTuJQVHTEVVUMEmM","refresh_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QtcmVmcmVzaCIsInNlY29uZGFyeV91c2VyX2lkIjoxOSwiaWF0IjoxNTU4MzM1NTA0LCJleHAiOiIifQ.nuBkFvdcKb8D91YxxERofVf8cxOeY_-qKyQgb98Id_w","primary_users":[{"id":6,"name":"Krishan","email":"krishan.k@ninestars.in","phone":"8277027575"}]}
     */

    private int resultcode;
    private String message;
    private DataBean data;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

}
