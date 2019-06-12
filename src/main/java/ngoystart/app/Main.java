package ngoystart.app;

import ngoy.Ngoy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/*")
public class Main implements InitializingBean {

    private Ngoy<AppComponent> ngoy;

    @Override
    public void afterPropertiesSet() throws Exception {
        createApp();
    }

    private void createApp() {
        ngoy = Ngoy.app(AppComponent.class)
                .translateBundle("messages")
                .build();
    }

    @GetMapping()
    public void home(HttpServletResponse response) throws Exception {
        // re-create while developing to have changes picked-up
//		createApp();
        ngoy.render(response.getOutputStream());
    }
}