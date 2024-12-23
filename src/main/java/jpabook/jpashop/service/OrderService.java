package jpabook.jpashop.service;


import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    // 주문
    @Transactional
    public Long order(long MemberId,Long ItemId, int count){

        Member member = memberRepository.findOne(MemberId);
        Item item = itemRepository.findOne(ItemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAdress(member.getAdress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //저장
        orderRepository.save(order);

        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(long Id){
        Order order = orderRepository.findOne(Id);
        order.cancel();
    }

    // 검색
//    public List<Order> getOrders(long MemberId){
//        return orderRepository.findAll();
//    }
}
