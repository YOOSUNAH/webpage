package webpage.demo.member;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>();

    public static Member save(Member member) {
        member.getName();
        return member;
    }

//    public void saveAll(List<Member> member) {
//        for (Member member : member) {
//            store.put(member.getName(), member);
//        }
//    }
}

