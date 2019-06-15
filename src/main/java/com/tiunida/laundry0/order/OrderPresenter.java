package com.tiunida.laundry0.order;

import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.order.events.OrderEvents;
import com.tiunida.laundry0.order.ui.OrderViewMvp;

import org.greenrobot.eventbus.Subscribe;

public class OrderPresenter implements OrderPresenterMvp{
    private OrderViewMvp mOrderViewMvp;
    private OrderInteractorMvp mOrderInteractorMvp;

    private EventBus mEventBus;

    public OrderPresenter(OrderViewMvp orderViewMvp){
        mOrderViewMvp = orderViewMvp;
        mOrderInteractorMvp = new OrderInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    public void validateInputs(String desc, String time,
                               String bandana, String topi, String masker, String kupluk, String krudung, String peci,
                               String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk,
                               String sarung_tangan, String sapu_tangan,
                               String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki,
                               String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar){

        mOrderInteractorMvp.doInputs(desc, time, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mOrderViewMvp = null;
        mEventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(OrderEvents event){
        switch (event.getEventType()){
            case OrderEvents.onInputSuccess:
                onInputSuccess();
                //mOrderViewMvp.showMessage("input succes");
                break;
            case OrderEvents.onInputError:
                onInputError(event.getErrorMessage());
                break;
        }
    }

    public void onInputSuccess(){
        if (mOrderViewMvp != null){
            mOrderViewMvp.navigateToMainScreen();
            mOrderViewMvp.showMessage("masuk sukses");
        }
    }

    public void onInputError(String error){
        if (mOrderViewMvp != null){
            //mOrderViewMvp.hideProgress();
            //mOrderViewMvp.enableInputs();
            mOrderViewMvp.showMessage(error);
        }
    }

}
