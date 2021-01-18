package com.sky.order.web;

import com.sky.order.model.OrderInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                            @AuthenticationPrincipal String userName// 也可以研究下怎么拿到想要的对象，可以的
    ) {
//        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9080/price/" + info.getProductId(), PriceInfo.class);
//
//        System.out.println(priceInfo);

        return info;
    }
}
