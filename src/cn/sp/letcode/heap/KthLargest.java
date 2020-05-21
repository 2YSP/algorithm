package cn.sp.letcode.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by 2YSP on 2019/4/29.
 */
public class KthLargest {

    final PriorityQueue<Integer> q;

    final int k;


    public KthLargest(int k, int[] nums) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for(int i : nums){
            add(i);
        }
    }

    public int add(int val) {
        if (q.size() < k){
            q.offer(val);
        }else if (q.peek() < val){
            q.poll();
            q.offer(val);

        }
        Map<Integer,Integer> countMap = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry :countMap.entrySet()){

        }
        return q.peek();
    }


}
