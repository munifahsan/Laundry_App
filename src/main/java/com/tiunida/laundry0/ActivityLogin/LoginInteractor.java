package com.tiunida.laundry0.ActivityLogin;

public class LoginInteractor implements LoginInteractorMvp {

    private LoginRepositoryMvp mLoginRepositoryMvp;

    public LoginInteractor(){
        mLoginRepositoryMvp = new LoginRepository();
    }
    @Override
    public void doSignIn(String email, String password) {
        mLoginRepositoryMvp.signIn(email,password);
    }
}
