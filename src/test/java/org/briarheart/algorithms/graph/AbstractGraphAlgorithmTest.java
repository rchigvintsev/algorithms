package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractGraphAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tinyCG.txt";

    protected static Graph<Integer> graph;

    @BeforeClass
    public static void setUp() throws IOException {
        ClassLoader classLoader = DepthFirstPathsTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(TEST_DATA_FILE_NAME);
        if (fileUrl == null)
            throw new FileNotFoundException(TEST_DATA_FILE_NAME);

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
