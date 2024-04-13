package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.FileSerializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class Term extends AbstractTerm implements  Comparable<hust.cs.javacourse.search.index.AbstractTerm>, FileSerializable {
    /**
     * Term内容
     */


    /**
     * 缺省构造函数
     */
    public Term(){
        super();
    }

    /**
     * 因为要作为HashMap里面的key，因此必须要覆盖hashCode方法
     * 返回对象的HashCode
     * @return ：对象的HashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 构造函数
     * @param content ：Term内容
     */
    public Term(String content){
        super(content);
    }

    /**
     * 判断二个Term内容是否相同
     * @param obj ：要比较的另外一个Term
     * @return 如果内容相等返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Term){
            Term term = (Term) obj;
            if(Objects.equals(term.content,content))
                return true;
        }
        return false;
    }

    /**
     * 返回Term的字符串表示
     * @return 字符串
     */
    @Override
    public  String toString(){
        return "Term"+content;
    }

    /**
     * 返回Term内容
     * @return Term内容
     */
    public  String getContent(){
        return content;
    }

    /**
     * 设置Term内容
     * @param content：Term的内容
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * 比较二个Term大小（按字典序）
     * @param o： 要比较的Term对象
     * @return ： 返回二个Term对象的字典序差值
     */
    @Override
    public int compareTo(hust.cs.javacourse.search.index.AbstractTerm o) {
        return this.getContent().compareTo(o.getContent());
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(this.content);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            this.content = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

