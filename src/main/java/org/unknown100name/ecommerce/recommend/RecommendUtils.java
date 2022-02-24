package org.unknown100name.ecommerce.recommend;

import org.unknown100name.ecommerce.pojo.entity.Item;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserSimilarity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author unknown100name
 * @description 商品推荐实现
 * @since 2022/1/2
 */
public class RecommendUtils {

    public static final Integer MIN_SIMILARITY_USER_SIZE = 2;
    
    /**
     * 更新用户的购买行为，插入数据库中
     * @param userId 用户id
     * @param itemId 商品id
     * @return true则更新成功，false则更新失败
     */

    public static boolean updatePurchaseBehavior(Long userId, Long itemId) {
        boolean flag = false;
        return flag;
    }
    
    /**
     * 分析用户行为并进行推荐类目
     * @param userActiveList 用户的购买行为列表
     * @return 组装好的用户的购买行为的map集合
     *         key: 用户 ID
     *         value: Map
     *                key: 二级品类 ID
     *                value: 点击次数
     */
    public static ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> assembleUserBehavior(List<UserActivity> userActiveList) {
        ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> resultMap = new ConcurrentHashMap<>();
        // 遍历查询到的用户点击行为数据
        for (UserActivity userActive : userActiveList) {

            Long userId = userActive.getUserId();
            Long categoryTwoId = userActive.getCategoryTwoId();
            Long hits = userActive.getHits();
            
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
     * @param activeMap 用户对各个二级类目的购买行为的一个map集合
     * @return 计算出的用户与用户之间的相似度的对象存储形式
     */
    public static List<UserSimilarity> calculateSimilarityUser(ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> activeMap) {

        // 用户之间的相似度对集合
        List<UserSimilarity> similarityList = new ArrayList<>();

        List<Long> userIdList = new ArrayList<>(activeMap.keySet());
        
        // 小于两个说明当前map集合中只有一个map集合的购买行为，或者一个都没有，直接返回
        if (userIdList.size() < MIN_SIMILARITY_USER_SIZE) {
            return similarityList;
        }
        
        // 计算所有的用户之间的相似度对
        for (int i = 0; i < userIdList.size() - 1; i++) {
            for (int j = i + 1; j < userIdList.size(); j++) {
                // 分别获取两个用户对每个二级类目的点击量
                ConcurrentHashMap<Long, Long> userCategoryTwoMap = activeMap.get(userIdList.get(i));
                ConcurrentHashMap<Long, Long> userRefCategoryTwoMap = activeMap.get(userIdList.get(j));
                
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
                UserSimilarity userSimilarityDTO = new UserSimilarity();
                userSimilarityDTO.setUserId(userIdList.get(i));
                userSimilarityDTO.setUserRefId(userIdList.get(j));
                userSimilarityDTO.setSimilarity(similarity);
                // 将计算出的用户以及用户之间的相似度对象存入list集合
                similarityList.add(userSimilarityDTO);
            }
        }
        
        return similarityList;
    }
    
    /**
     * 找出与userId购买行为最相似的topN个用户
     * @param userId 需要参考的用户id
     * @param userSimilarityList 用户相似度列表
     * @param topN 与userId相似用户的数量
     * @return 与usereId最相似的topN个用户
     */
    public static List<Long> getSimilarityBetweenUsers(Long userId, List<UserSimilarity> userSimilarityList, Integer topN) {
        // 用来记录与userId相似度最高的前N个用户的id
        List<Long> similarityList = new ArrayList<>(topN);
        
        // 堆排序找出最高的前N个用户，建立小根堆，遍历的时候当前的这个相似度比堆顶元素大就剔掉堆顶的值，把这个数入堆(把小的都删除干净,所以要建立小根堆)
        PriorityQueue<UserSimilarity> minHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.getSimilarity() - o2.getSimilarity() > 0) {
                return 1;
            } else if (o1.getSimilarity() - o2.getSimilarity() == 0) {
                return 0;
            } else {
                return -1;
            }
        });
        
