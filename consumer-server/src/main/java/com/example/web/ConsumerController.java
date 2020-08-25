package com.example.web;

import com.example.feign.UserClient;
import com.example.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer/user")
@DefaultProperties(defaultFallback = "fallBackGlobal")
@Slf4j
public class ConsumerController {

    //-----------------------------------------------------------------------------
    /**
     * 最原始的方法，使用restTemplate来进行远程调用
     * 使用discoveryClient获得服务的地址
     */
    @Autowired
    @Qualifier(value = "default")
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable Integer id) {
        //根据服务id获取服务（list）
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
        //因为你默认去index=0的userService，所以默认是不会负载均衡的
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;
        log.info("discoveryClient url:{}", url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
    //-----------------------------------------------------------------------------


    //-----------------------------------------------------------------------------
    /**
     * 使用spring提供的LoadBalancerClient来获取服务
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/ribbon/{id}")
    public User queryByIdWithRibbon(@PathVariable Integer id) {
        //ribbon自动实现负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;
        log.info("url:{}", url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
    //-----------------------------------------------------------------------------


    //-----------------------------------------------------------------------------
    @Autowired
    @Qualifier(value = "balance")
    private RestTemplate restTemplate2;

    /*
     * @LoadBalanced(ribbon提供的注解) 自动拦截RestTemplate ，实现负载均衡
     * */
    @RequestMapping("/loadBalanced/{id}")
    public User queryByIdWithLoadBalanced(@PathVariable Integer id) {

        String url = "http://user-service/user/" + id;
        User user = restTemplate2.getForObject(url, User.class);
        return user;
    }
    //-----------------------------------------------------------------------------


    //Hystrix服务降级
    /**
     * @HystrixCommand 开启线程隔离、服务降级
     * fallbackMethod = "fallBack" 调用失败时候 调用的方法
     */
    //默认1秒 便超时
    @RequestMapping("/hystrix1/{id}")
    @HystrixCommand(
            fallbackMethod = "fallBack",
            commandProperties = {
                    //配置请求超时时长（应该统一配置多有的接口超时时长）
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public String queryByIdHystrix1(@PathVariable Integer id) {
        String url = "http://user-service/user/sleep/" + id;
        String user = restTemplate2.getForObject(url, String.class);
        return user;
    }


    //Hystrix服务降级
    //默认1秒 便超时
    @RequestMapping("/hystrix2/{id}")
    @HystrixCommand(
            fallbackMethod = "fallBack",
            commandProperties = {
                    //请求超时时长设置 默认1秒请求超时就降级
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),
                    //多少次请求开始统计 默认20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //休眠时间窗 也就是熔断后 多久重连服务 默认5000毫秒
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    //失败百分比 默认50%
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            })
    public String queryByIdHystrix2(@PathVariable Integer id) {
        if (id % 2 == 0) {
            throw new RuntimeException("手动控制");
        }
        String url = "http://user-service/user/" + id;
        String user = restTemplate2.getForObject(url, String.class);
        return user;
    }


    //返回值和参数列表必须完全一样
    //方法上的返回调用
    public String fallBack(Integer id) {
        return "服务器拥挤";
    }

    //返回值完全一样
    //类上的返回调用
    public String fallBackGlobal() {
        return "服务器拥挤class";
    }




    //---------------------------使用feign调用--------------------------
    @Autowired
    private UserClient userClient;

    @GetMapping("/feign/{id}")
    public User queryByFeignClient(@PathVariable Integer id) {
        User user = userClient.queryById(id);
        return user;
    }
    //---------------------------使用feign调用--------------------------


}
