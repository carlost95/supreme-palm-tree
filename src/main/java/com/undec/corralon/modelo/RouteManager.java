package com.undec.corralon.modelo;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RouteManager {

    private static final Logger logger = Logger.getLogger(RouteManager.class.getName());
    private final int cantidadVehiculos = 1;
    private final int deposito = 0;
    private final double[][] distancias;

    public RouteManager(double[][] distancias) {
        this.distancias = distancias;
    }


    public Ruta printSolution(
            RoutingModel routing, RoutingIndexManager manager, Assignment solution) {

        Ruta ruta = new Ruta();
        List<Integer> paradas = new ArrayList<>();
        long routeDistance = 0;
        String route = "";
        long index = routing.start(0);
        while (!routing.isEnd(index)) {
            paradas.add(manager.indexToNode(index));
            route += manager.indexToNode(index) + " -> ";
            long previousIndex = index;
            index = solution.value(routing.nextVar(index));
            routeDistance += routing.getArcCostForVehicle(previousIndex, index, 0);
        }
        route += manager.indexToNode(routing.end(0));
        paradas.add(manager.indexToNode(routing.end(0)));
        ruta.setParada(paradas);
        ruta.setDistancia(routeDistance);
        return ruta;
    }

    public Ruta getRoute() throws Exception {

        // Create Routing Index Manager
        RoutingIndexManager manager =
                new RoutingIndexManager(this.distancias.length, this.cantidadVehiculos, this.deposito);

        // Create Routing Model.
        RoutingModel routing = new RoutingModel(manager);

        // Create and register a transit callback.
        final int transitCallbackIndex =
                routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                    // Convert from routing variable Index to user NodeIndex.
                    int fromNode = manager.indexToNode(fromIndex);
                    int toNode = manager.indexToNode(toIndex);
                    return (long) this.distancias[fromNode][toNode];
                });

        // Define cost of each arc.
        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);


        // Setting first solution heuristic.
        RoutingSearchParameters searchParameters =
                main.defaultRoutingSearchParameters()
                        .toBuilder()
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();

        // Solve the problem.
        Assignment solution = routing.solveWithParameters(searchParameters);

        // Print solution on console.
        return printSolution(routing, manager, solution);
    }
}