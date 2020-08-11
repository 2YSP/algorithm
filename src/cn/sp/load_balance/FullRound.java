package cn.sp.load_balance;

import java.util.ArrayList;
import java.util.List;

/**
 * 负载均衡算法之完全轮询
 */
public class FullRound {

    static int index;

    public static void main(String[] args) {
        final Servers servers = new Servers();
        System.out.println("10次请求负载均衡结果为:");
        for(int i=1;i<=10;i++){
            System.out.println("第"+i+"次请求服务ip为："+chooseOne(servers));
        }
    }

    public static String chooseOne(Servers servers){
        if (index >= servers.ipList.size()){
            index = 0;
        }
        return servers.ipList.get(index++);
    }


    static class Servers{

        public static List<String> ipList = new ArrayList<>(4);

        static {
            ipList.add("196.128.6.1");
            ipList.add("196.128.6.2");
            ipList.add("196.128.6.3");
            ipList.add("196.128.6.4");
        }
    }
}
