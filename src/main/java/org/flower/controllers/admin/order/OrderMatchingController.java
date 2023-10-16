package org.flower.controllers.admin.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class OrderMatchingController {

    @GetMapping("/ordermatching")
    public String orderstoreMatch(){

        return "admin/order/ordermatching";
    }
}
