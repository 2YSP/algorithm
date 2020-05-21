package cn.sp.search;

/**
 * @author 2YSP
 * @date 2020/5/21 21:22
 * 二分查找算法
 */
public class BinarySearch {

    /**
     * 从排序好的没有重复值的数组中查找给定值(循环实现)
     *
     * @param a     从小到大排序好的数组
     * @param n     数组大小
     * @param value 要查找的值
     * @return
     */
    public static int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            // 如果low和high都比较大时，两者之和就有可能溢出，故用下面这种方式
//            int mid = (low + high) / 2;
            int mid = low + ((high - low) >> 1);

            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 从排序好的没有重复值的数组中查找给定值(递归实现)
     *
     * @param a
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int bSearch2(int[] a, int low, int high, int value) {
        int mid = low + ((high - low) >> 1);
        if (a[mid] > value) {
            bSearch2(a, low, mid - 1, value);
        } else if (a[mid] < value) {
            bSearch2(a, mid + 1, high, value);
        } else {
            return mid;
        }
        // 没找到
        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param a     有重复值的从小到大的数组
     * @param n
     * @param value
     * @return
     */
    public static int bSearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //相等
                if (mid == 0 || a[mid - 1] != value) {
                    // 如果已经是数组的第一个元素或者前一个元素不等于value则当前元素是第一个
                    return mid;
                } else {
                    // 继续往前找
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素(装逼写法！！！)
     *
     * @param a     有重复值的从小到大的数组
     * @param n
     * @param value
     * @return
     */
    public static int bSearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low] == value) {
            return low;
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     *
     * @param a     有重复值的从小到大的数组
     * @param n
     * @param value
     * @return
     */
    public static int bSearch5(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //相等
                if (mid == n - 1 || a[mid + 1] != value) {
                    // 如果已经是数组的最后一个元素或者后一个元素不等于value则当前元素是最后一个
                    return mid;
                } else {
                    // 继续往后找
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param a     有重复值的从小到大的数组
     * @param n
     * @param value
     * @return
     */
    public static int bSearch6(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    // 如果min前面已经没有元素或前一个元素小于value则a[mid]就是我们要找的元素
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param a     有重复值的从小到大的数组
     * @param n
     * @param value
     * @return
     */
    public static int bSearch7(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if (mid == n - 1 || a[mid + 1] > value) {
                    // 如果mid是最后一个元素或者后面一个元素大于value则a[mid]就是我们要找的元素
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = {1, 4, 7, 9, 12};
        int index = bSearch(a, a.length, 7);
        System.out.println(index);
        int i = bSearch2(a, 0, a.length - 1, 7);
        System.out.println(i);
        int[] b = {1, 3, 4, 8, 8, 8, 11};
        int result = bSearch4(b, b.length, 8);
        System.out.println("第一个值等于8的index是:" + result);
    }
}
