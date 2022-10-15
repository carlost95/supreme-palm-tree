package com.undec.corralon.service;

//import com.undec.corralon.modelo.TSP;
//import com.undec.corralon.modelo.TSPNearestNeighbour;
import com.undec.corralon.DTO.Distancia;
import com.undec.corralon.modelo.GoogleTSP;
import org.springframework.stereotype.Service;

@Service
public class LogisticaService {

//    public String[] obtenerRuta() {
////        Map<String, Map<String, Integer>> mapDistances = new HashMap<String, Map<String, Integer>>();
////        char initial = 'A';
////        List<String> ciudades = distancias.stream().map((ciudad, indice) -> ('A'+indice) );
////
//
//        Map<String, Map<String, Integer>> vtDistances = Map.of(
//                "0", Map.of(
//                        "1", 341,
//                        "2", 726,
//                        "3", 855,
//                        "4", 242),
//                "1", Map.of(
//                        "0", 1379,
//                        "2", 384,
//                        "3", 513,
//                        "4", 1014),
//                "2", Map.of(
//                        "0", 1041,
//                        "1", 824,
//                        "3", 752,
//                        "4", 725),
//                "3", Map.of(
//                        "0", 1765,
//                        "1", 1518,
//                        "2", 452,
//                        "4", 1401),
//                "4", Map.of(
//                        "0", 780,
//                        "1", 532,
//                        "2", 917,
//                        "3", 1041));
//        TSP tsp = new TSP(vtDistances);
//        String[] shortestPath = tsp.findShortestPath();
//        int distance = tsp.pathDistance(shortestPath);
//        System.out.println("The shortest path is " + Arrays.toString(shortestPath) + " in " +
//                distance + " miles.");
//        return shortestPath;
//    }
//
//
//    public String [] tsp() {
//        int number_of_nodes = 4;
//        int row1 [] = {0, 341, 726, 855, 242};
//        int row2 [] = {1379, 0, 384, 513, 1014};
//        int row3 [] = {1041, 824, 0, 752, 725};
//        int row4 [] = {1765, 1518, 452, 0, 1401};
//        int row5 [] = { 780, 532, 917, 1041, 0};
//        int adjacency_matrix[][] = {row1, row2, row3, row4, row5};
//        try
//        {
//            for (int i = 0; i <= number_of_nodes; i++)
//            {
//                System.out.print("Line " + i );
//                for (int j = 0; j <= number_of_nodes; j++)
//                {
//                    System.out.print(" " + adjacency_matrix[i][j] + " ");
//                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
//                    {
//                        adjacency_matrix[j][i] = 1;
//                    }
//                }
//                System.out.println("");
//            }
//            for (int i = 0; i <= number_of_nodes; i++){
//                for (int j = 0; j <= number_of_nodes; j++){
//                    System.out.print(" " + adjacency_matrix[i][j] + " ");
//                }
//                System.out.println("");
//            }
//
//            System.out.println("the citys are visited as follows");
//            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
//            return tspNearestNeighbour.tsp(adjacency_matrix);
//        } catch (InputMismatchException inputMismatch)
//        {
//            System.out.println("Wrong Input format");
//        }
//
//        return new String[0];
//    }
    public String obtenerRuta(Distancia distancia) throws Exception {
        return new GoogleTSP(distancia.getDistancia()).getRoute();

    }
//    public String obtenerRuta(long[][] distancia) throws Exception {
//        return new GoogleTSP(distancia).getRoute();
//
//    }
}
