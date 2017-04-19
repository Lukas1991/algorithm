package tools;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CategorizeProblem {

    private Map<Integer, Problem> dpProblems = new HashMap<>();

    private Map<Integer, Problem> googleProblems = new HashMap<>();

    private Stream<String> getLinesFromFile(String file) throws IOException {
        URL url = getClass().getClassLoader().getResource(file);
        return Files.lines(Paths.get(url.getPath()));
    }

    public void getDpProblems(String file) {
        try (Stream<String> stream = getLinesFromFile(file)) {
            stream.forEach(s -> {
                Problem problem = new Problem(s.split("\t"));
                dpProblems.put(problem.getNumber(), problem);
            });
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

    public void getGoogleProblems(String file) {
        try (Stream<String> stream = getLinesFromFile(file)) {
            stream.forEach(s -> {
                Problem problem = new Problem(s.split("\t"));
                googleProblems.put(problem.getNumber(), problem);
            });
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

    public List<Problem> findDPInGoogle() {
        List<Problem> problemList = new ArrayList<>();

        dpProblems.keySet().forEach(num -> {
            if (googleProblems.containsKey(num)) {
                problemList.add(googleProblems.get(num));
            }
        });
        return problemList;
    }

    public static void main(String[] args) {
        CategorizeProblem obj = new CategorizeProblem();
        obj.getDpProblems("DP.txt");
        obj.getGoogleProblems("Google.txt");

        List<Problem> dps = obj.findDPInGoogle();
        dps.stream()
            .filter(p -> p.getDifficulty().equals(Problem.Difficulty.Medium))
            .sorted((Problem p1, Problem p2) -> p1.getNumber() - p2.getNumber())
            .forEach(p -> System.err.println(p.toString()));
    }
}
