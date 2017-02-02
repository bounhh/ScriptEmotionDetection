package org.emotiondetection.campus;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author BOUDEFFA AMIN
 * @author TIFARINE MOHAMED AMINE
 * @author BOUGHELLOUT ZAKARYA
 * @author MERAZKA ABDERRAOUF
 *
 */

public class ClassificationEmotion {

    static EmotionClasses obj = new EmotionClasses();

    public int getIndexOfClass( String word, List<ArrayList<String>> classes1 ) throws Exception {
        DictionaryReader dict = new DictionaryReader();
        FileLoading fileLoading = new FileLoading();
        word = word.toLowerCase();
        int indice = -1;
        for ( int i = 0; i < classes1.size(); i++ ) {
            ArrayList<String> tempList = new ArrayList<String>( classes1.get( i ) );
            for ( int j = 0; j < tempList.size(); j++ ) {
                if ( tempList.get( j ).toString().equals( word ) ) {
                    indice = i;
                    break;

                } else {
                    List<String> synonymList = new ArrayList<String>();
                    synonymList = dict.getListSynonyms( word );
                    if ( synonymList != null ) {
                        for ( int k = 0; k < synonymList.size(); k++ ) {
                            if ( tempList.get( j ).toString().equals( synonymList.get( k ) ) ) {
                                indice = i;
                                List<String> ancienList = new ArrayList<String>();
                                String file = "class-" + i + ".txt";
                                ancienList = fileLoading.readFile( file );
                                if ( !ancienList.contains( word ) ) {
                                    ancienList.add( word );
                                    FileWriter writer = new FileWriter( "Classnew-" + i + ".txt" );
                                    writer.write( ancienList.toString() );
                                    writer.close();
                                }
                                break;
                            }

                        }
                    }

                }
            }
        }
        return indice;
    }

    public Map<Integer, ArrayList<Integer>> getClassName( Map<Integer, List<String>> wordsPerPage ) throws Exception {
        Map<Integer, ArrayList<Integer>> tempMap = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> l0 = new ArrayList<Integer>();
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        ArrayList<Integer> l3 = new ArrayList<Integer>();
        ArrayList<Integer> l4 = new ArrayList<Integer>();
        ArrayList<Integer> l5 = new ArrayList<Integer>();
        EmotionClasses ec = new EmotionClasses();
        List<ArrayList<String>> dictionnaire = ec.ReadDict();
        for ( int i = 0; i < wordsPerPage.size(); i++ ) {
            int[] tab = new int[6];
            List<String> tempList = new ArrayList<String>();
            tempList = wordsPerPage.get( i + 1 );
            int index = -1;
            for ( String str : tempList ) {
                index = getIndexOfClass( str, dictionnaire );
                switch ( index ) {
                case 0:
                    tab[0] += 1;
                    break;
                case 1:
                    tab[1] += 1;
                    break;
                case 2:
                    tab[2] += 1;
                    break;
                case 3:
                    tab[3] += 1;
                    break;
                case 4:
                    tab[4] += 1;
                    break;
                case 5:
                    tab[5] += 1;
                    break;
                default:
                    break;
                }
            }
            l0.add( tab[0] );
            l1.add( tab[1] );
            l2.add( tab[2] );
            l3.add( tab[3] );
            l4.add( tab[4] );
            l5.add( tab[5] );
        }
        tempMap.put( 0, l0 );
        tempMap.put( 1, l1 );
        tempMap.put( 2, l2 );
        tempMap.put( 3, l3 );
        tempMap.put( 4, l4 );
        tempMap.put( 5, l5 );
        return tempMap;
    }

    public static void main( String[] args ) throws Exception {
        ClassificationEmotion c = new ClassificationEmotion();
        FileLoading fl = new FileLoading();
        List<String> listWords = new ArrayList<String>();
        listWords = fl.readFile( "titanic.txt" );

        PrintWriter writer = new PrintWriter( new File( "ResultFile.txt" ) );
        Map<Integer, List<String>> wordsPerPage = new HashMap<Integer, List<String>>();
        Map<Integer, ArrayList<Integer>> emotionMap = new HashMap<Integer, ArrayList<Integer>>();

        wordsPerPage = fl.separatePerPage( listWords );
        emotionMap = c.getClassName( wordsPerPage );

        for ( int i = 0; i < emotionMap.size(); i++ ) {

            for ( Integer val : emotionMap.get( i ) ) {
                writer.write( val.toString() + " " );
            }
            writer.write( "\n" );
        }
        System.out.println( "le fichier est crée" );
        writer.close();

    }
}
