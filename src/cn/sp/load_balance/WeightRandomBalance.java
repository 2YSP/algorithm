package cn.sp.load_balance;

import java.util.*;

/**
 * 负载均衡算法之加权随机
 *
 * 每个服务器的性能不同，所以根据权重来平衡，
 * 权重大的获取请求的概率大，权重小的获取请求的概率小。
 */
public class WeightRandomBalance {

    private static Random random = new Random();

    /**
     * 方案一：
     * 构建一个服务器的List，如果A服务器的权重是2，
     * 那么往List里面Add两次A服务器，如果B服务器的权重是7，
     * 那么我往List里面Add7次B服务器，以此类推，
     * 然后我再生成一个随机数，随机数的上限就是权重的总和，也就是List的Size。
     * 这样权重越大的，被选中的概率当然越高
     * @param servers
     * @return
     */
    public static String chooseOne(Servers servers){
        List<String> ipList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry: servers.MAP.entrySet()){
            String ipAddress = entry.getKey();
            Integer weight = entry.getValue();
            for(int i=0;i<weight;i++){
                ipList.add(ipAddress);
            }
        }
        return ipList.get(random.nextInt(ipList.size()));
    }

    /**
     * 方案二
     *
     * @param servers
     * @return
     */
    public static String chooseOneV2(Servers servers){
        int allWight = servers.MAP.values().stream().mapToInt(a -> a).sum();
        int number = random.nextInt(allWight);
        for(Map.Entry<String,Integer> entry: servers.MAP.entrySet()){
            if (entry.getValue() >= number){
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
        public static final Map<String,Integer> MAP = new HashMap<>(4);

        static {
            MAP.put("196.128.6.1",1);
            MAP.put("196.128.6.2",3);
            MAP.put("196.128.6.3",5);
            MAP.put("196.128.6.4",7);
        }
    }

    public static void main(String[] args) {
        final Servers servers = new Servers();
        System.out.println("10次请求负载均衡结果为:");
//        for(int i=1;i<=10;i++){
//            System.out.println("第"+i+"次请求服务ip为："+chooseOne(servers));
//        }

        for(int i=1;i<=10;i++){
            System.out.println("第"+i+"次请求服务ip为："+chooseOneV2(servers));
        }
    }

}
