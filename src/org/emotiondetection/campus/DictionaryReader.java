package org.emotiondetection.campus;

import java.io.File;
import java.io.IOException;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

/**
 * 
 * @author BOUDEFFA AMIN
 * @author TIFARINE MOHAMED AMINE
 * @author BOUGHELLOUT ZAKARYA
 *
 */
public class DictionaryReader {

	public void testDictionary() throws IOException {
		// construct the dictionary object and open it
		String url = "dict";
		IDictionary dict = new Dictionary(new File(url));
		dict.open();

		// look up first sense of the word "dog"
		IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
		IWordID wordID = idxWord.getWordIDs().get(0);
		IWord word = dict.getWord(wordID);
		System.out.println("Id = " + wordID);
		ISynset synset = word.getSynset();

		// iterate over words associated with the synset
		for (IWord w : synset.getWords())
			System.out.println(w.getLemma());
	}

	public static void main(String[] args) throws IOException{
		DictionaryReader dr = new DictionaryReader();
		dr.testDictionary();
	}
}
