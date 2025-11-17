package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor        // `final`이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다.
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String item(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        /*
            @ModelAttribute 기능1. 요청 파라미터 처리
            - `@ModelAttribute`는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법으로 입력해준다.

            @ModelAttribute 기능2. Model 추가
            - `@ModelAttribute`는 모델(Model)에 `@ModelAttribute`로 지정한 객체를 자동으로 넣어준다.
            - 따라서 `model.addAttribute("item", item)` 생략 가능
            - 모델에 데이터를 담을 때는 이름이 필요한데, 이름은 `@ModelAttribute`에 지정한 `name(value)` 속성을 사용한다.
         */

        itemRepository.save(item);
//        model.addAttribute("item", item);     // 자동 추가, 생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        /*
            @ModelAttribute의 name은 생략 가능하다.
            생략시 model에 저장되는 name은 클래스명 첫글자인 소문자로 등록 Item -> item
         */

        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {        // @ModelAttribute 자체도 생략 가능하다. 대상 객체는 모델에 자동 등록된다.
        itemRepository.save(item);
        return "basic/item";
    }

    /*
        PRG : Post/Redirect/Get
        기존의 addItemV4는 상품 등록 폼에서 데이터를 입력하고 저장을 선택하면 POST /add + 상품 데이터를 서버로 전송한다.
        이 상태에서 새로 고침을 또 선택하면 마지막에 전송한 POST /add + 상품 데이터를 서버로 다시 전송하게 된다.
        그래서 내용은 같고, ID만 다른 상품 데이터가 계속 쌓이게 된다.

        이런 새로 고침 문제를 해결하려면 상품 저장 후에 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로 리다이렉트를 호출해주면 된다.
        웹 브라우저는 리다이렉트의 영향으로 상품 저장 후에 실제 상품 상세 화면으로 다시 이동한다.
        따라서 마지막에 호출한 내용이 상품 상세 화면인 GET /items/{id}가 되는 것이다.
        이후 새로고침을 해도 상품 상세 화면으로 이동하게 되므로 새로 고침 문제를 해결할 수 있다.
     */
    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct      // 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다.
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
