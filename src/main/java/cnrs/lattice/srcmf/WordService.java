package cnrs.lattice.srcmf;
 
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import cnrs.lattice.tools.utils.Tools;
 
@ManagedBean(name="wordService")
public class WordService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chemin = Params.userhome + Params.dir;
    private List<Word> words = new ArrayList<Word>();
    private SelectView selectView = findBean("selectView");
    
    
    public WordService (){
    	this.words = new ArrayList<Word>();
    }
    
    public List<Word> createWords() throws IOException {
    	words = setWords();
    	return words;
    }
    
    private List<Word> setWords() throws IOException{
    	List<Word> words = new ArrayList<Word>();
    	String content = "_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\n";
    	if(selectView.getTmpResPath().length() > 0)
    		content = Tools.readFile(selectView.getTmpResPath());//Tools.readFile(chemin + "res.conll");
    	words = new Reader().readMate(content);
		return words;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}