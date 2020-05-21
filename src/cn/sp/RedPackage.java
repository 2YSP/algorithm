package cn.sp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 2YSP on 2020/2/2.
 * <p>
 * 抢红包算法：二倍均值法
 * 剩余红包金额为M,剩余人数为N，那么有如下公式
 * 每次抢到的金额 = 随机区间(0,M/Nx2)
 */
public class RedPackage {


    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(5000, 4);
        for (int i = 0; i < amountList.size(); i++) {
            System.out
                    .println("抢到的金额：" + new BigDecimal(amountList.get(i)).divide(new BigDecimal("100")));
        }
    }

    /**
     * @param totalAmount 红包总金额，单位分
     */
    private static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>(totalPeopleNum);
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 随机范围：[1,剩余人均金额的两倍)
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
//      int amount = restPeopleNum * random.nextInt(restAmount / 2 -1) + 1;
            restAmount -= amount;
            amountList.add(amount);
            restPeopleNum--;
        }

        // 最后一个
        amountList.add(restAmount);
        return amountList;
    }

}
