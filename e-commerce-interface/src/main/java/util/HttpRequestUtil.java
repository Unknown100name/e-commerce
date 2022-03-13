package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
/**
 * @author unknown100name
 * @description HTTP 发送返回类
 * @since 2022/3/11
 */
public class HttpRequestUtil {

    /**
     * 发送Get请求
     *
     * @param url       请求的url路径
     * @param header    请求的参数对象
     * @return JSONObject 结果
     */
    public static JSONObject doGet(String url, Object header){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setConnection("keep-alive");
        headers.setCacheControl("max-age=0");

        url = toUrl(url, header);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> strBody = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        //将 String 返回值解析为 JSON
        return JSONObject.parseObject(strBody.getBody());
    }

    /**
     *  发送POST请求
     *
     * @param url       请求的URL路径
     * @param header    请求头的参数对象
     * @param body      请求体的参数对象
     * @return JSONObject结果
     */
    public static JSONObject doPost(String url, Object header, Object body){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setConnection("keep-alive");
        headers.setCacheControl("max-age=0");

        url = toUrl(url,header);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(toMap(body),headers);
        ResponseEntity<String> strBody = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        //将 String 返回值解析为 JSON
        return JSONObject.parseObject(strBody.getBody());
    }

    /**
     *  将对象转为Map
     *
     * @param object 对象
     * @return Map<String,Object>
     */
    private static Map<String,Object> toMap(Object object){
        if (object instanceof Map){
            return (Map<String, Object>) object;
        }else{
            return (Map<String, Object>) JSON.toJSON(object);
        }
    }

    /**
     * 将 Object 转为URL参数
     *
     * @param url URL
     * @param object 对象
     * @return 最终形成的URL
     */
    private static String toUrl(String url,Object object){
        if (object != null){
            Map<String,Object> map = toMap(object);
            StringBuilder tempParam = new StringBuilder();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    tempParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            return url + "?" + tempParam.substring(0, tempParam.length() - 1);
        }
        return url;
    }

    public static void main(String[] args) {
//        JSONObject jsonObject = doGet("http://127.0.0.1:8081/carOperationApp/getCarState",new Car().setId("1"));
//        System.out.println(jsonObject.toJSONString());
//        JSONObject jsonObject2 = doPost("http://127.0.0.1:8081/carManage/brand",null,new BrandUpdateParam().setChName("汽车品牌B").setEnName("brandB").setUrl("null"));
//        System.out.println(jsonObject2.toJSONString());
    }
}
