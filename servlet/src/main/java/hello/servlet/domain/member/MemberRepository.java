package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
* */
public class MemberRepository { //저장소

    private static Map<Long , Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository () { //싱글톤 패턴이기 때문에 private로 생성자를 막아서 아무나 생성하지 못 하도록 한다.
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() { //stort 자체를 보호하기 위해
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
