package cn.sp.chapter07;


/**
 * Created by 2YSP on 2019/9/26.
 */
public class QuickSort {


  public static <AnyType extends Comparable<? super AnyType>> AnyType[] quickSort(AnyType[] array,
      int left, int right) {
    if (left > right) {
      return array;
    }
    AnyType temp = array[left];
    int i = left;
    int j = right;
    while (i < j) {
      //先看右边，依次往左递减，直到找到小于基准数的数字
      while (array[j].compareTo(temp) >= 0 && i < j) {
        j--;
      }
      //比基准小的记录移到低端
      array[i] = array[j];

      //后看左边，依次往右递增，直到找到大于基准数的数字
      while (array[i].compareTo(temp) <= 0 && i < j) {
        i++;
      }
      //比基准大的记录移到高端
      array[j] = array[i];
    }

    //记录基准
    array[i] = temp;

    //递归调用左半数组
    quickSort(array, left, j - 1);
    //递归调用右半数组
    quickSort(array, j + 1, right);

    return array;
  }


  public static void main(String[] args) {
    Integer[] a = {8, 34, 10, 89, 23};
    Integer[] result = quickSort(a, 0, a.length - 1);
    for(Integer i : result){
      System.out.println(i);
    }
  }

}
