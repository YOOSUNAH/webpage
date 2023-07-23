package webpage.demo.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RestController
@Controller
@RequestMapping("/posts")
public class MappingClassController {

    // GET - 가져오다 ... 페이지 요청, 데이터 조회
    // POST - 보낸다 ... 자원 생성, 데이터를 입력한다
    // PUT - 넣는다 ... 데이터를 넣는것 -> 전체 모든 데이터를 주는 것
    // PATCH - 부분적으로 수정한다 ... 데이터를 넣는 것 -> 일부 데이터만 주는 것
    // DELETE - 삭제s

	@GetMapping("/write")
	public String write() {
		// return "get write";
		log.info("작성 페이지 포워딩 컨트롤러 탔다 러브!");
		return "addForm";
	}

	@GetMapping("")
	public String post() {
		return "post posts";
	}

    @PostMapping("")
    public String save(
		@RequestParam String category,
        @RequestParam String title,
        @RequestParam String content,
        @RequestParam String writerName
    ) {
		// log.info("카테고리 : {}", category);
		// log.info("제목 : {}", title);
		// log.info("내용 : {}", content);
		// log.info("작성자 : {}", writerName);

        return "love";
    }

    // @GetMapping("/{postId}")
    // public String findPost(@PathVariable String postId){
    //     return "get postId= " + postId;
	// }

}
