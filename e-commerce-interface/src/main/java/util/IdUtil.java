package util;

/**
 * @author unknown100name
 * @description 雪花 ID 生成器
 * @since 2022/1/3
 */
public final class IdUtil {
    
    private IdUtil(){
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 起始的时间戳
     * 2022-01-01 00:00:00
     */
    private final static long START_STAMP = 1640966400000L;

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private final static long DATA_CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据中心ID(0~31)
     */
    private final static long DATA_CENTER_ID = 0L;
    /**
     * 工作机器ID(0~31)
     */
    private final static long MACHINE_ID = 0L;
    /**
     * 毫秒内序列(0~4095)
     */
    private static long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private static long lastStamp = -1L;

    /**
     * 产生下一个ID
     */
    public static synchronized long getId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                //阻塞到下一个毫秒,获得新的时间戳
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return (currStamp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | DATA_CENTER_ID << DATA_CENTER_LEFT       //数据中心部分
                | MACHINE_ID << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private static long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private static long getNewStamp() {
        return System.currentTimeMillis();
    }
}
