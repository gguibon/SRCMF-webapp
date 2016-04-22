package cnrs.lattice.srcmf;
 
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import cnrs.lattice.tools.utils.Tools;
 
@ManagedBean(name="wordService")
//@ViewScoped
public class WordService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chemin = Params.dir;
    private List<Word> words = new ArrayList<Word>();
    
    
    public WordService (){
    	this.words = new ArrayList<Word>();
    }
    
    public List<Word> createWords() throws IOException {
    	words = setWords();
    	return words;
    }
    
    private List<Word> setWords() throws IOException{
    	List<Word> words = new ArrayList<Word>();
    	String content = Tools.readFile(chemin + "res.conll");
    	words = new Reader().readMate(content);
		return words;
    }
}