/**
 * 
 */
package nlp;

import java.io.StringReader;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.util.MyStaticValue;
import org.apache.commons.lang3.StringUtils;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * @author ZhuJiahui705
 *
 */
public class WordSegment {

	/**
	 * 
	 */
	public WordSegment() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Chinese word segment with POS
	 * @param sentence
	 * @return String
	 */
	public static String CNSegmentWithPOS(String sentence) {
		
		List<Term> parse = NlpAnalysis.parse(sentence);
		
		String[] segmentResult = new String[parse.size()];
		for (int i = 0; i < parse.size(); i++) {
			segmentResult[i] = parse.get(i).toString();
		}
		return StringUtils.join(segmentResult, " ");
	}
	
	/**
	 * Chinese word segment with POS
	 * @param sentence
	 * @return String[]
	 */
	public static String[] CNSegmentWithPOSToArray(String sentence) {
		
		List<Term> parse = NlpAnalysis.parse(sentence);
		
		String[] segmentResult = new String[parse.size()];
		for (int i = 0; i < parse.size(); i++) {
			segmentResult[i] = parse.get(i).toString();
		}
		
		return segmentResult;
	}
	
	/**
	 * Chinese word segment without POS
	 * @param sentence
	 * @return String
	 */
	public static String CNSegmentWithoutPOS(String sentence) {
		
		List<Term> parse = NlpAnalysis.parse(sentence);
		
		String[] segmentResult = new String[parse.size()];
		for (int i = 0; i < parse.size(); i++) {
			segmentResult[i] = parse.get(i).toString().split("/")[0];
		}
		
		return StringUtils.join(segmentResult, " ");
	}
	
	/**
	 * Chinese word segment without POS
	 * @param sentence
	 * @return String[]
	 */
	public static String[] CNSegmentWithoutPOSToArray(String sentence) {
		
		List<Term> parse = NlpAnalysis.parse(sentence);
		
		String[] segmentResult = new String[parse.size()];
		for (int i = 0; i < parse.size(); i++) {
			segmentResult[i] = parse.get(i).toString().split("/")[0];
		}
		
		return segmentResult;
	}
	
	
    //==============================================================

	/**
	 * English word segment with POS
	 * @param sentence
	 * @param maxentTagger
	 * @return String
	 */
	public static String ENSegmentWithPOS(String sentence, MaxentTagger maxentTagger) {
		
		String tagged = maxentTagger.tagString(sentence);
		return tagged.trim();
	}
	
	
	/**
	 * English word segment with POS
	 * @param sentence
	 * @param maxentTagger
	 * @return String[]
	 */
	public static String[] ENSegmentWithPOSToArray(String sentence, MaxentTagger maxentTagger) {
		
		String tagged = maxentTagger.tagString(sentence).trim();
		String[] segmentResult = tagged.split(" ");
		return segmentResult;
	}
	
	/**
	 * English word segment without POS
	 * @param sentence
	 * @return String
	 */
	public static String ENSegmentWithoutPOS(String sentence) {
		
		List<CoreLabel> rawWords = PTBTokenizer.coreLabelFactory().getTokenizer(new StringReader(sentence)).tokenize();

		String[] segmentResult = new String[rawWords.size()];
		
		for (int i = 0; i < rawWords.size(); i++) {
			segmentResult[i] = rawWords.get(i).word();
		}

		return StringUtils.join(segmentResult, " ");
	}
	
	/**
	 * English word segment without POS
	 * @param sentence
	 * @return String[]
	 */
	public static String[] ENSegmentWithoutPOSToArray(String sentence) {
		
		List<CoreLabel> rawWords = PTBTokenizer.coreLabelFactory().getTokenizer(new StringReader(sentence)).tokenize();

		String[] segmentResult = new String[rawWords.size()];
		
		for (int i = 0; i < rawWords.size(); i++) {
			segmentResult[i] = rawWords.get(i).word();
		}

		return segmentResult;
	}
	
	
	/**
	 * Initialize the English POS model
	 * The "english-left3words-distsim.tagger" is highly recommended.
	 * @param modelFile
	 * @return
	 */
	public static MaxentTagger initENPOSModel(String modelFile) {
		// MaxentTagger maxentTagger = new MaxentTagger("pos_model/english-left3words-distsim.tagger");
		MaxentTagger maxentTagger = new MaxentTagger(modelFile);
		return maxentTagger;
	}
	
