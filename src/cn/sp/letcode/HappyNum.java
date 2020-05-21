package cn.sp.letcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 2YSP on 2019/4/29.
 */
public class HappyNum {


    public boolean isHappy(int number){
        Set<Integer> set = new HashSet<>();
        while (number != 1){
            int sum = 0;
            while (number > 0){
                //计算当前值的每位数的平方 相加的和 在放入set中，如果存在相同的就认为不是 happy数字
                sum += (number%10) *(number % 10);
                number = number / 10;
            }

            if (set.contains(sum)){
                return false;
            }else {
                set.add(sum);
            }
            number = sum;
        }
        return true;
    }

    public static void main(String[] args) {
//        int num = 345;
//        int i = num % 10;
//        int i1 = num / 10;
//        int i2 = i1 / 10;
//        System.out.println(i);
//        System.out.println(i1);
//        System.out.println(i2);
        HappyNum happyNum = new HappyNum();
        System.out.println(happyNum.isHappy(19));
    }


}
