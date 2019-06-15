package com.example.web;

import com.example.Feign.UserClient;
import com.example.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "fallBackClass")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "balance")
    private RestTemplate restTemplate2;

    @Autowired
    private DiscoveryClient discoveryClient;

//    @Autowired
//    private RibbonLoadBalancerClient ribbonLoadBalancerClient;

    @RequestMapping("{id}")
    public User queryById(@PathVariable Integer id){
        //根据服务id获取服务（list）
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
        ServiceInstance serviceInstance = instances.get(0);
        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;
        System.out.println(url);
        User user=restTemplate.getForObject(url,User.class);
        return user;
    }

//    @RequestMapping("/ribbon/{id}")
//    public User queryByIdWithRibbon(@PathVariable Integer id){
//        //ribbon自动实现负载均衡
//        ServiceInstance serviceInstance = ribbonLoadBalancerClient.choose("user-service");
//        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;
//        User user=restTemplate.getForObject(url,User.class);
//        return user;
//    }

    /*
    * @LoadBalanced 自动拦截RestTemplate ，实现负载均衡
    * */
    @RequestMapping("/loadBalanced/{id}")
    public User queryByIdWithLoadBalanced(@PathVariable Integer id){

        String url="http://user-service/user/"+id;
        User user=restTemplate2.getForObject(url,User.class);
        return user;
    }




    //Hystrix服务降级
    //默认1秒 便超时
    @RequestMapping("/hystrix/{id}")
//    @HystrixCommand(fallbackMethod = "fallBack")
//    @HystrixCommand(commandProperties = {
//
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "50000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String queryByIdHystrix(@PathVariable Integer id){
        if(id%2==0){
            throw new RuntimeException("手动控制");
        }
        String url="http://user-service/user/"+id;
        String user=restTemplate2.getForObject(url,String.class);
        return user;
    }


    //返回值和参数列表必须完全一样
    //方法上的返回调用
    public String fallBack(Integer id){

        return "服务器拥挤";
    }


    //返回值和参数列表必须完全一样
    //类上的返回调用
    public String fallBackClass(){

        return "服务器拥挤class";
    }





    @Autowired
    private UserClient userClient;


    @GetMapping("/feign/{id}")
    public User queryByFeignClient(@PathVariable Integer id){
        User user = userClient.queryById(id);

        return user;
    }








}
