package webpage.demo.member.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private static final List<Member> members = new ArrayList<>();

    public Member save(Member member) {
        members.add(member);
        return member;
    }

    public List<Member> findAll() {
        return members;
    }

    public Member findByUsername(String username) {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }
}
