package org.emotiondetection.campus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author BOUDEFFA AMIN
 * @author TIFARINE MOHAMED AMINE
 * @author BOUGHELLOUT ZAKARYA
 * @author MERAZKA ABDERRAOUF
 *
 */
public class EmotionClasses {

    private ArrayList<String>              Happiness = new ArrayList<String>(
            Arrays.asList( "love", "lol", "fun", "good", "happy", "nice", "awesome", "funny", "great", "excited" ) );

    private ArrayList<String>              Sadness   = new ArrayList<String>(
            Arrays.asList( "hurt", "miss", "Sorry", "Bad", "Sad", "Lost", "Cry", "Stress", "Wept", "Longing" ) );

    private ArrayList<String>              Anger     = new ArrayList<String>(
            Arrays.asList( "fucking", "angry", "bitch", "furious", "annoyed",
                    "possed", "yelling", "upset", "mad", "shut up" ) );

    private ArrayList<String>              Disgust   = new ArrayList<String>(
            Arrays.asList( "hate", "dislike", "Shit", "Stupid", "Fucking", "Disgusting", "Crap", "Bitch", "Sick" ) );

    private ArrayList<String>              Surprise  = new ArrayList<String>(
            Arrays.asList( "Surprise", "amazing", "wonder", "unexpected",
                    "can't belive", "weird", "suddenly", "odd", "strange" ) );

    private ArrayList<String>              Fear      = new ArrayList<String>(
            Arrays.asList( "afraid", "scared", "nervous", "worry", "security",
                    "fear", "what if", "threat", "freak", "dangerous" ) );

    private static List<ArrayList<String>> classes   = new ArrayList<ArrayList<String>>();

    public EmotionClasses() {
        classes.add( Happiness );
        classes.add( Sadness );
        classes.add( Anger );
        classes.add( Disgust );
        classes.add( Surprise );
        classes.add( Fear );
    }

    public List<ArrayList<String>> addInDictionary( List<ArrayList<String>> classes ) {
        DictionaryReader dict = new DictionaryReader();
        ArrayList<String> liste;
        ArrayList<String> synList;
        for ( int a = 0; a < 10; a++ ) {
            for ( int m = 0; m < classes.size(); m++ ) {
                liste = new ArrayList<String>( classes.get( m ) );
                ArrayList<String> tempList = liste;
                for ( int i = 0; i < liste.size(); i++ ) {
                    String str = liste.get( i );
                    try {
                        synList = dict.getListSynonyms( str );
                        if ( synList != null ) {
                            for ( int j = 0; j < synList.size(); j++ ) {
                                if ( !tempList.contains( synList.get( j ) ) )
                                    tempList.add( synList.get( j ) );
                            }
                        }
                        synList = null;
                    } catch ( IOException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                classes.set( m, tempList );
                tempList = null;

            }
        }
        return classes;
    }

    public List<ArrayList<String>> ReadDict() throws IOException {
        List<ArrayList<String>> classes = new ArrayList<ArrayList<String>>();
        ArrayList<String> tempList = new ArrayList<String>();
        FileLoading fileLoading = new FileLoading();
        for ( int i = 0; i < 6; i++ ) {
            String file = "class-" + i + ".txt";
            try {
                tempList = fileLoading.readFile( file );
                classes.add( tempList );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return classes;
    }

    public static void main( String[] args ) throws IOException {
        EmotionClasses ec = new EmotionClasses();
        List<ArrayList<String>> classes1 = new ArrayList<ArrayList<String>>();
        // classes1 = ec.addInDictionary( classes );
        for ( int i = 0; i < classes.size(); i++ ) {
            ArrayList<String> tempList = new ArrayList<String>( classes.get( i ) );
            FileWriter writer = new FileWriter( "Class-" + i + ".txt" );
            writer.write( tempList.toString() );
            writer.close();
        }
    }

}
