package com.tiunida.laundry0.register;

public class RegisterInteractor implements RegisterInteractorMvp {

    private RegisterRepositoryMvp mRegisterRepositoryMvp;
    public RegisterInteractor(){
        mRegisterRepositoryMvp = new RegisterRepository();
    }

    @Override
    public void doSignUp(String email, String password){
        mRegisterRepositoryMvp.signUp(email, password);
    }

}
