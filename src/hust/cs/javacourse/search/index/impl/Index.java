package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
//没完成，需要完成doc
public class Index extends AbstractIndex {

    public Index(){
        super();
    }
    @Override
    public String toString() {
        return termToPostingListMapping.toString();
    }

    @Override
    public void addDocument(AbstractDocument document) {

    }

    @Override
    public void load(File file) {

    }

    @Override
    public void save(File file) {

    }

    @Override
    public AbstractPostingList search(AbstractTerm term) {
        return null;
    }

    @Override
    public Set<AbstractTerm> getDictionary() {
        return null;
    }

    @Override
    public void optimize() {

    }

    @Override
    public String getDocName(int docId) {
        return null;
    }

    @Override
    public void writeObject(ObjectOutputStream out) {

    }

    @Override
    public void readObject(ObjectInputStream in) {

    }
}
