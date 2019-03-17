package com.gdl.schedule;

import com.gdl.schedule.config.Interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootConfiguration
public class MvcConfig implements WebMvcConfigurer {

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(getCustomFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.addInitParameter("paramName","paramValue");
//        registrationBean.setName("customFilter");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
//    @Bean(name="customFilter")
//    public CustomFilter getCustomFilter(){
//        return new CustomFilter();
//    }

//    以下WebMvcConfigurerAdapter 比较常用的重写接口
//    /** 解决跨域问题 **/
//    public void addCorsMappings(CorsRegistry registry) ;
//    /** 添加拦截器 **/
//    void addInterceptors(InterceptorRegistry registry);
//    /** 这里配置视图解析器 **/
//    void configureViewResolvers(ViewResolverRegistry registry);
//    /** 配置内容裁决的一些选项 **/
//    void configureContentNegotiation(ContentNegotiationConfigurer configurer);
//    /** 视图跳转控制器 **/
//    void addViewControllers(ViewControllerRegistry registry);
//    /** 静态资源处理 **/
//    void addResourceHandlers(ResourceHandlerRegistry registry);
//    /** 默认静态资源处理器 **/
//    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

    @Autowired
    private MyInterceptor myInterceptor;

    /**
     *
     * 功能描述:
     *  配置静态资源,避免静态资源请求被拦截
     * @auther: Tt(yehuawei)
     * @date:
     * @param:
     * @return:
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("/templates/**")
//                .addResourceLocations("classpath:/templates/");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        //注意content-path：ding是不用填写的
        //项目启动测试接口
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/");
//                //钉钉回调事件
//                .excludePathPatterns("/callback/**")
//                //检查验证码
//                .excludePathPatterns("/check_auth_code")
//                //发送验证码
//                .excludePathPatterns("/send_auth_code")
//                //获取用户基本信息
//                .excludePathPatterns("/get_user_base_info")
//                //获取用户详情
//                .excludePathPatterns("/get_user_detail_info");
    }

}
