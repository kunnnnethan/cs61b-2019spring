package bearmaps.proj2c;

import bearmaps.hw4.WeightedEdge;
import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.lab9.MyTrieSet;
import bearmaps.proj2ab.Point;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private KDTree kdTree;
    Map<Point, Long> pointToID = new HashMap<>();
    List<Point> points = new ArrayList<>();
    MyTrieSet trie = new MyTrieSet();
    Map<String, List<Node>> newToOldNames = new HashMap<>();

    // 好像根本不用這個鬼東西 Map<Node, Point> map = new HashMap<>();

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);

        // Project Part II
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        for (Node n : nodes) {

            if (n.name() != null) {
                String name = n.name();
                String cleanedName = cleanString(name);
                if (!newToOldNames.containsKey(cleanedName)) {
                    newToOldNames.put(cleanedName, new LinkedList<>()); // 將修改過跟未修改過的存在同一個map裡
                }
                newToOldNames.get(cleanedName).add(n);
                trie.add(cleanedName);
            }

            Long id = n.id();
            // Only consider the node that has neighbors.
            if (!this.neighbors(id).isEmpty()) {
                Point point = new Point(n.lon(), n.lat());
                points.add(point);
                pointToID.put(point, id);
            }
        }

        kdTree = new KDTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point closest = kdTree.nearest(lon, lat);
        return pointToID.get(closest);
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        prefix = cleanString(prefix);

        List<String> cleanedNames = trie.keysWithPrefix(prefix);
        List<String> originalNames = new LinkedList<>();

        for (String l : cleanedNames) {
            originalNames.add(newToOldNames.get(l).get(0).name());
        }

        return originalNames;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> results = new LinkedList<>();

        locationName = cleanString(locationName);

        if (newToOldNames.containsKey(locationName)) {
            List<Node> locations = newToOldNames.get(locationName);

            for (Node n : locations) {
                Map<String, Object> locationsInformations = new HashMap<>();
                locationsInformations.put("lat", n.lat());
                locationsInformations.put("lon", n.lon());
                locationsInformations.put("name", n.name());
                locationsInformations.put("id", n.id());
                results.add(locationsInformations);
            }
        }

        return results;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
