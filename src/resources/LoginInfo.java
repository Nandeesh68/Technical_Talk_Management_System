package resources;


public class LoginInfo {

    UserInfo u1;
    UserInfo u2;
    UserInfo u3;
    public LoginInfo(){
        u1= new UserInfo("sdm","sdm");
        u2= new UserInfo("sdm1","sdm");
        u3= new UserInfo("","");

        Resources.loginDetails.add(u1);
        Resources.loginDetails.add(u2);
        Resources.loginDetails.add(u3);

    }

    public String getData(){
        return u1.userName;
    }


}

