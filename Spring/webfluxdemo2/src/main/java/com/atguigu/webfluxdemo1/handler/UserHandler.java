package com.atguigu.webfluxdemo1.handler;

import com.atguigu.webfluxdemo1.entity.User;
import com.atguigu.webfluxdemo1.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    //根据id查询
    public Mono<ServerResponse> getUserById(ServerRequest request){
        //获取id值
        int userId=Integer.valueOf(request.pathVariable("id"));
        //调用service方法得到数据
        Mono<User> userMono=this.userService.getUserById(userId);
        //把userMono进行转换返回
        //使用Reactor操作符flatMap
        return userMono.flatMap(person->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject()))
    }

    //查询所有

    //添加

}
