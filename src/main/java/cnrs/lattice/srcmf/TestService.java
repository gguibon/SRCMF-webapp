package cnrs.lattice.srcmf;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

import cnrs.lattice.tools.utils.Tools;


@ManagedBean(name = "testService")
// @ApplicationScoped
public class TestService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String chemin = Params.userhome + Params.dir;
	private List<TestFile> testFiles = new ArrayList<TestFile>();

	public TestService() {
		this.testFiles = new ArrayList<TestFile>();
	}

	public List<TestFile> createTestFiles() throws IOException {
		testFiles = setTestFiles();
		return testFiles;
	}
	
	private List<TestFile> setTestFiles() throws IOException{
		List<TestFile> testFiles = new ArrayList<TestFile>();
		try {
			List<File> files = Tools.dir2listefiles(chemin+"tests");
			for(File f : files){
				TestFile tf = new TestFile();
				String baseName = FilenameUtils.getBaseName(f.getAbsolutePath());
				tf.setName(baseName);
				tf.setAbsolutePath(f.getAbsolutePath());
				testFiles.add(tf);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testFiles;
    }

}