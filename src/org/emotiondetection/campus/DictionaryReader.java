package org.emotiondetection.campus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @author MERAZKA ABDERRAOUF
 *
 */
public class DictionaryReader {

    String      url  = "dict";
    IDictionary dict = new Dictionary( new File( url ) );

    public ArrayList<String> getSynonyms( String str, POS p ) throws IOException {
        ArrayList<String> synonyms = new ArrayList<String>();
        try {
            // look up first sense of the word "dog"
            IIndexWord idxWord = dict.getIndexWord( str, p );
            IWordID wordID = idxWord.getWordIDs().get( 0 );
            IWord word = dict.getWord( wordID );
            ISynset synset = word.getSynset();
            // iterate over words associated with the synset
            for ( IWord w : synset.getWords() )
                synonyms.add( w.getLemma() );

        } catch ( Exception e ) {

        }
        return synonyms;
    }

    public ArrayList<String> getListSynonyms( String word ) throws IOException {
        ArrayList<String> elem = new ArrayList<String>();
        // construct the dictionary object and open it
        dict.open();
        List<POS> pos = new ArrayList<POS>();
        pos.add( POS.NOUN );
        pos.add( POS.ADJECTIVE );
        pos.add( POS.VERB );
        pos.add( POS.ADVERB );
        for ( POS p : pos ) {
            elem = getSynonyms( word, p );
            if ( !elem.isEmpty() ) {
                return elem;
            }
        }
        return null;
    }

    public static void main( String[] args ) {
        DictionaryReader dr = new DictionaryReader();
        ArrayList<String> hs = new ArrayList<String>();
        try {
            hs = dr.getListSynonyms( "hurt" );
            if ( hs != null ) {
                for ( String str : hs ) {
                    System.out.println( str );
                }
            }
        } catch ( IOException e ) {

        }
    }
}
