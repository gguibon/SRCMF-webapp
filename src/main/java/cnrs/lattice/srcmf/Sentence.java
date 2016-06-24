package cnrs.lattice.srcmf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.google.common.base.Joiner;

@ManagedBean
public class Sentence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0 ;
	private List<Word> words = new ArrayList<Word>();
	private String jsonSentence = "";
	private String jsonWords = "";
	
	public Sentence(){
		words = new ArrayList<Word>();
		id = 0;
		jsonSentence = "yop";
	}
	
	public static Sentence create() {
		return new Sentence();
	}
	
	public void setWords(List<Word> words){
		this.words = words;
	}
	
	public List<Word> getWords(){
		return this.words;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setJsonWords(String jsonWords){
		this.jsonWords = jsonWords;
	}
	
	public String getJsonWords(){
		createJsonSentence();
		return jsonWords;
	}
	
	public void setJsonSentence(String jsonSentence){
		this.jsonSentence = jsonSentence;
	}
	
	public String getJsonSentence(){
		createJsonSentence();
		return jsonSentence;
	}
	
	public void createJsonSentence(){
//		StringBuilder sb = new StringBuilder();
		Joiner joiner_json = Joiner.on(",");
		List<String> json_words = new ArrayList<String>();
		for(Word w : words){
			json_words.add( String.format("\"%s\" : { \"cat\" : \"%s\", "
					+ "\"gov\" : { \"%s\" : \"%s\" },	" //gov and syn
					+ "\"lemma\" : \"%s\", "
					+ "\"nb\" : \"%s\", "
					+ "\"t\" : \"%s\", "
					+ "\"token\" : \"%s\" }", w.getIdSent(), w.getPosPredictedWapiti(), w.getGovernorP()
					, w.getSynFunctionP(), w.getLemmaPredicted(), w.getIdSent(), w.getForm(), w.getForm()) );
		}
//		String json_sentence = String.format("&#60;script type='text/javascript'&#62;"
//				+ "$(document).ready(function() {'"
//				+ "draw('holder%s' , { %s } " // id, word format json
//					+ "}); "
//				+ "});"
//				+ "&#60;/script&#62;"
//				+ "&#60;div id='sentencediv2' class='sentencediv' style='margin: 10px;'&#62; "
//				+ "%s: %s&#60;/div&#62;" // id sent , forms
//				+ "&#60;div id='holder%s' style='overflow: auto;'&#62;&#60;/div&#62;" // 
//				, id, joiner_json.join(json_words), id, "sentence", id);
		jsonWords = joiner_json.join(json_words);
//		jsonSentence = json_sentence;
	}
}
