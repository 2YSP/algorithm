package cn.sp.chapter03;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现的LRU缓存
 * Created by 2YSP on 2019/10/20.
 */
public class LRUBasedArray<T> {

    private static final int DEFAULT_CAPACITY = (1 << 3);

    private int capacity;
    /**
     * 数组中的元素个数
     */
    private int count;

    private T[] value;
    /**
     * key:数据
     * value: 数组中的index
     */
    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<>(capacity);
    }

    /**
     * 模拟访问某个值
     */
    public void offer(T object) {
        if (object == null) {
            throw new IllegalArgumentException("该容器不支持null");
        }

        Integer index = holder.get(object);
        if (index == null) {
            if (isFull()) {
                removeAndCache(object);
            } else {
                cache(object, count);
            }
        } else {
            update(index);
        }
    }

    /**
     * 数据缓存到头部，但要先右移
     *
     * @param object
     */
    private void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;
    }

    /**
     * 缓存满的情况，剔除后再缓存到头部
     *
     * @param object
     */
    private void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        cache(object, count);
    }

    private boolean isFull() {
        return count == capacity;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    /**
     * 若缓存中有指定的值，更新位置
     */
    private void update(Integer end) {
        T target = value[end];
        rightShift(end);
        value[0] = target;
        holder.put(target, 0);

    }

    /**
     * end左边的数据统一右移一位
     */
    private void rightShift(Integer end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUBasedArray<Integer> lru = new LRUBasedArray<>();
        lru.offer(1);
        lru.offer(3);
        lru.offer(6);
        lru.offer(3);
        lru.offer(4);
        System.out.println(lru);
        lru.offer(1);
        System.out.println(lru);

    }

}
