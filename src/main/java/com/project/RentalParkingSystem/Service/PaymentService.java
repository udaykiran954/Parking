package com.project.RentalParkingSystem.Service;

import com.project.RentalParkingSystem.Model.customer;
import com.project.RentalParkingSystem.Model.customerOrder;
import com.project.RentalParkingSystem.Repository.orderRepository;
import com.razorpay.Customer;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private orderRepository repo;
    @Value("${razorpay.key.id}")
    private String razorPayId;
    @Value("${razorpay.secret.key}")
    private String razorPaySecret;
    private RazorpayClient client;
    public customerOrder createorder(customerOrder custOrder) throws Exception{
        JSONObject obj = new JSONObject();
        obj.put("amount",(custOrder.getAmount()*100));
        obj.put("currency","INR");
        obj.put("receipt", String.valueOf(custOrder.getPhoneNumber()));
        this.client = new RazorpayClient(razorPayId,razorPaySecret);
        Order razorPayOrder = client.orders.create(obj);
        custOrder.setRazorpayOrderId(razorPayOrder.get("id"));         // Order ID
        custOrder.setOrderStatus(razorPayOrder.get("status"));         // Order status
        repo.save(custOrder);
        return custOrder;
    }
    @Autowired
    private UserService service;
    public customerOrder update(Map<String, String> responsePayLoad) {
        String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
        customerOrder order = repo.findByRazorpayOrderId(razorPayOrderId);
        order.setOrderStatus("paid");
        service.set(order.getVehicleNumber());
        return repo.save(order);
    }


}
