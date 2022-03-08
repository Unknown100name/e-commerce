package org.unknown100name.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.unknown100name.ecommerce.dao.CategoryMapper;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.pojo.dto.CategoryDTO;
import org.unknown100name.ecommerce.pojo.entity.CategoryTwo;
import org.unknown100name.ecommerce.pojo.entity.InnerItem;
import org.unknown100name.ecommerce.pojo.entity.Item;
import org.unknown100name.ecommerce.pojo.vo.InnerItemCreateParam;
import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author unknown100name
 * @since 2022.03.07
 */
@SpringBootTest
public class DatabaseItemBackupTest {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private CategoryMapper categoryMapper;

    private final List<Item> itemList = new ArrayList<>();
    private final List<InnerItem> innerItemList = new ArrayList<>();
    private final Map<Long, CategoryTwo> categoryMap = new HashMap<>();

    private Integer nowId = 1;
    private Boolean isNext = false;


    @Test
    public void test(){
        List<CategoryDTO> categoryList = categoryMapper.getCategoryList();
        categoryList.forEach(categoryDTO ->
                categoryDTO.getCategoryTwoList().forEach(
                        categoryTwo -> categoryMap.put(categoryTwo.getCategoryTwoId(), categoryTwo)
                ));

        // 1-20

        addItem("国产白虾 万景北海鲜冻白虾",
                "广西北部湾出产，出口30年企业直供。无冰净重4斤，大规格50/60，体长11-15cm，盐冻工艺可单只拿取，新鲜活冻。北部湾位于中国南海，是一个美丽的海湾。\n",
                new String[]{"国产白虾50/60 2kg","国产白虾40/50 2kg","1kg国产白虾50/60"});

        addItem("【保温箱+冰袋发货】金葵日式裙带菜",
                "【保温箱+冰袋发货】金葵日式裙带菜500g开袋即食下饭菜海藻寿司中华海草沙拉海带梗丝酸甜口味芥末口味 酸甜味500g-冷冻存储",
                new String[]{"酸甜味500g-冷冻存储","芥末味500g-冷冻存储","酸甜味200g*2袋","芥末味200g*2袋"});

        addItem("南翔 上海灌汤小笼包",
                "中华老字号，皮嫩软弹，馅纯味鲜。",
                new String[]{"猪肉小笼包180g*3袋（30只）","黑猪肉小笼包180g*3袋（18只）"});

        addItem("云鼎轩 深海鳕鱼排310g 10个/盒 整块鳕鱼制作 煎油炸小吃 速冻生鲜食",
                "云鼎轩 深海鳕鱼排310g 10个/盒 整块鳕鱼制作 煎油炸小吃 速冻生鲜食品 半成品方便菜 海鲜制品",
                new String[]{"深海鳕鱼排310g【10枚装】","香酥虾排250g【8枚装】","海苔虾饼250g【8枚装】"});

        addItem("良品铺子 东方食力零食大礼包中国风过年囤货年货送礼猪肉脯鸭脖30袋休闲零食网红小吃送亲人礼品礼盒装600g",
                "【良品铺子·女神好礼】高颜好味低至9.9元！！人气美味，专区领神券300减200！！进店抢购！！",
                new String[]{"【12款纯肉礼盒】肉肉礼盒1450g","【16款纯肉礼盒】肉肉狂欢1770g","【8款纯肉礼盒】肉肉大派对1011g","【8荤7素国潮礼】食力满满2027g","【5款荤素】无肉不欢671g","【5款辣条】无辣不欢826g"});

        addItem("Orion 好丽友 休闲零食 薯片 非油炸 薯愿三连罐312g/组（104g*3薯片）（新老包装随机发货）",
                "3.7日茶里京东超级品牌日，一杯好茶在茶里，限时抢99-98大额优惠券立即抢购",
                new String[]{"五连罐520g","三连罐104g*3","香烤原味104g","红酒牛排104g","清新番茄104g","蜂蜜牛奶104g"});

        addItem("鲜菓篮水果礼盒生鲜水果年货礼盒 绿心猕猴桃30粒单果70-90g 新鲜水果生鲜",
                "【百亿补贴】19.8元抢5斤耙耙柑大果彩箱装，果园新鲜采摘，甜蜜多汁细嫩",
                new String[]{"丑橘不知火12枚大果水果礼盒橘子水果","春见耙耙柑橘子12粒大果精美水果礼盒","绿心猕猴桃20粒单果110-150g","绿心猕猴桃30粒单果70-90g"});

        addItem("柿子饼500g圆柿饼 平柿新鲜柿子饼 霜降柿饼 500g",
                "",
                new String[]{"柿子饼"});

        addItem("英国进口 麦维他（Mcvitie's）燕麦酥性消化饼干 300g 早餐代餐饼干 春季小食 进口休闲零食",
                "3.7日茶里京东超级品牌日，一杯好茶在茶里，限时抢99-98大额优惠券立即抢购",
                new String[]{"燕麦300g","燕麦300g*2"});

        addItem("唯他可可（Vita Coco）椰子水天然原味清甜椰汁水0脂低卡进口饮料整箱 NFC 椰青果汁330ml*12瓶",
                "【3.8节囤好物】唯他可可椰子水0脂低卡、含丰富电解质，每满200-20优惠券！抢购中~戳我",
                new String[]{"原味330ml*12","原味500ml*6","菠萝味330ml*12","醇香风味330ml*12","原味罐装310ml*12","原味1L*4"});

        addItem("食月冬初 恩施糍粑手工糯米糍粑火锅食材小吃农家传统糕点特产驴打滚年糕美食红糖糍粑 12个3斤左右纯糯米糍粑+红糖豆粉",
                "土家腊后腿肉，限量抢购，3000份售空即止，先到先得川味烟熏腊肉",
                new String[]{"12个3斤左右纯糯米糍粑+红糖豆粉","8个2斤左右纯糯米糍粑","4个1斤左右纯糯米糍粑","20个5斤左右纯糯米糍粑+红糖豆粉"});

        addItem("【广丰助农馆】饶情南昌拌粉 7种配料美味小吃江西米粉干米线螺丝粉土特产炒粉方便食品 南昌拌粉206g*3盒",
                "里面小料都有足足7包！而且它的粉是螺蛳粉的米粉，很有弹性！很爽滑！购买更多美食请点击",
                new String[]{"南昌拌粉206g*5盒","米粉干4斤","南昌拌粉206g*3盒","红薯粉丝500g","红薯粉丝500g*2袋","江西米粉1斤装","南昌拌粉206g*1盒【每盒里面7包小料】"});

        addItem("雀巢（Nestle）醇品 速溶 黑咖啡 无蔗糖 冲调饮品 焕新升级 盒装48包*1.8克（新老包装随机发货）",
                "3.7日茶里京东超级品牌日，一杯好茶在茶里，限时抢99-98大额优惠券立即抢购",
                new String[]{"醇品黑咖48包","醇品黑咖20包","醇品黑咖50g","醇品黑咖100g","醇品黑咖200g","醇品礼盒"});

        addItem("蒙牛纯甄 常温风味酸牛奶 送礼推荐 200g*24 纯正生牛乳发酵 礼盒装(新老包装随机发货)",
                "3.7日茶里京东超级品牌日，一杯好茶在茶里，限时抢99-98大额优惠券立即抢购",
                new String[]{"【口碑爆款】纯甄原味24盒","【跳跳糖馋酸奶】6杯","【蓝莓果粒】纯甄风味酸奶10盒","【王一博推荐】纯甄黄桃藜麦口味","【草莓果粒】纯甄风味酸奶10盒","【黄桃燕麦】纯甄风味酸奶10盒","【新品上市】纯甄芒芒西柚*10"});

        addItem("大午五香驴肉175g*5袋常温熟食真空老汤卤煮酱驴肉正宗熟驴肉火烧河北特产",
                "京东牛羊肉，全球好味，精挑细选，爆款直降，部分3件7折。好物稀缺，点击速买》》",
                new String[]{"【无礼盒】五香驴肉：175g*5袋","【无礼盒】五香驴肉：200g*4袋","【有礼盒】五香礼盒：175g*4袋","【有礼盒】御品礼盒：175g*6袋","【有礼盒】珍品礼盒：175g*4袋","【有礼盒】福瑞礼盒：175g*6袋"});

        addItem("梅花鹿 桃李旺 梅花鹿肉 现杀新鲜梅花鹿排 新鲜鹿肉 成年梅花鹿生鲜肉类 【2斤】梅花鹿肉",
                "新鲜现杀品质，有鹿肉，鹿腿，鹿排，鹿头，顺丰发出！",
                new String[]{"【2斤】梅花鹿肉","【5斤】鸵鸟肉","【约10斤】整只梅花鹿腿","【3斤】梅花鹿排","【干鹿鞭/130克】整根礼盒装","【500ml/瓶】纯正鹿茸血酒","【干鹿鞭/80克】整根礼盒装","梅花鹿内脏","【4~5斤】整个母鹿头","【3斤】鸵鸟掌+鸵鸟筋","【5斤】鸵鸟掌+鸵鸟筋","【9~10斤】整个公鹿头"});

        addItem("【贵州珍酒 酒厂自营】珍品整箱53度酱香型白酒500ml*6 纯粮食大曲坤沙 陈年窖藏酒 口粮酒",
                "【三八节】满额享养生酒、燕窝、鱼胶礼盒与周六福金饰；新人可0元入会领20元优惠券可叠加首购礼金享折上折；新品预售末波倒计时，限时享优惠；更多优惠活动请点击查看",
                new String[]{"一件"});

        addItem("卡慕（CAMUS）干邑白兰地 皇冠GMC700ml 法国进口洋酒 礼品",
                "",
                new String[]{"皇冠GMC 700ml","布特妮VSOP 1L","卡慕XO 700ml"});

        addItem("海天 酱油 生抽酱油 1.9L 中华老字号",
                "元气女神厨房，粮油调味&小家电联合来袭！部分满300减30，厨房好物尽在这里速戳》》",
                new String[]{"鲜味生抽1.9L*6【整箱装】","凉拌生抽500ml","薄盐生抽500ml","鲜味生抽500ml","头道酱油250ml","生抽酱油1.9L"});

        addItem("金龙鱼 东北大米 盘锦大米 5kg 蟹稻共生 大米 粳米 香米 十斤",
                "元气女神厨房，粮油调味&小家电联合来袭！部分满300减30，厨房好物尽在这里速戳》》",
                new String[]{"十斤"});

        addItem("绿食者 新鲜蔬菜沙拉组合3斤 西餐色拉沙拉生菜轻食健身食材配菜",
                "",
                new String[]{"3斤"});

        addItem("旭耕 有机蔬菜8斤套餐 家庭体验当季时令8种净菜 有机认证供港资质 节日礼盒配送 8斤自选套餐",
                "",
                new String[]{"8斤"});

        addItem("天福茗茶 瓷罐大红袍 武夷山特产茶 福建名茶正宗乌龙茶礼盒200g 茶叶送礼长辈",
                "",
                new String[]{"200g","400g","800g"});

        addItem("【买1送1共1斤 不好喝全额退款】修哲无农残茶叶台湾高山茶金萱乌龙 赛过大红袍铁观音 带奶香512g",
                "不好喝全额退款\\买一送一到手共一斤",
                new String[]{"1斤"});

        addItem("思维导图：文言文满分学习法（涵盖初中生必学文言文35篇，七至九年级通用）",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"思维导图学习法记忆力","思维导图单词记忆法","思维导图作文提升宝典套装3册","思维导图从入门到精通套装3册","思维导图学习法应试力","思维导图学习法学习力","思维导图学习法笔记力","思维导图学习法阅读力","思维导图学习法创造力"});

