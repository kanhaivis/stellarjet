package com.ns.model;

import com.ns.model.LoginResponse.ContactsBean;

import java.util.List;

public class ContactDetailsPojo {
    /**
     * resultcode : 1
     * message : Guests list.
     * data : {"contacts":[{"id":1,"name":"Darling","nick_name":"Darling","relationship":"","email":"","user":2,"phone":"9874563210","user_info":{"id":2,"name":"Mani Murugan"}},{"id":2,"name":"Karthik Guest","nick_name":"gueessttt2","relationship":"","email":"guessssttk@gmail.com","user":4,"phone":"9990494501","user_info":{"id":4,"name":"Karthik"}},{"id":3,"name":"Guest Abhi","nick_name":null,"relationship":null,"email":null,"user":3,"phone":"9900885566","user_info":{"id":3,"name":"Abhilash"}},{"id":4,"name":"Yatindra","nick_name":"Yatindra","relationship":"Friend","email":"yati@aviatorsindia.com","user":5,"phone":"7259212345","user_info":{"id":5,"name":"Arun Sharma"}},{"id":5,"name":"sandeep","nick_name":null,"relationship":null,"email":null,"user":5,"phone":"9977884455","user_info":{"id":5,"name":"Arun Sharma"}},{"id":6,"name":"Ravi","nick_name":null,"relationship":null,"email":null,"user":5,"phone":"6978454248","user_info":{"id":5,"name":"Arun Sharma"}},{"id":7,"name":"varun","nick_name":null,"relationship":null,"email":null,"user":5,"phone":"9878965412","user_info":{"id":5,"name":"Arun Sharma"}},{"id":8,"name":"jsjdjdksj","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"6161616161","user_info":{"id":2,"name":"Mani Murugan"}},{"id":9,"name":"bbsns","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"9464619464","user_info":{"id":2,"name":"Mani Murugan"}},{"id":10,"name":"ghgh","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6666666698","user_info":{"id":12,"name":"Varun RV"}},{"id":11,"name":"hghg","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6386966669","user_info":{"id":12,"name":"Varun RV"}},{"id":12,"name":"bdheg","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6565659595","user_info":{"id":12,"name":"Varun RV"}},{"id":13,"name":"nxnzznnnz","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"3767676767","user_info":{"id":12,"name":"Varun RV"}},{"id":14,"name":"bxbdbdn","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6464610484","user_info":{"id":12,"name":"Varun RV"}},{"id":15,"name":"veitnfbd","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"6589662659","user_info":{"id":7,"name":"Ashwani"}},{"id":16,"name":"veitnfbd","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"6589662659","user_info":{"id":7,"name":"Ashwani"}},{"id":17,"name":"veitnfbd","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"6589662659","user_info":{"id":7,"name":"Ashwani"}},{"id":18,"name":"uuhgfgh","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"5555656655","user_info":{"id":7,"name":"Ashwani"}},{"id":19,"name":"bxhdjfnf","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"9862659895","user_info":{"id":7,"name":"Ashwani"}},{"id":20,"name":"Krishan Guest","nick_name":null,"relationship":"Friend","email":null,"user":6,"phone":"9874563201","user_info":{"id":6,"name":"Krishan"}},{"id":21,"name":"thanks","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"6565659959","user_info":{"id":7,"name":"Ashwani"}},{"id":22,"name":"Krishan Guest2","nick_name":null,"relationship":"Friend","email":null,"user":6,"phone":"9874563202","user_info":{"id":6,"name":"Krishan"}},{"id":23,"name":"hshdh","nick_name":null,"relationship":null,"email":null,"user":7,"phone":"9799797979","user_info":{"id":7,"name":"Ashwani"}},{"id":24,"name":"kishan","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"8134849484","user_info":{"id":12,"name":"Varun RV"}},{"id":25,"name":"mani","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6434846484","user_info":{"id":12,"name":"Varun RV"}},{"id":26,"name":"rupam","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"5454348494","user_info":{"id":12,"name":"Varun RV"}},{"id":27,"name":"joel","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6464348494","user_info":{"id":12,"name":"Varun RV"}},{"id":28,"name":"vikas","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"9835648364","user_info":{"id":2,"name":"Mani Murugan"}},{"id":29,"name":"akash","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"6834331285","user_info":{"id":2,"name":"Mani Murugan"}},{"id":30,"name":"hshshsh","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"4346494404","user_info":{"id":12,"name":"Varun RV"}},{"id":31,"name":" ssjsjs","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6494949449","user_info":{"id":12,"name":"Varun RV"}},{"id":32,"name":"hshshsh","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"4346494404","user_info":{"id":12,"name":"Varun RV"}},{"id":33,"name":" ssjsjs","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6494949449","user_info":{"id":12,"name":"Varun RV"}},{"id":34,"name":"hshshsh","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"4346494404","user_info":{"id":12,"name":"Varun RV"}},{"id":35,"name":" ssjsjs","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6494949449","user_info":{"id":12,"name":"Varun RV"}},{"id":36,"name":"jsjsjssj","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"4464949466","user_info":{"id":12,"name":"Varun RV"}},{"id":37,"name":"Darlinghchf","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"9874555353","user_info":{"id":2,"name":"Mani Murugan"}},{"id":38,"name":"gxjhidtgogjg","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"4253557985","user_info":{"id":2,"name":"Mani Murugan"}},{"id":39,"name":"afjolfacvhji","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"2465765764","user_info":{"id":2,"name":"Mani Murugan"}},{"id":40,"name":"cabjjj","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"9669966665","user_info":{"id":12,"name":"Varun RV"}},{"id":41,"name":"lllllllll","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"9999999999","user_info":{"id":2,"name":"Mani Murugan"}},{"id":42,"name":"aaaaaaaaa","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"1111111111","user_info":{"id":2,"name":"Mani Murugan"}},{"id":43,"name":"frrrrrr","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"2251818181","user_info":{"id":12,"name":"Varun RV"}},{"id":44,"name":"ghuuu","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"5333333333","user_info":{"id":12,"name":"Varun RV"}},{"id":45,"name":"yygyy","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"5588653568","user_info":{"id":12,"name":"Varun RV"}},{"id":46,"name":"gxgxhxhx","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"9868686868","user_info":{"id":12,"name":"Varun RV"}},{"id":47,"name":"Varun","nick_name":null,"relationship":null,"email":null,"user":2,"phone":"9484542154","user_info":{"id":2,"name":"Mani Murugan"}},{"id":48,"name":"vagaagag","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"4545455454","user_info":{"id":12,"name":"Varun RV"}},{"id":49,"name":"varun","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"9902609192","user_info":{"id":12,"name":"Varun RV"}},{"id":50,"name":"Soumen","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6464348494","user_info":{"id":12,"name":"Varun RV"}},{"id":51,"name":"ram","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"6434169194","user_info":{"id":12,"name":"Varun RV"}},{"id":52,"name":"soumen","nick_name":null,"relationship":null,"email":null,"user":12,"phone":"9437979787","user_info":{"id":12,"name":"Varun RV"}}]}
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

    public static class DataBean {
        private List<ContactsBean> contacts;

        public List<ContactsBean> getContacts() {
            return contacts;
        }

        public void setContacts(List<ContactsBean> contacts) {
            this.contacts = contacts;
        }

    }
}
