package org.unknown100name.ecommercerecommend.service.impl;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.unknown100name.ecommercerecommend.util.RecommendGetTask;
import org.unknown100name.ecommercerecommend.service.RecommendService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

import static common.ConstUtil.RECOMMEND_CONSUMER_RETURN_TIME;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource(name = "RecommendConsumer")
    private ThreadPoolTaskExecutor recommendConsumer;

    @Override
    public List<Long> getRecommendCategoryTwoId(Long userId) {

        // 提交任务
        FutureTask<List<Long>> recommendGetTask = new FutureTask<>(new RecommendGetTask(userId));
        recommendConsumer.submit(recommendGetTask);

        // 不使用 get 是要求线程一直处理， 但我只在固定时间返回
        long startTime = System.currentTimeMillis();
        while(true){
            if (recommendGetTask.isDone()){
                try {
                    return recommendGetTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if (System.currentTimeMillis() - startTime > RECOMMEND_CONSUMER_RETURN_TIME){
                return null;
            }
        }
    }
}