        for (UserSimilarity userSimilarity : userSimilarityList) {
            if (minHeap.size() < topN) {
                minHeap.offer(userSimilarity);
                System.out.println(minHeap.peek().getSimilarity());
            } else if (minHeap.peek().getSimilarity() < userSimilarity.getSimilarity()) {
                minHeap.poll();
                minHeap.offer(userSimilarity);
            }
        }
        // 把得到的最大的相似度的用户的id取出来(不要取它自己)
        for (UserSimilarity userSimilarity : minHeap) {
            similarityList.add(userSimilarity.getUserId().equals(userId) ? userSimilarity.getUserRefId() : userSimilarity.getUserId());
        }
        
        return similarityList;
    }

    /**
     * 到similarUserList中的用户访问的二级类目中查找userId不经常点击的二级类目中获得被推荐的类目id
     * @param userId 被推荐商品的用户id
     * @param similarUserList 用userId相似的用户集合
     * @param userActivities 所有用户的浏览行为
     * @return 可以推荐给userId的二级类目id列表
     */
    public static List<Long> getRecommendCategoryTwo(Long userId, List<Long> similarUserList, List<UserActivity> userActivities) {
        List<Long> recommendProductList = new ArrayList<Long>();
        
        // userId的浏览行为列表
        List<UserActivity> userIdActiveList = findUsersBrowsBehavior(userId, userActivities);
        
        // 对userId的浏览行为按照二级类目id排个序，方便后续与推荐的用户的浏览行为中的二级类目的点击次数直接相减，避免时间复杂度为O(n2)
        userIdActiveList.sort(Comparator.comparing(UserActivity::getCategoryTwoId));

        // 1.从与useId浏览行为相似的每个用户中找出一个推荐的二级类目
        for (Long refId : similarUserList) {
            // 计算当前用户所点击的二级类目次数与被推荐的用户所点击的二级类目的次数的差值
            // 找到当前这个用户的浏览行为
            List<UserActivity> currActiveList = findUsersBrowsBehavior(refId, userActivities);
            
            // 排序，同上述理由
            currActiveList.sort(Comparator.comparing(UserActivity::getCategoryTwoId));
            
            // 记录差值最大的二级类目的id
            long maxCategory2 = 0L;
            
            // 记录最大的差值
            double maxDifference = 0.0;
            for (int i = 0; i < currActiveList.size(); i++) {
                // 求出点击量差值最大的二级类目，即为要推荐的类目
                double difference = Math.abs(currActiveList.get(i).getHits() - userIdActiveList.get(i).getHits());
                if (difference > maxDifference) {
                    maxDifference = difference;
                    maxCategory2 = currActiveList.get(i).getCategoryTwoId();
                }
            }
            recommendProductList.add(maxCategory2);
        }
        return recommendProductList;
    }

    /**
     * 找到当前用户的浏览行为列表
     * @param userId 当前用户id
     * @param userActiveList 所有用户的浏览行为列表
     * @return 当前用户的浏览行为列表
     */
    private static List<UserActivity> findUsersBrowsBehavior(Long userId, List<UserActivity> userActiveList) {
        List<UserActivity> currActiveList = new ArrayList<UserActivity>();
        for (UserActivity userActiveDTO : userActiveList) {
            if (userActiveDTO.getUserId().equals(userId)) {
                currActiveList.add(userActiveDTO);
            }
        }
        return currActiveList;
    }

    /**
     * 找到当前商品列表中点击量最高的商品
     * @param productList 商品列表
     * @return 点击量最高的商品
     */
    public static Item findMaxHitsProduct(List<? extends Item> productList) {
        if (productList == null || productList.size() == 0) {
            return null;
        }
        // 记录当前最大的点击量
        Long maxHits = 0L;
        
        // 记录当前点击量最大的商品
        Item product = null;
        for (Item temp : productList) {
            if (temp.getHits() >= maxHits) {
                maxHits = temp.getHits(); 
                product = temp;
            }
        }
        return product;
    }
    
}
