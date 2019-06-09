package com.example.web;

import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

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
        User user=restTemplate.getForObject(url,User.class);
        return user;
    }

}
