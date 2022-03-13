package common;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author unknown100name
 * @since 2022.03.11
 * @description 常量保存
 */
public class ConstUtil {

    // ---------------------------------- HTTP 请求 ---------------------------------- //

    public static final String BACKEND_HOST = "http://127.0.0.1:8081/e-commerce/";
    public static final String RECOMMEND_HOST = "http://127.0.0.1:8082/e-commerce/";

    public static final String RECOMMEND_CONTROLLER = "recommend/";

    public static final String GET_RECOMMEND_CATEGORY = "getRecommendCategory";
    public static final String SAVE_ACTIVITY_USER = "saveActivity";
    public static final String REGISTER_NEW_USER = "registerNewUser";

    // ---------------------------------- Recommend 算法配置 ---------------------------------- //

    /**
     * 最小计算用户个数
     */
    public static final Integer MIN_SIMILARITY_USER_SIZE = 2;

    /**
     * 获取相似度最高的 N 个用户
     */
    public static final Integer DEFAULT_USER_FIND_WHEN_CAL_SIMILARITY = 1;

    // ---------------------------------- RecommendConsumer 线程池配置 ---------------------------------- //

    /**
     * Name
     */
    public static final String RECOMMEND_CONSUMER_NAME = "recommend-consumer-";

    /**
     * Core Poll Size
     */
    public static final Integer RECOMMEND_CONSUMER_CORE_POOL_SIZE = 8;

    /**
     * MaxPoolSize
     */
    public static final Integer RECOMMEND_CONSUMER_MAX_POOL_SIZE = 10;

    /**
     * Capacity Size
     */
    public static final Integer RECOMMEND_CONSUMER_CAPACITY_SIZE = 10000;

    /**
     * Active Time
     */
    public static final Integer RECOMMEND_CONSUMER_ACTIVE_TIME = 60;

    /**
     * Reject Policy
     */
    public static final RejectedExecutionHandler RECOMMEND_CONSUMER_REJECT_POLICY = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * shutdown wait?
     */
    public static final Boolean RECOMMEND_SHUTDOWN_POLICY = true;

    /**
     * 返回的最慢时间
     */
    public static final Integer RECOMMEND_CONSUMER_RETURN_TIME = 3000;

    /**
     * 线程 KILL 最慢时间
     */
    public static final Integer RECOMMEND_CONSUMER_MAX_TIME = 3;

}
