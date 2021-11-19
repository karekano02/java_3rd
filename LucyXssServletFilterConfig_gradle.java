/**
 * fileName       : LucyXssServletFilterConfig
 * author         : leechangjoo
 * date           : 2021-10-25
 * description    :
 * =============================================================
 * DATE             AUTHOR          NOTE
 * -------------------------------------------------------------
 * 2021-10-25          leechangjoo         최초생성
 **/
public class LucyXssServletFilterConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new XssEscapeServletFilter());
        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
}
