package test_main;

import java.io.File;

public class ReadFromPath {

    private File fileDir;

    /**
	 * 获取一个txt文件的路径
	 * @param dir
	 */
    public void setFileDir(String dir) {
        this.fileDir = new File(dir);
    }
    
    public File getFileDir() {
		return fileDir;
	}

    /**
	 * 读取一个txt文件，将其中的单次信息变为图
	 * @param graph
	 */
    public void generateGraph(DWGraph graph) {
		graph.readFile(fileDir);
    }
    
}
