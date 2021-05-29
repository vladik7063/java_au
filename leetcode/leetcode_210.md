210. Course Schedule II

https://leetcode.com/problems/course-schedule-ii/

    class Solution_1 {
    
        int[][] mapper;
        HashMap<Integer, Integer> coursePreReqCountMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> countToPreReqMap = new HashMap<>();
        LinkedHashSet<Integer> order = new LinkedHashSet<>();
        HashSet<Integer> traversed = new HashSet<>();
    
        public boolean recurse(int course) {
            if (order.contains(course)) {
                traversed.add(course);
                return true;
            }
            if (traversed.contains(course)) {
                return false;
            }
            traversed.add(course);
            for (int i = 0; i < mapper[course].length; i++) {
                if (mapper[course][i] == 1) {
                    if (!recurse(i)) {
                        return false;
                    }
                }
            }
            order.add(course);
            return true;
        }
    
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            mapper = new int[numCourses][numCourses];
            IntStream.range(0, numCourses).forEach(i -> {
                mapper[i] = new int[numCourses];
                coursePreReqCountMap.put(i, 0);
            });
            for (int[] pre : prerequisites) {
                if (pre.length <= 1) continue;
                mapper[pre[0]][pre[1]] = 1;
                coursePreReqCountMap.put(pre[0], coursePreReqCountMap.getOrDefault(pre[0], 0) + 1);
            }
            int maxCount = 0;
            for (int course : coursePreReqCountMap.keySet()) {
                int count = coursePreReqCountMap.get(course);
                HashSet<Integer> set = countToPreReqMap.getOrDefault(count, new HashSet<>());
                set.add(course);
                countToPreReqMap.put(count, set);
                if (maxCount < count) {
                    maxCount = count;
                }
            }
    
            countToPreReqMap.getOrDefault(0, new HashSet<>()).stream().forEach(order::add);
    
            final List<Integer> lst = countToPreReqMap.keySet().stream()
                    .sorted(Comparator.reverseOrder())
                    .map(x -> countToPreReqMap.get(x))
                    .flatMap(x -> x.stream()).collect(Collectors.toList());
            for (int x : lst) {
                traversed.clear();
                if (!recurse(x)) {
                    // loop
                    return new int[0];
                }
            }
            return order.stream().mapToInt(x -> x).toArray();
        }
    }