import java.util.concurrent.TimeUnit;

/**
 * author: Smău George Robert @ FII/2A3
 */
public class Lab1 {
    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
//        lab1.compulsory();
        lab1.homework(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    void compulsory() {
        String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "Javascript", "PHP", "Swift", "Java" };
        int n = (int)(Math.random() * 1_000_000);
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        while(n > 9) {
            int newN = 0;
            while(n != 0) {
                newN += n % 10;
                n /= 10;
            }
            n = newN;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    private int getDigitCount(int n) {
        int copyN = n, count = 0;
        do {
            count ++;
            copyN /= 10;
        } while(copyN > 0);
        return count;
    }

    private void printMatrix(boolean[][] matrix, int n) {
        StringBuilder matrixString = new StringBuilder();
        int cellSize = 2 + getDigitCount(n);

        matrixString.append("╔").append("═".repeat(cellSize));
        for(int node = 0; node < n; node ++) {
            matrixString.append("╦").append("═".repeat(cellSize));
        }
        matrixString.append("╗").append("\n");

        matrixString.append("║").append(" ".repeat(cellSize)).append("║");
        for(int node = 0; node < n; node ++) {
            int count = getDigitCount(node);
            int spaceCount = cellSize - count;
            matrixString.append(" ".repeat(spaceCount / 2));
            matrixString.append(node).append(" ".repeat((spaceCount + 1) / 2));
            matrixString.append("║");
        }
        matrixString.append("\n");
        for(int node1 = 0; node1 < n; node1 ++) {
            matrixString.append("╠").append("═".repeat(cellSize));
            for(int node = 0; node < n; node ++) {
                matrixString.append("╬").append("═".repeat(cellSize));
            }
            matrixString.append("╣").append("\n").append("║");
            int count = getDigitCount(node1);
            int spaceCount = cellSize - count;
            matrixString.append(" ".repeat(spaceCount / 2));
            matrixString.append(node1).append(" ".repeat((spaceCount + 1) / 2));

            for(int node2 = 0; node2 < n; node2 ++) {
                spaceCount = cellSize - 1;
                matrixString.append("║").append(" ".repeat(spaceCount / 2));
                matrixString.append(matrix[node1][node2] ? "●" : " ").append(" ".repeat((spaceCount + 1) / 2));
            }
            matrixString.append("║").append("\n");
        }
        matrixString.append("╚").append("═".repeat(cellSize));
        for(int node = 0; node < n; node ++) {
            matrixString.append("╩").append("═".repeat(cellSize));
        }
        matrixString.append("╛").append("\n");

        System.out.println(matrixString);
    }

    void homework(int n, int k) {
        long StartTime = System.currentTimeMillis();
        boolean[][] AdjacencyMatrix = new boolean[n][n];
        boolean LARGE = n > 50;

        if(k > n) // upper bound of k is n
            k = n;

        // Create clique
        for(int node1 = 0; node1 < k; node1 ++) {
            for(int node2 = 0; node2 < k; node2 ++) {
                if(node1 != node2) {
                    AdjacencyMatrix[node1][node2] = true;
                    AdjacencyMatrix[node2][node1] = true;
                }
            }
        }

        // Nodes from k to 2 * k - 1 form a stable set

        // Create random edges for the rests of the nodes
        for(int node1 = 2 * k; node1 < n; node1 ++) {
            int degree = (int)(Math.random() * n);
            for(int i = 0; i < degree; i ++) {
                int node2 = (int)(Math.random() * n);
                if(node1 != node2) {
                    AdjacencyMatrix[node1][node2] = true;
                    AdjacencyMatrix[node2][node1] = true;
                }
            }
        }

        if(!LARGE) printMatrix(AdjacencyMatrix, n);

        int edgeCount = 0;
        for(int node1 = 0; node1 < n; node1 ++) {
            for(int node2 = node1 + 1; node2 < n; node2 ++) {
                edgeCount += AdjacencyMatrix[node1][node2] ? 1 : 0;
            }
        }

        System.out.println("The number of edges is " + edgeCount);

        int minDegree = edgeCount + 1, maxDegree = -1;
        for(int node1 = 0; node1 < n; node1 ++) {
            int currentDegree = 0;
            for(int node2 = 0; node2 < n; node2 ++) {
                currentDegree += AdjacencyMatrix[node1][node2] ? 1 : 0;
            }

            if(currentDegree < minDegree)
                minDegree = currentDegree;

            if(currentDegree > maxDegree)
                maxDegree = currentDegree;
        }

        System.out.println("Δ(G) = " + minDegree);
        System.out.println("δ(G) = " + maxDegree);

        int degreeSum = 0;
        for(int node1 = 0; node1 < n; node1 ++) {
            for(int node2 = 0; node2 < n; node2 ++) {
                degreeSum += AdjacencyMatrix[node1][node2] ? 1 : 0;
            }
        }

        System.out.println("Sum of degrees is " + (degreeSum == 2 * edgeCount ? "" : "not ") + "equal to 2 * E");

        long EndTime = System.currentTimeMillis();
        System.out.println("Execution time: " + TimeUnit.MILLISECONDS.toSeconds(EndTime - StartTime) + " seconds");
    }

    void bonus() {

    }
}