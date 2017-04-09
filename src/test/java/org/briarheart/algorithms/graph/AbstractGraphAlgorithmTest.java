package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractGraphAlgorithmTest {
    protected static Graph<Integer> graph;

    public static void setUp(String testDataFileName) throws IOException {
        ClassLoader classLoader = DepthFirstPathsTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(testDataFileName);
        if (fileUrl == null)
            throw new FileNotFoundException(testDataFileName);

        MutableGraph<Integer> mutableGraph = GraphBuilder.undirected().build();
        Scanner scanner = new Scanner(fileUrl.openStream());
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (lineNumber < 2) {
                if (lineNumber == 0) { // Number of nodes
                    int nodesNumber = Integer.parseInt(line);
                    for (int i = 0; i < nodesNumber; i++)
                        mutableGraph.addNode(i);
                }
                lineNumber++;
                continue;
            }
            String[] nodes = line.split(" ");
            int nodeU = Integer.parseInt(nodes[0]);
            int nodeV = Integer.parseInt(nodes[1]);
            mutableGraph.putEdge(nodeU, nodeV);
            lineNumber++;
        }
        graph = mutableGraph;
    }
}
