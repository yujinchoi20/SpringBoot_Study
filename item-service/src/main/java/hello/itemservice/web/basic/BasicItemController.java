package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //final이 붙은 생성자 자동 생성
public class BasicItemController {

    @Autowired
    ItemRepository itemRepository;

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    /**
     * 상품 상세 보기
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * 상품 등록 Form --> Get
     * @return
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * 상품 등록 처리 - @RequestParam
     */
    /*@PostMapping("/add")
    public String addItemV1(@RequestParam(name = "itemName") String itemName,
                            @RequestParam(name = "price") int price,
                            @RequestParam(name = "quantity") Integer quantity,
                            Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }*/

    /**
     * 상품 등록 처리 - ModelAttribute
     * @ModelAttribute("item") --> model.addAttribute("item", item); 코드가 자동으로 추가됨
     * 1. 요청 파라미터 처리
     * 2. Model 추가
     */
    /*@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        //model.addAttribute("item", item); --> ("item") 속성값인 item을 통해 값을 전달함
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { //Item -> item으로 자동으로 model에 추가됨
        itemRepository.save(item);
        return "basic/item";
    }*/

    @PostMapping("/add")
    public String addItemV4(Item item) { //@ModelAttribute 생략 가능
        // String, Integer 등을 제외한 나머지는 ModelAttribute 가 적용됨!
        itemRepository.save(item);
        return "basic/item";
    }

    //@RequestParam(name = "itemId") String itemId ...
    // -> @ModelAttribute("itemId") Item item
    // -> @ModelAttribute Item item
    // -> Item item

    /**
     * 상품 정보 수정
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit") //상품 수정 폼
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit") //상품 수정 처리
    public String editItem(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; //수정 후 리다이렉트로 상품 상세 페이지로 이동
        //리다이렉트를 통해 url이 간단해짐.
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 100000, 10));
        itemRepository.save(new Item("itemB", 200000, 20));
    }
}
