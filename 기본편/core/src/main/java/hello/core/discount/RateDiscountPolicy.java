package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")  // @Qualifier와 @Primary를 같이 사용하면 @Qualifier가 더 우선권이 높다.
//@Primary    // 우선순위를 정하는 방법 -> @Autowired 시에 여러 빈이 매칭되면 @Primary가 우선권을 가진다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRercent = 10;   // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountRercent / 100;
        } else {
            return 0;
        }
    }
}
