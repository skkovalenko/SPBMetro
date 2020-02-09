import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator routeCalculator;

    List<Station> routeWithOneConnection;
    List<Station> routeWithTwoConnections;



    @Override
    public void setUp() throws Exception {
        stationIndex = new StationIndex();


        stationIndex.addLine(new Line(1, "F"));
        stationIndex.addLine(new Line(2, "S"));
        stationIndex.addLine(new Line(3, "T"));

        stationIndex.addStation(new Station("Красная", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Белая", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("Круглая", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Треугольная", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Прямоугольная", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Квадратная", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("Бумажная", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("Кирпичная", stationIndex.getLine(3)));

        stationIndex.getLine(1).addStation(stationIndex.getStation("Красная"));
        stationIndex.getLine(1).addStation(stationIndex.getStation("Белая"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("Круглая"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("Треугольная"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("Прямоугольная"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("Квадратная"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("Бумажная"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("Кирпичная"));


        List<Station> connectionStations1 = new ArrayList<>();
        List<Station> connectionStations2 = new ArrayList<>();
        connectionStations1.add(stationIndex.getStation("Белая", 1));
        connectionStations1.add(stationIndex.getStation("Круглая", 2));
        connectionStations2.add(stationIndex.getStation("Прямоугольная", 2));
        connectionStations2.add(stationIndex.getStation("Бумажная", 3));

        stationIndex.addConnection(connectionStations1);
        stationIndex.addConnection(connectionStations2);

        routeCalculator = new RouteCalculator(stationIndex);

        /////////////////////////////////////////////////////////

        routeWithOneConnection = new ArrayList<>();

        routeWithOneConnection.add(stationIndex.getStation("Красная"));
        routeWithOneConnection.add(stationIndex.getStation("Белая"));
        routeWithOneConnection.add(stationIndex.getStation("Круглая"));
        routeWithOneConnection.add(stationIndex.getStation("Треугольная"));
        routeWithOneConnection.add(stationIndex.getStation("Прямоугольная"));
        routeWithOneConnection.add(stationIndex.getStation("Квадратная"));

        routeWithTwoConnections = new ArrayList<>();

        routeWithTwoConnections.add(stationIndex.getStation("Красная"));
        routeWithTwoConnections.add(stationIndex.getStation("Белая"));
        routeWithTwoConnections.add(stationIndex.getStation("Круглая"));
        routeWithTwoConnections.add(stationIndex.getStation("Треугольная"));
        routeWithTwoConnections.add(stationIndex.getStation("Прямоугольная"));
        routeWithTwoConnections.add(stationIndex.getStation("Бумажная"));
        routeWithTwoConnections.add(stationIndex.getStation("Кирпичная"));


    }

    public void testGetShortestRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Красная"), stationIndex.getStation("Квадратная"));

        assertEquals(routeWithOneConnection, actual);

    }

    public void testGetShortestRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Красная"), stationIndex.getStation("Кирпичная"));

        assertEquals(routeWithTwoConnections, actual);

    }
    public void testCalculateDurationWithOneConnection() {
        double actual = RouteCalculator.calculateDuration(routeWithOneConnection);
        double expected = 13.5;
        assertEquals(expected, actual);
    }
    public void testCalculateDurationWithTwoConnections() {
        double actual = RouteCalculator.calculateDuration(routeWithTwoConnections);
        double expected = 17;
        assertEquals(expected, actual);
    }

    @Override
    public void tearDown() throws Exception {
    }


}