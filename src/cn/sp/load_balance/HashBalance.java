package cn.sp.load_balance;

import java.util.*;

/**
 * 负载均衡算法之哈希算法
 */
public class HashBalance {



    public static class Servers{

        public static List<String> ipList = new ArrayList<>(4);

        static {
            ipList.add("196.128.6.1");
            ipList.add("196.128.6.2");
            ipList.add("196.128.6.3");
            ipList.add("196.128.6.4");
        }

    }


    public static String chooseOne(String client){
        int nodeCount = 20;
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        for(String ip : Servers.ipList){
            for(int i=0;i<nodeCount;i++){
                treeMap.put((ip +"--服务器--"+i).hashCode(),ip);
            }
        }

        int clientHash = client.hashCode();
        SortedMap<Integer, String> subMap = treeMap.tailMap(clientHash);
        Integer firstHash;
        if (subMap.size() > 0){
            firstHash = subMap.firstKey();
        }else {
            firstHash = treeMap.firstKey();
        }
        return treeMap.get(firstHash);
    }

    public static void main(String[] args) {
        System.out.println(chooseOne("196.128.113.1"));
        System.out.println(chooseOne("客户端A"));
        System.out.println(chooseOne("你还发打发点"));
        System.out.println(chooseOne("00"));
        System.out.println(chooseOne("fadfdfa"));
    }
}
