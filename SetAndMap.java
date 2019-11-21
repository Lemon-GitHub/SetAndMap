package SetAndMap;

import java.util.*;

public class SetAndMap {
//    // ------------------------------
//    // 求只出现一次的数字
//    // [1, 1, 2, 2, 3]
//    public int singleNumber(int[] nums) {
//        // key数字
//        // value 数字出现的次数
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int x : nums) {
//            // 若数字没有出现 默认值为0
//            int count = map.getOrDefault(x, 0);
//            map.put(x, count + 1);
//        }
//        // end for 统计每个数字出现的次数
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
//                return entry.getKey();
//            }
//        }
//        return 0;
//    }

    // 线性时间复杂度 不使用额外空间
    // 方法二：异或 (找只出现一次的数字 前提是其他数字均出现两次
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int x : nums) {
            result ^= x;
        }
        return result;
    }
    // ------------------------------



    // 复制带随机指针的链表
    class Node {
        public int val;
        public Node next;
        public Node random;


        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
    public Node copyRandomList(Node head) {
        // 1.创建HashMap, key是旧节点, value是旧节点对应的新节点
        HashMap<Node, Node> hashMap = new HashMap<>();
        for (Node cur = head; cur != null; cur = cur.next) {
            // 2.把旧链表的节点依次以键值对的形式插入进来
            hashMap.put(cur, new Node(cur.val, null, null));
        }
        //3.再次遍历旧链表 根据刚才得到的hashMap将next和random指向正确的位置
        for (Node cur = head; cur != null; cur = cur.next) {
            //hashMap.get 得到value(旧节点对应的新节点)  修改新节点的next指向
            hashMap.get(cur).next = hashMap.get(cur.next);
            //hashMap.get 得到value(旧节点对应的新节点)  修改新节点的random指向
            hashMap.get(cur).random = hashMap.get(cur.random);
        }
        return hashMap.get(head);
    }


    // 宝石与石头
    // 比string查找高效
    public int numJewelsInStones(String J, String S) {
        //map 次数      set 存在
        // 1. 创建set保存所有的宝石
        Set<Character> set = new HashSet<>();
        // 2. 遍历J 把所有的宝石加入set
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        // 3. 遍历S 取出每个字符 如果是宝石 count++
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if(set.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }


    // 求前k个高频单词
    // 海量数据选择堆
    public List<String> topKFrequent(String[] words, int k) {
        // 1.统计每个单词出现的次数 map
        Map<String, Integer> map = new HashMap<>();
        for(String w : words) {
            int count = map.getOrDefault(w, 0);
            map.put(w, count + 1);
        }

        // 2.单词放在ArrayList中 对单词降序排序(次数  取结果的前k个
        // ✨✨排序过程 自定制比较规则
        // map.keySet 获取单词 放入数组中(去重
        List<String> result = new ArrayList<>(map.keySet());
        // 在sort方法中的第二个参数指定一个比较器对象
        Collections.sort(result, new MyComp(map));

        // sublist 返回list的一个子区间
        // [0, k)
        return result.subList(0, k);
    }
    class MyComp implements Comparator<String> {
        private Map<String, Integer> map = null;
        public MyComp(Map<String, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            int count1 = map.get(o1);
            int count2 = map.get(o2);
            if (count1 == count2) {
                return o1.compareTo(o2);
            }
            //降序 count2 - count1
            //升序 count1 - count2
            return count2 - count1;
        }
    }

}
