package test_main;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class ShortestPathWorker {

    private String word1;
    private String word2;

    private Map<Integer, File> mapImg;

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public Map<Integer, File> getMapImg() {
        return mapImg;
    }

    public Vector<String> calcShortestPath(DWGraph graph) {
		Vector<String> paths = new Vector<String>();
		
		Map<String, Vector<String[]>> tmpMap = graph.getShortestPath(word1, word2);
		Iterator<Entry<String, Vector<String[]>>> iterTM = tmpMap.entrySet().iterator();
		String tMsgStr;
		String tPathStr;
		
		GraphViz graphViz;
		File out;
		int imgCount = 0;
		mapImg = new HashMap<Integer, File>();
		
		while (iterTM.hasNext()) {
			tMsgStr = "";
			tPathStr = "";
			Entry<String, Vector<String[]>> tEntry = iterTM.next();
			if (tEntry.getKey().equals("RetCode")) {
				tMsgStr = "RetCode";
				tPathStr = tEntry.getValue().get(0)[0];
				paths.add(tMsgStr + "==" + tPathStr);
			} else {
				Iterator<String[]> iterTPath = tEntry.getValue().iterator();
				while (iterTPath.hasNext()) {
					String[] tPath = iterTPath.next();
					if (tPath[0].equals("Length")) {
						tMsgStr = tEntry.getKey();
						tMsgStr += "==";
						tMsgStr += tPath[1];
					} else {
						tPathStr = String.join("->", tPath);
						graphViz = new GraphViz();
						graphViz.addln(graph.getBaseDot());
						graphViz.addln("edge[color=\"#FF6347\"]");
						String outV = tPath[0];
						graphViz.addln(outV + "[fillcolor=\"#FFAA22\", style=filled]");
						for (int i = 1; i < tPath.length; i++) {
							graphViz.addln(outV + "->" + tPath[i]);
							outV = tPath[i];
							graphViz.addln(outV + "[fillcolor=\"#FFAA22\", style=filled]");
						}
						graphViz.addln(graphViz.end_graph());
						out = new File("src/source/image/" + Integer.toString(imgCount) + ".png");
						graphViz.writeGraphToFile(graphViz.getGraph(graphViz.getDotSource(), "png"), out);
						mapImg.put(imgCount, out);
						imgCount++;
						paths.add(tMsgStr + "==" + tPathStr);
					}
				}
			}
		}
		
		return paths;
	}

}