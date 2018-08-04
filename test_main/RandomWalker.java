package test_main;

public class RandomWalker {

    public String randomWalk(DWGraph graph) {
        return String.join(" ", graph.randomNext());
    }

}