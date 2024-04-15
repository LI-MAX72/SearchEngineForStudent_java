package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//没完成，需要完成doc
public class Index extends AbstractIndex {

    public Index(){
        super();
    }
    @Override
    public String toString() {
        return termToPostingListMapping.toString();
    }
    /**
     * 添加文档到索引，更新索引内部的HashMap
     * @param document ：文档的AbstractDocument子类型表示
     */
    @Override
    public void addDocument(AbstractDocument document) {
        docIdToDocPathMapping.put(document.getDocId(),document.getDocPath());
        Map<AbstractTerm, ArrayList<Integer>> map = new TreeMap<AbstractTerm,  ArrayList<Integer>>();
        for(AbstractTermTuple termTuple:document.getTuples()){
            AbstractTerm term  = termTuple.term;
            if(map.get(term)==null)
                map.put(term,new  ArrayList<Integer>());
            map.get(term).add(termTuple.curPos);
        }
        for(AbstractTerm term:map.keySet()){
            if(termToPostingListMapping.get(term)==null)
                termToPostingListMapping.put(term,new PostingList());
            termToPostingListMapping.get(term).add(new Posting(document.getDocId(),map.get(term).size(),map.get(term)));
        }
    }

    @Override
    public void load(File file) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readObject(in);
    }

    @Override
    public void save(File file) {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeObject(out);
    }

    @Override
    public AbstractPostingList search(AbstractTerm term) {
        return termToPostingListMapping.get(term);
    }

    @Override
    public Set<AbstractTerm> getDictionary() {
        return termToPostingListMapping.keySet();
    }

    @Override
    public void optimize() {
        for(AbstractPostingList postinglist:termToPostingListMapping.values()){
            postinglist.sort();
            for(int i = 0;i<postinglist.size();i++){
                postinglist.get(i).sort();
            }
        }
    }

    @Override
    public String getDocName(int docId) {
        return docIdToDocPathMapping.get(docId);
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(docIdToDocPathMapping);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.writeObject(termToPostingListMapping);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            docIdToDocPathMapping = (Map<Integer, String>)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            termToPostingListMapping = (Map<AbstractTerm, AbstractPostingList>)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
