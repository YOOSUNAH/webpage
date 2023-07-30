package webpage.demo.member.domain;

import lombok.Getter;

@Getter
public class Member {

    private String username;
    private String nickname;
    private String password;

    public Member(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}
