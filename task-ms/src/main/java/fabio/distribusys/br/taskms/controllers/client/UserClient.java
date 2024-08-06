package fabio.distribusys.br.taskms.controllers.client;

import fabio.distribusys.br.taskms.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-ms", url = "${user.service.url}", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/users/check-user/{id}")
    Boolean CheckUserExists(@PathVariable Long id);

}
