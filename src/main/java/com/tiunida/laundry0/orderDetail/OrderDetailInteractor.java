package com.tiunida.laundry0.orderDetail;

public class OrderDetailInteractor implements OrderDetailInteractorMvp{
    private OrderDetailReposritoryMvp mOrderDetailReposritoryMvp;

    public OrderDetailInteractor(){
        mOrderDetailReposritoryMvp = new OrderDetailRepository();
    }

    @Override
    public void getOrderData(String order_id) {
        mOrderDetailReposritoryMvp.getOrdersData(order_id);
    }
}
