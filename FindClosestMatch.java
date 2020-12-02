package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Arrays;

/**
 * Takes a list of URLs on the command line and prints the two URLs whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {
    /**
     * Compute and print the two URLs whose web pages have the highest cosine
     * similarity.
     * @param args URL input Strings
     * @throws IOException exceptions thrown by the URL
     */
    public static void main(String[] args) throws IOException {
        int n = args.length;
        Document[] docs = new Document[n];
        for (int i=0; i<n; i++) {
            docs[i] = new Document(args[i]);
        }

        Map<Double, List<Integer>> distance = new TreeMap<>(Collections.reverseOrder());
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                distance.put(docs[i].computeSimilarity(docs[j]), new ArrayList<>(Arrays.asList(i, j)));
            }
        }

        System.out.println("Most Similar pair:");
        List<Integer> index = distance.get(distance.keySet().toArray()[0]);
        System.out.println(docs[index.get(0)]);
        System.out.println(docs[index.get(1)]);

    }
}
