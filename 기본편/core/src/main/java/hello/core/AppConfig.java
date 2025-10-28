package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
public class AppConfig {

    // 생성자를 통해서 주입(연결)
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // `new MemoryMemberRepository()` 중복이 제거 -> 다른 구현체로 변경할 때 한 부분만 변경하면 됨.
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
