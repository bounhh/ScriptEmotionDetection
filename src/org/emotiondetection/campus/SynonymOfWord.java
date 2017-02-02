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

public class SynonymOfWord {

    public void getNoun() {
        try {
            String url = "dict";
            IDictionary dict = new Dictionary( new File( url ) );
            dict.open();
            IIndexWord idxWord = dict.getIndexWord( "Sorry", POS.ADJECTIVE );
            IWordID wordID = idxWord.getWordIDs().get( 0 );
            IWord word = dict.getWord( wordID );
            ISynset synset = word.getSynset();
            for ( IWord w : synset.getWords() )
                System.out.println( w.getLemma() );

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main( String[] args ) {
        SynonymOfWord synonyme = new SynonymOfWord();
        synonyme.getNoun();

    }

}
