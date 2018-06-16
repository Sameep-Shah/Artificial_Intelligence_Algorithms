
import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author sameepshah
 */
public class EightQueens {

    // The random start board generator for 8 queens
    public Queen[] generateBoard() {
        Queen[] start = new Queen[8];
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            start[i] = new Queen(r.nextInt(8), i);
        }
        return start;
    }

    public static void main(String[] args) {
        EightQueens q = new EightQueens();
        int numberOfRuns = 10000;
        double hcSNodes = 0.0;
        double hcUNodes = 0.0;
        int asSNodes = 0;
        int asUNodes = 0;
        int hcSuccesses = 0;
        int aSuccesses = 0;
        //loop for 10000 random starts
        for (int i = 0; i < numberOfRuns; i++) {
            Queen[] startBoard = q.generateBoard();
            
            //object for calling hill climbing class
            hillClimbing h = new hillClimbing(startBoard);
            // object for calling Simulated Annealing class
            SimulatedAnnealing s = new SimulatedAnnealing(startBoard);
            // calling hill climbing algorithm
            Node computeHill = h.hilClimbing();
            //calling simulated annealing algorithm and setting initial temperature and step
            Node annealSolved = s.simulatedAnneal(1, 0.001);
            //checking if the goal state is reached or no
            if (computeHill.getHeuristic() == 0) {

                hcSNodes = hcSNodes + h.getNodesGenerated();
                hcUNodes = hcUNodes + h.getNodesGenerated1();

                hcSuccesses++;
            }

            if (annealSolved.getHeuristic() == 0) {

                asSNodes = asSNodes + s.getNodesGenerated();

                aSuccesses++;
            } else {
                asUNodes = asUNodes + s.getNodesGenerated();
            }

        }

        double avgSuccessMovehc = hcSNodes / (double) hcSuccesses;
        double avgStuckMovehc = hcUNodes / (double) (numberOfRuns - hcSuccesses);
        double avgSuccessMoveas = asSNodes / (double) aSuccesses;
        double avgStuckMoveas = asUNodes / (double) (numberOfRuns - aSuccesses);

        double hcPercent = ((double) hcSuccesses / (double) numberOfRuns) * 100;
        double asPercent = ((double) aSuccesses / (double) numberOfRuns) * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        String percenthc = df.format(hcPercent);
        String percentas = df.format(asPercent);
        String successMovehc = df.format(avgSuccessMovehc);
        String stuckMovehc = df.format(avgStuckMovehc);
        String successMoveas = df.format(avgSuccessMoveas);
        String stuckMoveas = df.format(avgStuckMoveas);
        System.out.println("Hill climbing: " + percenthc + "% , " + successMovehc + " , " + stuckMovehc);
        System.out.println("Simulated Annealing: " + percentas + "% , " + successMoveas + " , " + stuckMoveas);

    }

}
