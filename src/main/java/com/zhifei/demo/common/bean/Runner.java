package com.zhifei.demo.common.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)//保证不同的 CommandLineRunner 的执行顺序
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("The Runner start to initialize....");
//        2018-08-22 15:06:29.549  INFO 15240 --- [
//        The Runner start to initialize....
//        2018-08-22 15:06:30.235  INFO 15240 --- [
//        24
//        2018-08-22 15:06:30. 282  INFO 15240 --- [

}
}
