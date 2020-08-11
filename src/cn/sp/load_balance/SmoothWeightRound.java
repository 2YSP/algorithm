package cn.sp.load_balance;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 负载均衡算法之平滑加权轮询
 * 在加权轮询的基础让让服务器之间交叉执行，
 * 避免某台服务器压力突然上升。
 */
public class SmoothWeightRound {

    public static String chooseOne(Servers servers){
        Server maxWeightServer = null;

        int allWeight = servers.server_map.values().stream().mapToInt(o -> o.getWeight()).sum();

        for(Map.Entry<String,Server> entry : servers.server_map.entrySet()){
            Server currentServer = entry.getValue();
            if (maxWeightServer == null || currentServer.getCurrentWeight() > maxWeightServer.getCurrentWeight()){
                maxWeightServer = currentServer;
            }
        }

        assert maxWeightServer != null;
        maxWeightServer.setCurrentWeight(maxWeightServer.getCurrentWeight() - allWeight);
        for(Map.Entry<String,Server> entry : servers.server_map.entrySet()){
            Server currentServer = entry.getValue();
            currentServer.setCurrentWeight(currentServer.getCurrentWeight() + currentServer.getWeight());
        }
        return maxWeightServer.getIp();
    }


    public class Servers {
        /**
         * key: 服务器ip value:权重
         */
        public final Map<String, Server> server_map = new LinkedHashMap<>(4);

        public Servers() {
            server_map.put("196.128.6.1", new Server(1, "196.128.6.1", 1));
            server_map.put("196.128.6.2", new Server(3, "196.128.6.2", 3));
            server_map.put("196.128.6.3", new Server(5, "196.128.6.3", 5));
            server_map.put("196.128.6.4", new Server(7, "196.128.6.4", 7));
        }
    }


    public class Server {
        /**
         * 固定权重
         */
        private int weight;

        private String ip;
        /**
         * 非固定权重
         */
        private int currentWeight;

        public Server(int weight, String ip, int currentWeight) {
            this.weight = weight;
            this.ip = ip;
            this.currentWeight = currentWeight;
        }


        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }
    }


    public static void main(String[] args) {
        SmoothWeightRound smoothWeightRound = new SmoothWeightRound();
        final Servers servers = smoothWeightRound.new Servers();
        System.out.println("20次请求负载均衡结果为:");
        for(int i=1;i<=20;i++){
            System.out.println("第"+i+"次请求服务ip为："+chooseOne(servers));
        }

    }

}
