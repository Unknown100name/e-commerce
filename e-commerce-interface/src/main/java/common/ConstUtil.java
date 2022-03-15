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
    public static final String SAVE_ACTIVITY = "saveActivity";
    public static final String REGISTER_NEW_USER = "registerNewUser";
    public static final String UPDATE_SIMILARITY = "updateSimilarity";

    // ---------------------------------- Redis Prefix 配置 ---------------------------------- //

    /**
     * 用户登录 Token 记录
     */
    public static final String REDIS_PREFIX_TOKEN = "token-";

    /**
     * 用户点击次数配置
     */
    public static final String REDIS_PREFIX_USER_CLICK = "uc-";

    /**
     * 用户计算相似度
     */
    public static final String REDIS_PREFIX_CAL_SIMILARITY = "cs-";

    // ---------------------------------- Recommend 算法配置 ---------------------------------- //

    public static final Integer MIN_SIMILARITY_RECAL_SIZE = 10;

    /**
     * 最小计算用户个数
     */
    public static final Integer MIN_SIMILARITY_USER_SIZE = 2;

    /**
     * 获取相似度最高的 N 个用户
     */
    public static final Integer DEFAULT_RECOMMEND_USER_NUMBER = 5;

    // ---------------------------------- HttpSender 线程池配置 ---------------------------------- //

    /**
     * Name
     */
    public static final String HTTP_SENDER_NAME = "http-sender-";

    /**
     * Core Poll Size
     */
    public static final Integer HTTP_SENDER_CORE_POOL_SIZE = 8;

    /**
     * MaxPoolSize
     */
    public static final Integer HTTP_SENDER_MAX_POOL_SIZE = 16;

    /**
     * Capacity Size
     */
    public static final Integer HTTP_SENDER_CAPACITY_SIZE = 100;

    /**
     * Active Time
     */
    public static final Integer HTTP_SENDER_ACTIVE_TIME = 10;

    /**
     * Reject Policy
     */
    public static final RejectedExecutionHandler HTTP_SENDER_REJECT_POLICY = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * shutdown wait?
     */
    public static final Boolean HTTP_SENDER_SHUTDOWN_POLICY = true;

    // ---------------------------------- SimilarityUpdater 线程池配置 ---------------------------------- //

    /**
     * Name
     */
    public static final String SIMILARITY_UPDATER_NAME = "similarity-updater-";

    /**
     * Core Poll Size
     */
    public static final Integer SIMILARITY_UPDATER_CORE_POOL_SIZE = 8;

    /**
     * MaxPoolSize
     */
    public static final Integer SIMILARITY_UPDATER_MAX_POOL_SIZE = 16;

    /**
     * Capacity Size
     */
    public static final Integer SIMILARITY_UPDATER_CAPACITY_SIZE = 10000;

    /**
     * Active Time
     */
    public static final Integer SIMILARITY_UPDATER_ACTIVE_TIME = 60;

    /**
     * Reject Policy
     */
    public static final RejectedExecutionHandler SIMILARITY_UPDATER_REJECT_POLICY = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * shutdown wait?
     */
    public static final Boolean SIMILARITY_UPDATER_SHUTDOWN_POLICY = true;

    // ---------------------------------- RecommendConsumer 线程池配置 ---------------------------------- //

    /**
     * Name
     */
    public static final String RECOMMEND_CONSUMER_NAME = "recommend-consumer-";

    /**
     * Core Poll Size
     */
    public static final Integer RECOMMEND_CONSUMER_CORE_POOL_SIZE = 6;

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
    public static final Integer RECOMMEND_CONSUMER_ACTIVE_TIME = 30;

    /**
     * Reject Policy
     */
    public static final RejectedExecutionHandler RECOMMEND_CONSUMER_REJECT_POLICY = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * shutdown wait?
     */
    public static final Boolean RECOMMEND_CONSUMER_SHUTDOWN_POLICY = true;

    /**
     * 返回的最慢时间
     */
    public static final Integer RECOMMEND_CONSUMER_RETURN_TIME = 3000;

    /**
     * 线程 KILL 最慢时间
     * 暂且未使用
     */
    public static final Integer RECOMMEND_CONSUMER_MAX_TIME = 10000;

}
