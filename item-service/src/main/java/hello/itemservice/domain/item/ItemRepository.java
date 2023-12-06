package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //동시에 여러 쓰레드에서 접근하면 hashMap을 사용하면 안됨 -> ConcurrentHashMap 사용
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
        //store.values()를 Collection 자체로 반환해도 되지만 변경될 가능성도 있기 때문에 ArrayList로 감싸 안전하게 반환!
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //DTO -> 일단 Controller 단계에서 DTO를 적용해 Domain의 노출을 막으려함.
    //DTO 사용 이유: 클라이언트에게 도메인 구조가 노출될 수 있다. 불필요한 데이터까지 넘어갈 수 있다.

    public void clearStore() { //test를 위함
        store.clear();
    }
}
