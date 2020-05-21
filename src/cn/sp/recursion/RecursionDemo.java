package cn.sp.recursion;

/**
 * Created by 2YSP on 2019/11/18.
 *
 * 递归
 */
public class RecursionDemo {

  /***
   *  假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
   *  如果有 7 个台阶，你可以 2，2，2，1 这样子上去，也可以 1，2，1，1，2 这样子上去，那么一共有多少种走法？
   *
   *  实际上，可以根据第一步的走法把所有走法分为两类，第一类是第一步走了 1 个台阶，另一类是第一步走了 2 个台阶。
   *  所以 n 个台阶的走法就等于先走 1 阶后，n-1 个台阶的走法 加上先走 2 阶后，n-2 个台阶的走法。用公式表示就是：
   *
   *  f(n) = f(n-1) + f(n-2)，终止条件f(1) = 1,f(2) = 2
   *  递归代码技巧
   *  1.写出递推公式
   *  2.找到终止条件
   *  3.将递推公式转换为代码
   */

  public static void main(String[] args) {
    System.out.println(f(7));
  }

  private static int f(int n){
    if (n == 1){
      return 1;
    }
    if (n == 2){
      return 2;
    }
    return f(n-1) + f(n-2);
  }
}
