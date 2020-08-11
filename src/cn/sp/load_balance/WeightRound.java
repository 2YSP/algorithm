package cn.sp.load_balance;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 负载均衡算法之加权轮询
 */
public class WeightRound {

    static int index;

    /**
     *
     * @param servers
     * @return
     */
    public static String chooseOne(Servers servers){
        int allWeight = servers.MAP.values().stream().mapToInt(a -> a).sum();
        int number = (index++) % allWeight;
        for(Map.Entry<String,Integer> entry : servers.MAP.entrySet()){
            if (entry.getValue() > number){
                return entry.getKey();
            }
            number -= entry.getValue();
        }
        return "";
    }

    static class Servers{
        /**
         * key: 服务器ip value:权重
         */
        public static final Map<String,Integer> MAP = new LinkedHashMap<>(4);

        static {
            MAP.put("196.128.6.1",1);
            MAP.put("196.128.6.2",3);
            MAP.put("196.128.6.3",5);
            MAP.put("196.128.6.4",7);
        }
    }

    public static void main(String[] args) {
        final Servers servers = new Servers();
        System.out.println("20次请求负载均衡结果为:");
        for(int i=1;i<=20;i++){
            System.out.println("第"+i+"次请求服务ip为："+chooseOne(servers));
        }
    }
}
