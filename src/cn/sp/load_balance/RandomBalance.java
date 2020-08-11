package cn.sp.load_balance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 负载均衡算法之随机
 */
public class RandomBalance {

    private static Random random = new Random();

    private static List<String> ipList = new ArrayList<>(4);

    static {
        ipList.add("196.128.6.1");
        ipList.add("196.128.6.2");
        ipList.add("196.128.6.3");
        ipList.add("196.128.6.4");
    }

    public static void main(String[] args) {
        System.out.println("这次请求的服务器ip地址为："+chooseOne(ipList));
    }

    public static String chooseOne(List<String> ipLit){
        int index = random.nextInt(ipLit.size());
        return ipLit.get(index);
    }
}
