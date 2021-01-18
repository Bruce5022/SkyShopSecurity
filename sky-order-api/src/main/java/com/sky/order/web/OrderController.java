package com.sky.order.web;

import com.sky.order.model.OrderInfo;
import com.sky.order.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo info,
                            @AuthenticationPrincipal User user// 也可以研究下怎么拿到想要的对象，可以的
    ) {
        System.out.println("UserName is " + user);
//        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9080/price/" + info.getProductId(), PriceInfo.class);
//
//        System.out.println(priceInfo);

        return info;
    }


    @GetMapping("/{id}")
    public OrderInfo getInfo(@PathVariable Long id) {
        System.out.println("OrderId is " + id);
        return new OrderInfo();
    }
}
