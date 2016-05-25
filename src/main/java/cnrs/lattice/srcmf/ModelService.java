package cnrs.lattice.srcmf;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;


@ManagedBean(name = "modelService")
// @ApplicationScoped
public class ModelService {
	private String chemin = Params.userhome + Params.dir;
	private List<Model> models = new ArrayList<Model>();

	public ModelService() {
		this.models = new ArrayList<Model>();
	}

	public List<Model> createModels() throws IOException {
		models = setModels();
		return models;
	}

	private List<Model> setModels() throws IOException{
    	List<Model> models = new ArrayList<Model>();
    	String[] directories;
    	try {
			directories = getDirs(chemin+"models");
		
		
    	for(String dir : directories){
    		Model m = new Model();
    		m.setName(dir);
    		m.setMateName(dir+".mategoldmodel");
    		m.setWapitiName(dir+".wapitimodel");
    		models.add(m);
    	}
    	
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return models;
    }

	private String[] getDirs(String dirPath) throws Exception {
		File file = new File(dirPath);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		return directories;
	}
}