package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StringSplitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

//大体完成
public class TermTupleScanner extends AbstractTermTupleScanner {
    private int curPos; //当前位置
    private LinkedList<String> stringBuff;
    StringSplitter split = new StringSplitter();
    /**
     * input作为输入流对象，读取文本文件得到一个个三元组TermTuple
     */
    protected BufferedReader input = null;

    {
        curPos = 0;
        stringBuff = new LinkedList<String>();
        split.setSplitRegex(Config.STRING_SPLITTER_REGEX);
    }
    /**
     * 缺省构造函数
     */
    public  TermTupleScanner(){

    }

    /**
     * 构造函数
     * @param input：指定输入流对象，应该关联到一个文本文件
     */
    public  TermTupleScanner(BufferedReader input){
        super(input);
    }

    /**
     * 实现父类AbstractTermTupleStream的close方法，关闭流
     */
    @Override
    public void close(){
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public AbstractTermTuple next(){
        try{
            if(stringBuff.isEmpty()){
                String str = this.input.readLine();//读取一行字符
                while(str != null && str.equals("")){//略过空行,比较字符串是否相同要用equals
                    str = input.readLine();
                }
                if(str == null){//读到输入流结束
                    return null;
                }
                stringBuff = new LinkedList<String>(split.splitByRegex(str));
                if(stringBuff.isEmpty()){
                    return null;
                }
            }
            String elem = stringBuff.getFirst();
            stringBuff.removeFirst();
            return new TermTuple(elem, ++this.curPos);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

}