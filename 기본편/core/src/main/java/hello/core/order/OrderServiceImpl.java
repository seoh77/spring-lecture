package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // `final`이 붙은 필드를 모아서 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {

    // 생성자 주입 방식만 `final` 키워드를 사용할 수 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
        @Autowired 매칭 정리
        1. 타입 매칭
        2. 타입 매칭의 결과가 2개 이상일 때 필드명, 파라미터명으로 빈 이름 매칭

        @Autowired
        public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = rateDiscountPolicy;
        }
     */

    /* 주입시에 @Qualifier를 붙여주고 등록한 이름을 적어준다.

        @Qualifier 매칭 정리
        1. @Qualifier 끼리 매칭
        2. 빈 이름 매칭
        3. `NoSuchBeanDefinitionException` 예외 발생

        public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
     */

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
