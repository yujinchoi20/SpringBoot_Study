package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();
    //model에 원하는 데이터를 넣어두고 사용한다.

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
