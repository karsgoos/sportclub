package com.realdolmen.sportclub.payment.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.realdolmen.sportclub.payment.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderApiController {



    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<OrderList> orderGet() {
        OrderListInner element = new OrderListInner();
        element.setIsPayed(false);
        element.setOrderedOn(LocalDate.now().toString());
        element.setPrice(new BigDecimal(25));
        UUID uuid = new UUID(2,5);
        element.setUuid(uuid);
        element.setLinks(new OrderListInnerLinks().self("https://localhost:.../order/" + uuid));

        OrderList list = new OrderList();
        list.add(element);
        return new ResponseEntity<OrderList>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> orderOrderIdDelete(@PathVariable("orderId") UUID orderId) {
        System.out.println("remove order with id: " + orderId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderDetails> orderOrderIdGet(@PathVariable("orderId") UUID orderId) {
        OrderDetails details = new OrderDetails();
        details.setIsPayed(true);
        details.setOrderedOn(LocalDate.now().toString());
        details.setPrice(new BigDecimal(125));

        OrderDetailsLinks links = new OrderDetailsLinks();
        links.setSelf("http://localhost:8080/order/" + orderId);
        links.setEvent("https://localhost:8080/event/bla-bla-bla");
        links.setUser("https://localhost:8080/user/foor-bar");

        details.setLinks(links);
        return new ResponseEntity<OrderDetails>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<OrderDetailsList> orderPost(@RequestBody OrderInput orderData) {
        System.out.println(orderData.toString());
        OrderDetailsList details = new OrderDetailsList();
        details.setIsPayed(false);
        details.setOrderedOn(LocalDate.now().toString());
        details.setTotalPrice(new BigDecimal(125));

        OrderDetailsListLinks links = new OrderDetailsListLinks();
        links.setUser("https://localhost:8080/user/" + orderData.getUserId());

        List<OrderDetailsListEvents> events = new ArrayList<>();
        for (OrderInputEvents e : orderData.getEvents()) {
            OrderDetailsListEvents event = new OrderDetailsListEvents();
            events.add(new OrderDetailsListEvents().eventUuid(e.getEventUuid()).links(new OrderListInnerLinks().self("http://localhost:8080/order/" + e.getEventUuid())));
        }

        details.setLinks(links);
        details.setEvents(events);
        return new ResponseEntity<OrderDetailsList>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<OrderList> orderUserUserIdGet(@PathVariable("userId") UUID userId) {
        OrderListInner element = new OrderListInner();
        element.setIsPayed(false);
        element.setOrderedOn(LocalDate.now().toString());
        element.setPrice(new BigDecimal(25));
        UUID uuid = new UUID(2,5);
        element.setUuid(uuid);
        element.setLinks(new OrderListInnerLinks().self("https://localhost:8080/order/" + uuid));

        OrderList list = new OrderList();
        list.add(element);
        return new ResponseEntity<OrderList>(list, HttpStatus.OK);
    }

}
