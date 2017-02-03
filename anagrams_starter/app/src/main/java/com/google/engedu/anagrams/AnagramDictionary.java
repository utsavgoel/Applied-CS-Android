/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> list= new ArrayList<String>();
    HashMap<String,ArrayList<String>> lettersToWord= new HashMap<String,ArrayList<String>>();
    HashSet<String> wordSet= new HashSet<String>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line, key;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            list.add(word);
            wordSet.add(word);
            ArrayList arraylist = new ArrayList();
            key = sortLetters(word);
            if (lettersToWord.containsKey(key) == false) {
                arraylist.add(word);
                lettersToWord.put(key, arraylist);
            }
            else {
                arraylist = (ArrayList)lettersToWord.get(key);//
                arraylist.add(word);
                lettersToWord.put(key, arraylist);
            }
        }
    }
    public String sortLetters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sorted=new String(chars);
        return sorted;
    }

        public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base))
            return true;
        else
            return false;//checks whether it is a substring or not
        }


   /* public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        result=sortLetters(targetWord);
        return result;
    }*/

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> preresult = new ArrayList<String>();
        for(char ch='a';ch<='z';ch++) {
            String newWord;
            newWord = word + ch;
            newWord = sortLetters(newWord);
            if (lettersToWord.containsKey(newWord)) {
                preresult =(ArrayList)lettersToWord.get(newWord);//
                for (int i = 0; i < preresult.size(); i++) {
                    result.add(String.valueOf(preresult.get(i)));
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        Random random = new Random();
        int  i = random.nextInt(list.size());
        return list.get(i);
   }
}