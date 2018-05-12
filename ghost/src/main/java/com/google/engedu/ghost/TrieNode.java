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

package com.google.engedu.ghost;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
//        if(s== null || s.length()==0){
//            this.isWord=true;
//            return ;
//        }
//        if(!this.children.containsKey(String.valueOf(s.charAt(0) ) )){
//            TrieNode temp_trie_node=new TrieNode();
//            this.children.put(String.valueOf(s.charAt(0) ),temp_trie_node);
//            temp_trie_node.add(s.substring(1));
//        }
//        else{
//            this.children.get(String.valueOf(s.charAt(0) ) ).add(s.substring(1) );
//        }
        Log.e("MESSAGE","ANKI "+ s);
        if(s.length()==0){
            isWord=true;
        }else{
            String key=Character.toString(s.charAt(0));
            if(!children.containsKey(key))
                children.put(key,new TrieNode());
            children.get(key).add(s.substring(1));
        }
    }

    public boolean isWord(String s) {
        if(s.length()==0)
            return this.isWord;

        if(this.children.containsKey(String.valueOf(s.charAt(0) ) ) ){
            return this.children.get(String.valueOf(s.charAt(0))).isWord (s.substring(1) ) ;
        }
        return false;
    }

    public String getAnyWordStartingWith(String s) {
        TrieNode cur=this;
        int i=0;
        for(i=0;i<s.length();i++){
            if(cur.children.containsKey(String.valueOf(s.charAt(i)))){
                cur=cur.children.get(String.valueOf(s.charAt(i)) );
                Log.d("WORD LETTER:",""+s.charAt(i));
            }else{
                return null;
            }
        }
        Log.e("MESSAGE","-------------");
        Log.e("BOOLEAN",String.valueOf(cur.isWord));
        for(String s1:cur.children.keySet()){
            Log.e("CHILD",s1);
        }
        if(cur.isWord)
            return null;

        return s+(String)cur.children.keySet().toArray()[0];
//        ArrayList<String> rem_string=new ArrayList<>();
//        if(i==s.length()){
//            /*traverse any path from cur*/
//            while(cur.isWord!=true){
//                rem_string.add(cur.children.keySet().toArray()[0].toString());
//                cur=cur.children.get(rem_string.get(rem_string.size()-1));
//            }
//
//            StringBuilder sb = new StringBuilder();
//            for (String ss : rem_string)
//            {
//                sb.append(ss);
//            }
//            String result=s+sb.toString();
//            return result;
//        }
//        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
