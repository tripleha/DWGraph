package test_main;

import java.util.regex.Pattern;

public class BridgeWordWorker {

    private String word1;
    private String word2;

    private String inputText;

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String queryBridgeWords(DWGraph graph) {
        String[] tmpList = graph.getBridgingList(word1, word2);
		if (tmpList.length != 0) {
			if (tmpList[0].equals("0")) {
				return "No \"" + word1 + "\" in the graph!";
			} else if (tmpList[0].equals("1")) {
				return "No \"" + word2 + "\" in the graph!";
			} else if (tmpList[0].equals("2")) {
				return "No \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
			} else {
				String result = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ";
				if (tmpList.length > 1) {
					result = result + String.join(", ", tmpList);
					result = Pattern.compile(", " + tmpList[tmpList.length - 1] + "$").matcher(result).replaceAll(", and " + tmpList[tmpList.length - 1]);
					return result;
				} else {
					return result + tmpList[0];
				}
			}
		} else {
			return "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
		}
    }

    public String generateNewText(DWGraph graph) {
        return String.join(" ", graph.makeNewText(inputText));
    }

}