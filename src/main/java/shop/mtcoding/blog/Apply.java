package shop.mtcoding.blog;

import lombok.Data;

@Data
public class Apply {
    private Integer id; // 지원 번호
    private String name; // 지원자명
    private Integer comId; // 회사아이디
    private String status; // 3개의 값 : 1. 지원중 2. 검토중 3. 승인 or 1. 합격 2. 불합격 -> 이런거 설정하는걸 도메인 설정이라고 함 (데이터에서 도메인의 범주는 비즈니스 범주라고 생각함) -> ApplyEnum을 넣으면 위에 주석이 다 필요없어짐

    public Apply(Integer id, String name, Integer comId, ApplyEnum status) {
        this.id = id;
        this.name = name;
        this.comId = comId;
        this.status = status.value;
    }
}
