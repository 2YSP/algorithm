package cn.sp.sort;

/**
 * Created by 2YSP on 2020/2/2.
 * 计数排序,时间复杂度O(n)
 * 现有8个考生，分数0到5之间，现在把成绩放到一个A{8}数组中，分别是
 * 2,5,3,0,2,3,0,3
 */
public class CountingSort {


  /**
   * 假设数组中存储的都是非负整数
   *
   * @param a 数组
   * @param n 数组大小
   */
  public static void countingSort(int[] a, int n) {
    if (n <= 1) {
      return;
    }

    //查找数组数据范围
    int max = a[0];
    for (int i = 0; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }

    // 使用数组C，大小为6对应6个桶，角标为分数 值为每个分数对应的人数
    // 申请一个计数数组c,下标大小[0,max]
    int[] c = new int[max + 1];
    for (int i = 0; i <= max; i++) {
      c[i] = 0;
    }

    // 计算每个元素的个数，放入c中
    for (int i = 0; i < n; i++) {
      c[a[i]]++;
    }

    // 依次累加
    for (int i = 1; i <= max; i++) {
      c[i] = c[i - 1] + c[i];
    }

    // 临时数组r，存储排序之后的结果
    int[] r = new int[n];

    for (int i = n - 1; i >= 0; --i) {
      int rIndex = c[a[i]] - 1;
      c[a[i]]--;
      r[rIndex] = a[i];
    }

    // 结果拷贝给数组a
    for (int i = 0; i < n; i++) {
      a[i] = r[i];
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 5, 3, 0, 2, 3, 0, 3};
    countingSort(a, a.length);
    for (int i = 0; i < a.length; i++) {

      System.out.println(a[i]);
    }
  }

}
