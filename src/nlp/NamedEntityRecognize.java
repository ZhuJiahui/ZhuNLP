/**
 * 
 */
package nlp;

import java.io.IOException;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * @author ZhuJiahui705
 *
 */
public class NamedEntityRecognize {

	/**
	 * 
	 */
	public NamedEntityRecognize() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initialize the NER model
	 * @param modelFile
	 * @return AbstractSequenceClassifier<CoreLabel>
	 */
	public static AbstractSequenceClassifier<CoreLabel> initNERModel(String modelFile) {
		AbstractSequenceClassifier<CoreLabel> classifier = null;
		try {
			classifier = CRFClassifier.getClassifier(modelFile);
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return classifier;
	}
	
	/**
	 * NER
	 * @param wordSequence
	 * @param classifier
	 * @return String
	 */
	public static String ner(String wordSequence, AbstractSequenceClassifier<CoreLabel> classifier) {
		String nerResult = classifier.classifyToString(wordSequence);
		
		return nerResult;
	}
	
	/**
	 * NER
	 * @param wordSequence
	 * @param classifier
	 * @return String[]
	 */
	public static String[] nerToArray(String wordSequence, AbstractSequenceClassifier<CoreLabel> classifier) {
		String nerResult = classifier.classifyToString(wordSequence);
		
		return nerResult.split(" ");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String modelFile = "ner_model/english.all.3class.distsim.crf.ser.gz";
		String modelFile = "ner_model/chinese.misc.distsim.crf.ser.gz";
		String s1 = "»¨Ç§¹Ç Ï²»¶ É±ÚäÄ°";
		
		// Must initialize first.
		AbstractSequenceClassifier<CoreLabel> classifier = initNERModel(modelFile);
		String[] result = nerToArray(s1, classifier);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		//System.out.println(result);  // ×îºóÎÞ¿Õ¸ñ

	}

}
