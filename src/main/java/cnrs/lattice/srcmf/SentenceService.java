package cnrs.lattice.srcmf;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cnrs.lattice.tools.corpus.Corpus;
 
@ManagedBean(name="sentenceService")
public class SentenceService implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String chemin = Params.userhome + Params.dir;
    private List<Sentence> sentences = new ArrayList<Sentence>();
    private SelectView selectView = findBean("selectView");
    
    
    public SentenceService (){
    	this.sentences = new ArrayList<Sentence>();
    }
    
    public List<Sentence> createSentences() throws Exception {
    	sentences = setSentences();
    	return sentences;
    }
    
    private List<Sentence> setSentences() throws Exception{
//    	List<Word> words_list = new ArrayList<>();
    	System.out.println("yopplaplapal");
    	List<Sentence> sentences = new ArrayList<>();
    	List<String> sentences_str = new ArrayList<String>();
//    	sentences_str.add("10	1	This	this	_	Prep	_	_	_	2	2	SjPer	SjPer	_\n"
//    			+ "11	2	is	be	_	VERconj	_	_	_	0	0	RelNC	RelNC	_\n"
//    			+ "12	3	a	a	_	A	_	_	_	4	4	_	_	_\n"
//    			+ "13	4	dummy	dummy	_	ADJ	_	_	_	5	5	ModA	ModA	_\n"
//    			+ "14	5	sentence	sentence	_	NOMcom	_	_	_	2	2	Obj	Obj	_\n\n");
    	
    String str = "1	This	_	this	_	Prep	_	_	2	2	SjPer	SjPer	_\n"
			+ "2	is	_	be	_	VERconj	_	_	0	0	RelNC	RelNC	_\n"
			+ "3	a	_	a	_	A	_	_	4	4	_	_	_\n"
			+ "4	dummy	_	dummy	_	ADJ	_	_	5	5	ModA	ModA	_\n"
			+ "5	sentence	_	sentence	_	NOMcom	_	_	2	2	Obj	Obj	_\n\n";
    	sentences_str.add(str);
//    	String content = "_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\n";
    	if(selectView.getTmpResPath().length() > 0)
//    		content = Tools.readFile(selectView.getTmpResPath());
    		sentences_str = Corpus.getSentences(selectView.getTmpResPath());
    	int numSent = 0;
    	for(String s : sentences_str){
    		Sentence sent = new Sentence();
    		sent.setWords( new Reader().readMate(s) );
    		sent.setId( numSent++ );
    		sentences.add(sent);
    	}
		return sentences;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}