        addItem("给孩子的语文三书全3册：语文趣味 文章做法 读和写（继刘薰宇《给孩子的数学三书》后的又一部经典",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"给孩子的数学三书","给孩子的语文三书","数学思维秘籍（全10册）","课里名人课外读","给孩子的科学三书","小尼莫梦乡历险记","给孩子读的中国古诗词","中国历代圣贤故事集","给孩子的数学课（全3册）","给孩子的几何四书","写给孩子看的趣味汉字（全6册）"});

        addItem("热爱 天霸BJD娃娃妆造作品集",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"热爱 天霸BJD娃娃妆造","哇！黏土 小小的萌系精灵","巧手姐姐的黏土童话书","巧手姐姐的黏土魔法书","BJD娃娃化妆术全解析","米米酱的黏土手办教程","黏土花 留下花开最美时","黏土花制作完全教程","手工饰品定制技法大全","簪花录 热缩片唯美古风饰品制作","人形玩偶妆容 服装 饰品道具制作"});

        addItem("决胜高中三年关键期（京东专享几何网格本）",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"决胜高中三年关键期"});

        addItem("余华：活着（精装版，余华代表作，易烊千玺推荐阅读 2021新版，随机赠珍藏复刻手稿） ",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"活着（定本·2021版）","余华长篇小说精选集","文城"});

        addItem("围城（精装）",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"围城（精装）","洗澡（杨绛作品 精装）","洗澡（杨绛作品 平装）"});

        addItem("【签名版】遇见幸福 喻丰 著 武汉大学积极心理学知名教授 恋爱心理学课堂爆火 窗户上都挂满人 读懂积极心理学 中信出版社",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"活出心花怒放的人生","强大内心的自我对话","喻丰 遇见幸福"});

        addItem("船山遗书（套装全15册）",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"船山遗书（全15册）","曾文正公家书（全2册）","鲁迅全集 精装（全20册）"});

        addItem("中国传统色1+2（套装共2册）",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"中国传统色1+2","中国传统色 故宫里的色彩美学","中国传统色+日本传统色","中国传统色：色彩通识100讲","中国传统色日历"});

        addItem("【正版包邮】中国传统色 故宫里的色彩美学 郭浩李健明著 黄晓明力荐 中国色彩文化传承",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"1本"});

        addItem("影响力 2021年全新升级版 限量作者签章版?影响力教父罗伯特·西奥迪尼新作",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购查看>",
                new String[]{"影响力（2021全新升级版）","影响力（老版售罄）","签章版 （影响力&福格套装）"});

        addItem("底层逻辑：看清这个世界的底牌 刘润最新力作 2021刘润年度演讲【进化的力量】主推",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购查看>",
                new String[]{"底层逻辑","刘润三部曲","刘润商业书籍套装共2册"});

        addItem("血战长津湖",
                "朝鲜战争拐点之战，足以改变历史进程！",
                new String[]{"1本"});

        addItem("李夕夜，不再沉默",
                "韩国版《房思琪的初恋乐园》女性主义小说代表作；韩国文坛Z高荣誉“万海文学奖”获奖作品；亲爱的女孩，请别再沉默！引发万千女性震撼共鸣！",
                new String[]{"1本"});

        addItem("商界评论",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购查看>",
                new String[]{"（2019年4月号）","预定（2019年5月号）","预定（2019年5月号）"});

        addItem("中国国家地理",
                "",
                new String[]{"（2018年10月号）(本期超值加厚版)","预定（2018年11月号）","预定（2018年12月号）"});

        // 21-40

        addItem("京东专享】植物学家的野外考察手记 原始森林和洞穴的神奇之旅",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"1本"});

        addItem("给91件未来事物写历史[知乎出品]",
                "奔跑吧！新学期！图书每满100减50,满减叠券享400减250,团购电话4006186622抢购",
                new String[]{"1本"});

        addItem("汽车音乐发烧：金耳朵（5CD）（京东专卖）",
                "赵鹏，区瑞强，龚玥，童丽，刘紫玲 演唱，三木刚 曲",
                new String[]{"1件"});

        addItem("世界最美轻音乐集（限量珍藏版）（10CD）",
                "张璐，肯尼基（KENNY G），宁林，肖乘光，谭炎健 ... 演唱，久石让，萨拉萨蒂，勃拉姆斯，詹姆斯·霍纳 等 曲",
                new String[]{"1件"});

        addItem("微课网高一高二生物必修选修高中在线辅导高考视频课程教程家教学习复习网课课件 0元试听 选修3（部分） 人教版",
                "",
                new String[]{"必修1","必修2","必修3","选修1（部分）","选修3（部分）","选择性必修1"});

        addItem("万门大学 轻松学JavaScript 零基础网课教学视频 在线课程培训试听 java语言程序设置",
                "网络课程为虚拟产品，无实际物流，需咨询客服获取课程激活码，课程有效期为15天，请尽快安排听课。",
                new String[]{"15天"});

        addItem("萌翻了，米尼诺：给孩子的科学启蒙推拉书（套装共4册） 去冲浪、魔法种子、下雨了、上月球 [0-3岁]",
                "低音出品|专为宝宝设计的科学启蒙绘本！4大主题，34种推拉设计，锻炼精细动作和想象力。　团购电话4006186622",
                new String[]{"给孩子的科学启蒙推拉书","萌翻了，米尼诺. 下雨了","萌翻了，米尼诺. 去冲浪","萌翻了，米尼诺. 上月球","萌翻了，米尼诺. 魔法种子","就像我爸爸·妈妈","就像我爸爸","就像我妈妈"});

        addItem("跟我走 3-6岁（启发出品） [2-6岁]",
                "100册以上团购优惠联系电话4006186622",
                new String[]{"（新书）跟我走 儿童自我保护","（二手）跟我走 儿童自我保护"});

        addItem("飞利浦（PHILIPS）CSS5235 音响 音箱 家庭影院 随需环绕 无线蓝牙 电视音响 一秒变影院",
                "",
                new String[]{"10.5kg"});

        addItem("JBL BAR5.1电视回音壁音响家庭影院5.1声道套装音箱客厅无线环绕Soundbar条形蓝牙低音炮",
                "【8日巅峰开抢】爆品直降1600!抢购价:4999!5.1声道,510W强劲功率，HIFI级音效！更多热销电视音响》查看>",
                new String[]{"Bar 5.1(真5.1声道 无线环绕）","STV105升级版(电视好伴侣)","JBL Cinema SB120(新品)","SB170(杜比全景声)","SB190(杜比全景声)","K歌套装(Bar5.1＋点唱机)"});

        addItem("苏泊尔(SUPOR) 抽油烟机 18立方大吸力 经典侧吸式吸油烟机家用 黑晶面板 DJ13 油烟机",
                "【家电3·8节】限时开抢！暖心到手699元！限量100台【下单赠火鸡筷子篓】【18立方近吸速排！黑晶面板一拭即净！琴键易操作】》新7字烟灶套装查看>",
                new String[]{"DJ13+5.0KW不锈钢灶 液化气","DJ13+5.0KW不锈钢灶 天然气","DJ13+5.0KW猛火灶 液化气","DJ13+5.0KW猛火灶 天然气","【经典侧吸】DJ13 单烟机"});

        addItem("美的（Midea） 抽油烟机 侧吸式 17立方大吸力立体环吸多重油烟分离技术家用吸油烟机排烟机抽烟机J30",
                "【家电3·8节】火热抢购中！即刻下单抢惊爆价1099！享免费上门测量！17立方立体环吸！国民品质！库存有限！抢到赚到！》新品抢华为P50查看>",
                new String[]{"油烟机+5.0KW液化气灶","油烟机+5.0KW天燃气灶","油烟机+4.5KW液化气灶","油烟机+4.5KW天燃气灶","【单独购买烟机】"});

        addItem("小天鹅(LittleSwan)10公斤变频滚筒洗衣机全自动 京东小家 纯净系列 健康除螨洗 BLDC变频TG100VT096WDG-Y1T",
                "【领每满200减20元券】今日限时到手不高于1899元 【晒单赠留香去渍洗衣液！赠电机十年包修&3期免息 【咨询客服领优惠！整机三年包修&价保30天免费送装】戳查看>",
                new String[]{"【爆款银离子除菌套装】热泵柔烘","【彩屏巡航除菌】10KG智投款滚筒","【彩屏巡航除菌】10KG智投款洗烘","【巡航除菌】10KG智投款钛色滚筒","【巡航除菌】10KG智投款蒸汽洗烘","【月销千万】10KG银离子除菌滚筒","【金榜认证】10KG银离子纤维柔烘","【镇店推荐】10KG健康除螨滚筒款","【金榜认证】10KG除菌净螨洗烘款"});

        addItem("海尔（Haier)滚筒洗衣机全自动BLDC变频电机10KG大容量高温除菌除螨EG100MATE2S",
                "【元气生活MATE开启】【今日下单立减200元|满2000减300元|到手价不高于1999元】20-21点再赠50元京豆|赠电机10年保修|羽绒洗更蓬松戳查看>",
                new String[]{"【Mate】10KG洗烘套装|香薰热泵","【微蒸汽空气洗】10KG洗烘|银色","【Mate系列】10KG滚筒|行业爆款"});

        addItem("海尔（Haier）安心浴60升电热水器速热大水量金刚无缝胆水质监测健康灭菌WIFI智控一级能效 EC6001-PD3(U1)",
                "【3.8节全面开抢】【下单到手价1149元！】剩余热水量可视化！2200W大功率速热！智能APP操控！【收藏加购咨询客服抽手机大奖】顶配双胆恒温黑科技新品查看>",
                new String[]{"⭐⭐⭐热水器产品全网TOP1","【空间大师】恒温黑科技新品","【3D速热】七星净水洗 健康可视","【3D速热短款】七星净水洗","【3300W变频】镁棒免换 健康可视","【3300W短款】健康可视 玲珑机身","【3000W变频】WIFI智能 健康抑菌","【1750W厨宝5L】一级能效高保温"});

        addItem("美的（Midea）初见套系60升电热水器2100W速热高温健康洗 一级变频无缝内胆京东小家智能生态F6021-JA1(HEY)",
                "【3月8巅峰开抢】【立减到手价不高于1049】咨询客服晒单登记送电水壶【大功率变频一级节能省电】【wifi智控】【双重防漏电】【新品纤薄扁桶首发，抢0元试用】查看>",
                new String[]{"⭐️⭐️⭐️美的热水器全网TOP1","【超级新品】3200W大功率三显","⭐️⭐️⭐️亲肤 电子镁棒美肌","⭐️⭐️⭐️首发 高贵 安装全免"});

        addItem("美的(Midea) 1.5匹空调风酷新一级变频冷暖壁挂式空调挂机大风口 京东小家智能家电 以旧换新KFR-35GW/N8XHC1",
                "【3月8日巅峰开抢】活动到手不高于2799元！省电明星节能升级款！【店铺主推爆款挂机】【升级大风口速享舒适丨WiFi远程智控】点击查看>",
                new String[]{"【大1匹】风酷三级自清洁","【大1匹】风酷新一级大风口","【大1.5匹】风酷新一级大风口"});

        addItem("格力（GREE）1.5匹 云佳 新一级能效 变频冷暖 自清洁 壁挂式空调挂机KFR-35GW/NhGc1B以旧换新",
                "【3月8日巅峰开抢】到手价不高于2999元！【店铺主推爆款机型】【加购指定型号抽好礼】【整机10年包修】以旧换新更优惠！点击查看更多！查看>",
                new String[]{"【大1匹】云佳一级自清洁","【1.5匹】云佳一级自清洁","【大1匹】云佳三级变频","【1.5匹】云佳三级变频","【2匹】云佳三级变频","【3匹】云佳三级变频"});

        addItem("大宇（DAEWOO）挂烫机家用手持蒸汽熨烫机熨衣机旅游出差便携式熨斗HI-029-ZI",
                "【京东38节】5-8号活动开抢，到手349元！半价购熨衣板+晒单20元红包 【0.3s穿透衣物！超强蒸汽，超大面板设计，熨烫更省时！】活动会场查看>\n",
                new String[]{"便携手持熨斗-029紫【爆款】","便携手持熨斗-029白【爆款】","029-熨衣板"});

        addItem("沁园（TRULIVA）京品家电小白鲸旗舰净水器家用2.5升/分钟UV杀菌智能龙头厨下反渗透直饮净水机KRL5018",
                "【3月8日巅峰开抢，到手2899元,享12期免息】下单赠PGP*2，晒单赠CTO*2，晒单前100名赠电烤炉，100后送烤箱》顶配款查看>",
                new String[]{"UV杀菌大流量数显款 2.5升/分钟","屋水路保护800G+FMP308","全屋净饮速热800G+LNW580","全屋净热套装800G+FMP308+LNW580"});

        addItem("飞利浦(PHILIPS) 电动牙刷 成人声波震动牙刷 净力刷 2种模式 温和清洁 白色 HX2431/02",
                "【呵护口腔健康，选飞利浦口腔护理】到手价199元 【限量加赠138元牙刷头+牙刷盒，限前1000名】 【年轻人优选|2种洁齿模式|30天超长续航】价保30天查看>",
                new String[]{"呵护敏感牙龈|2种模式【白】","呵护敏感牙龈|2种模式【深蓝】"});

        addItem("松下（Panasonic）电吹风机 家用 纳诺怡护发 大功率速干 恒温护发 EH-JNA3C 蔡徐坤同款",
                "【3.6-3.8焕新季】【到手价369元，限量1000件，抢完即止】纳诺怡滋养护发，50度恒温护发，价保30天",
                new String[]{"【少女粉】纳米水离子 1800W","【樱花粉】1800W 双风嘴","【紫色】纳米水离子 1600W"});

        addItem("容声(Ronshen)【离子净味系列】536升双变频冰箱双开门对开门家用风冷无霜大容量BCD-536WD18HP超薄节能净味",
                "【女王尝鲜季·年度店铺热销王】追加100名领券省280仅2699【20点起全店前50名返500元E卡相当于2199】410升超薄组合嵌入冰箱上新立省1000查看>",
                new String[]{"【超级爆品】536L净味纤薄变频","【大容量爆款】636L一级能效变频","【纳米补水】645L负离子除菌养鲜","【晶钻热卖】536L全净化干湿分储"});

        addItem("美的(Midea)606升变频一级能效对开双门家用冰箱京东小家智能家电风冷无霜BCD-606WKPZM(E)大容量精细分储",
                "【钜惠女王节】【秒杀特惠到手2999,晒单送三年延保】 【大容量精细分储，双变频节能养鲜，风冷无霜不结冰】 【新品516升法式多门，超大存储空间】查看>",
                new String[]{"【宽911】650升母婴抑菌独立空间","【宽910】601升24H全时除菌净味","【宽910】609升微晶一周鲜","【宽911】606升热卖爆款99%抗菌","【宽835】470升热卖爆款超薄机身"});

        addItem("华凌 洗碗机预约上门测量链接（非实物，详情咨询客服）",
                "",
                new String[]{"免费上门测量"});

        addItem("美的（Midea）15套大容量 嵌入式 骄阳家用洗碗机 热风烘干 银离子净味 双驱变频 智能家电 自动刷碗机RX600",
                "【3月8日巅峰开抢】【到手价不高于4299元】【下单送洗碗粉套装！晒单送挂烫机】【享白条6期免息】【咨询客服享惊喜】一级水效新品上市查看>",
                new String[]{"⭐⭐⭐【RX600】15套变频节能洗","⭐⭐⭐【JV800】16套节能分层洗","⭐⭐⭐⭐⭐【RX600W白】一级水效"});

        addItem("美的（Midea）燃气灶天然气 双灶具 5.0kW大火力 铜火盖 钢玻面板 六年质保 台嵌两用 以旧换新一级能效Q230B",
                "【美的燃气灶特惠-享六年包修】限时24H！满减到手699！美的更美丽！全国联保！5.0kw火力！全进风循环供氧！9档精准大火力！【5.2kW极限火上市查看>",
                new String[]{"行业爆款⭐5.0kW⭐6年质保","⭐⭐智能三防：定时-控温-防干烧","⭐⭐⭐超大猛火 5.2极限猛火"});

        addItem("苏泊尔(SUPOR) 燃气灶双灶 天然气灶双灶具5.0KW家用猛火灶 钢化玻璃双灶台嵌两用 全进风 一级能效 JZT-Q5天",
                "【家电3·8节】限时开抢！暖心到手629元！库存有限！百万口碑优选【5.0KW爆炒烈焰猛火灶！全方位立体进风】70%热效率新品灶查看>",
                new String[]{"【销量王者】5.0KW大火力 Q5-液","【销量王者】5.0KW大火力 Q5-天"});

        addItem("小米电视EA65 2022款 65英寸 金属全面屏 远场语音 逐台校准4K超高清智能教育电视机L65M7-EA",
                "【3月8日巅峰开抢】【到手价不高于2499元，赶快抢购】 【金属全面屏，超高屏占比，4K超高清画质，立体声影院级扬声器】 【65英寸性价比之选点击查看>",
                new String[]{"【65英寸】Redmi A65 4K超高清","【65英寸】E65X 全面屏","【65英寸】EA65 金属全面屏","【65英寸】ES65 画质轻旗舰"});

        addItem("华为智慧屏 SE 55英寸 超薄电视 广色域鸿鹄画质 4K超高清智能液晶电视机 HD55DESA 2+16GB搭载 HarmonyOS 2",
                "【3月7日晚8点开抢】到手价不高于1999元，购机赠全屏影视会员月卡,前100名下单赠价值99元智能台灯，赠完即止【更多推荐】查看>",
                new String[]{"SE系列 畅连通话版","SE系列 标准版"});

        addItem("联想拯救者Y90电竞手机 18GB+640GB 全新骁龙8 霜刃散热 双X轴线性马达 四肩键双压感 144Hz AMOLED电竞屏",
                "【新品上市】预约优惠300，享6期免息，晒单返百元E卡，买赠原厂三件套拯救者Y90保护壳，水凝膜，手游肩键（更多好货）查看>",
                new String[]{"16GB+256GB","12GB+256GB","18GB+640GB"});

        addItem("Apple Watch Series 7 智能手表GPS款41 毫米绿色铝金属表壳苜蓿草色运动型表带 MKN03CH/A",
                "【她的节日，与她同乐】限时抢券享大额优惠，3月4日晚8点-3月8日可用！猛戳！查看>",
                new String[]{"GPS款 41毫米","GPS款 45毫米","GPS+蜂窝款 41毫米","GPS+蜂窝款 45毫米"});

        addItem("Apple AirPods 配无线充电盒 Apple蓝牙耳机 适用iPhone/iPad/Apple Watch",
                "3月4日晚八点起,AirPodsPro抢券+直降,限时限量低至1649元!AirPods现已支持个性定制!查看>",
                new String[]{"H1芯片 有线充电","H1芯片 无线充电","AirPods的无线充电盒","【官方AppleCare+版】有线充电","【官方AppleCare+版】无线充电"});

        addItem("森海塞尔（Sennheiser）Momentum Wireless 大馒头3主动降噪音乐耳机 无线蓝牙头戴式耳机 黑色",
                "【38女神节，限时抢购！】享6期免息！优惠到手仅1799元！古典黑，主动降噪，高品质无线声音，智能免摘通话，奢华皮质耳垫",
                new String[]{"【爆款】HD458 蓝牙降噪","大馒头3蓝牙降噪 白","大馒头3蓝牙降噪 黑"});

        addItem("百诺（Benro）三脚架 IF28+ 单反三脚架佳能尼康相机 可反折转独脚架 超强锁紧 稳定便携云台套装",
                "航空铝脚管+镁合金架专业三轴球形云台反折便携可拆独脚架加强稳定款",
                new String[]{"IF28C+碳纤维反折变独脚","IF18+反折变独脚架","IF28+反折变独脚架"});

        addItem("适马（SIGMA）Art 85mm F1.4 DG HSM 全画幅 大光圈定焦镜头 人像肖像特写（佳能单反卡口）",
                "",
                new String[]{"35/1.2","35/1.4","40/1.4","50/1.4","85/1.4"});

        addItem("【官网认证】苹果无线充电器15W快充MagSafe磁吸iPhone13/12ProMax维肯 【磁吸增强-低温升级版】【限量赠送20W闪充头】 支持苹果13/12/11/pro Max mini",
                "【自营原装】重磅活动直降40元+领神券99减20元，到手仅88，限7-8日抢完即止，再送20W快充头【京东超市】当/次日达【升级版】低温不烫手，磁性加强升级",
                new String[]{"【苹果12/13快充头+USB-C闪充线1米】","【冰磁吸附-双口豪华版】限量送30W氮化镓快充头","【磁吸增强-低温升级版】【限量赠送20W闪充头】"});

        addItem("黑鲨冰封散热背夹2Pro 屏显手机散热器 吃鸡神器 炫彩灯效 适配主流苹果iPhone小米华为荣耀IQOO手机",
                "",
                new String[]{"背夹 标准版","背夹 Pro","背夹2 Pro"});

        addItem("中国联通 5G畅爽冰激凌套餐159元档 40GB+700分钟 老用户套餐变更 话费流量更多",
                "可办2张副卡副卡同主卡速率，当前办理可享受6个月7折优惠，联通5G超快体验，内含5G视频会员包",
                new String[]{"129套餐（30G+500分钟）","159套餐（40G+700分钟）","199套餐（60G+1000分钟）"});

        addItem("中国电信 电信卡无限流量卡全国不限量卡手机卡0月租大王卡上网卡电话卡日租卡电信流量卡",
                "流量卡手机卡电话卡流量卡纯上网电信流量卡中国电信电信卡大王卡流量卡无限量流量无限流量卡手机号上网卡纯流量卡上网卡手机卡流量卡流量卡纯上网纯流量卡5g流量卡电信",
                new String[]{"1张"});

        // 41-60

        // 61-80

        // 81-100

        // 101-120

        itemList.forEach(item -> itemMapper.insert(item));
        itemMapper.insertInnerItem(innerItemList);
    }

    private void addItem(String title, String subTitle, String[] typeName) {
        ItemCreateParam itemCreateParam = new ItemCreateParam();
        itemCreateParam.setUserId(21979855163752448L);
        itemCreateParam.setTitle(title);
        itemCreateParam.setSubTitle(subTitle);
        itemCreateParam.setH5Base64(null);
        itemCreateParam.setCategoryTwoId(nowId.longValue());
        itemCreateParam.setCategoryOneId(categoryMap.get(nowId.longValue()).getCategoryOneId());
        Item item = new Item(itemCreateParam);
        itemList.add(item);
        Random random = new Random();
        for (String s : typeName) {
            innerItemList.add(new InnerItem(item, new InnerItemCreateParam(s, BigDecimal.valueOf(random.nextInt(1000) + random.nextDouble() % 1), random.nextInt(1000))));
        }

        if (isNext){
            isNext = false;
            nowId++;
        }else {
            isNext = true;
        }
    }
}
