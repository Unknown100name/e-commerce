package org.unknown100name.ecommercerecommend.util;

import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static common.ConstUtil.*;

/**
 * @author unknown100name
 * @description 商品推荐实现
 * @since 2022/1/2
 */
public class RecommendUtils {
    
    /**
     * 分析用户行为并进行推荐类目
     * @param userActivityList 用户的购买行为列表
     * @return 组装好的用户的购买行为的map集合
     *         key: 用户 ID
     *         value: Map
     *                key: 二级品类 ID
     *                value: 点击次数
     */
    public static ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> assembleUserBehavior(List<UserActivity> userActivityList) {
        ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> resultMap = new ConcurrentHashMap<>();
        // 遍历查询到的用户点击行为数据
        for (UserActivity userActivity : userActivityList) {

            Long userId = userActivity.getUserId();
            Long categoryTwoId = userActivity.getCategoryTwoId();
            Long hits = userActivity.getHits();
            
            // 更新 resultMap
            ConcurrentHashMap<Long, Long> categoryTwoMapForPerson =
                    Optional.ofNullable(resultMap.get(userId)).orElse(new ConcurrentHashMap<>());
            categoryTwoMapForPerson.put(categoryTwoId, hits);
            resultMap.put(userId, categoryTwoMapForPerson);
        }

        return resultMap;
    }
    
    /**
     * 计算用户与用户之间的相似性，返回计算出的用户与用户之间的相似度对象
     * @param userId 作为基础的用户 Id
     * @param activityMap 用户对各个二级类目的点击行为的一个map集合
     * @return 计算出的用户与用户之间的相似度的对象存储形式
     */
    public static List<UserSimilarity> calculateSimilarityUser(Long userId, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> activityMap) {

        // 用户之间的相似度对集合
        List<UserSimilarity> similarityList = new ArrayList<>();

        List<Long> userIdList = new ArrayList<>(activityMap.keySet());
        
        // 小于两个说明当前map集合中只有一个map集合的购买行为，或者一个都没有，直接返回
        if (userIdList.size() < MIN_SIMILARITY_USER_SIZE) {
            return similarityList;
        }
        
        // 计算所有的用户之间的相似度对
        for (Long refUserId : userIdList) {
            // 分别获取两个用户对每个二级类目的点击量
            ConcurrentHashMap<Long, Long> userCategoryTwoMap = activityMap.get(userId);
            ConcurrentHashMap<Long, Long> userRefCategoryTwoMap = activityMap.get(refUserId);

            // 获取两个map中二级类目id的集合
            Set<Long> key1Set = userCategoryTwoMap.keySet();
            Set<Long> key2Set = userRefCategoryTwoMap.keySet();
            Iterator<Long> it1 = key1Set.iterator();
            Iterator<Long> it2 = key2Set.iterator();

            // 两用户之间的相似度
            double similarity = 0.0;
            // 余弦相似度公式中的分子
            double molecule = 0.0;
            // 余弦相似度公式中的分母
            double denominator = 1.0;
            // 余弦相似度公式中分母根号下的两个向量的模的值
            double vector1 = 0.0;
            double vector2 = 0.0;

            while (it1.hasNext() && it2.hasNext()) {
                Long it1Id = it1.next();
                Long it2Id = it2.next();
                // 获取二级类目对应的点击次数
                Long hits1 = userCategoryTwoMap.get(it1Id);
                Long hits2 = userRefCategoryTwoMap.get(it2Id);
                // 累加分子
                molecule += hits1 * hits2;
                // 累加分母中的两个向量的模
                vector1 += Math.pow(hits1, 2);
                vector2 += Math.pow(hits2, 2);
            }
            // 计算分母
            denominator = Math.sqrt(vector1) * Math.sqrt(vector2);
            // 计算整体相似度
            similarity = molecule / denominator;

            // 创建用户相似度对对象
            UserSimilarity userSimilarity = new UserSimilarity();
            userSimilarity.setUserId(userId);
            userSimilarity.setUserRefId(refUserId);
            userSimilarity.setSimilarity(similarity);
            // 将计算出的用户以及用户之间的相似度对象存入list集合
            similarityList.add(userSimilarity);
        }

        return similarityList;
    }

    /**
     * 到similarUserList中的用户访问的二级类目中查找userId不经常点击的二级类目中获得被推荐的类目id
     * @param userId 被推荐商品的用户id
     * @param similarityUserList 与userId相似的用户集合
     * @param userActivityList 与userId相似的用户的浏览行为
     * @return 可以推荐给userId的二级类目id列表
     */
    public static List<Long> getRecommendCategoryTwo(Long userId, List<UserActivity> userActivityList, List<Long> similarityUserList, List<UserActivity> similarityUserActivityList) {
        List<Long> recommendItemList = new ArrayList<>();

        Map<Long, List<UserActivity>> userActivityMap = new HashMap<>(similarityUserActivityList.size());
        similarityUserActivityList.forEach(
            userActivity ->{
                if(!userActivityMap.containsKey(userActivity.getUserId())){
                    userActivityMap.put(userActivity.getUserId(), new ArrayList<>());
                }
                userActivityMap.get(userActivity.getUserId()).add(userActivity);
            }
        );
        
        // 对userId的浏览行为按照二级类目id排个序，方便后续与推荐的用户的浏览行为中的二级类目的点击次数直接相减，避免时间复杂度为O(n2)
        userActivityList.sort(Comparator.comparing(UserActivity::getCategoryTwoId));

        // 1.从与useId浏览行为相似的每个用户中找出一个推荐的二级类目
        for (Long refId : similarityUserList) {
            // 计算当前用户所点击的二级类目次数与被推荐的用户所点击的二级类目的次数的差值
            // 找到当前这个用户的浏览行为
            List<UserActivity> currentUserActivity = userActivityMap.get(refId);

            // 排序，同上述理由
            currentUserActivity.sort(Comparator.comparing(UserActivity::getCategoryTwoId));
            
            // 记录差值最大的二级类目的id
            long maxCategoryTwo = 0L;
            
            // 记录最大的差值
            double maxDifference = 0.0;
            for (int i = 0; i < currentUserActivity.size(); i++) {
                // 求出点击量差值最大的二级类目，即为要推荐的类目
                double difference = Math.abs(currentUserActivity.get(i).getHits() - userActivityList.get(i).getHits());
                if (difference > maxDifference) {
                    maxDifference = difference;
                    maxCategoryTwo = currentUserActivity.get(i).getCategoryTwoId();
                }
            }
            recommendItemList.add(maxCategoryTwo);
        }
        return recommendItemList;
    }
    
}
