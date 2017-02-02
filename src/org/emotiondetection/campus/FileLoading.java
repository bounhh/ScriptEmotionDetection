package org.emotiondetection.campus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * @author BOUDEFFA AMIN
 * @author TIFARINE MOHAMED AMINE
 * @author BOUGHELLOUT ZAKARYA
 * @author MERAZKA ABDERRAOUF
 *
 */
public class FileLoading {

    public ArrayList<String> readFile( String fileName ) throws Exception {
        ArrayList<String> ListWords = new ArrayList<String>();
        try ( BufferedReader br = new BufferedReader( new FileReader( fileName ) ) ) {
            for ( String line; ( line = br.readLine() ) != null; ) {
                line = line.trim();
                if ( !line.equals( "" ) )
                    ListWords.addAll( filterLines( line ) );
            }
        }
        return ListWords;
    }

    /*
     * filter line per line
     */
    public List<String> filterLines( String line ) {
        List<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer( line, " \t\n\r\f,.:;?![]'/\\@^_-()\"" );
        while ( st.hasMoreTokens() ) {
            String elem = st.nextToken();
            if ( elem.length() >= 3 )
                words.add( elem );
        }
        return words;
    }

    /*
     * separate words per pag
     */
    public Map<Integer, List<String>> separatePerPage( List<String> list ) {
        Map<Integer, List<String>> wppge = new HashMap<Integer, List<String>>();
        int pagesNbr;
        if ( list.size() % 250 == 0 )
            pagesNbr = list.size() / 250;
        else
            pagesNbr = list.size() / 250 + 1;
        List<String> tempList;
        for ( int i = 0; i < pagesNbr; i++ ) {
            tempList = new ArrayList<String>();
            for ( int j = 0; j < 250; j++ ) {
                if ( ( j + i * 250 ) < list.size() ) {
                    tempList.add( list.get( j + i * 250 ) );
                }
            }
            wppge.put( i + 1, tempList );
        }
        return wppge;
    }

    /*
     * main method
     */
    public static void main( String[] args ) throws Exception {
        FileLoading fl = new FileLoading();
        List<String> listWords = new ArrayList<String>();
        listWords = fl.readFile( "Titanic.txt" );

        PrintWriter writer = new PrintWriter( new File( "Result.txt" ) );
        Map<Integer, List<String>> wordsPerPage = new HashMap<Integer, List<String>>();
        wordsPerPage = fl.separatePerPage( listWords );
        for ( int i = 1; i <= wordsPerPage.size(); i++ ) {
            writer.write( "Page index: " + ( i ) + "\n" );
            for ( String str : wordsPerPage.get( i ) ) {
                writer.write( str + "\n" );
            }
            writer.write( "*********************************\n" );
        }
        System.out.println( "le fichier est crée" );
        writer.close();

    }

}
