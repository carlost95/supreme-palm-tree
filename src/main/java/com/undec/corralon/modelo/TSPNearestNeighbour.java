//package com.undec.corralon.modelo;
//
//
//import java.util.Stack;
//
//public class TSPNearestNeighbour
//{
//    private int numberOfNodes;
//    private String [] route;
//    private Stack<Integer> stack;
//
//    public TSPNearestNeighbour()
//    {
//        stack = new Stack<Integer>();
//    }
//
//    public String[] tsp(int[][] adjacencyMatrix)
//    {
//        numberOfNodes = adjacencyMatrix[1].length - 1;
//        System.out.println("Numb of nodes " + numberOfNodes );
//        route = new String[numberOfNodes + 1];
//        int count = 1;
//        int[] visited = new int[numberOfNodes + 1];
//        visited[1] = 1;
//        stack.push(1);
//        int element, dst = 0, i;
//        int min = Integer.MAX_VALUE;
//        boolean minFlag = false;
//        System.out.print(1 + "\t");
//        this.route[0] = "1";
//
//        while (!stack.isEmpty())
//        {
//            element = stack.peek();
//            i = 0;
//            min = Integer.MAX_VALUE;
//            while (i <= numberOfNodes)
//            {
//                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
//                {
//                    if (min > adjacencyMatrix[element][i])
//                    {
//                        min = adjacencyMatrix[element][i];
//                        dst = i;
//                        minFlag = true;
//                    }
//                }
//                i++;
//            }
//            if (minFlag)
//            {
//                visited[dst] = 1;
//                stack.push(dst);
//                System.out.print(dst + "\t");
//                this.route[count] = dst + "";
//                count++;
//                minFlag = false;
//                continue;
//            }
//            stack.pop();
//        }
//        return route;
//    }
//
//
//}
