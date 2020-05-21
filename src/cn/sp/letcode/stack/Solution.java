package cn.sp.letcode.stack;

import java.util.*;

/**
 * Created by 2YSP on 2019/4/28.
 */
public class Solution {

    public boolean isValid(String s){
        if (s == null || s.length() == 0){
            return true;
        }
        Set<String> openStr = new HashSet<>();
        openStr.add("[");
        openStr.add("{");
        openStr.add("(");
        Set<String> closeStr = new HashSet<>();
        closeStr.add("]");
        closeStr.add("}");
        closeStr.add(")");
        Stack<String> stack = new Stack<>();
        Map<String,String> map  = new HashMap<>(3);
        map.put("]","[");
        map.put("}","{");
        map.put(")","(");
        char[] chars = s.toCharArray();

        for(int i=0;i< chars.length;i++){
            String str = String.valueOf(chars[i]);
            if (openStr.contains(str)){
                //入栈
                stack.push(String.valueOf(str));
            }else if (closeStr.contains(str)){
                if (stack.empty()){
                    return false;
                }
                if (!map.get(str).equals(stack.pop())){
                    return false;
                }
            }
        }

        if (!stack.empty()){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean valid = solution.isValid("[]{[}");
        System.out.println(valid);
    }
}
