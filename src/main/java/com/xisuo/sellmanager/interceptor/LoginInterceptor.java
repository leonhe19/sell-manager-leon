package com.xisuo.sellmanager.interceptor;

import com.xisuo.sellmanager.constant.Constant;
import com.xisuo.sellmanager.entity.User;
import com.xisuo.sellmanager.service.UserService;
import com.xisuo.sellmanager.utils.CacheUtils;
import com.xisuo.sellmanager.utils.CookieUtil;
import com.xisuo.sellmanager.utils.DESUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zk
 * @Description: 登录的过滤器
 * @date 2021-07-15 15:06
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Autowired
    @Qualifier("userService")
    private UserService userService;


    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //使用Cookie作为是否登录的判断依据
        String value = CookieUtil.getCookieValue(request, Constant.COOKIE_NAME);
        String id = DESUtil.decrypt(value);
        if (StringUtils.isBlank(value) || StringUtils.isBlank(id)) {
            response.sendRedirect("/");
            return false;
        }
        Object o = CacheUtils.get(Constant.USER_CACHE_PREFIX + id);
        User user = null;
        if (o != null) {
            user = (User) o;
        } else {
            user = (User) CacheUtils.get(Constant.USER_CACHE_PREFIX + id, () -> {
                logger.info("拦截器缓存中没有用户{} 的信息,去数据库查询了", id);
                return userService.getUserDetail(Long.parseLong(id));
            });
        }
        if (user == null) {
            response.sendRedirect("/");
            return false;
        }
        CacheUtils.put(Constant.USER_CACHE_PREFIX + (user.getId()), user);
        UserContext.setUser(user);
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