	/**
	 * Initialize the Chinese user dictionary and ambiguity dictionary
	 * @param userDictFile
	 * @param ambiguityDictFile
	 */
	public static void initCNDict(String userDictFile, String ambiguityDictFile) {
		
		MyStaticValue.userLibrary = userDictFile;
		MyStaticValue.ambiguityLibrary = ambiguityDictFile;
	}
	
	/**
	 * Initialize the Chinese user dictionary
	 * @param userDictFile
	 */
	public static void initCNUserDict(String userDictFile) {
		
		MyStaticValue.userLibrary = userDictFile;
	}
	
	
	/**
	 * Initialize the Chinese ambiguity dictionary
	 * @param ambiguityDictFile
	 */
	public static void initCNAmbiguityDict(String ambiguityDictFile) {
		
		MyStaticValue.ambiguityLibrary = ambiguityDictFile;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String sentence = "���½�����������Ѹ�ײ����ڶ�֮�Ƴ���ʹ�������û��Ȥ����ʵۣ��Ȱ�̫����ն��ɱ����һ�������ˣ�ȫ�����ڵ��¡����в����������������Ľ���˾��٣������������Ȩ���ơ�Ҳ���ڱ��Ⱥ���С̫����Ҳ���Ź���ʷ�Ƶ�ʱ���ź�ԩ֮��������������ţ��ѻ��Ϻʹ��ǳ�����ס���Ժ��ٷ��������ࡣ������������ٰ��ɵ���ս���������̫����Ҳ���͵ش�������������ս����ͬ־��ͨ����������������������������Ϊʱ�������꣨��Ԫ�����������������꣩�Ļ¹�ͳ�����µ�ʱ�����ڽ����ˡ�";
		
		WordSegment.initCNDict("cn_segment/user.dic", "cn_segment/ambiguity.dic");
		
		long startTime = System.currentTimeMillis();
		String result1 = CNSegmentWithoutPOS(sentence);
		long endTime = System.currentTimeMillis();
		
		System.out.println("��������ʱ�䣺 " + (endTime - startTime) + "ms");
		System.out.println(result1);
		
		
		/*
		String sentence = "PTBTokenizer is a an efficient, fast, deterministic tokenizer. (For the more technically inclined, it is implemented as a finite automaton, produced by JFlex.) On a 2015 laptop computer, it will tokenize text at a rate of about 1,000,000 tokens per second. While deterministic, it uses some quite good heuristics, so it can usually decide when single quotes are parts of words, when periods do an don't imply sentence boundaries, etc. Sentence splitting is a deterministic consequence of tokenization: a sentence ends when a sentence-ending character (., !, or ?) is found which is not grouped with other characters into a token (such as for an abbreviation or number), though it may still include a few tokens that can follow a sentence ending character as part of the same sentence (such as quotes and brackets).";
		MaxentTagger maxentTagger = WordSegment.initENPOSModel("pos_model/english-left3words-distsim.tagger");
		
		long startTime = System.currentTimeMillis();
		//String result1 = ENSegmentWithPOS(sentence, maxentTagger);
		String[] result1 = ENSegmentWithoutPOSToArray(sentence);
		long endTime = System.currentTimeMillis();
		
		System.out.println("��������ʱ�䣺 " + (endTime - startTime) + "ms");
		//System.out.println(result1);
		
		for (int i = 0; i < result1.length; i++) {
			System.out.println(result1[i]);
		}
		*/
	}

}
